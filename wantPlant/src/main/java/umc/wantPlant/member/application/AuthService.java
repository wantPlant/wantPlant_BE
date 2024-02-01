package umc.wantPlant.member.application;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.wantPlant.config.security.jwt.JwtTokenProvider;
import umc.wantPlant.config.security.jwt.TokenInfo;
import umc.wantPlant.member.client.KakaoMemberClient;
import umc.wantPlant.member.domain.Member;
import umc.wantPlant.member.domain.dto.response.MemberGenerateTokenResponseDTO;
import umc.wantPlant.member.domain.dto.response.MemberLoginResponseDTO;
import umc.wantPlant.member.repository.MemberRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final MemberRepository memberRepository;
    private final KakaoMemberClient kakaoMemberClient;
    private final JwtTokenProvider jwtTokenProvider;
    private final RefreshTokenService refreshTokenService;

    @Transactional
    public MemberLoginResponseDTO socialLogin(final String accessToken){
        Member memberInfo = kakaoMemberClient.getkakaoInfo(accessToken);
        Member getMember = memberRepository.findByEmail(memberInfo.getEmail())
                .map(entity -> entity.update(memberInfo.getNickname(), memberInfo.getProfileImage()))
                .orElse(memberInfo);

        return getNewToken(memberRepository.save(getMember));
    }

    @Transactional
    public MemberGenerateTokenResponseDTO generateNewAccessToken(String refreshToken){
        if(!jwtTokenProvider.validateRefreshToken(refreshToken)) {
            throw new IllegalArgumentException("Unexpected token");
        }
        Long memberId = Long.valueOf(jwtTokenProvider.getMemberIdByRefreshToken(refreshToken));

        return new MemberGenerateTokenResponseDTO(jwtTokenProvider.generateAccessToken(memberId));
    }
    private MemberLoginResponseDTO getNewToken(Member member) {
        TokenInfo tokenInfo = jwtTokenProvider.generateToken(member.getId());
        refreshTokenService.saveTokenInfo(tokenInfo.getRefreshToken(), member.getId());
        return MemberLoginResponseDTO.builder()
                .accessToken(tokenInfo.getAccessToken())
                .refreshToken(tokenInfo.getRefreshToken())
                .build();
    }
}
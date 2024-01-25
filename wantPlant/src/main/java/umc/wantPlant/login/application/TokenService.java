package umc.wantPlant.login.application;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import umc.wantPlant.config.security.jwt.TokenProvider;
import umc.wantPlant.login.domain.Member;

import java.time.Duration;

@RequiredArgsConstructor
@Service
public class TokenService {
    private final TokenProvider tokenProvider;
    private final RefreshTokenService refreshTokenService;
    private final MemberService memberService;

    public String createNewAccessToken(String refreshToken){
        if(!tokenProvider.validToken(refreshToken)){
            throw new IllegalArgumentException("Unexpected token");
        }

        Long memberId = refreshTokenService.findByRefreshToken(refreshToken).getMemberId();
        Member member = memberService.findById(memberId);

        return tokenProvider.generateToken(member, Duration.ofHours(2));
    }
}

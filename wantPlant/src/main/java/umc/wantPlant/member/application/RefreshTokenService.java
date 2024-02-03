package umc.wantPlant.member.application;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.wantPlant.member.domain.RefreshToken;
import umc.wantPlant.member.repository.RefreshTokenRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RefreshTokenService {

    private final RefreshTokenRepository refreshTokenRepository;

    @Transactional
    public RefreshToken saveTokenInfo(String refreshToken, Long memberId){
        RefreshToken token = RefreshToken.builder()
                .refreshToken(refreshToken)
                .memberId(memberId)
                .build();
        RefreshToken getToken = refreshTokenRepository.findByMemberId(memberId)
                .map(entity -> entity.update(token.getRefreshToken()))
                .orElse(token);
        return refreshTokenRepository.save(getToken);
    }

    @Transactional
    public Optional<RefreshToken> findByMemberId(Long memberId){
        return refreshTokenRepository.findByMemberId(memberId);
    }
    @Transactional
    public Optional<RefreshToken> findByRefreshToken(String refreshToken){
        return refreshTokenRepository.findByRefreshToken(refreshToken);
    }

    @Transactional
    public void delete(RefreshToken refreshToken){
        refreshTokenRepository.delete(refreshToken);
    }
}

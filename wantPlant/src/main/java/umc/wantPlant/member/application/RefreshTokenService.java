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
        return refreshTokenRepository.save(
                RefreshToken.builder()
                        .memberId(memberId)
                        .refreshToken(refreshToken)
                        .build()
        );
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

package umc.wantPlant.member.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import umc.wantPlant.member.domain.RefreshToken;

import java.util.Optional;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {
    Optional<RefreshToken> findByMemberId(Long memberId);
    Optional<RefreshToken> findByRefreshToken(String refreshToken);
}

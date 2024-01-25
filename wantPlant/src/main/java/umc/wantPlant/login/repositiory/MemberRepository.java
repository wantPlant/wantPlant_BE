package umc.wantPlant.login.repositiory;

import org.springframework.data.jpa.repository.JpaRepository;
import umc.wantPlant.login.domain.Member;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {
    Optional<Member> findByNickname(String nickname);
}

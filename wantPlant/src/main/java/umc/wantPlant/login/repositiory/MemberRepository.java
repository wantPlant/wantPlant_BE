package umc.wantPlant.login.repositiory;

import org.springframework.data.jpa.repository.JpaRepository;
import umc.wantPlant.login.domain.Member;

public interface MemberRepository extends JpaRepository<Member, Long> {

}

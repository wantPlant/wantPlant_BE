package umc.wantPlant.member.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import umc.wantPlant.member.domain.Member;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {
    Optional<Member> findByEmail(String email);
    Optional<Member> findById(Long id);
}

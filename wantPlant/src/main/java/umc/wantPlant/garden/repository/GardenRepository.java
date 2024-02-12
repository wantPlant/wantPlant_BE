package umc.wantPlant.garden.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import umc.wantPlant.garden.domain.Garden;
import umc.wantPlant.garden.domain.enums.GardenCategories;
import umc.wantPlant.member.domain.Member;
import umc.wantPlant.pot.domain.Pot;
import umc.wantPlant.tag.domain.Tag;

public interface GardenRepository extends JpaRepository<Garden, Long> {
	Page<Garden> findAllGardensByMember(Member member,PageRequest pageRequest);
	Page<Garden> findByMemberAndCategory(Member member,GardenCategories category, PageRequest pageRequest);
	List<Garden> findAllByMember(Member member);
	Long countByMember(Member member);
	void deleteGardenById(Long GardenId);
	Garden findByMemberAndId(Member member, Long gardenId);
}

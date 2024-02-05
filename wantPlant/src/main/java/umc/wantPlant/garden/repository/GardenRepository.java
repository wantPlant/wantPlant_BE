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
import umc.wantPlant.pot.domain.Pot;
import umc.wantPlant.tag.domain.Tag;

public interface GardenRepository extends JpaRepository<Garden, Long> {
	Page<Garden> findAllGardensBy(PageRequest pageRequest);

	Page<Garden> findByCategory(GardenCategories category, PageRequest pageRequest);
	@Query("SELECT p FROM Pot p WHERE (p.garden) = :garden")
	List<Pot> findPotByGardenId(@Param("garden") Garden garden);

	@Modifying
	@Query("DELETE FROM Garden g WHERE g = :garden")
	void deleteGardenAndPots(@Param("garden") Garden garden);

	List<Garden> findByUserIdAndId(Long userId, Long gardenId);





}

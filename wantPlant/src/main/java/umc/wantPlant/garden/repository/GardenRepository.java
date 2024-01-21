package umc.wantPlant.garden.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import umc.wantPlant.garden.domain.Garden;
import umc.wantPlant.garden.domain.enums.GardenCategories;

public interface GardenRepository extends JpaRepository<Garden, Long> {
	Page<Garden> findAllGardensBy(PageRequest pageRequest);

	Page<Garden> findByCategory(GardenCategories category, PageRequest pageRequest);
}

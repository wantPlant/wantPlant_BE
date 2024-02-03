package umc.wantPlant.garden.application;

import java.util.Optional;

import org.springframework.data.domain.Page;

import umc.wantPlant.garden.domain.Garden;
import umc.wantPlant.garden.domain.enums.GardenCategories;

public interface GardenQueryService {
	Garden getGardenById(Long gardenId);

	Page<Garden> getGardens(Integer page, Integer pageSize);

	Page<Garden> getGardensByCategory(String category, Integer page, Integer pageSize);

	Long getGardenSize();

	GardenCategories getGardenCategory(String category);

	boolean existGardenById(Long gardenId);
}


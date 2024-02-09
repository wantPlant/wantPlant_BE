package umc.wantPlant.garden.application;

import java.util.Optional;

import org.springframework.data.domain.Page;

import umc.wantPlant.garden.domain.Garden;
import umc.wantPlant.garden.domain.dto.GardenRequestDTO;
import umc.wantPlant.garden.domain.enums.GardenCategories;

public interface GardenQueryService {
	Garden getGardenById(Long gardenId);

	Page<Garden> getGardens(GardenRequestDTO.GardenPage getPage);

	Page<Garden> getGardensByCategory(GardenRequestDTO.GardenPage getPage);

	Long getGardenSize(Long memberId);

//	GardenCategories getGardenCategory(GardenCategories category);

	boolean existGardenById(Long gardenId);
}


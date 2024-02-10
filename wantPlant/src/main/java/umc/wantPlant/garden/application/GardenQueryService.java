package umc.wantPlant.garden.application;

import java.util.Optional;

import org.springframework.data.domain.Page;

import umc.wantPlant.garden.domain.Garden;
import umc.wantPlant.garden.domain.dto.GardenRequestDTO;
import umc.wantPlant.garden.domain.dto.GardenResponseDTO;
import umc.wantPlant.garden.domain.enums.GardenCategories;
import umc.wantPlant.member.domain.Member;

public interface GardenQueryService {
	Garden getGardenById(Long gardenId);
	Page<Garden> getGardensToPage(int page,Member member);

	Page<Garden> getGardensByCategoryToPage(int page,Member member, GardenCategories gardenCategories);

	Long getGardenSize(Member member);

	boolean existGardenById(Long gardenId);

}


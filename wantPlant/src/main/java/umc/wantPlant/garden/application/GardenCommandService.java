package umc.wantPlant.garden.application;

import org.springframework.data.domain.Page;

import umc.wantPlant.garden.domain.Garden;
import umc.wantPlant.garden.domain.dto.GardenRequestDTO;
import umc.wantPlant.garden.domain.dto.GardenResponseDTO;
import umc.wantPlant.member.domain.Member;

public interface GardenCommandService {
	GardenResponseDTO.GardenCreatResultDTO toCreatGarden(Member member,GardenRequestDTO.GardenCreatRequestDTO toCreatGarden);
	GardenResponseDTO.GardenGetResultPageDTO toGetGardenByPage(Page<Garden> GardenPageRequestDTO);
	GardenResponseDTO.GardenGetResultListDTO toGetGardenByList(Member member);
	GardenResponseDTO.GardenUpdateResultDTO toUpdateGarden(Member member ,GardenRequestDTO.GardenUpdateRequestDTO update);
	GardenResponseDTO.GardenGetResultDTO toGardenResultDTO(Garden garden);
	void toDeleteGarden(Long memberId, Long gardenId);
}

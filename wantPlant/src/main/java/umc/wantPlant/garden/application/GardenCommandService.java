package umc.wantPlant.garden.application;

import org.springframework.data.domain.Page;

import umc.wantPlant.garden.domain.Garden;
import umc.wantPlant.garden.domain.dto.GardenRequestDTO;
import umc.wantPlant.garden.domain.dto.GardenResponseDTO;

public interface GardenCommandService {
	GardenResponseDTO.GardenCreatResultDTO creat(GardenRequestDTO.GardenCreatDTO creat);

	GardenResponseDTO.GardenUpdateResultDTO update(GardenRequestDTO.UpdateGardenDTO update);

	GardenResponseDTO.GardenUpdateResultDTO updateName(GardenRequestDTO.UpdateGardenDTO update);

	GardenResponseDTO.GardenUpdateResultDTO updateDescription(GardenRequestDTO.UpdateGardenDTO update);

	GardenResponseDTO.GardenResultList getGardenList(Long memberId);
	GardenResponseDTO.GardenListDTO getGardenList(Page<Garden> gardenPage);

	GardenResponseDTO.GardenResultDTO toGardenResultDTO(Garden garden);

	void delete(Long memberId, Long gardenId);
}

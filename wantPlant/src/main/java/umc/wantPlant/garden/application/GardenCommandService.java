package umc.wantPlant.garden.application;

import org.springframework.data.domain.Page;

import umc.wantPlant.garden.domain.Garden;
import umc.wantPlant.garden.domain.dto.GardenRequestDTO;
import umc.wantPlant.garden.domain.dto.GardenResponseDTO;

public interface GardenCommandService {
	GardenResponseDTO.GardenCreatResultDTO creat(GardenRequestDTO.GardenCreatDTO creat);

	GardenResponseDTO.GardenUpdateResultDTO update(Long gardenId, GardenRequestDTO.UpdateGardenDTO update);

	GardenResponseDTO.GardenUpdateResultDTO updateName(Long gardenId, String name);

	GardenResponseDTO.GardenUpdateResultDTO updateDescription(Long gardenId, String description);

	GardenResponseDTO.GardenListDTO getGardenList(Page<Garden> gardenPage);

	GardenResponseDTO.GardenResultDTO toGardenResultDTO(Garden garden);

	void delete(Long gardenId);
}

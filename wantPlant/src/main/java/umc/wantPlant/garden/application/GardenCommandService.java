package umc.wantPlant.garden.application;

import org.springframework.data.domain.Page;

import umc.wantPlant.garden.domain.Garden;
import umc.wantPlant.garden.domain.dto.GardenRequestDTO;
import umc.wantPlant.garden.domain.dto.GardenResponseDTO;

public interface GardenCommandService {
	GardenResponseDTO.GardenResultDTO creat(GardenRequestDTO.GardenCreatDTO creat);

	GardenResponseDTO.GardenResultDTO update(Garden garden, GardenRequestDTO.UpdateGardenDTO update);

	GardenResponseDTO.GardenResultDTO updateName(Garden garden, String name);

	GardenResponseDTO.GardenResultDTO updateDescription(Garden garden, String description);

	GardenResponseDTO.GardenListDTO getGardenList(Page<Garden> gardenPage);

	GardenResponseDTO.GardenDTO toGardenDTO(Garden garden);

	void delete(Long gardenId);
}

package umc.wantPlant.garden.application;

import umc.wantPlant.garden.domain.Garden;
import umc.wantPlant.garden.domain.dto.GardenRequestDTO;

public interface GardenCommandService {
	Garden creatGarden(GardenRequestDTO.CreatDTO creat);
}

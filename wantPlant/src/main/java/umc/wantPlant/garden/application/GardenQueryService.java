package umc.wantPlant.garden.application;

import java.util.Optional;

import umc.wantPlant.garden.domain.Garden;

public interface GardenQueryService {
	Optional<Garden> findById(Long gardenId);
}


package umc.wantPlant.garden.application;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import umc.wantPlant.garden.domain.Garden;
import umc.wantPlant.garden.domain.dto.GardenRequestDTO;
import umc.wantPlant.garden.domain.enums.GardenCategories;
import umc.wantPlant.garden.repository.GardenRepository;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class GardenCommandServiceImpl implements GardenCommandService {

	private final GardenRepository gardenRepository;

	@Override
	@Transactional
	public Garden creatGarden(GardenRequestDTO.GardenCreatDTO creat) {

		GardenCategories gardenCategories = null;

		switch (creat.getGardenCategory()) {
			case 1:
				gardenCategories = GardenCategories.EXERCISE;
				break;
			case 2:
				gardenCategories = GardenCategories.STUDY;
				break;
			case 3:
				gardenCategories = GardenCategories.HOBBY;
				break;
		}

		return gardenRepository.save(
			Garden.builder()
				.name(creat.getName())
				.description(creat.getDescription())
				.category(gardenCategories)
				.build());
	}
}

package umc.wantPlant.garden.application;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import umc.wantPlant.garden.domain.Garden;
import umc.wantPlant.garden.domain.enums.GardenCategories;
import umc.wantPlant.garden.repository.GardenRepository;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class GardenQueryServiceImpl implements GardenQueryService {

	private final GardenRepository gardenRepository;

	@Override
	public Optional<Garden> getGardenById(Long gardenId) {
		return gardenRepository.findById(gardenId);
	}

	@Override
	public Page<Garden> getGardens(Integer page, Integer pageSize) {
		return gardenRepository.findAllGardensBy(PageRequest.of(page, pageSize));
	}

	@Override
	public Page<Garden> getGardensByCategory(String category, Integer page, Integer pageSize) {
		GardenCategories gardenCategory = null;

		switch (category) {
			case "EXERCISE":
				gardenCategory = GardenCategories.EXERCISE;
				break;
			case "STUDY":
				gardenCategory = GardenCategories.STUDY;
				break;
			case "HOBBY":
				gardenCategory = GardenCategories.HOBBY;
				break;
		}
		return gardenRepository.findByCategory(gardenCategory, PageRequest.of(page, pageSize));
	}

	@Override
	public boolean existGardenById(Long gardenId) {
		return gardenRepository.existsById(gardenId);
	}
}

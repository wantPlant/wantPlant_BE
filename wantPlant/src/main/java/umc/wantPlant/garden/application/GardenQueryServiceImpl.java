package umc.wantPlant.garden.application;


import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import umc.wantPlant.apipayload.code.status.ErrorStatus;
import umc.wantPlant.apipayload.exceptions.handler.GardenCategoryHandler;
import umc.wantPlant.apipayload.exceptions.handler.GardenHandler;
import umc.wantPlant.garden.domain.Garden;
import umc.wantPlant.garden.domain.enums.GardenCategories;
import umc.wantPlant.garden.repository.GardenRepository;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class GardenQueryServiceImpl implements GardenQueryService {

	private final GardenRepository gardenRepository;

	@Override
	public Garden getGardenById(Long gardenId) {
		return gardenRepository.findById(gardenId).orElseThrow(() -> new GardenHandler(ErrorStatus.GARDEN_NOT_FOUND));
	}

	@Override
	public Page<Garden> getGardens(Integer page, Integer pageSize) {
		return gardenRepository.findAllGardensBy(PageRequest.of(page, pageSize));
	}

	@Override
	public Page<Garden> getGardensByCategory(String category, Integer page, Integer pageSize) {
		GardenCategories gardenCategory = getGardenCategory(category);

		return gardenRepository.findByCategory(gardenCategory, PageRequest.of(page, pageSize));
	}

	@Override
	public Long getGardenSize() {
		Long count = gardenRepository.count();
		if (count == 0)
			throw new GardenHandler(ErrorStatus.GARDEN_NOT_EXIST);
		return count;
	}

	@Override
	public GardenCategories getGardenCategory(String category) {
		try {
			return GardenCategories.valueOf(category.toUpperCase());
		} catch (IllegalArgumentException e) {
			throw new GardenCategoryHandler(ErrorStatus.GARDEN_CATEGORY_NOT_FOUND);
		}
	}

	public boolean existGardenById(Long gardenId) {
		return gardenRepository.existsById(gardenId);
	}
}

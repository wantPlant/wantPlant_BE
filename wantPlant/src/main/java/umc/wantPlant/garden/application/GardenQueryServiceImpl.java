package umc.wantPlant.garden.application;

import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import umc.wantPlant.garden.domain.Garden;
import umc.wantPlant.garden.repository.GardenRepository;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class GardenQueryServiceImpl implements GardenQueryService {

	private final GardenRepository gardenRepository;

	@Override
	@Transactional
	public Optional<Garden> findById(Long gardenId) {
		return gardenRepository.findById(gardenId);
	}
}

package umc.wantPlant.garden.application;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import umc.wantPlant.garden.domain.Garden;
import umc.wantPlant.garden.domain.dto.GardenRequestDTO;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class GardenCommandServiceImpl implements GardenCommandService {
	@Override
	@Transactional
	public Garden creatGarden(GardenRequestDTO.CreatDTO creat) {
		return null;
	}
}

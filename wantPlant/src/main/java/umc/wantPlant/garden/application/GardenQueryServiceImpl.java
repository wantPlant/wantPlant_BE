package umc.wantPlant.garden.application;

import java.util.List;
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
import umc.wantPlant.garden.domain.dto.GardenRequestDTO;
import umc.wantPlant.garden.domain.dto.GardenResponseDTO;
import umc.wantPlant.garden.domain.enums.GardenCategories;
import umc.wantPlant.garden.repository.GardenRepository;
import umc.wantPlant.member.domain.Member;
import umc.wantPlant.pot.domain.Pot;
import umc.wantPlant.pot.domain.dto.PotResponseDTO;
import umc.wantPlant.pot.repository.PotRepository;

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
	public Page<Garden> getGardensToPage(int page, Member member) {

		page = page == 0 ? 0 : page - 1;

		return gardenRepository.findAllGardensByMember(member, PageRequest.of(page, 3));
	}

	@Override
	public Page<Garden> getGardensByCategoryToPage(int page, Member member, GardenCategories gardenCategories) {

		page = page == 0 ? 0 : page - 1;

		return gardenRepository.findByMemberAndCategory(member, gardenCategories,
			PageRequest.of(page, 3));
	}

	@Override
	public Long getGardenSize(Member member) {
		Long count = gardenRepository.countByMember(member);

		if (count == 0)
			throw new GardenHandler(ErrorStatus.GARDEN_NOT_EXIST);

		return count;
	}

	public boolean existGardenById(Long gardenId) {
		return gardenRepository.existsById(gardenId);
	}
}

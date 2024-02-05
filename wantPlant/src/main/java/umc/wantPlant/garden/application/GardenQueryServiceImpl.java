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
import umc.wantPlant.garden.domain.dto.GardenRequestDTO;
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
	public Page<Garden> getGardens(GardenRequestDTO.GardenPage getPage) {
		// 내 모든 정원 조회하기  : 페이지

		int page= getPage.getPage()<0?0:getPage.getPageSize()-1;

		return gardenRepository.findAllGardensByMemberId(getPage.getMemberID(),PageRequest.of(page, getPage.getPageSize()));
	}

	@Override
	public Page<Garden> getGardensByCategory(GardenRequestDTO.GardenPage getPage) {

		//정원 카테고리 생성 및 유효성 검증
		GardenCategories gardenCategory = getGardenCategory(getPage.getCategory());

		//페이지 유효한 넘버로 변경
		int page= getPage.getPage()==0?0:getPage.getPageSize()-1;

		return gardenRepository.findByMemberIdAndCategory(getPage.getMemberID(),gardenCategory, PageRequest.of(page,
			getPage.getPageSize()));
	}

	@Override
	public Long getGardenSize(Long memberId) {
		Long count = gardenRepository.countByMemberId(memberId);
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

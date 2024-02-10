package umc.wantPlant.garden.application;

import static java.util.stream.Collectors.*;
import static umc.wantPlant.garden.converter.GardenConverter.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import umc.wantPlant.apipayload.code.status.ErrorStatus;
import umc.wantPlant.apipayload.exceptions.handler.GardenHandler;
import umc.wantPlant.apipayload.exceptions.handler.MemberHandler;
import umc.wantPlant.apipayload.exceptions.handler.PotHandler;
import umc.wantPlant.garden.converter.GardenConverter;
import umc.wantPlant.garden.domain.Garden;
import umc.wantPlant.garden.domain.dto.GardenRequestDTO;
import umc.wantPlant.garden.domain.dto.GardenResponseDTO;
import umc.wantPlant.garden.domain.enums.GardenCategories;
import umc.wantPlant.garden.repository.GardenRepository;
import umc.wantPlant.goal.domain.Goal;
import umc.wantPlant.member.domain.Member;
import umc.wantPlant.member.repository.MemberRepository;
import umc.wantPlant.pot.application.PotCommandService;
import umc.wantPlant.pot.application.PotCommandServiceImpl;
import umc.wantPlant.pot.domain.Pot;
import umc.wantPlant.pot.domain.dto.PotResponseDTO;
import umc.wantPlant.pot.repository.PotRepository;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class GardenCommandServiceImpl implements GardenCommandService {

	private final GardenRepository gardenRepository;
	private final EntityManager em;    //엔티티매니저 주입
	private final GardenQueryService queryService;
	private final PotRepository potRepository;
	private final PotCommandService potCommandService;
	private final MemberRepository memberRepository;

	@Override
	@Transactional
	public GardenResponseDTO.GardenCreatResultDTO toCreatGarden(Member member ,GardenRequestDTO.GardenCreatRequestDTO creat) {

		return GardenCreatResultDTOof(gardenRepository.save(Gardenof(creat,member)));
	}



	@Override
	public GardenResponseDTO.GardenGetResultDTO toGardenResultDTO(Garden garden) {

		List<Pot> pots = potRepository.findAllByGarden(garden).orElseThrow();
		// TODO POT DTO 컨버터 생기면 이용해서 코드 줄이기.
		List<PotResponseDTO.PotDTO> potDTOS = pots.stream().map(pot->
				PotResponseDTO.PotDTO.builder()
						.potId(pot.getPotId())
						.potName(pot.getPotName())
						.potTagColor(pot.getPotTagColor())
						.proceed(pot.getProceed()%30)
						.potImageUrl(pot.getPotImageUrl())
						.startAt(pot.getStartAt())
						.build()).toList();

		return GardenConverter.GardenGetResultDTOof(potDTOS, garden);
	}

	@Override
	public GardenResponseDTO.GardenGetResultListDTO toGetGardenByList(Member member) {

		List<Garden> gardens = gardenRepository.findAllByMember(member);

		List<GardenResponseDTO.GardenGetResultDTO> gardenGetResultDTOS = gardens.stream().map(this::toGardenResultDTO).toList();

		return GardenGetResultListDTOof(gardenGetResultDTOS,gardenGetResultDTOS.size());
	}

	@Override
	public GardenResponseDTO.GardenGetResultPageDTO toGetGardenByPage(Page<Garden> gardenPage) {

		List<GardenResponseDTO.GardenGetResultDTO> gardenGetResultDTOS = gardenPage.stream()
			.map(this::toGardenResultDTO)
			.collect(toList());

		return GardenGetResultPageDTOof(gardenGetResultDTOS,gardenPage);
	}

	@Override
	@Transactional
	public GardenResponseDTO.GardenUpdateResultDTO toUpdateGarden(Member member ,GardenRequestDTO.GardenUpdateRequestDTO gardenUpdateRequestDTO) {

		Garden garden = em.find(Garden.class, gardenUpdateRequestDTO.getGardenId());
		garden.setName(gardenUpdateRequestDTO.getName());
		garden.setDescription(gardenUpdateRequestDTO.getDescription());

		return GardenUpdateResultDTOof(garden);
	}


	@Override
	@Transactional
	public void toDeleteGarden(Long memberId,Long gardenId) {

		Garden deleteGarden = queryService.getGardenById(gardenId);

		gardenRepository.deleteGardenById(deleteGarden.getId());
	}
}

/*
	@Override
	@Transactional
	public GardenResponseDTO.GardenUpdateResultDTO updateName(GardenRequestDTO.GardenUpdateRequestDTO update) {
		Garden testGarden = queryService.getGardenById(update.getId());

		Member member = memberRepository.findById(update.getMemberId())
			.orElseThrow(() -> new MemberHandler(ErrorStatus.MEMBER_NOT_FOUND));

		Garden garden = em.find(Garden.class, update.getId());
		garden.setName(update.getName());

		return GardenResponseDTO.GardenUpdateResultDTO.builder()
			.gardenId(garden.getId())
			.name(garden.getName())
			.gardenCategory(garden.getCategory().toString())
			.description(garden.getDescription())
			.build();
	}
*/

	/*	@Override
		@Transactional
		public GardenResponseDTO.GardenUpdateResultDTO updateDescription(GardenRequestDTO.GardenUpdateRequestDTO update) {
			Garden testGarden = queryService.getGardenById(update.getId());

			Member member = memberRepository.findById(update.getMemberId())
				.orElseThrow(() -> new MemberHandler(ErrorStatus.MEMBER_NOT_FOUND));
			Garden garden = em.find(Garden.class, update.getId());
			garden.setDescription(update.getDescription());

			return GardenResponseDTO.GardenUpdateResultDTO.builder()
				.gardenId(garden.getId())
				.name(garden.getName())
				.gardenCategory(garden.getCategory().toString())
				.description(garden.getDescription())
				.build();
		}*/

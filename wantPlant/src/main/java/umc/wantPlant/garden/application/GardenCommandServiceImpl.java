package umc.wantPlant.garden.application;

import static java.util.stream.Collectors.*;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import umc.wantPlant.apipayload.code.status.ErrorStatus;
import umc.wantPlant.apipayload.exceptions.handler.GardenHandler;
import umc.wantPlant.garden.domain.Garden;
import umc.wantPlant.garden.domain.dto.GardenRequestDTO;
import umc.wantPlant.garden.domain.dto.GardenResponseDTO;
import umc.wantPlant.garden.domain.enums.GardenCategories;
import umc.wantPlant.garden.repository.GardenRepository;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class GardenCommandServiceImpl implements GardenCommandService {

	private final GardenRepository gardenRepository;
	private final EntityManager em;    //엔티티매니저 주입
	private final GardenQueryService queryService;

	@Override
	@Transactional
	public GardenResponseDTO.GardenCreatResultDTO creat(GardenRequestDTO.GardenCreatDTO creat) {

		GardenCategories gardenCategories = queryService.getGardenCategory(creat.getCategory());

		Garden newGarden = gardenRepository.save(
			Garden.builder()
				.name(creat.getName())
				.description(creat.getDescription())
				.category(gardenCategories)
				.build());

		return GardenResponseDTO.GardenCreatResultDTO
			.builder()
			.gardenId(newGarden.getId())
			.name(newGarden.getName())
			.description(newGarden.getDescription())
			.gardenCategory(creat.getCategory())
			.build();
	}

	@Override
	@Transactional
	public GardenResponseDTO.GardenUpdateResultDTO update(GardenRequestDTO.UpdateGardenDTO update) {

		Garden testGarden = queryService.getGardenById(update.getId());

		Garden garden = em.find(Garden.class, update.getId());
		garden.setName(update.getName());
		garden.setDescription(update.getDescription());

		return GardenResponseDTO.GardenUpdateResultDTO.builder()
			.gardenId(garden.getId())
			.name(garden.getName())
			.gardenCategory(garden.getCategory().toString())
			.description(garden.getDescription())
			.build();
	}

	@Override
	@Transactional
	public GardenResponseDTO.GardenUpdateResultDTO updateName(GardenRequestDTO.UpdateGardenDTO update) {
		Garden testGarden = queryService.getGardenById(update.getId());

		Garden garden = em.find(Garden.class, update.getId());
		garden.setName(update.getName());

		return GardenResponseDTO.GardenUpdateResultDTO.builder()
			.gardenId(garden.getId())
			.name(garden.getName())
			.gardenCategory(garden.getCategory().toString())
			.description(garden.getDescription())
			.build();
	}

	@Override
	@Transactional
	public GardenResponseDTO.GardenUpdateResultDTO updateDescription(GardenRequestDTO.UpdateGardenDTO update) {
		Garden testGarden = queryService.getGardenById(update.getId());

		Garden garden = em.find(Garden.class, update.getId());
		garden.setDescription(update.getDescription());

		return GardenResponseDTO.GardenUpdateResultDTO.builder()
			.gardenId(garden.getId())
			.name(garden.getName())
			.gardenCategory(garden.getCategory().toString())
			.description(garden.getDescription())
			.build();
	}

	@Override
	@Transactional
	public void delete(Long gardenId) {

		Garden deleteGarden = queryService.getGardenById(gardenId);
		gardenRepository.deletePotsByGarden(deleteGarden);
		gardenRepository.deleteGardenAndPots(deleteGarden);
	}

	@Override
	public GardenResponseDTO.GardenResultDTO toGardenResultDTO(Garden garden) {
		String category = garden.getCategory().toString();

		return GardenResponseDTO.GardenResultDTO
			.builder()
			.gardenId(garden.getId())
			.name(garden.getName())
			.description(garden.getDescription())
			.gardenCategory(category)
			.potList(gardenRepository.findPotByGardenId(garden))
			.build();
	}

	@Override
	public GardenResponseDTO.GardenResultList getGardenList() {
		List<Garden> gardens = gardenRepository.findAll();
		Long count = queryService.getGardenSize();


		List<GardenResponseDTO.GardenResultDTO> result = gardens.stream()
			.map(this::toGardenResultDTO)
			.toList();

		return GardenResponseDTO.GardenResultList.builder()
			.gardens(result)
			.totalElements(count)
			.build();
	}

	@Override
	public GardenResponseDTO.GardenListDTO getGardenList(Page<Garden> gardenPage) {

		List<GardenResponseDTO.GardenResultDTO> gardenDTOList = gardenPage.stream()
			.map(this::toGardenResultDTO)
			.collect(toList());

		return GardenResponseDTO.GardenListDTO.builder()
			.isLast(gardenPage.isLast())
			.isFirst(gardenPage.isFirst())
			.totalPage(gardenPage.getTotalPages())
			.totalElements(gardenPage.getTotalElements())
			.listSize(gardenDTOList.size())
			.gardenList(gardenDTOList)
			.build();

	}

}

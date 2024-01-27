package umc.wantPlant.garden.application;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
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

	@Override
	@Transactional
	public GardenResponseDTO.GardenCreatResultDTO creat(GardenRequestDTO.GardenCreatDTO creat) {

		GardenCategories gardenCategories = null;

		switch (creat.getCategory()) {
			case "EXERCISE":
				gardenCategories = GardenCategories.EXERCISE;
				break;
			case "STUDY":
				gardenCategories = GardenCategories.STUDY;
				break;
			case "HOBBY":
				gardenCategories = GardenCategories.HOBBY;
				break;
		}

		Garden newGarden = gardenRepository.save(
			Garden.builder()
				.name(creat.getName())
				.description(creat.getDescription())
				.category(gardenCategories)
				.build());

		return GardenResponseDTO.GardenCreatResultDTO
			.builder()
			.gardenId(newGarden.getId())
			.gardenCategory(creat.getCategory())
			.build();
	}

	@Override
	@Transactional
	public GardenResponseDTO.GardenUpdateResultDTO update(Long gardenId, GardenRequestDTO.UpdateGardenDTO update) {
		Garden garden = em.find(Garden.class, gardenId);
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
	public GardenResponseDTO.GardenUpdateResultDTO updateName(Long gardenId, String name) {
		Garden garden = em.find(Garden.class, gardenId);
		garden.setName(name);

		return GardenResponseDTO.GardenUpdateResultDTO.builder()
			.gardenId(garden.getId())
			.name(garden.getName())
			.gardenCategory(garden.getCategory().toString())
			.description(garden.getDescription())
			.build();
	}

	@Override
	@Transactional
	public GardenResponseDTO.GardenUpdateResultDTO updateDescription(Long gardenId, String description) {
		Garden garden = em.find(Garden.class, gardenId);
		garden.setDescription(description);

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
		gardenRepository.deleteById(gardenId);
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
	public GardenResponseDTO.GardenListDTO getGardenList(Page<Garden> gardenPage) {

		List<GardenResponseDTO.GardenResultDTO> gardenDTOList = gardenPage.stream()
			.map(this::toGardenResultDTO)
			.collect(Collectors.toList());

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

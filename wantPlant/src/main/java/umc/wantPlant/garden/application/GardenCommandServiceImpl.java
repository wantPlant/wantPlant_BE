package umc.wantPlant.garden.application;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

	@Override
	@Transactional
	public GardenResponseDTO.GardenResultDTO creat(GardenRequestDTO.GardenCreatDTO creat) {

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

		return GardenResponseDTO.GardenResultDTO
			.builder()
			.gardenId(newGarden.getId())
			.gardenCategory(creat.getCategory())
			.build();
	}

	@Override
	@Transactional
	public GardenResponseDTO.GardenResultDTO update(Garden garden, GardenRequestDTO.UpdateGardenDTO update) {
		gardenRepository.save(
			garden.builder()
				.name(update.getName())
				.description(update.getDescription())
				.build()
		);
		return GardenResponseDTO.GardenResultDTO
			.builder()
			.gardenId(garden.getId())
			.build();
	}

	@Override
	@Transactional
	public GardenResponseDTO.GardenResultDTO updateName(Garden garden, String name) {
		gardenRepository.save(
			garden.builder()
				.name(name)
				.build()
		);
		return GardenResponseDTO.GardenResultDTO
			.builder()
			.gardenId(garden.getId())
			.build();
	}

	@Override
	@Transactional
	public GardenResponseDTO.GardenResultDTO updateDescription(Garden garden, String description) {
		gardenRepository.save(
			garden.builder()
				.name(description)
				.build()
		);
		return GardenResponseDTO.GardenResultDTO
			.builder()
			.gardenId(garden.getId())
			.build();
	}

	@Override
	@Transactional
	public void delete(Long gardenId) {
		gardenRepository.deleteById(gardenId);
	}

	@Override
	public GardenResponseDTO.GardenDTO toGardenDTO(Garden garden) {
		String category = garden.getCategory().toString();
		return GardenResponseDTO.GardenDTO
			.builder()
			.gardenId(garden.getId())
			.name(garden.getName())
			.description(garden.getDescription())
			.gardenCategory(category)
			.build();
	}

	@Override
	public GardenResponseDTO.GardenListDTO getGardenList(Page<Garden> gardenPage) {

		List<GardenResponseDTO.GardenDTO> gardenDTOList = gardenPage.stream()
			.map(this::toGardenDTO)
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

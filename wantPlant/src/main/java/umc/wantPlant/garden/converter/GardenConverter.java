package umc.wantPlant.garden.converter;

import java.util.List;

import org.springframework.data.domain.Page;

import umc.wantPlant.garden.domain.Garden;
import umc.wantPlant.garden.domain.dto.GardenRequestDTO;
import umc.wantPlant.garden.domain.dto.GardenResponseDTO;
import umc.wantPlant.member.domain.Member;
import umc.wantPlant.pot.domain.dto.PotResponseDTO;

public class GardenConverter {
	public static GardenResponseDTO.GardenCreatResultDTO GardenCreatResultDTOof(Garden garden) {
		return GardenResponseDTO.GardenCreatResultDTO.builder()
			.gardenId(garden.getId())
			.name(garden.getName())
			.description(garden.getDescription())
			.gardenCategory(garden.getCategory())
			.build();
	}

	public static GardenResponseDTO.GardenUpdateResultDTO GardenUpdateResultDTOof(Garden garden) {
		return GardenResponseDTO.GardenUpdateResultDTO.builder()
			.gardenId(garden.getId())
			.name(garden.getName())
			.gardenCategory(garden.getCategory().toString())
			.description(garden.getDescription())
			.build();
	}

	public static GardenResponseDTO.GardenGetResultDTO GardenGetResultDTOof(List<PotResponseDTO.PotDTO> potDTOS,
		Garden garden) {
		return GardenResponseDTO.GardenGetResultDTO.builder()
			.gardenId(garden.getId())
			.name(garden.getName())
			.description(garden.getDescription())
			.gardenCategory(garden.getCategory())
			.potList(potDTOS)
			.build();
	}

	public static GardenResponseDTO.GardenGetResultListDTO GardenGetResultListDTOof(
		List<GardenResponseDTO.GardenGetResultDTO> gardenGetResultDTOS, int count) {
		return GardenResponseDTO.GardenGetResultListDTO.builder()
			.gardens(gardenGetResultDTOS)
			.totalElements(count)
			.build();
	}

	public static GardenResponseDTO.GardenGetResultPageDTO GardenGetResultPageDTOof(
		List<GardenResponseDTO.GardenGetResultDTO> gardenGetResultDTOS, Page<Garden> gardenPage) {
		return GardenResponseDTO.GardenGetResultPageDTO.builder()
			.isLast(gardenPage.isLast())
			.isFirst(gardenPage.isFirst())
			.totalPage(gardenPage.getTotalPages())
			.totalElements(gardenPage.getTotalElements())
			.listSize(gardenGetResultDTOS.size())
			.gardenList(gardenGetResultDTOS)
			.build();
	}
	public static Garden Gardenof(GardenRequestDTO.GardenCreatRequestDTO creat, Member member) {
		return Garden.builder()
			.name(creat.getName())
			.description(creat.getDescription())
			.category(creat.getCategory())
			.member(member)
			.build();
	}

}


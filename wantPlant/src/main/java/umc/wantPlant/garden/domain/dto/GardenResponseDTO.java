package umc.wantPlant.garden.domain.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import umc.wantPlant.garden.domain.Garden;
import umc.wantPlant.garden.domain.enums.GardenCategories;
import umc.wantPlant.pot.domain.Pot;
import umc.wantPlant.pot.domain.dto.PotResponseDTO;

public class GardenResponseDTO {

	@Builder
	@Getter
	@NoArgsConstructor
	@AllArgsConstructor
	public static class GardenUpdateResultDTO{
		Long gardenId;
		String name;
		String description;
		String gardenCategory;
	}
	@Builder
	@Getter
	@NoArgsConstructor
	@AllArgsConstructor
	public static class GardenCreatResultDTO {
		Long gardenId;
		String name;
		String description;
		GardenCategories gardenCategory;
	}

	@Builder
	@Getter
	@NoArgsConstructor
	@AllArgsConstructor
	public static class GardenGetResultDTO {
		Long gardenId;
		String name;
		String description;
		GardenCategories gardenCategory;
		List<PotResponseDTO.PotDTO> potList;
	}

	@Builder
	@Getter
	@NoArgsConstructor
	@AllArgsConstructor
	public static class GardenGetResultListDTO {
		int totalElements;
		List<GardenGetResultDTO> gardens;
	}
	@Builder
	@Getter
	@NoArgsConstructor
	@AllArgsConstructor
	public static class GardenSizeResultDTO {
		Long totalElements;
	}


	@Builder
	@NoArgsConstructor
	@AllArgsConstructor
	@Getter
	public static class GardenGetResultPageDTO {
		List<GardenGetResultDTO> gardenList;

		Integer listSize;

		Integer totalPage;

		Long totalElements;

		Boolean isFirst;

		Boolean isLast;
	}

}

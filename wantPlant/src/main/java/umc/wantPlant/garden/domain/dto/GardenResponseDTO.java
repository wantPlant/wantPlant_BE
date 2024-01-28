package umc.wantPlant.garden.domain.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import umc.wantPlant.garden.domain.enums.GardenCategories;
import umc.wantPlant.pot.domain.Pot;

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
		String gardenCategory;
	}

	@Builder
	@Getter
	@NoArgsConstructor
	@AllArgsConstructor
	public static class GardenResultDTO {
		Long gardenId;
		String name;
		String description;
		String gardenCategory;
		List<Pot> potList;
	}

	@Builder
	@NoArgsConstructor
	@AllArgsConstructor
	@Getter
	public static class GardenListDTO {
		List<GardenResultDTO> gardenList;

		Integer listSize;

		Integer totalPage;

		Long totalElements;

		Boolean isFirst;

		Boolean isLast;
	}

}

package umc.wantPlant.garden.domain.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class GardenResponseDTO {
	@Builder
	@Getter
	@NoArgsConstructor
	@AllArgsConstructor
	public static class GardenResultDTO {
		Long gardenId;

		String gardenCategory;
	}

	@Builder
	@Getter
	@NoArgsConstructor
	@AllArgsConstructor
	public static class GardenDTO {
		Long gardenId;
		String name;
		String description;
		String gardenCategory;
	}

	@Builder
	@NoArgsConstructor
	@AllArgsConstructor
	@Getter
	public static class GardenListDTO {
		List<GardenDTO> gardenList;

		Integer listSize;

		Integer totalPage;

		Long totalElements;

		Boolean isFirst;

		Boolean isLast;
	}

}

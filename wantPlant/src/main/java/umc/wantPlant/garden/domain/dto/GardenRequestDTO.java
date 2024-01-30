package umc.wantPlant.garden.domain.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import umc.wantPlant.garden.domain.enums.GardenCategories;

public class GardenRequestDTO {

	@Getter
	public static class GardenCreatDTO {

		@NotBlank
		private String name;

		@NotBlank
		private String description;

		@NotBlank
		private String category;
	}

	@Getter
	public static class UpdateGardenDTO {

		@NotBlank
		private String name;

		@NotBlank
		private String description;
	}
}

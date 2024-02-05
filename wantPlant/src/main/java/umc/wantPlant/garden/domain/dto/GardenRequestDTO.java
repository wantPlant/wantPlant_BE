package umc.wantPlant.garden.domain.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import umc.wantPlant.garden.domain.enums.GardenCategories;

public class GardenRequestDTO {

	@Getter
	public static class GardenCreatDTO {

		@NotBlank
		private String name;

		private String description;
		@NotBlank
		private String category;

		@NotNull
		private Long memberId;
	}

	@Getter
	public static class UpdateGardenDTO {
		@NotNull
		private Long id;

		private String name;

		private String description;

		@NotNull
		private Long memberId;
	}
}

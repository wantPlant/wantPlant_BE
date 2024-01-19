package umc.wantPlant.garden.domain.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

public class GardenRequestDTO {

	@Getter
	public static class CreatDTO {

		@NotBlank
		private String name;

		@NotBlank
		private String description;
		
		@NotNull
		private Long gardenCategory;
	}
}

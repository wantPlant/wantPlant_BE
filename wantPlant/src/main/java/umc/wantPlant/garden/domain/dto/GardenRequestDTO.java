package umc.wantPlant.garden.domain.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

public class GardenRequestDTO {

	@Getter
	public static class GardenCreatDTO {

		@NotBlank
		private String name;

		@NotBlank
		private String description;

		// TODO: String으로 정원카테고리 바꾸기
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

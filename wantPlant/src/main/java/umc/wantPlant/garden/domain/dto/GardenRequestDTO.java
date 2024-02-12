package umc.wantPlant.garden.domain.dto;

import org.springframework.web.bind.annotation.RequestParam;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import umc.wantPlant.garden.domain.enums.GardenCategories;

public class GardenRequestDTO {
	@Getter
	@Builder
	public static class GardenCreatRequestDTO {

		@NotBlank
		private String name;

		private String description;
		@NotBlank
		private GardenCategories category;
	}

	@Getter
	@Builder
	public static class GardenUpdateRequestDTO {
		@NotNull
		private Long gardenId;

		private String name;

		private String description;
	}
}

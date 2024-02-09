package umc.wantPlant.garden.domain.dto;

import org.springframework.web.bind.annotation.RequestParam;

import jakarta.validation.constraints.DecimalMin;
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
		private GardenCategories category;

		@NotNull
		private Long memberId;
	}

	@Getter
	public static class GardenPage {
		@NotNull
		Long memberID;

		GardenCategories category;
		@DecimalMin(value = "1")
		Integer page;
		@DecimalMin(value = "1")
		Integer pageSize;
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

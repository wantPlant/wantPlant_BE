package umc.wantPlant.garden.ui;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import umc.wantPlant.apipayload.ApiResponse;
import umc.wantPlant.garden.application.GardenCommandService;
import umc.wantPlant.garden.domain.Garden;
import umc.wantPlant.garden.domain.dto.GardenRequestDTO;
import umc.wantPlant.garden.domain.dto.GardenResponseDTO;

@RestController
@RequiredArgsConstructor
@RequestMapping("/gardens")
public class GardenController {

	private final GardenCommandService gardenCommandService;

	@PostMapping("/")
	@Operation(
		summary = "정원 생성 API",
		description = "정원을 생성하는 API입니다.")
	@ApiResponses({
		@io.swagger.v3.oas.annotations.responses.ApiResponse(
			responseCode = "COMMON200",
			description = "OK, 성공"),
	})
	public ApiResponse<GardenResponseDTO.GardenCreatResultDTO> creat(
		@RequestBody GardenRequestDTO.GardenCreatDTO request) {

		Garden garden = gardenCommandService.creatGarden(request);

		return ApiResponse.onSuccess(
			GardenResponseDTO.GardenCreatResultDTO
				.builder()
				.gardenId(garden.getId())
				.build()
		);
	}

}

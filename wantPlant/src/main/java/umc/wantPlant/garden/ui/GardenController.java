package umc.wantPlant.garden.ui;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import umc.wantPlant.apipayload.ApiResponse;
import umc.wantPlant.garden.application.GardenCommandService;
import umc.wantPlant.garden.application.GardenQueryService;
import umc.wantPlant.garden.domain.Garden;
import umc.wantPlant.garden.domain.dto.GardenRequestDTO;
import umc.wantPlant.garden.domain.dto.GardenResponseDTO;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/gardens")
public class GardenController {

	private final GardenCommandService gardenCommandService;
	private final GardenQueryService gardenQueryService;

	// 정원 생성 API
	@PostMapping("")
	@Operation(summary = "정원 생성 API", description = "정원을 생성하는 API입니다."
		+ "리퀘스트 바디로 이름,설명,카테고리번호를 주세요"
		+ "카테고리 번호는 1:운동 2:공부 3:취미입니다.")
	@ApiResponses({
		@io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200", description = "OK, 성공"),})
	public ApiResponse<GardenResponseDTO.GardenResultDTO> creat(
		@RequestBody GardenRequestDTO.GardenCreatDTO request) {

		Garden garden = gardenCommandService.creatGarden(request);

		return ApiResponse.onSuccess(GardenResponseDTO.GardenResultDTO.builder().gardenId(garden.getId()).build());
	}

	// 정원 수정 API
	@PutMapping("/{gardenId}")
	@Operation(summary = "정원 이름,설명 수정 API", description = "정원의 이름과 설명을 수정하는 API입니다.")
	@ApiResponses({
		@io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200", description = "OK, 성공"),})
	@Parameters({@Parameter(name = "gardenId", description = "정원ID를 주세요, path variable 입니다!")})
	public ApiResponse<GardenResponseDTO.GardenResultDTO> updateGarden(@PathVariable Long gardenId,
		@RequestBody GardenRequestDTO.UpdateGardenDTO request) {

		//TODO: Validation 추가
		Garden newGarden = gardenQueryService.findById(gardenId).orElseThrow();

		return null;
	}

	@PatchMapping("/{gardenId}/name")
	@Operation(summary = "정원 이름 수정 API", description = "정원의 이름을 수정하는 API입니다.,"
		+ " pathvariable로 id를 주시고 requestparam으로 name을 주세섦")
	@ApiResponses({
		@io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200", description = "OK, 성공"),})
	@Parameters({@Parameter(name = "gardenId", description = "정원 id를 주세요, path variable 입니다!"),
		@Parameter(name = "name", description = "바꿀 정원의 이름 정보를 주세요, requestParam 입니다!")})
	public ApiResponse<GardenResponseDTO.GardenResultDTO> updateGardenName(@PathVariable Long gardenId,
		@RequestParam String name) {

		return null;
	}

	@PatchMapping("/{gardenId}/description")
	@Operation(summary = "정원 설명 수정 API", description = "정원의 설명을 수정하는 API입니다.")
	@ApiResponses({
		@io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200", description = "OK, 성공"),})
	@Parameters({@Parameter(name = "gardenId", description = "정원 id를 주세요, path variable 입니다!"),
		@Parameter(name = "description", description = "바꿀 정원의 설명 정보를 주세요, requestParam 입니다!")})
	public ApiResponse<GardenResponseDTO.GardenResultDTO> updateGardenDescription(@PathVariable Long gardenId,
		@RequestParam String description) {

		return null;
	}

	// 특정 카테고리의 정원 조회
	@GetMapping("")
	@Operation(summary = "모든 정원 조회 API", description = "모든 정원을 조회하는 API입니다.")
	@ApiResponses({
		@io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200", description = "OK, 성공"),})
	public ApiResponse<GardenResponseDTO.GardenResultDTO> getGardens() {

		return null;
	}

	@GetMapping("/category/{category}")
	@Operation(summary = "특정 카테고리 정원 조회 API", description = "특정 카테고리의 정원을 조회하는 API입니다.,")
	@ApiResponses({
		@io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200", description = "OK, 성공"),})
	@Parameters({@Parameter(name = "gardenCategory", description = "카테고리 번호 1=운동 2=공부 3=취미, path variable 입니다!")})
	public ApiResponse<GardenResponseDTO.GardenResultDTO> getGardensByCategory(
		@PathVariable Integer category) {

		return null;
	}

}

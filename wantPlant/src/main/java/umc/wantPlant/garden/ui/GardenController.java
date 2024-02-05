package umc.wantPlant.garden.ui;

import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.DeleteMapping;
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
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Null;
import lombok.RequiredArgsConstructor;
import umc.wantPlant.apipayload.ApiResponse;
import umc.wantPlant.apipayload.code.status.ErrorStatus;
import umc.wantPlant.apipayload.exceptions.handler.GardenHandler;
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
	@Operation(summary = "정원 생성 API", description = "정원을 생성하는 API입니다." + "리퀘스트 바디로 멤버 ID,이름,설명,카테고리이름을 주세요")
	@ApiResponses({
		@io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200", description = "OK, 성공")})
	public ApiResponse<GardenResponseDTO.GardenCreatResultDTO> creat(
		@Valid @RequestBody GardenRequestDTO.GardenCreatDTO request) {

		GardenResponseDTO.GardenCreatResultDTO result = gardenCommandService.creat(request);

		return ApiResponse.onSuccess(result);
	}

	// 정원 수정 API
	@PutMapping("")
	@Operation(summary = "정원 이름,설명 수정 API", description = "정원의 이름과 설명을 수정하는 API입니다." + "리퀘스트 바디로 멤버 ID,이름,설명을 주세요")
	@ApiResponses({
		@io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200", description = "OK, 성공"),
		@io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "GARDEN400", description = "정원을 찾을수 없습니다.")})
	public ApiResponse<GardenResponseDTO.GardenUpdateResultDTO> updateGarden(
		@RequestBody @Valid GardenRequestDTO.UpdateGardenDTO update) {

		GardenResponseDTO.GardenUpdateResultDTO result = gardenCommandService.update(update);

		return ApiResponse.onSuccess(result);
	}

	@PatchMapping("/{memberId}/name")
	@Operation(summary = "정원 이름 수정 API", description = "정원의 이름을 수정하는 API입니다.")
	@ApiResponses({
		@io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200", description = "OK, 성공"),
		@io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "GARDEN400", description = "정원을 찾을수 없습니다.")})
	@Parameter(name = "memberId",description = "유저정보를 주세요, Query Parameter입니다" )
	public ApiResponse<GardenResponseDTO.GardenUpdateResultDTO> updateGardenName(
		@RequestBody @Valid GardenRequestDTO.UpdateGardenDTO update,
		@RequestParam Long memberId) {
		GardenResponseDTO.GardenUpdateResultDTO result = gardenCommandService.updateName(update);

		return ApiResponse.onSuccess(result);
	}

	@PatchMapping("/{memberId}/description")
	@Operation(summary = "정원 설명 수정 API", description = "정원의 설명을 수정하는 API입니다.")
	@ApiResponses({
		@io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200", description = "OK, 성공"),
		@io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "GARDEN400", description = "정원을 찾을수 없습니다.")})
	@Parameter(name = "memberId",description = "유저정보를 주세요, Query Parameter입니다" )
	public ApiResponse<GardenResponseDTO.GardenUpdateResultDTO> updateGardenDescription(
		@RequestBody @Valid GardenRequestDTO.UpdateGardenDTO update,
		@RequestParam Long memberId) {

		GardenResponseDTO.GardenUpdateResultDTO result = gardenCommandService.updateDescription(update);

		return ApiResponse.onSuccess(result);
	}

	// 특정 카테고리의 정원 조회
	@GetMapping("/{page}/{pageSize}")
	@Operation(summary = "모든 정원 조회 API", description = "모든 정원을 조회하는 API이며, 페이징을 포함합니다.  Path Variable로 page 번호를 주세요")
	@ApiResponses({
		@io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200", description = "OK, 성공"),
		@io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "GARDEN401", description = "정원이 하나도 존재하지 않습니다.", content = @Content(schema = @Schema(implementation = ApiResponse.class)))})
	@Parameter(name = "memberId",description = "유저정보를 주세요, Query Parameter입니다" )
	public ApiResponse<GardenResponseDTO.GardenListDTO> getGardenPage(@PathVariable Integer page,
		@PathVariable Integer pageSize ,@RequestParam Long memberId) {
		// Garden valid
		Long count = gardenQueryService.getGardenSize();
		page = (page == 0) ? 0 : page - 1;

		Page<Garden> gardenPage = gardenQueryService.getGardens(page, pageSize);
		GardenResponseDTO.GardenListDTO result = gardenCommandService.getGardenList(gardenPage);
		return ApiResponse.onSuccess(result);
	}

	@GetMapping("/{memberId}")
	@Operation(summary = "내 모든 정원 조회 API", description = "모든 정원을 조회하는 API입니다. 리스트형태로 반환합니다.")
	@ApiResponses({
		@io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200", description = "OK, 성공"),
		@io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "GARDEN400", description = "정원을 찾을수 없습니다.", content = @Content(schema = @Schema(implementation = ApiResponse.class))),
		@io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "GARDEN401", description = "정원이 하나도 존재하지 않습니다.", content = @Content(schema = @Schema(implementation = ApiResponse.class)))})
	@Parameter(name = "memberId",description = "유저정보를 주세요, Query Parameter입니다")
	public ApiResponse<GardenResponseDTO.GardenResultList> getGardenList(@RequestParam Long memberId) {
		// Garden valid
		Long count = gardenQueryService.getGardenSize();

		GardenResponseDTO.GardenResultList result = gardenCommandService.getGardenList();
		return ApiResponse.onSuccess(result);
	}

	@GetMapping("/{memberId}/category")
	@Operation(summary = "특정 카테고리 정원 조회 API", description = "특정 카테고리의 정원을 조회하는 API입니다.,")
	@ApiResponses({
		@io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200", description = "OK, 성공"),
		@io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "GARDEN400", description = "정원을 찾을수 없습니다.", content = @Content(schema = @Schema(implementation = ApiResponse.class))),
		@io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "GARDEN401", description = "정원이 하나도 존재하지 않습니다.", content = @Content(schema = @Schema(implementation = ApiResponse.class)))})
	@Parameter(name = "memberId",description = "유저정보를 주세요, Query Parameter입니다")
	public ApiResponse<GardenResponseDTO.GardenListDTO> getGardensByCategory(@RequestParam String category,
		@RequestParam Integer page, @RequestParam Integer pageSize) {

		// Garden valid
		Long count = gardenQueryService.getGardenSize();

		page = (page == 0) ? 0 : page - 1;
		Page<Garden> gardenPage = gardenQueryService.getGardensByCategory(category, page, pageSize);
		GardenResponseDTO.GardenListDTO result = gardenCommandService.getGardenList(gardenPage);
		return ApiResponse.onSuccess(result);
	}

	@GetMapping("/{memberId}/count")
	@Operation(summary = " 정원 개수 조회 API", description = "정원의 총 개수를 조회하는 API입니다.,")
	@ApiResponses({
		@io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200", description = "OK, 성공"),
		@io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "GARDEN401", description = "정원이 하나도 존재하지 않습니다.", content = @Content(schema = @Schema(implementation = ApiResponse.class)))})
	@Parameter(name = "memberId",description = "유저정보를 주세요, Query Parameter입니다")
	public ApiResponse<String> getGardensCount() {
		Long count = gardenQueryService.getGardenSize();

		return ApiResponse.onSuccess(count + "개의 정원이 존재합니다.");
	}

	//정원 삭제 API
	@DeleteMapping("/{memberId}/{gardenId}")
	@Operation(summary = "정원 삭제 API", description = "정원을 삭제하는 API입니다.,")
	@ApiResponses({
		@io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200", description = "OK, 성공"),
		@io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "GARDEN400", description = "정원을 찾을수 없습니다.", content = @Content(schema = @Schema(implementation = ApiResponse.class)))})
	@Parameters({
		@Parameter(name = "gardenId", description = "정원의 번호를 주세요, path variable 입니다!"),
		@Parameter(name = "memberId",description = "유저정보를 주세요, Query Parameter입니다")
	})
	public ApiResponse<String> deleteGardenById(@PathVariable Long gardenId,
		@RequestParam Long memberId) {
		gardenCommandService.delete(gardenId);
		return ApiResponse.onSuccess("삭제성공");

	}

}

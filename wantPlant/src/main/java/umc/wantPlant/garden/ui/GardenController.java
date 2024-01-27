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
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.constraints.Null;
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
	@Operation(summary = "정원 생성 API", description = "정원을 생성하는 API입니다." + "리퀘스트 바디로 이름,설명,카테고리이름을 주세요")
	@ApiResponses({
		@io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200", description = "OK, 성공")})
	public ApiResponse<GardenResponseDTO.GardenCreatResultDTO> creat(@RequestBody GardenRequestDTO.GardenCreatDTO request) {

		GardenResponseDTO.GardenCreatResultDTO result = gardenCommandService.creat(request);

		return ApiResponse.onSuccess(result);
	}

	// 정원 수정 API
	@PutMapping("/{gardenId}")
	@Operation(summary = "정원 이름,설명 수정 API", description = "정원의 이름과 설명을 수정하는 API입니다.")
	@ApiResponses({
		@io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200", description = "OK, 성공")})
	@Parameters({@Parameter(name = "gardenId", description = "정원ID를 주세요, path variable 입니다!")})
	public ApiResponse<GardenResponseDTO.GardenUpdateResultDTO> updateGarden(@PathVariable Long gardenId,
		@RequestBody GardenRequestDTO.UpdateGardenDTO update) {


		GardenResponseDTO.GardenUpdateResultDTO result = gardenCommandService.update(gardenId, update);

		return ApiResponse.onSuccess(result);
	}

	@PatchMapping("/{gardenId}/name")
	@Operation(summary = "정원 이름 수정 API", description = "정원의 이름을 수정하는 API입니다. 바꿀 정원의 이름 정보를 주세요, querystring 입니다!")
	@ApiResponses({
		@io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200", description = "OK, 성공")})
	@Parameters({@Parameter(name = "gardenId", description = "정원 id를 주세요, path variable 입니다!")})
	public ApiResponse<GardenResponseDTO.GardenUpdateResultDTO> updateGardenName(@PathVariable Long gardenId,
		@RequestParam String name) {

		GardenResponseDTO.GardenUpdateResultDTO result = gardenCommandService.updateName(gardenId, name);

		return ApiResponse.onSuccess(result);
	}

	@PatchMapping("/{gardenId}/description")
	@Operation(summary = "정원 설명 수정 API", description = "정원의 설명을 수정하는 API입니다. , query String 으로 page 번호를 주세요")
	@ApiResponses({
		@io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200", description = "OK, 성공")})
	@Parameters({@Parameter(name = "gardenId", description = "정원 id를 주세요, path variable 입니다!")})
	public ApiResponse<GardenResponseDTO.GardenUpdateResultDTO> updateGardenDescription(@PathVariable Long gardenId,
		@RequestParam String description) {

		GardenResponseDTO.GardenUpdateResultDTO result = gardenCommandService.updateDescription(gardenId, description);

		return ApiResponse.onSuccess(result);
	}

	// 특정 카테고리의 정원 조회
	@GetMapping("")
	@Operation(summary = "모든 정원 조회 API", description = "모든 정원을 조회하는 API이며, 페이징을 포함합니다.  query String 으로 page 번호를 주세요")
	@ApiResponses({
		@io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200", description = "OK, 성공")})
	public ApiResponse<GardenResponseDTO.GardenListDTO> getGardens(@RequestParam Integer page,
		@RequestParam Integer pageSize) {
		page = (page == 0) ? 0 : page - 1;
		Page<Garden> gardenPage = gardenQueryService.getGardens(page, pageSize);
		GardenResponseDTO.GardenListDTO result = gardenCommandService.getGardenList(gardenPage);
		return ApiResponse.onSuccess(result);
	}

	@GetMapping("/category")
	@Operation(summary = "특정 카테고리 정원 조회 API", description = "특정 카테고리의 정원을 조회하는 API입니다.,")
	@ApiResponses({
		@io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200", description = "OK, 성공")})
	public ApiResponse<GardenResponseDTO.GardenListDTO> getGardensByCategory(
		@RequestParam String category, @RequestParam Integer page, @RequestParam Integer pageSize) {

		page = (page == 0) ? 0 : page - 1;
		Page<Garden> gardenPage = gardenQueryService.getGardensByCategory(category, page, pageSize);
		GardenResponseDTO.GardenListDTO result = gardenCommandService.getGardenList(gardenPage);
		return ApiResponse.onSuccess(result);
	}

	@DeleteMapping("{gardenId}")
	@Operation(summary = "정원 삭제 API", description = "정원을 삭제하는 API입니다.,")
	@ApiResponses({
		@io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200", description = "OK, 성공")})
	@Parameters({@Parameter(name = "gardenId", description = "정원의 번호를 주세요, path variable 입니다!")})
	public ApiResponse<Null> deleteGardenById(@PathVariable Long gardenId) {
		try {
			gardenCommandService.delete(gardenId);
			return ApiResponse.onSuccess(null);
		} catch (Exception e) {
			return ApiResponse.onSuccess(null);
		}
	}

}

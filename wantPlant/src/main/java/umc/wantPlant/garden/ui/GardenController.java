package umc.wantPlant.garden.ui;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import umc.wantPlant.apipayload.ApiResponse;
import umc.wantPlant.config.security.oauth.CurrentMember;
import umc.wantPlant.garden.application.GardenCommandService;
import umc.wantPlant.garden.application.GardenQueryService;
import umc.wantPlant.garden.domain.Garden;
import umc.wantPlant.garden.domain.dto.GardenRequestDTO;
import umc.wantPlant.garden.domain.dto.GardenResponseDTO;
import umc.wantPlant.garden.domain.enums.GardenCategories;
import umc.wantPlant.member.domain.Member;

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
	public ApiResponse<GardenResponseDTO.GardenCreatResultDTO> creatGarden(
		@CurrentMember Member member, @RequestBody GardenRequestDTO.GardenCreatRequestDTO gardenCreatRequestDTO) {

		return ApiResponse.onSuccess(gardenCommandService.toCreatGarden(member, gardenCreatRequestDTO));
	}

	// 특정 카테고리의 정원 조회
	@GetMapping("/{gardenId}")
	@Operation(summary = "내 정원 상세보기 API", description = "정원 상세보기 API입니다.")
	@ApiResponses({
		@io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200", description = "OK, 성공"),
		@io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "GARDEN400", description = "정원을 찾을수 없습니다.", content = @Content(schema = @Schema(implementation = ApiResponse.class)))})
	@Parameters({@Parameter(name = "gardenId", description = "정원 Id를 입력하세요.")})
	public ApiResponse<GardenResponseDTO.GardenGetResultDTO> getGarden(@CurrentMember Member member,
		@PathVariable Long gardenId) {
		Garden garden = gardenQueryService.getGardenById(gardenId);

		return ApiResponse.onSuccess(gardenCommandService.toGardenResultDTO(garden));
	}

	@GetMapping("")
	@Operation(summary = "내 모든 정원을 리스트로 조회하기 API", description = "모든 정원을 조회하는 API입니다. 리스트형태로 반환합니다.")
	@ApiResponses({
		@io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200", description = "OK, 성공"),
		@io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "GARDEN400", description = "정원을 찾을수 없습니다.", content = @Content(schema = @Schema(implementation = ApiResponse.class)))})
	public ApiResponse<GardenResponseDTO.GardenGetResultListDTO> getGardenByList(@CurrentMember Member member) {

		return ApiResponse.onSuccess(gardenCommandService.toGetGardenByList(member));
	}

	@GetMapping("/page")
	@Operation(summary = "내 모든 정원을 페이지로 조회하기 API", description = "모든 정원을 조회하는 API이며, 페이징을 포함합니다. 페이지 사이즈는 5로 고정입니다.")
	@ApiResponses({
		@io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200", description = "OK, 성공")})
	@Parameters({@Parameter(name = "gardenCategory", description = "EXERCISE,STUDY,HOBBY 중 하나를 입력하세요.")})
	public ApiResponse<GardenResponseDTO.GardenGetResultPageDTO> getGardenByPage(
		@CurrentMember Member member, @RequestParam int page) {

		// 내 모든 정원 페이지로 불러오기
		Page<Garden> gardenPage = gardenQueryService.getGardensToPage(page, member);

		return ApiResponse.onSuccess(gardenCommandService.toGetGardenByPage(gardenPage));
	}

	@GetMapping("/page/category")
	@Operation(summary = "특정 카테고리 정원 조회 API", description = "특정 카테고리의 정원을 조회하는 API입니다. 페이지 사이즈는 5로 고정입니다.")
	@ApiResponses({
		@io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200", description = "OK, 성공"),
		@io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "GARDEN400", description = "정원을 찾을수 없습니다.", content = @Content(schema = @Schema(implementation = ApiResponse.class))),})
	@Parameters({@Parameter(name = "page", description = "0 이상의 페이지 넘버를 입력하세요"),
		@Parameter(name = "gardenCategory", description = "EXERCISE,STUDY,HOBBY 중 하나를 입력하세요.")})
	public ApiResponse<GardenResponseDTO.GardenGetResultPageDTO> getGardensByCategoryToPage(
		@CurrentMember Member member, @RequestParam int page, @RequestParam GardenCategories gardenCategory) {

		Page<Garden> gardenPage = gardenQueryService.getGardensByCategoryToPage(page, member, gardenCategory);

		return ApiResponse.onSuccess(gardenCommandService.toGetGardenByPage(gardenPage));
	}

	@GetMapping("/count")
	@Operation(summary = " 정원 개수 조회 API", description = "정원의 총 개수를 조회하는 API입니다.,")
	@ApiResponses({
		@io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200", description = "OK, 성공"),
		@io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "GARDEN401", description = "정원이 하나도 존재하지 않습니다.", content = @Content(schema = @Schema(implementation = ApiResponse.class)))})
	public ApiResponse<String> getGardensCount(@CurrentMember Member member) {
		return ApiResponse.onSuccess(gardenQueryService.getGardenSize(member) + "개의 정원이 존재합니다.");
	}

	// 정원 수정 API
	@PutMapping("")
	@Operation(summary = "정원 이름,설명 수정 API", description = "정원의 이름과 설명을 수정하는 API입니다." + "리퀘스트 바디로 멤버 ID,이름,설명을 주세요")
	@ApiResponses({
		@io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200", description = "OK, 성공"),
		@io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "GARDEN400", description = "정원을 찾을수 없습니다.")})
	public ApiResponse<GardenResponseDTO.GardenUpdateResultDTO> updateGarden(
		@CurrentMember Member member, @RequestBody GardenRequestDTO.GardenUpdateRequestDTO gardenUpdateRequestDTO) {

		return ApiResponse.onSuccess(gardenCommandService.toUpdateGarden(member, gardenUpdateRequestDTO));
	}

	//정원 삭제 API
	@DeleteMapping("/{gardenId}")
	@Operation(summary = "정원 삭제 API", description = "정원을 삭제하는 API입니다.")
	@ApiResponses({
		@io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200", description = "OK, 성공"),
		@io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "GARDEN400", description = "정원을 찾을수 없습니다.", content = @Content(schema = @Schema(implementation = ApiResponse.class)))})
	@Parameters({@Parameter(name = "gardenId", description = "정원의 번호를 주세요, path variable 입니다!")})
	public ApiResponse<String> deleteGarden(@PathVariable Long gardenId, @CurrentMember Member member) {
		gardenCommandService.toDeleteGarden(member.getId(), gardenId);
		return ApiResponse.onSuccess("삭제성공");
	}
}

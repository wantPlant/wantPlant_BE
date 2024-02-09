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
import umc.wantPlant.member.domain.Member;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/gardens")
public class GardenControllerTemp {
    private final GardenCommandService gardenCommandService;
    private final GardenQueryService gardenQueryService;

    // 정원 생성 API
    @PostMapping("")
    @Operation(summary = "정원 생성 API", description = "정원을 생성하는 API입니다." + "리퀘스트 바디로 멤버 ID,이름,설명,카테고리이름을 주세요")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200", description = "OK, 성공")})
    public ApiResponse<GardenResponseDTO.GardenCreatResultDTO> creat(
            @RequestBody GardenRequestDTO.GardenCreatDTO request) {

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
            @RequestBody GardenRequestDTO.UpdateGardenDTO update) {

        GardenResponseDTO.GardenUpdateResultDTO result = gardenCommandService.update(update);

        return ApiResponse.onSuccess(result);
    }

    // 특정 카테고리의 정원 조회
    @GetMapping("")
    @Operation(summary = "모든 정원 조회 API", description = "모든 정원을 조회하는 API이며, 페이징을 포함합니다.  Path Variable로 page 번호를 주세요")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200", description = "OK, 성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "GARDEN401", description = "정원이 하나도 존재하지 않습니다.", content = @Content(schema = @Schema(implementation = ApiResponse.class)))})
    public ApiResponse<GardenResponseDTO.GardenListDTO> getGardenPage(
            @CurrentMember Member member,
            @RequestBody GardenRequestDTO.GardenPage getPage) {
        // Garden valid
        Long count = gardenQueryService.getGardenSize(member.getId());

        // 내 모든 정원 페이지로 불러오기
        Page<Garden> gardenPage = gardenQueryService.getGardens(getPage);

        // 불러온 페이지를 RequestDTO에 매핑하기
        GardenResponseDTO.GardenListDTO result = gardenCommandService.getGardenList(gardenPage);
        return ApiResponse.onSuccess(result);
    }

    @GetMapping("/")
    @Operation(summary = "내 모든 정원 조회 API", description = "모든 정원을 조회하는 API입니다. 리스트형태로 반환합니다.")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200", description = "OK, 성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "GARDEN400", description = "정원을 찾을수 없습니다.", content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "GARDEN401", description = "정원이 하나도 존재하지 않습니다.", content = @Content(schema = @Schema(implementation = ApiResponse.class)))})
    public ApiResponse<GardenResponseDTO.GardenResultList> getGardenList(@CurrentMember Member member) {
        // Garden valid
        Long count = gardenQueryService.getGardenSize(member.getId());

        GardenResponseDTO.GardenResultList result = gardenCommandService.getGardenList(member.getId());
        return ApiResponse.onSuccess(result);
    }

    @GetMapping("/category")
    @Operation(summary = "특정 카테고리 정원 조회 API", description = "특정 카테고리의 정원을 조회하는 API입니다.,")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200", description = "OK, 성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "GARDEN400", description = "정원을 찾을수 없습니다.", content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "GARDEN401", description = "정원이 하나도 존재하지 않습니다.", content = @Content(schema = @Schema(implementation = ApiResponse.class)))})
    @Parameter(name = "memberId", description = "유저정보를 주세요, Query Parameter입니다")
    public ApiResponse<GardenResponseDTO.GardenListDTO> getGardensByCategory(
            @RequestBody GardenRequestDTO.GardenPage getPage) {

        // Garden valid
        Long count = gardenQueryService.getGardenSize(getPage.getMemberID());

        Page<Garden> gardenPage = gardenQueryService.getGardensByCategory(getPage);

        GardenResponseDTO.GardenListDTO result = gardenCommandService.getGardenList(gardenPage);
        return ApiResponse.onSuccess(result);
    }

    @GetMapping("/count")
    @Operation(summary = " 정원 개수 조회 API", description = "정원의 총 개수를 조회하는 API입니다.,")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200", description = "OK, 성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "GARDEN401", description = "정원이 하나도 존재하지 않습니다.", content = @Content(schema = @Schema(implementation = ApiResponse.class)))})
    public ApiResponse<String> getGardensCount(@CurrentMember Member member) {
        Long count = gardenQueryService.getGardenSize(member.getId());

        return ApiResponse.onSuccess(count + "개의 정원이 존재합니다.");
    }

    //정원 삭제 API
    @DeleteMapping("/{gardenId}")
    @Operation(summary = "정원 삭제 API", description = "정원을 삭제하는 API입니다.,")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200", description = "OK, 성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "GARDEN400", description = "정원을 찾을수 없습니다.", content = @Content(schema = @Schema(implementation = ApiResponse.class)))})
    @Parameters({@Parameter(name = "gardenId", description = "정원의 번호를 주세요, path variable 입니다!")})
    public ApiResponse<String> deleteGardenById(@PathVariable Long gardenId, @CurrentMember Member member) {
        gardenCommandService.delete(member.getId(), gardenId);
        return ApiResponse.onSuccess("삭제성공");
    }
}

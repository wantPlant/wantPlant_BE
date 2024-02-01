package umc.wantPlant.pot.ui;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import umc.wantPlant.apipayload.ApiResponse;
import umc.wantPlant.apipayload.code.status.ErrorStatus;
import umc.wantPlant.apipayload.exceptions.GeneralException;
import umc.wantPlant.apipayload.exceptions.handler.PotHandler;
import umc.wantPlant.pot.application.PotCommandService;
import umc.wantPlant.pot.application.PotQueryService;
import umc.wantPlant.pot.domain.Pot;
import umc.wantPlant.pot.domain.dto.PotRequestDTO;
import umc.wantPlant.pot.domain.dto.PotResponseDTO;

import java.time.LocalDate;

@RestController
@RequiredArgsConstructor
@Validated
@RequestMapping("/api/pots")
public class PotController {
    private final PotCommandService potCommandService;
    private final PotQueryService potQueryService;

    @PostMapping("")
    @Operation(summary = "화분 생성 API", description = "화분을 생성하는 API입니다.")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200",description = "OK, 성공"),
    })
    public ApiResponse<String> postPot(@RequestBody PotRequestDTO.PostPotDTO request){
        Pot newPot = potCommandService.createPot(request);

        return ApiResponse.onSuccess(newPot.getPotId()+"번 화분이 정상적으로 생성되었습니다.");
    }

    @PostMapping("/goals/todos")
    @Operation(summary = "화분, goal 리스트, todo 리스트 생성 API", description = "화분, goal 리스트, todo 리스트를 생성하는 API입니다.")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200",description = "OK, 성공"),
    })
    public ApiResponse<String> postPotGoalTodo(@RequestBody PotRequestDTO.PostPotGoalTodoDTO request){
        Pot newPot = potCommandService.createPotGoalsTodos(request);
        return ApiResponse.onSuccess(newPot.getPotId()+" 화분이 정상적으로 생성되었습니다.");
    }

    @GetMapping("/names")
    @Operation(summary = "정원당 화분 이름 조회 API", description = "정원당 화분 id, 화분 이름을 조회하는 API입니다")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200",description = "OK, 성공"),
    })
    @Parameter(name = "gardenId", description = "Query String으로 정원 ID를 주세요")
    public ApiResponse<PotResponseDTO.GetPotNamesResultDTO> getPotNames(
            @RequestParam(name = "gardenId") Long gardenId){

        return ApiResponse.onSuccess(potQueryService.getPotNamesByGardenId(gardenId));
    }

    @GetMapping("/images")
    @Operation(summary = "정원당 화분 이미지 조회 API", description = "정원당 화분 id, 화분 이미지를 조회하는 API입니다.")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200",description = "OK, 성공"),
    })
    @Parameter(name = "gardenId", description = "Query String으로 정원 ID를 주세요")
    public ApiResponse<PotResponseDTO.GetPotImagesResultDTO> getPotImages(
            @RequestParam(name = "gardenId") Long gardenId){
        return ApiResponse.onSuccess(potQueryService.getPotImagesByGardenId(gardenId));
    }

    @GetMapping("")
    @Operation(summary = "정원 당 화분 리스트 조회 API", description = "정원 당 화분 리스트를 조회하는 API입니다. paging을 포함합니다.")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200",description = "OK, 성공"),
    })
    @Parameters({
            @Parameter(name = "gardenId", description = "Query String으로 정원 ID를 주세요"),
            @Parameter(name = "page", description = "Query String으로 page번호(>=1)를 주세요")
    })
    public ApiResponse<PotResponseDTO.GetPotsResultDTO> getPots(
            @RequestParam(name = "gardenId") Long gardenId,
            @RequestParam(name = "page") int page){
        return ApiResponse.onSuccess(potQueryService.getPotsByGardenId(gardenId, page-1));
    }

    @GetMapping("/todos/date")
    @Operation(summary = "날짜별 카테고리 & 화분 & todo 리스트 조회 API", description = "날짜별 카테고리 & 화분 & todo 리스트 조회하는 API입니다.")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200",description = "OK, 성공"),
    })
    @Parameter(name = "date", description = "Query String으로 date(yyyy-mm-dd)를 주세요")
    public ApiResponse<PotResponseDTO.GetCategoryPotTodoPerDateDTO> getCategoryPotTodoPerDate(
            @RequestParam(name = "date") LocalDate date
    ){
        return ApiResponse.onSuccess(potQueryService.getCategoryPotTodoByDate(date));
    }

    @GetMapping("/{potId}")
    @Operation(summary = "화분 상세 조회 API", description = "화분 상세 조회 API입니다.")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200",description = "OK, 성공"),
    })
    @Parameter(name = "potId", description = "PathVariable로 potId를 주세요")
    public ApiResponse<PotResponseDTO.GetPotDetailResultDTO> getPotDetail(
            @PathVariable(name = "potId")Long potId){
        return ApiResponse.onSuccess(potQueryService.getPotDetailByPotId(potId));
    }

    @PatchMapping("/{potId}")
    @Operation(summary = "화분 수정 API", description = "화분 수정 API입니다.")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200",description = "OK, 성공"),
    })
    @Parameters({
            @Parameter(name = "potId", description = "Path Variable로 potId를 주세요")
    })
    public ApiResponse<String> patchPot(
            @PathVariable(name = "potId") Long potId,
            @RequestBody PotRequestDTO.PatchPotDTO request){
        Pot pot = potCommandService.updatePot(potId, request);
        return ApiResponse.onSuccess(pot.getPotId()+"번 화분이 정상적으로 수정되었습니다.");
    }

    @DeleteMapping("/{potId}")
    @Operation(summary = "화분 삭제 API", description = "화분 삭제 API입니다.")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200",description = "OK, 성공"),
    })
    @Parameter(name = "potId", description = "Path Variable로 potId를 주세요")
    public ApiResponse<String> deletePot(@PathVariable(name = "potId") Long potId){
        try {
            potCommandService.deletePot(potId);
        }catch(GeneralException e){
            throw new PotHandler(ErrorStatus.POT_DELETE_BAD_REQUEST);
        }

        return ApiResponse.onSuccess(potId+" 화분이 정상적으로 삭제되었습니다.");
    }
}

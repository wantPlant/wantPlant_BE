package umc.wantPlant.pot.ui;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.web.bind.annotation.*;
import umc.wantPlant.apipayload.ApiResponse;
import umc.wantPlant.pot.application.PotCommandService;
import umc.wantPlant.pot.application.PotQueryService;
import umc.wantPlant.pot.domain.dto.PotRequestDTO;
import umc.wantPlant.pot.domain.dto.PotResponseDTO;

import java.time.LocalDate;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/pots")
public class PotController {
//    private final PotQueryService potQueryService;
//    private final PotCommandService potCommandService;

    @PostMapping("")
    @Operation(summary = "화분 생성 API", description = "화분을 생성하는 API입니다.")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200",description = "OK, 성공"),
    })
    public ApiResponse<String> postPot(@RequestBody PotRequestDTO.PostPotDTO request){
        return null;
    }

    @PostMapping("/goals/todos")
    @Operation(summary = "화분, goal 리스트, todo 리스트 생성 API", description = "화분, goal 리스트, todo 리스트를 생성하는 API입니다.")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200",description = "OK, 성공"),
    })
    public ApiResponse<String> postPotGoalTodo(@RequestBody PotRequestDTO.PostPotGoalTodoDTO request){
        return null;
    }

    @GetMapping("/names")
    @Operation(summary = "정원당 화분 이름 조회 API", description = "정원당 화분 id, 화분 이름을 조회하는 API입니다")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200",description = "OK, 성공"),
    })
    @Parameter(name = "gardenId", description = "Query String으로 정원 ID를 주세요")
    public ApiResponse<PotResponseDTO.GetPotNamesResultDTO> getPotNames(
            @RequestParam(name = "gardenId") Long gardenId){
        return null;
    }

    @GetMapping("/images")
    @Operation(summary = "정원당 화분 이미지 조회 API", description = "정원당 화분 id, 화분 이미지를 조회하는 API입니다.")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200",description = "OK, 성공"),
    })
    @Parameter(name = "gardenId", description = "Query String으로 정원 ID를 주세요")
    public ApiResponse<PotResponseDTO.GetPotImagesResultDTO> getPotImages(
            @RequestParam(name = "gardenId") Long gardenId){
        return null;
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
        return null;
    }

    @GetMapping("/todos/date")
    @Operation(summary = "날짜별 카테고리 & 화분 & todo 리스트 조회 API", description = "날짜별 카테고리 & 화분 & todo 리스트 조회하는 API입니다.")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200",description = "OK, 성공"),
    })
    @Parameter(name = "date", description = "Query String으로 date를 주세요")
    public ApiResponse<PotResponseDTO.GetCategoryPotTodoPerDateDTO> getCategoryPotTodoPerDate(
            @RequestParam(name = "date") LocalDate date
    ){
        return null;
    }

    @GetMapping("/{potId}")
    @Operation(summary = "화분 상세 조회 API", description = "화분 상세 조회 API입니다.")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200",description = "OK, 성공"),
    })
    @Parameter(name = "potId", description = "PathVariable로 potId를 주세요")
    public ApiResponse<PotResponseDTO.GetPotDetailResultDTO> getPotDetail(
            @PathVariable(name = "potId")Long potId){
        return null;
    }

    @GetMapping ("/completed")
    @Operation(summary = "완료한 화분 리스트 조회 API", description = "완료한 화분 리스트를 조회하는 API입니다. 도감에 사용하시면 됩니다.")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200",description = "OK, 성공"),
    })
    @Parameter(name = "gardenId", description = "Query String으로 gardenId를 주세요")
    public ApiResponse<PotResponseDTO.GetCompletedPotsResultDTO> getCompletedPots(
            @RequestParam(name = "gardenId") Long gardenId
    ){
        return null;
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
        return null;
    }

    @DeleteMapping("/{potId}")
    @Operation(summary = "화분 삭제 API", description = "화분 삭제 API입니다.")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200",description = "OK, 성공"),
    })
    @Parameter(name = "potId", description = "Path Variable로 potId를 주세요")
    public ApiResponse<String> deletePot(@PathVariable(name = "potId") Long potId){
        return null;
    }
}

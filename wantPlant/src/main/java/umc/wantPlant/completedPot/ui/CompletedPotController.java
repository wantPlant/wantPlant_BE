package umc.wantPlant.completedPot.ui;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import umc.wantPlant.apipayload.ApiResponse;
import umc.wantPlant.completedPot.application.CompletedPotCommandService;
import umc.wantPlant.completedPot.application.CompletedPotQueryService;
import umc.wantPlant.completedPot.domain.dto.CompletedPotResponseDTO;
import umc.wantPlant.pot.domain.dto.PotResponseDTO;
import umc.wantPlant.pot.validation.annotation.ExistGarden;

@RestController
@RequiredArgsConstructor
@Validated
@RequestMapping("/api/completed-pots")
public class CompletedPotController {
    private final CompletedPotCommandService completedPotCommandService;
    private final CompletedPotQueryService completedPotQueryService;

    @GetMapping("/app")
    @Operation(summary = "[앱] 완료한 화분 리스트 조회 API", description = "완료한 화분 리스트를 조회하는 API입니다. 도감에 사용하시면 됩니다.")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200",description = "OK, 성공"),
    })
    @Parameter(name = "gardenId", description = "Query String으로 gardenId를 주세요")
    public ApiResponse<CompletedPotResponseDTO.GetCompletedPotsResultDTO> getCompletedPots(
            @ExistGarden @RequestParam(name = "gardenId") Long gardenId
    ){
        completedPotQueryService.getCompletedPotsByGardenId(gardenId);
        return ApiResponse.onSuccess(completedPotQueryService.getCompletedPotsByGardenId(gardenId));
    }

    @GetMapping ("/web")
    @Operation(summary = "[웹] 완료한 화분 리스트 조회 API", description = "완료한 화분 리스트를 조회하는 API입니다. 도감에 사용하시면 됩니다.")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200",description = "OK, 성공"),
    })
    public ApiResponse<CompletedPotResponseDTO.GetCompletedPotsForWebResultDTO> getCompletedPotsForWeb(){
        return ApiResponse.onSuccess(completedPotQueryService.getCompletedPotsForWeb());
    }
}

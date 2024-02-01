package umc.wantPlant.goal.ui;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import umc.wantPlant.apipayload.ApiResponse;
import umc.wantPlant.goal.application.GoalCommandService;
import umc.wantPlant.goal.application.GoalQueryService;
import umc.wantPlant.goal.domain.Goal;
import umc.wantPlant.goal.domain.dto.GoalRequestDTO;
import umc.wantPlant.goal.domain.dto.GoalResponseDTO;
import umc.wantPlant.goal.validation.annotation.ExistGoal;
import umc.wantPlant.pot.validation.annotation.ExistPot;

@RestController
@RequiredArgsConstructor
@Validated
@RequestMapping("api/goals")
public class GoalController {
    private final GoalCommandService goalCommandService;
    private final GoalQueryService goalQueryService;

    @PostMapping("")
    @Operation(summary = "목표 생성 API", description = "목표 생성 API입니다.")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200",description = "OK, 성공"),
    })
    public ApiResponse<String> postGoal(@RequestBody @Valid GoalRequestDTO.PostGoalDTO request){
        Goal goal = goalCommandService.createGoal(request);
        return ApiResponse.onSuccess(goal.getGoalId()+"번 목표 생성 완료");
    }

    @PostMapping("/todos")
    @Operation(summary = "목표 & todo(한 개) 생성 API", description = "목표와 그 하위 todo 한 개를 생성하는 API입니다.")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200",description = "OK, 성공"),
    })
    public ApiResponse<String> postGoalTodo(@RequestBody @Valid GoalRequestDTO.PostGoalTodoDTO request){
        Goal goal = goalCommandService.createGoalTodo(request);
        return ApiResponse.onSuccess(goal.getGoalId()+"번 목표와 하위 todo 생성 완료");
    }

    @GetMapping("/todos")
    @Operation(summary = "화분 당 목표 & todo 조회 API", description = "화분 당 목표 리스트와 todo 리스트를 조회하는 API입니다.")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200",description = "OK, 성공"),
    })
    @Parameter(name = "potId", description = "QueryString으로 potId를 주세요")
    public ApiResponse<GoalResponseDTO.GetGoalsTodosByPotResultDTO> getGoalsTodosByPot(
            @ExistPot @RequestParam Long potId){
        return ApiResponse.onSuccess(goalQueryService.getGoalsTodosByPot(potId));
    }

    @PatchMapping("/{goalId}")
    @Operation(summary = "목표 수정 API", description = "목표 수정 API입니다.")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200",description = "OK, 성공"),
    })
    @Parameter(name = "goalId", description = "PathVariable로 goalId를 주세요")
    public ApiResponse<String> patchGoal(
            @ExistGoal @PathVariable Long goalId, @Valid @RequestBody GoalRequestDTO.PatchGoalDTO request){
        Goal goal = goalCommandService.modifyGoal(goalId, request);
        return ApiResponse.onSuccess(goal.getGoalId()+"번 목표 수정 완료");
    }

    @DeleteMapping("/{goalId}")
    @Operation(summary = "목표 삭제 API", description = "목표와 하위 todo들을 삭제하는 API입니다.")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200",description = "OK, 성공"),
    })
    @Parameter(name = "goalId", description = "PathVariable로 goalId를 주세요")
    public ApiResponse<String> deleteGoal(@ExistGoal @PathVariable Long goalId){
        goalCommandService.deleteGoal(goalId);
        return ApiResponse.onSuccess(goalId+"번 목표와 하위 todo들 삭제 완료");
    }

}

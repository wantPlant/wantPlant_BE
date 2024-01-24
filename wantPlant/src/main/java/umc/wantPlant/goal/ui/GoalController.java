package umc.wantPlant.goal.ui;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import umc.wantPlant.apipayload.ApiResponse;
import umc.wantPlant.goal.domain.dto.GoalRequestDTO;
import umc.wantPlant.goal.domain.dto.GoalResponseDTO;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/goals")
public class GoalController {
    @PostMapping("")
    public ApiResponse<String> postGoal(@RequestBody GoalRequestDTO.PostGoalDTO request){
        return null;
    }

    @PostMapping("/todos")
    public ApiResponse<String> postGoalTodo(@RequestBody GoalRequestDTO.PostGoalTodoDTO request){
        return null;
    }

    @GetMapping("/todos")
    public ApiResponse<GoalResponseDTO.GetGoalsTodosByPotResultDTO> getGoalsTodosByPot(@RequestParam Long potId){
        return null;
    }

    @PatchMapping("/{goalId}")
    public ApiResponse<String> patchGoal(@PathVariable Long goalId, @RequestBody GoalRequestDTO.PatchGoalDTO request){
        return null;
    }

    @DeleteMapping("{goalId}")
    public ApiResponse<String> deleteGoal(@PathVariable Long goalId){
        return null;
    }

}

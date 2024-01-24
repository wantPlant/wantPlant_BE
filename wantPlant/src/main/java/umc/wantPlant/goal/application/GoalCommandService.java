package umc.wantPlant.goal.application;


import umc.wantPlant.goal.domain.Goal;
import umc.wantPlant.goal.domain.dto.GoalRequestDTO;
import umc.wantPlant.goal.domain.dto.GoalResponseDTO;
import umc.wantPlant.pot.domain.Pot;
import umc.wantPlant.pot.domain.dto.PotRequestDTO;

import java.util.List;

public interface GoalCommandService {
    public Goal createGoal(GoalRequestDTO.PostGoalDTO request);
    public Goal createGoalTodo(GoalRequestDTO.PostGoalTodoDTO request);
    public void createGoalsTodos(Pot pot, List<PotRequestDTO.GoalDTO> request);
    public Goal modifyGoal(Long goalId, GoalRequestDTO.PatchGoalDTO request);
    public void deleteGoal(Long goalId);
    public void deleteAllByPot(Pot pot);
}

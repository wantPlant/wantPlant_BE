package umc.wantPlant.goal.application;

import umc.wantPlant.goal.domain.Goal;
import umc.wantPlant.goal.domain.dto.GoalResponseDTO;
import umc.wantPlant.pot.domain.Pot;

import java.util.List;

public interface GoalQueryService {
    public GoalResponseDTO.GetGoalsTodosByPotResultDTO getGoalsTodosByPot(Long potId);
    public List<Goal> findAllByPot(Pot pot);
}

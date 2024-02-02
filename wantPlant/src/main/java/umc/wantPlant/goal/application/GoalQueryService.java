package umc.wantPlant.goal.application;

import umc.wantPlant.garden.domain.Garden;
import umc.wantPlant.goal.domain.Goal;
import umc.wantPlant.goal.domain.dto.GoalResponseDTO;
import umc.wantPlant.pot.domain.Pot;

import java.util.List;
import java.util.Optional;

public interface GoalQueryService {
    public GoalResponseDTO.GetGoalsTodosByPotResultDTO getGoalsTodosByPot(Long potId);
    public List<Goal> findAllByPot(Pot pot);

    Optional<Goal> getGoalById(Long goalId);
}

package umc.wantPlant.goal.application;

import umc.wantPlant.garden.domain.Garden;
import umc.wantPlant.goal.domain.Goal;
import umc.wantPlant.goal.domain.dto.GoalResponseDTO;
import umc.wantPlant.pot.domain.Pot;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface GoalQueryService {
    public GoalResponseDTO.GetGoalsTodosByPotResultDTO getGoalsTodosByPot(Long potId);
    public GoalResponseDTO.GetGoalsTodosByDateAndPotResultDTO getGoalsTodosPerPotAndDate(LocalDate date, Long potId);
    public List<Goal> findAllByPot(Pot pot);
    public boolean existGoalById(Long goalId);
    public Goal getGoalById(Long goalId);
}

package umc.wantPlant.goal.application;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import umc.wantPlant.garden.domain.Garden;
import umc.wantPlant.goal.domain.Goal;
import umc.wantPlant.goal.domain.dto.GoalResponseDTO;
import umc.wantPlant.goal.repository.GoalRepository;
import umc.wantPlant.pot.application.PotQueryService;
import umc.wantPlant.pot.domain.Pot;
import umc.wantPlant.todo.application.TodoService;
import umc.wantPlant.todo.domain.Todo;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class GoalQueryServiceImpl implements GoalQueryService {
    private final GoalRepository goalRepository;
    private final PotQueryService potQueryService;
    private final TodoService todoService;
    @Override
    public GoalResponseDTO.GetGoalsTodosByPotResultDTO getGoalsTodosByPot(Long potId) {
        List<Goal> goals = goalRepository.findAllByPot(potQueryService.getPotByPotId(potId)).get();

        List<GoalResponseDTO.GoalDTO> goalDTOS = goals.stream().map(goal->
                {
                    List<Todo> todos = todoService.getTodosByGoal(goal);
                    //TodoDTO리스트 생성
                    List<GoalResponseDTO.TodoDTO> todoDTOS = todos.stream().map(todo->
                            GoalResponseDTO.TodoDTO.builder()
                                    .todoId(todo.getId())
                                    .todoTitle(todo.getTitle())
                                    .date(todo.getDate())
                                    .time(todo.getTime())
                                    .isComplete(todo.getIsComplete())
                                    .build()).collect(Collectors.toList());
                    //GoalDTO생성
                    return GoalResponseDTO.GoalDTO.builder()
                            .goalId(goal.getGoalId())
                            .goalTitle(goal.getGoalTitle())
                            .todoList(todoDTOS)
                            .build();
                }
        ).collect(Collectors.toList());

        return GoalResponseDTO.GetGoalsTodosByPotResultDTO.builder()
                .goalList(goalDTOS)
                .build();
    }

    @Override
    public List<Goal> findAllByPot(Pot pot) {
        return goalRepository.findAllByPot(pot).get();
    }

    @Override
    public Optional<Goal> getGoalById(Long goalId){return goalRepository.findById(goalId);}

}

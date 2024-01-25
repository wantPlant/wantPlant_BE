package umc.wantPlant.goal.application;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import umc.wantPlant.goal.domain.Goal;
import umc.wantPlant.goal.domain.dto.GoalRequestDTO;
import umc.wantPlant.goal.repository.GoalRepository;
import umc.wantPlant.pot.application.PotQueryService;
import umc.wantPlant.pot.domain.Pot;
import umc.wantPlant.pot.domain.dto.PotRequestDTO;
import umc.wantPlant.todo.application.TodoService;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class GoalCommandServiceImpl implements GoalCommandService{
    private final GoalRepository goalRepository;
    private final PotQueryService potQueryService;
    private final TodoService todoService;
    @Override
    @Transactional
    public Goal createGoal(GoalRequestDTO.PostGoalDTO request) {
        Goal newGoal = Goal.builder()
                .goalTitle(request.getGoalTitle())
                .pot(potQueryService.getPotByPotId(request.getPotId()))
                .build();

        return goalRepository.save(newGoal);
    }

    @Override
    @Transactional //pot 생성에서 사용
    public void createGoalsTodos(Pot pot, List<PotRequestDTO.GoalDTO> request){
        List<Goal> goals = request.stream().map(goalDTO ->
                {
                    Goal newGoal = Goal.builder()
                            .goalTitle(goalDTO.getGoalTitle())
                            .pot(pot)
                            .build();
                    todoService.createTodos(newGoal, goalDTO.getTodoList());
                    return newGoal;
                }

        ).collect(Collectors.toList());;

        goalRepository.saveAll(goals);

    }

    @Override
    @Transactional
    public Goal createGoalTodo(GoalRequestDTO.PostGoalTodoDTO request) {
        Goal newGoal = Goal.builder()
                .goalTitle(request.getGoalTitle())
                .pot(potQueryService.getPotByPotId(request.getPotId()))
                .build();
        todoService.createTodo(newGoal, request.getTodo());

        return goalRepository.save(newGoal);
    }

    @Override
    public Goal modifyGoal(Long goalId, GoalRequestDTO.PatchGoalDTO request) {
        Goal goal = goalRepository.findById(goalId).get();
        goal.setGoalTitle(request.getGoalTitle());

        return goalRepository.save(goal);
    }

    @Override //gaol지우면서 todo같이 지우기
    public void deleteGoal(Long goalId) {
        Goal goal = goalRepository.findById(goalId).get();
        todoService.deleteTodosByGoal(goal);

        goalRepository.deleteById(goalId);
    }

    @Override
    public void deleteAllByPot(Pot pot) {
        goalRepository.deleteAllByPot(pot);
    }


}

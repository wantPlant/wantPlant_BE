package umc.wantPlant.goal.domain.dto;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public class GoalResponseDTO {
    @Getter
    @Builder
    public static class GetGoalsTodosByPotResultDTO{
        Long goalId;
        String goalTitle;
        List<TodoDTO> todoList;
    }

    @Getter
    @Builder
    public static class TodoDTO{
        Long todoId;
        String todoTitle;
        LocalDate startAt;
        LocalTime startTime;
        boolean isComplete;
    }
}

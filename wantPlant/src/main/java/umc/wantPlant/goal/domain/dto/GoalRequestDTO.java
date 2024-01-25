package umc.wantPlant.goal.domain.dto;

import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class GoalRequestDTO {
    @Getter
    public static class PostGoalDTO{
        Long potId;
        String goalTitle;
    }

    @Getter
    public static class PostGoalTodoDTO{
        Long potId;
        String goalTitle;
        TodoDTO todo;
    }
    @Getter
    public static class TodoDTO{
        String todoTitle;
        LocalDateTime startAt;
    }

    @Getter
    public static class PatchGoalDTO{
        String goalTitle;
    }
}

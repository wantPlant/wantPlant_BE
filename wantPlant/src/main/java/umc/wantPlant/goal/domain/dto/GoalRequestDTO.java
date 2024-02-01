package umc.wantPlant.goal.domain.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NonNull;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class GoalRequestDTO {
    @Getter
    public static class PostGoalDTO{
        @NotNull
        Long potId;
        @NotBlank(message = "목표 이름은 비어 있을 수 없습니다.")
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

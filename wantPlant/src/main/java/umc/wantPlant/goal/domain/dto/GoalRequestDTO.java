package umc.wantPlant.goal.domain.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

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
        @NotNull
        Long potId;
        @NotBlank(message = "목표 이름은 비어 있을 수 없습니다.")
        String goalTitle;
        TodoDTO todo;
    }
    @Getter
    public static class TodoDTO{
        @NotBlank(message = "todo 제목은 비어 있을 수 없습니다.")
        String todoTitle;
        LocalDateTime startAt;
    }

    @Getter
    public static class PatchGoalDTO{
        @NotBlank(message = "목표 이름은 비어 있을 수 없습니다.")
        String goalTitle;
    }
}

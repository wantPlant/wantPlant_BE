package umc.wantPlant.goal.domain.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

import java.time.LocalDate;
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
        @JsonFormat(pattern = "yyyy-MM-dd")
        private LocalDate date;
        @JsonFormat(pattern = "kk:mm")
        @Schema(example = "12:30")
        private LocalTime time;
    }

    @Getter
    public static class PatchGoalDTO{
        String goalTitle;
    }
}

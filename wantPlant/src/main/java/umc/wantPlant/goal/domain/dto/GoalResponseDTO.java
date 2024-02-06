package umc.wantPlant.goal.domain.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

public class GoalResponseDTO {
    //화분당 목표 투두 조회
    @Getter
    @Builder
    public static class GetGoalsTodosByPotResultDTO{
        List<GoalDTO> goalList;
    }
    @Getter
    @Builder
    public static class GoalDTO{
        Long goalId;
        String goalTitle;
        List<TodoDTO> todoList;
    }
    @Getter
    @Builder
    public static class TodoDTO{
        Long todoId;
        String todoTitle;
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
        LocalDate date;
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "kk:mm", timezone = "Asia/Seoul")
        LocalTime time;
        Boolean isComplete;
    }

    //화분&시간당 목표 todos 조회
    @Getter
    @Builder
    public static class GetGoalsTodosByDateAndPotResultDTO{
        List<GoalsByDateAndPot> goals;
    }
    @Getter
    @Builder
    public static class GoalsByDateAndPot{
        Long goalId;
        String goalTitle;
        List<TodosByDateAndPot> todos;
    }
    @Getter
    @Builder
    public static class TodosByDateAndPot{
        Long todoId;
        String todoTitle;
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
        LocalDate date;
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "kk:mm", timezone = "Asia/Seoul")
        LocalTime time;
        Boolean isComplete;
    }
}

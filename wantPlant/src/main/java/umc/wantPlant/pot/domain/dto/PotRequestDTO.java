package umc.wantPlant.pot.domain.dto;

import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class PotRequestDTO {
    @Getter
    public static class TempPotDTO{
        Long gardenId;
        String potName;
        String potType;
        String proceed;
    }

    //화분 생성
    @Getter
    public static class PostPotDTO{
        Long gardenId;
        String potName;
        String potType;
        String potTageColor;
        String proceed;
    }

    //화분, goal, todos 생성
    @Getter
    public static class PostPotGoalTodoDTO{
        Long gardenId;
        String potName;
        String potType;
        GoalsDTO goalList;
    }
    @Getter
    public static class GoalsDTO{
        String goalTitle;
        TodosDTO todoList;
    }
    @Getter
    public static class TodosDTO{
        String todoTitle;
        LocalDate startAt;
        LocalDateTime startTime;
        LocalDateTime endTime;
    }

    //화분 수정
    @Getter
    public static class PatchPotDTO{
        String potTitle;
    }


}

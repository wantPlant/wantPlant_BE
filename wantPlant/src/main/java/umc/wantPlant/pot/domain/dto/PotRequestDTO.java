package umc.wantPlant.pot.domain.dto;

import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class PotRequestDTO {
    @Getter
    public static class TempPotDTO{
        Long gardenId;
        String potName;
        String potType;
        LocalDate startAt;
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
        List<GoalsDTO> goalList;
    }
    @Getter
    public static class GoalsDTO{
        String goalTitle;
        List<TodosDTO> todoList;
    }
    @Getter
    public static class TodosDTO{
        String todoTitle;
        LocalDate startAt;
        String startTime;
        String endTime;
    }

    //화분 수정
    @Getter
    public static class PatchPotDTO{
        String potTitle;
    }


}

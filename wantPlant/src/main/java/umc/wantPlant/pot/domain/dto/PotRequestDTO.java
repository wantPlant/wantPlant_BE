package umc.wantPlant.pot.domain.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NonNull;
import umc.wantPlant.pot.domain.enums.PotTagColor;
import umc.wantPlant.pot.domain.enums.PotType;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class PotRequestDTO {
    @Getter
    public static class TempPotDTO{
        Long gardenId;
        String potName;
        @NotNull(message = "유효하지 않은 화분 타입이 입력되었습니다.")
        PotType potType;
        LocalDate startAt;
    }

    //화분 생성
    @Getter
    public static class PostPotDTO{
        Long gardenId;
        String potName;
        @NotNull(message = "유효하지 않은 화분 타입이 입력되었습니다.")
        PotType potType;
        @NotNull(message = "유효하지 않은 화분 태그 컬러가 입력되었습니다.")
        PotTagColor potTageColor;
        LocalDate startAt;
    }

    //화분, goal, todos 생성
    @Getter
    public static class PostPotGoalTodoDTO{
        Long gardenId;
        String potName;
        @NotNull(message = "유효하지 않은 화분 타입이 입력되었습니다.")
        PotType potType;
        LocalDate startAt;
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
        String potName;
    }


}

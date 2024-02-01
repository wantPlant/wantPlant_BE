package umc.wantPlant.pot.domain.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NonNull;
import umc.wantPlant.pot.domain.enums.PotTagColor;
import umc.wantPlant.pot.domain.enums.PotType;
import umc.wantPlant.pot.validation.annotation.ExistGarden;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class PotRequestDTO {
    @Getter
    public static class TempPotDTO{
        @ExistGarden
        Long gardenId;
        @NotBlank(message = "화분 이름은 비어 있을 수 없습니다.")
        String potName;
        @NotNull(message = "유효하지 않은 화분 타입이 입력되었습니다.")
        PotType potType;
        LocalDate startAt;
    }

    //화분 생성
    @Getter
    public static class PostPotDTO{
        @ExistGarden
        Long gardenId;
        @NotBlank(message = "화분 이름은 비어 있을 수 없습니다.")
        String potName;
        @NotNull(message = "유효하지 않은 화분 태그 컬러가 입력되었습니다.")
        PotTagColor potTageColor;
        LocalDate startAt;
    }

    //화분, goal, todos 생성
    @Getter
    public static class PostPotGoalTodoDTO{
        @ExistGarden
        Long gardenId;
        @NotBlank(message = "화분 이름은 비어 있을 수 없습니다.")
        String potName;
        LocalDate startAt;
        List<GoalDTO> goalList;
    }
    @Getter
    public static class GoalDTO{
        @NotBlank(message = "목표 이름은 비어 있을 수 없습니다.")
        String goalTitle;
        List<TodoDTO> todoList;
    }
    @Getter
    public static class TodoDTO{
        @NotBlank(message = "todo 제목은 비어 있을 수 없습니다.")
        String todoTitle;
        LocalDateTime startAt;
    }

    //화분 수정
    @Getter
    public static class PatchPotDTO{
        @NotBlank(message = "화분 이름은 비어 있을 수 없습니다.")
        String potName;
    }


}

package umc.wantPlant.pot.domain.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NonNull;
import umc.wantPlant.pot.domain.enums.PotTagColor;
import umc.wantPlant.pot.domain.enums.PotType;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
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
        @NotNull(message = "유효하지 않은 화분 태그 컬러가 입력되었습니다.")
        PotTagColor potTageColor;
        LocalDate startAt;
    }

    //화분, goal, todos 생성
    @Getter
    public static class PostPotGoalTodoDTO{
        Long gardenId;
        String potName;
        LocalDate startAt;
        List<GoalDTO> goalList;
    }
    @Getter
    public static class GoalDTO{
        String goalTitle;
        List<TodoDTO> todoList;
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

    //화분 수정
    @Getter
    public static class PatchPotDTO{
        String potName;
    }


}

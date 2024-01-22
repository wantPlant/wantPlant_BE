package umc.wantPlant.pot.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class PotResponseDTO {
    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class TempPotResultDTO{
        Long potId;
        String potName;
    }


    //정원 당 화분 이름 조회
    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class GetPotNamesResultDTO{
        List<PotNameDTO> potNames;
    }
    public static class PotNameDTO{
        Long potId;
        String potName;
    }

    //정원당 화분 이미지 조회
    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class GetPotImagesResultDTO{
        List<PotImageDTO> potImages;
    }
    public static class PotImageDTO{
        Long potId;
        String potImageUrl;
    }

    //정원 당 화분 조회 paging
    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class GetPotsResultDTO{
        List<PotDTO> pots;
    }
    public static class PotDTO{
        Long potId;
        String potName;
        LocalDate startAt;
        int proceed;
        String potImageUrl;
    }

    //웹: 날짜별 화분&todos 조회
    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class GetCategoryPotTodoPerDateDTO{
        List<CategoryDTO> categories;
    }
    public static class CategoryDTO{
        String category;
        List<PotDTO> pots;
    }
    public static class potDTO{
        Long potId;
        String potName;
        String potTagColor;
        List<TodoDTO> todos;
    }
    public static class TodoDTO{
        Long todoId;
        String todoTitle;
        boolean isComplete;
    }

    //화분 상세 조회
    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class GetPotDetailResultDTO{
        String gardenName;
        Long potId;
        String potName;
        int proceed;
        String potImageUrl;
    }

    //완료한 화분 리스트 조회
    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class GetCompletedPotsResultDTO{
        List<PotCompleteDTO> pots;
    }
    public static class PotCompleteDTO{
        String potTitle;
        String potImageUrl;
        LocalDate startAt;
        LocalDate completeAt;
    }

}

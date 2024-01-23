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
    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
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
    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
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
        Integer listSize;
        Integer totalPage;
        Long totalElements;
        Boolean isFirst;
        Boolean isLast;
    }
    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class PotDTO{
        Long potId;
        String potName;
        int proceed;
        String potImageUrl;
        LocalDate startAt;
    }

    //웹: 날짜별 화분&todos 조회
    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class GetCategoryPotTodoPerDateDTO{
        List<CategoryDTO> categories;
    }
    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class CategoryDTO{
        String category;
        List<TodoPerDateDTO> todos;
    }
    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class TodoPerDateDTO{
        Long potId;
        String potName;
        String potTagColor;
        Long todoId;
        String todoTitle;
        boolean isComplete;
    }
//    @Builder
//    public static class CategoryDTO{
//        String category;
//        List<PotPerDateDTO> pots;
//    }
//    @Builder
//    public static class PotPerDateDTO{
//        Long potId;
//        String potName;
//        String potTagColor;
//        List<TodoPerDateDTO> todos;
//    }
//    @Builder
//    public static class TodoPerDateDTO{
//        Long todoId;
//        String todoTitle;
//        boolean isComplete;
//    }

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
    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class PotCompleteDTO{
        String potName;
        String potImageUrl;
        LocalDate startAt;
        LocalDate completeAt;
    }

}

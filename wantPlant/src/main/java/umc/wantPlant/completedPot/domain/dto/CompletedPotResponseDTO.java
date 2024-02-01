package umc.wantPlant.completedPot.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import umc.wantPlant.garden.domain.enums.GardenCategories;

import java.time.LocalDate;
import java.util.List;

public class CompletedPotResponseDTO {
    //앱 : 완료한 화분 리스트 조회
    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class GetCompletedPotsResultDTO{
        List<CompletedPotDTO> pots;
    }
    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class CompletedPotDTO{
        String potName;
        String potImageUrl;
        LocalDate startAt;
        LocalDate completeAt;
    }

    //웹 : 완료한 화분 리스트 조회
    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class GetCompletedPotsForWebResultDTO{
        List<CompletedPotForWebDTO> pots;
    }
    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class CompletedPotForWebDTO{
        GardenCategories gardenCategory;
        String potName;
        String GardenName;
        LocalDate startAt;
        LocalDate completedAt;
        String potImgUrl;
        String todoTitle1;
        String todoTitle2;
    }
}

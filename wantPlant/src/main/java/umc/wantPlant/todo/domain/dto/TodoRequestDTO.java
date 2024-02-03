package umc.wantPlant.todo.domain.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;


public class TodoRequestDTO {
    @Getter
    @RequiredArgsConstructor
    public static class TodoCreateDTO {
        private Long goalID;
        private String title;
        @JsonFormat(pattern = "yyyy-MM-dd")
        private LocalDate date;
        @JsonFormat(pattern = "kk:mm")
        @Schema(example = "12:30")
        private LocalTime time;
    }
    @Getter
    @RequiredArgsConstructor
    public static class TodoUpdateDTO {
        private String title;
        @JsonFormat(pattern = "yyyy-MM-dd")
        private LocalDate date;
        @JsonFormat(pattern = "kk:mm")
        @Schema(example = "12:30")
        private LocalTime time;
    }
    @Getter
    @RequiredArgsConstructor
    public static class TodoUpdateTitleDTO {
        private String title;
    }
    @Getter
    @RequiredArgsConstructor
    public static class TodoUpdateTimeDTO {
        @DateTimeFormat(pattern = "kk:mm")
        private LocalTime time;
    }

    @Getter
    @RequiredArgsConstructor
    public static class TodoUpdateDateDTO {
        @DateTimeFormat(pattern = "yyyy-MM-dd")
        private LocalDate date;
    }

    @Getter
    @RequiredArgsConstructor
    public static class TodoUpdateCompleteDTO{
        private Boolean isComplete;
    }
}



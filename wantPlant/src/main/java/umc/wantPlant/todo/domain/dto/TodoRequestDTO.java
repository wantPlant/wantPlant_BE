package umc.wantPlant.todo.domain.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;


public class TodoRequestDTO {
    @Getter
    @Setter
    @RequiredArgsConstructor
    public static class TodoCreateDTO {
        private String title;
        @JsonFormat(pattern = "yyyy-MM-dd")
        private LocalDate date;
        @JsonFormat(pattern = "HH:mm")
        private LocalTime startTime;
        @JsonFormat(pattern = "HH:mm")
        private LocalTime endTime;
        @ColumnDefault("false")
        private Boolean isComplete;
    }

//    @Getter
//    @Setter
//    @RequiredArgsConstructor
//    public static class TodoCheckDTO{
//        private Boolean isComplete;
//    }
}



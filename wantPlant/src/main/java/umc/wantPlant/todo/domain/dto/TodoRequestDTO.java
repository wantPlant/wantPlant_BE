package umc.wantPlant.todo.domain.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;


public class TodoRequestDTO {
    @Getter
    @Setter
    @RequiredArgsConstructor
    public static class TodoCreateDTO {
        private String title;
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
        private LocalDateTime startTime;
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
        private LocalDateTime endTime;
        private Boolean isComplete;
    }

//    @Getter
//    @Setter
//    @RequiredArgsConstructor
//    public static class TodoCheckDTO{
//        private Boolean isComplete;
//    }
}



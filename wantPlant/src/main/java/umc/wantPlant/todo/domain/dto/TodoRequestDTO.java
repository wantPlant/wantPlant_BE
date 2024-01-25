package umc.wantPlant.todo.domain.dto;


import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;



public class TodoRequestDTO {
    @Getter
    @Setter
    @RequiredArgsConstructor
    public static class TodoCreateDTO {
        private String title;
        @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
        private LocalDateTime startTime;
    }
    @Getter
    @Setter
    @RequiredArgsConstructor
    public static class TodoUpdateTitleDTO {
        private String title;
    }
    @Getter
    @Setter
    @RequiredArgsConstructor
    public static class TodoUpdateTimeDTO {
        @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
        private LocalDateTime startTime;
    }

    @Getter
    @Setter
    @RequiredArgsConstructor
    public static class TodoUpdateCompleteDTO{
        private Boolean isComplete;
    }
}



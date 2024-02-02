package umc.wantPlant.todo.domain.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;
import umc.wantPlant.garden.domain.dto.GardenResponseDTO;
import umc.wantPlant.pot.domain.Pot;
import umc.wantPlant.todo.domain.Todo;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;


public class TodoResponseDTO {

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class TodoResultDTO {
        Long id;
        String title;
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
        LocalDate date;
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "kk:mm", timezone = "Asia/Seoul")
        LocalTime time;
        Boolean isComplete;
    }

    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    @Getter
    public static class TodoListDTO {
        List<TodoResponseDTO.TodoResultDTO> todoList;
    }

}

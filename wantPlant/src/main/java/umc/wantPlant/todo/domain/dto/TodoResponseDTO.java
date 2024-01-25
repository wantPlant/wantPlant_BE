package umc.wantPlant.todo.domain.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Getter;
import umc.wantPlant.todo.domain.Todo;
import java.time.LocalDateTime;

@Getter
public class TodoResponseDTO {
    private final Long id;
    private final String title;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm", timezone = "Asia/Seoul")
    private final LocalDateTime startTime;
    private final Boolean isComplete;

    @Builder
    public TodoResponseDTO(Long id, String title, LocalDateTime startTime, Boolean isComplete) {
        this.id = id;
        this.title = title;
        this.startTime = startTime;
        this.isComplete = isComplete;
    }

    public static TodoResponseDTO of(Todo todo){
        return TodoResponseDTO.builder()
                .id(todo.getId())
                .title(todo.getTitle())
                .startTime(todo.getStartTime())
                .isComplete(todo.getIsComplete())
                .build();
    }


}

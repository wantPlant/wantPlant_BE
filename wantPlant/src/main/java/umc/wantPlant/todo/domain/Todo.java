package umc.wantPlant.todo.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import umc.wantPlant.goal.domain.Goal;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Todo {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private LocalDateTime startTime;
    @ColumnDefault("false")
    private Boolean isComplete;

    public void updateTodoDetail(String title,LocalDateTime startTime, Boolean isComplete) {
        this.title = title;
        this.startTime = startTime;
        this.isComplete = isComplete;
    }

//    public void updateTodoCheck(Boolean isComplete){
//        this.isComplete = isComplete;
//    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="GOAL_ID")
    Goal goal;
}

package umc.wantPlant.todo.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import umc.wantPlant.goal.domain.Goal;
import java.time.LocalDate;
import java.time.LocalDateTime;


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

    public void updateTodoDetail(String title,LocalDateTime startTime) {
        this.title = title;
        this.startTime = startTime;
    }

    public void updateTodoTitle(String title){this.title = title;}
    public void updateTodoTime(LocalDateTime startTime){
        this.startTime = startTime;
    }
    public void updateTodoComplete(Boolean isComplete){
        this.isComplete = isComplete;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="GOAL_ID")
    Goal goal;
}

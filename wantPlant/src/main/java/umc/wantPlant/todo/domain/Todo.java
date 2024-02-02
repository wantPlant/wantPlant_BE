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
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate date;
    @JsonFormat(pattern = "kk:mm")
    private LocalTime time;
    @ColumnDefault("false")
    private Boolean isComplete;
    @JsonFormat(pattern = "yyyy-MM-dd'T'kk:mm")
    private LocalDateTime startAt;

    public void updateTodoDetail(String title,LocalDate date, LocalTime time) {
        this.title = title;
        this.date = date;
        this.time = time;
        this.startAt = LocalDateTime.of(date, time);
    }

    public void updateTodoTitle(String title){this.title = title;}
    public void updateTodoDate(LocalDate date){
        this.date = date;
        this.startAt = LocalDateTime.of(date, this.time);
    }
    public void updateTodoTime(LocalTime time){
        this.time = time;
        this.startAt = LocalDateTime.of(this.date, time);

    }
    public void updateTodoComplete(Boolean isComplete){
        this.isComplete = isComplete;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="GOAL_ID")
    Goal goal;
}

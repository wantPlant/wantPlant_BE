package umc.wantPlant.todo.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import java.time.LocalDate;
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
    private LocalDate date;
    private LocalTime startTime;
    private LocalTime endTime;
    @ColumnDefault("false")
    private Boolean isComplete;

    public void updateTodoDetail(String title, LocalDate date, LocalTime startTime, LocalTime endTime, Boolean isComplete) {
        this.title = title;
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
        this.isComplete = isComplete;
    }

//    public void updateTodoCheck(Boolean isComplete){
//        this.isComplete = isComplete;
//    }

//    @ManyToOne
//    @JoinColumn(name="GOAL_ID")
//    Goal goal;
}

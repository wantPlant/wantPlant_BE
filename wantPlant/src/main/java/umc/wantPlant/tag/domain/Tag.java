package umc.wantPlant.tag.domain;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Tag {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime startDate;

    @Enumerated(EnumType.STRING)
    private TagColor tagColor;

    @NonNull
    private String tagName;

    @Builder
    public Tag(TagColor tagColor, @NonNull String tagName, LocalDateTime startDate) {
        this.tagColor = tagColor;
        this.tagName = tagName;
        this.startDate = startDate;
    }

    public int[] getDate(){
        int year = startDate.getYear();
        int month = startDate.getMonthValue();
        int day = startDate.getDayOfMonth();

        return new int[]{year, month, day};
    }

    public int[] getTime(){
        int hour = startDate.getHour();
        int minute = startDate.getMinute();
        int second = startDate.getSecond();

        return new int[]{hour, minute, second};
    }
}

package umc.wantPlant.tag.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.*;
import umc.wantPlant.tag.domain.dto.request.TagUpdateRequestDto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Tag {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate date;

    @JsonFormat(pattern = "kk:mm")
    private LocalTime tagTime;

    @Enumerated(EnumType.STRING)
    private TagColor tagColor;

    @NonNull
    private String tagName;

    @Builder
    public Tag(Long id, LocalDate date, LocalTime tagTime, TagColor tagColor, @NonNull String tagName) {
        this.id = id;
        this.date = date;
        this.tagTime = tagTime;
        this.tagColor = tagColor;
        this.tagName = tagName;
    }
}

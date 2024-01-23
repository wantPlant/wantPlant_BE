package umc.wantPlant.tag.domain;

import jakarta.persistence.*;
import lombok.*;
import umc.wantPlant.tag.domain.dto.request.TagUpdateRequestDto;

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
    public Tag(Long id, TagColor tagColor, @NonNull String tagName, LocalDateTime startDate) {
        this.id = id;
        this.tagColor = tagColor;
        this.tagName = tagName;
        this.startDate = startDate;
    }

}

package umc.wantPlant.tag.domain.dto.request;

import lombok.Getter;
import org.springframework.format.annotation.DateTimeFormat;
import umc.wantPlant.tag.domain.Tag;
import umc.wantPlant.tag.domain.TagColor;

import java.time.LocalDateTime;

@Getter
public class TagUpdateRequestDto {

    private Long tagId;

    private TagColor tagColor;

    private String tagName;

    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private LocalDateTime startDate;

    public Tag toEntity(){
        return Tag.builder()
                .id(tagId)
                .startDate(startDate)
                .tagColor(tagColor)
                .tagName(tagName)
                .build();
    }
}

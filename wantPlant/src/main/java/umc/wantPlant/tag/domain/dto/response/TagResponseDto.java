package umc.wantPlant.tag.domain.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import umc.wantPlant.tag.domain.Tag;
import umc.wantPlant.tag.domain.TagColor;

import java.time.LocalDateTime;

@Getter
public class TagResponseDto {

    private final Long id;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss", timezone = "Asia/Seoul")
    private final LocalDateTime startDate;

    private final TagColor tagColor;

    private final String tagName;

    @Builder
    public TagResponseDto(Long id, LocalDateTime startDate, TagColor tagColor, String tagName) {
        this.id = id;
        this.startDate = startDate;
        this.tagColor = tagColor;
        this.tagName = tagName;
    }

    public static TagResponseDto of(Tag tag){
        return TagResponseDto.builder()
                .id(tag.getId())
                .tagName(tag.getTagName())
                .startDate(tag.getStartDate())
                .tagColor(tag.getTagColor())
                .build();
    }
}

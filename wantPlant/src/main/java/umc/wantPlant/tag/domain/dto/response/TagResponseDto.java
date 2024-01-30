package umc.wantPlant.tag.domain.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Getter;
import umc.wantPlant.tag.domain.Tag;
import umc.wantPlant.tag.domain.TagColor;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
public class TagResponseDto {

    private final Long id;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "kk:mm", timezone = "Asia/Seoul")
    private final LocalTime tagTime;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
    private final LocalDate date;

    private final TagColor tagColor;

    private final String tagName;

    @Builder
    public TagResponseDto(Long id, LocalTime tagTime, LocalDate date, TagColor tagColor, String tagName) {
        this.id = id;
        this.tagTime = tagTime;
        this.date = date;
        this.tagColor = tagColor;
        this.tagName = tagName;
    }

    public static TagResponseDto of(Tag tag){
        return TagResponseDto.builder()
                .id(tag.getId())
                .tagName(tag.getTagName())
                .tagTime(tag.getTagTime())
                .date(tag.getDate())
                .tagColor(tag.getTagColor())
                .build();
    }
}

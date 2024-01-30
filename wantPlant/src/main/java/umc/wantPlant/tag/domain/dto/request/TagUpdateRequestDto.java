package umc.wantPlant.tag.domain.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import org.springframework.format.annotation.DateTimeFormat;
import umc.wantPlant.tag.domain.Tag;
import umc.wantPlant.tag.domain.TagColor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Getter
public class TagUpdateRequestDto {

    private Long tagId;

    private TagColor tagColor;

    private String tagName;

    @JsonFormat(pattern = "HH:mm")
    @DateTimeFormat(iso = DateTimeFormat.ISO.TIME)
    private LocalTime tagTime;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate date;

    public Tag toEntity(){
        return Tag.builder()
                .id(tagId)
                .tagTime(tagTime)
                .date(date)
                .tagColor(tagColor)
                .tagName(tagName)
                .build();
    }
}

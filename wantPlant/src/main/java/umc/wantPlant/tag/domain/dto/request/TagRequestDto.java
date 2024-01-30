package umc.wantPlant.tag.domain.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import org.springframework.format.annotation.DateTimeFormat;
import umc.wantPlant.tag.domain.Tag;
import umc.wantPlant.tag.domain.TagColor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;


@Getter
public class TagRequestDto {

    private TagColor tagColor;

    private String tagName;


    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern = "HH:mm")
    @Schema(example = "12:30")
    private LocalTime tagTime;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate date;

    public Tag toEntity(){
        return Tag.builder()
                .tagTime(tagTime)
                .date(date)
                .tagColor(tagColor)
                .tagName(tagName)
                .build();
    }

}

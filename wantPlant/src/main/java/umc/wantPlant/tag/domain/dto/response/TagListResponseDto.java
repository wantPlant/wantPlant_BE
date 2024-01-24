package umc.wantPlant.tag.domain.dto.response;

import lombok.Getter;

import java.util.List;

@Getter
public class TagListResponseDto {

    private List<TagResponseDto> tagResponseDtos;

    public TagListResponseDto(List<TagResponseDto> tagResponseDtos) {
        this.tagResponseDtos = tagResponseDtos;
    }

    public static TagListResponseDto of(List<TagResponseDto> tagResponseDtos){
        return new TagListResponseDto(tagResponseDtos);
    }
}

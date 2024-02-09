package umc.wantPlant.tag.application;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import umc.wantPlant.apipayload.code.status.ErrorStatus;
import umc.wantPlant.apipayload.exceptions.handler.TagHandler;
import umc.wantPlant.tag.domain.Tag;
import umc.wantPlant.tag.domain.dto.request.TagRequestDto;
import umc.wantPlant.tag.domain.dto.request.TagUpdateRequestDto;
import umc.wantPlant.tag.domain.dto.response.TagResponseDto;
import umc.wantPlant.tag.repository.TagRepository;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class TagService {

    private final TagRepository tagRepository;

    public TagResponseDto addTag(TagRequestDto tagRequestDto) {
        Tag savedTag = tagRepository.save(tagRequestDto.toEntity());
        return TagResponseDto.of(savedTag);
    }

    public TagResponseDto getTag(Long tagId) {
        return TagResponseDto.of(tagRepository.findById(tagId)
                .orElseThrow(()->new TagHandler(ErrorStatus.TAG_NOT_FOUNT)));
    }

    public List<TagResponseDto> getTagByMonth(int year, int month) {
        List<Tag> monthlyTag = tagRepository.findTagByMonth(year, month);
        return monthlyTag.stream().map(TagResponseDto::of).toList();
    }

    public List<TagResponseDto> getTagByDay(int year, int month, int day) {
        List<Tag> dailyTag = tagRepository.findTagByDay(year, month, day);
        return dailyTag.stream().map(TagResponseDto::of).toList();
    }

    public void deleteTag(Long tagId) {
        tagRepository.delete(tagRepository.findById(tagId)
                .orElseThrow(()->new TagHandler(ErrorStatus.TAG_NOT_FOUNT)));
    }

    public TagResponseDto updateTag(TagUpdateRequestDto tagUpdateRequestDto) throws Exception {
        tagRepository.findById(tagUpdateRequestDto.getTagId()).
                orElseThrow(()->new TagHandler(ErrorStatus.TAG_NOT_FOUNT));
        return TagResponseDto.of(tagRepository.save(tagUpdateRequestDto.toEntity()));
    }
}

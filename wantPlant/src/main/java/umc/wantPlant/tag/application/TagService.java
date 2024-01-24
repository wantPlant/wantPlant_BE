package umc.wantPlant.tag.application;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
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
        return TagResponseDto.of(tagRepository.save(tagRequestDto.toEntity()));
    }

    public TagResponseDto getTag(Long tagId) {
        return TagResponseDto.of(tagRepository.findById(tagId).orElseThrow());
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
        tagRepository.delete(tagRepository.findById(tagId).orElseThrow());
    }

    public TagResponseDto updateTag(TagUpdateRequestDto tagUpdateRequestDto) throws Exception {
        tagRepository.findById(tagUpdateRequestDto.getTagId()).
                orElseThrow(()->new Exception("일치하는 태그를 찾을 수 없습니다."));
        return TagResponseDto.of(tagRepository.save(tagUpdateRequestDto.toEntity()));
    }
}

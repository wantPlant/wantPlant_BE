package umc.wantPlant.tag.application;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import umc.wantPlant.tag.domain.Tag;
import umc.wantPlant.tag.domain.dto.request.TagSaveRequestDto;
import umc.wantPlant.tag.domain.dto.response.TagResponseDto;
import umc.wantPlant.tag.repository.TagRepository;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class TagService {

    private final TagRepository tagRepository;

    public TagResponseDto addTag(TagSaveRequestDto tagSaveRequestDto) {
        return TagResponseDto.of(tagRepository.save(tagSaveRequestDto.toEntity()));
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
}

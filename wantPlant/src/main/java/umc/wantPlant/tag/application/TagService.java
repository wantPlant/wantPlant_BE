package umc.wantPlant.tag.application;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import umc.wantPlant.tag.domain.Tag;
import umc.wantPlant.tag.domain.dto.request.TagSaveRequestDto;
import umc.wantPlant.tag.domain.dto.response.TagResponseDto;
import umc.wantPlant.tag.repository.TagRepository;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class TagService {

    private final TagRepository tagRepository;

    public void addTag(TagSaveRequestDto tagSaveRequestDto) {
        tagRepository.save(tagSaveRequestDto.toEntity());
    }

    public TagResponseDto getTag(Long tagId) {
        return TagResponseDto.of(tagRepository.findById(tagId).orElseThrow());
    }

    public List<TagResponseDto> getMonthlyTag(int month) {
        return null;
    }
}

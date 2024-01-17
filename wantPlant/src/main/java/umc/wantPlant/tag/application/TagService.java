package umc.wantPlant.tag.application;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import umc.wantPlant.tag.domain.Tag;
import umc.wantPlant.tag.repository.TagRepository;

@Service
@RequiredArgsConstructor
public class TagService {

    TagRepository tagRepository;

    public void addTag(Tag tag) {
        tagRepository.save(tag);
    }
}

package umc.wantPlant.tag.ui;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import umc.wantPlant.tag.application.TagService;
import umc.wantPlant.tag.domain.Tag;

@RestController
@RequestMapping("/api/tag")
@RequiredArgsConstructor
public class TagController {

    TagService tagService;

    @PostMapping("/add")
    public void addTag(@RequestBody  Tag tag){
        tagService.addTag(tag);
    }
}

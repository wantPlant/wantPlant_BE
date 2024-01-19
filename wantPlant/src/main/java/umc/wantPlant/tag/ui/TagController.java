package umc.wantPlant.tag.ui;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import umc.wantPlant.tag.application.TagService;
import umc.wantPlant.tag.domain.dto.request.TagSaveRequestDto;
import umc.wantPlant.tag.domain.dto.response.TagResponseDto;

import java.util.List;

@RestController
@RequestMapping("/api/tag")
@RequiredArgsConstructor
public class TagController {

    private final TagService tagService;

    @PostMapping("/add")
    public void addTag(@RequestBody TagSaveRequestDto tagSaveRequestDto){
        tagService.addTag(tagSaveRequestDto);
    }

    @GetMapping("/{tagId}")
    public TagResponseDto getTag(@PathVariable Long tagId){
        return tagService.getTag(tagId);
    }

    @GetMapping("/")
    public List<TagResponseDto> getMonthlyTag(
            @RequestParam(value="month") int month
    ){
        return tagService.getMonthlyTag(month);
    }
}

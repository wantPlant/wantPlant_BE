package umc.wantPlant.tag.ui;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import umc.wantPlant.tag.application.TagService;
import umc.wantPlant.tag.domain.Tag;
import umc.wantPlant.tag.domain.dto.request.TagSaveRequestDto;
import umc.wantPlant.tag.domain.dto.response.TagResponseDto;

import java.util.List;

@RestController
@RequestMapping("/api/tag")
@RequiredArgsConstructor
public class TagController {

    private final TagService tagService;

    @PostMapping("/add")
    @Operation(summary = "태그 생성 API")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200", description = "OK, 성공"),})
    public TagResponseDto addTag(@RequestBody TagSaveRequestDto tagSaveRequestDto){
        return tagService.addTag(tagSaveRequestDto);
    }

    @GetMapping("/{tagId}")
    @Operation(summary = "특정 태그를 ID로 조회")
    public TagResponseDto getTag(@PathVariable Long tagId){
        return tagService.getTag(tagId);
    }

    @GetMapping("/month")
    @Operation(summary = "특정 월에 있는 태그들 조회")
    public List<TagResponseDto> getMonthlyTag(@RequestParam(value="year") int year, @RequestParam(value="month") int month){
        return tagService.getTagByMonth(year, month);
    }

    @GetMapping("/day")
    @Operation(summary = "특정 일에 있는 태그들 조회")
    public List<TagResponseDto> getDailyTag(
            @RequestParam(value="year") int year,
            @RequestParam(value="month") int month,
            @RequestParam(value="day") int day){
        return tagService.getTagByDay(year, month, day);
    }
}

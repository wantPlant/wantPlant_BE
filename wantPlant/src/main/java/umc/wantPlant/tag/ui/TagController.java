package umc.wantPlant.tag.ui;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import umc.wantPlant.apipayload.ApiResponse;
import umc.wantPlant.tag.application.TagService;
import umc.wantPlant.tag.domain.dto.request.TagRequestDto;
import umc.wantPlant.tag.domain.dto.request.TagUpdateRequestDto;
import umc.wantPlant.tag.domain.dto.response.TagListResponseDto;
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
    public ApiResponse<TagResponseDto> addTag(@RequestBody TagRequestDto tagRequestDto){
        return ApiResponse.onSuccess(tagService.addTag(tagRequestDto));
    }

    @GetMapping("/{tagId}")
    @Operation(summary = "특정 태그를 ID로 조회")
    public ApiResponse<TagResponseDto> getTag(@PathVariable Long tagId){
        return ApiResponse.onSuccess(tagService.getTag(tagId));
    }

    @GetMapping("/month")
    @Operation(summary = "특정 월에 있는 태그들 조회")
    public ApiResponse<TagListResponseDto> getMonthlyTag(@RequestParam(value="year") int year, @RequestParam(value="month") int month){
        return ApiResponse.onSuccess(
                TagListResponseDto.of(
                        tagService.getTagByMonth(year, month)
                )
        );
    }

    @GetMapping("/day")
    @Operation(summary = "특정 일에 있는 태그들 조회")
    public ApiResponse<TagListResponseDto> getDailyTag(
            @RequestParam(value="year") int year,
            @RequestParam(value="month") int month,
            @RequestParam(value="day") int day){
        return ApiResponse.onSuccess(
                TagListResponseDto.of(
                        tagService.getTagByDay(year, month, day)
                )
        );
    }

    @DeleteMapping("/{tagId}")
    @Operation(summary = "특정 태그를 ID로 삭제")
    public void deleteTag(@PathVariable Long tagId){
        tagService.deleteTag(tagId);
    }

    @PatchMapping("/update")
    @Operation(summary = "특정 태그를 업데이트")
    public ApiResponse<TagResponseDto> updateTag(@RequestBody TagUpdateRequestDto tagUpdateRequestDto) throws Exception{
        return ApiResponse.onSuccess(tagService.updateTag(tagUpdateRequestDto));
    }
}

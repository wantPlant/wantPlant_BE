package umc.wantPlant.pot.ui;

import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.web.bind.annotation.*;
import umc.wantPlant.pot.apiPayload.ApiResponse;
import umc.wantPlant.pot.application.PotCommandService;
import umc.wantPlant.pot.application.PotQueryService;
import umc.wantPlant.pot.domain.dto.PotRequestDTO;
import umc.wantPlant.pot.domain.dto.PotResponseDTO;

import java.time.LocalDate;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/pots")
public class PotController {
//    private final PotQueryService potQueryService;
//    private final PotCommandService potCommandService;

    //화분 생성 API
    @PostMapping("")
    public ApiResponse<String> postPot(@RequestBody PotRequestDTO.PostPotDTO request){
        return null;
    }

    //화분, goal, todos 리스트 생성
    @PostMapping("/goals/todos")
    public ApiResponse<String> postPotGoalTodo(@RequestBody PotRequestDTO.PostPotGoalTodoDTO request){
        return null;
    }

    //정원당 화분 이름 조회 API
    @GetMapping("/names")
    public ApiResponse<PotResponseDTO.GetPotNamesResultDTO> getPotNames(
            @RequestParam(name = "gardenId") Long gardenId){
        return null;
    }

    //정원당 화분 이미지 조회 API
    @GetMapping("/images")
    public ApiResponse<PotResponseDTO.GetPotImagesResultDTO> getPotImages(
            @RequestParam(name = "gardenId") Long gardenId){
        return null;
    }

    //정원 당 화분 리스트 조회 API
    @GetMapping("/paging")
    public ApiResponse<PotResponseDTO.GetPotsResultDTO> getPots(
            @RequestParam(name = "gardenId") Long gardenId,
            @RequestParam(name = "page") int page){
        return null;
    }

    //날짜별 카테고리&화분&todos 리스트 조회
    @GetMapping("/todos/date")
    public ApiResponse<PotResponseDTO.GetCategoryPotTodoPerDateDTO> getCategoryPotTodoPerDate(
            @RequestParam(name = "date") LocalDate date
    ){
        return null;
    }

    //화분 상세 조회 API
    @GetMapping("/{potId}")
    public ApiResponse<PotResponseDTO.GetPotDetailResultDTO> getPotDetail(@PathVariable(name = "potId")Long potId){
        return null;
    }

    //완료한 화분 리스트 조회 API
    @GetMapping ("/completed")
    public ApiResponse<PotResponseDTO.GetCompletedPotsResultDTO> getCompletedPots(
            @RequestParam(name = "gardenId") Long gardenId
    ){
        return null;
    }

    //화분 수정 API
    @PatchMapping("/{potId}")
    public ApiResponse<String> patchPot(
            @PathVariable(name = "potId") Long potId,
            @RequestBody PotRequestDTO.PatchPotDTO request){
        return null;
    }

    //화분 삭제 API
    @DeleteMapping("/{potId}")
    public ApiResponse<String> deletePot(@PathVariable(name = "potId") Long potId){
        return null;
    }
}

package umc.wantPlant.member.ui;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import umc.wantPlant.apipayload.ApiResponse;
import umc.wantPlant.member.application.MemberService;
import umc.wantPlant.member.domain.dto.response.MemberInfoResponseDTO;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/members")
public class MemberController {
    private final MemberService memberService;


    @GetMapping("/{memberId}")
    @Operation(summary = "유저 정보 조회 API", description = "id를 통해 유저 정보를 조회하는 API")
    public ApiResponse<MemberInfoResponseDTO> getMemberInfo(@PathVariable Long memberId){
        return ApiResponse.onSuccess(memberService.getMemberInfo(memberId));
    }
}

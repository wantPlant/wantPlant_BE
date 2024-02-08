package umc.wantPlant.member.ui;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import umc.wantPlant.apipayload.ApiResponse;
import umc.wantPlant.config.security.oauth.CurrentMember;
import umc.wantPlant.member.application.MemberService;
import umc.wantPlant.member.domain.Member;
import umc.wantPlant.member.domain.dto.response.MemberInfoResponseDTO;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/members")
public class MemberController {
    private final MemberService memberService;


    @GetMapping
    @Operation(summary = "유저 정보 조회 API", description = "토큰을 통해 유저 정보를 조회하는 API")
    public ApiResponse<MemberInfoResponseDTO> getMemberInfo(@CurrentMember Member member){
        return ApiResponse.onSuccess(memberService.getMemberInfo(member.getId()));
    }
}

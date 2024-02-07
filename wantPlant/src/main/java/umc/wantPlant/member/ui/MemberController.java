package umc.wantPlant.member.ui;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import umc.wantPlant.apipayload.ApiResponse;
import umc.wantPlant.member.application.MemberService;
import umc.wantPlant.member.domain.dto.response.MemberInfoResponseDTO;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/members")
public class MemberController {
    private final MemberService memberService;
    @GetMapping
    public ApiResponse<MemberInfoResponseDTO> getMemberInfo(@RequestHeader(value = "Authorization")String accessToken){
        return ApiResponse.onSuccess(memberService.getMemberInfo(accessToken));
    }
}

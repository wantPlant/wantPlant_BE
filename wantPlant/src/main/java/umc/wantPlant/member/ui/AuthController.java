package umc.wantPlant.member.ui;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import umc.wantPlant.apipayload.ApiResponse;
import umc.wantPlant.config.security.oauth.CurrentMember;
import umc.wantPlant.member.application.AuthService;
import umc.wantPlant.member.domain.Member;
import umc.wantPlant.member.domain.RefreshToken;
import umc.wantPlant.member.domain.dto.request.MemberGenerateTokenRequestDTO;
import umc.wantPlant.member.domain.dto.response.MemberGenerateTokenResponseDTO;
import umc.wantPlant.member.domain.dto.response.MemberLoginResponseDTO;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/members")
public class AuthController {
    private final AuthService authService;

    @PostMapping("/login")
    public ApiResponse<MemberLoginResponseDTO> socialLogin(@RequestParam(value = "accessToken") String accessToken){
        return ApiResponse.onSuccess(authService.socialLogin(accessToken));
    }

    @PostMapping("/token")
    public ApiResponse<MemberGenerateTokenResponseDTO> regenerateToken(@RequestBody MemberGenerateTokenRequestDTO requestDTO){
        return ApiResponse.onSuccess(authService.generateNewAccessToken(requestDTO.getRefreshToken()));
    }

}
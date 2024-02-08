package umc.wantPlant.member.ui;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import umc.wantPlant.apipayload.ApiResponse;
import umc.wantPlant.config.security.oauth.CurrentMember;
import umc.wantPlant.member.application.AuthService;
import umc.wantPlant.member.domain.Member;
import umc.wantPlant.member.domain.dto.request.MemberGenerateTokenRequestDTO;
import umc.wantPlant.member.domain.dto.response.MemberGenerateTokenResponseDTO;
import umc.wantPlant.member.domain.dto.response.MemberLoginResponseDTO;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/members")
public class AuthController {
    private final AuthService authService;

    @PostMapping("/login")
    @Operation(summary = "카카오 소셜 로그인 API", description = "소셜 로그인을 수행하는 API")
    public ApiResponse<MemberLoginResponseDTO> socialLogin(@RequestParam(value = "accessToken") String accessToken){
        return ApiResponse.onSuccess(authService.socialLogin(accessToken));
    }

    @PostMapping("/token")
    @Operation(summary = "accessToken 재발급 API", description = "발급된 refreshToken으로 accessToken을 재발급 받는 API")
    public ApiResponse<MemberGenerateTokenResponseDTO> regenerateToken(@RequestBody MemberGenerateTokenRequestDTO requestDTO){
        return ApiResponse.onSuccess(authService.generateNewAccessToken(requestDTO.getRefreshToken()));
    }

    @PostMapping("/token/test")
    @Operation(summary = "테스트용 토큰 발급 API", description = "테스트용 토큰 발급 API")
    public ApiResponse<MemberLoginResponseDTO> generateTestToken(){
        return ApiResponse.onSuccess(authService.testToken());
    }

    @DeleteMapping("/logout")
    @Operation(summary = "로그아웃 API", description = "refreshToken을 삭제하는 API")
    public ApiResponse<String> logout(@CurrentMember Member member){
        return ApiResponse.onSuccess(authService.deleteRefreshToken(member));
    }
}

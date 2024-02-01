package umc.wantPlant.member.domain.dto.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class MemberLoginResponseDTO {
    private String accessToken;
    private String refreshToken;
}

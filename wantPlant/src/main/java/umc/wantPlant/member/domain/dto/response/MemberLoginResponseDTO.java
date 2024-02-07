package umc.wantPlant.member.domain.dto.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class MemberLoginResponseDTO {
    private Long id;
    private String accessToken;
    private String refreshToken;
}

package umc.wantPlant.member.client;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import umc.wantPlant.member.domain.Member;

import java.util.Map;

@Component
public class KakaoMemberClient {
    private final WebClient webClient;
    public KakaoMemberClient(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder
                .baseUrl("https://kapi.kakao.com/v2/user/me")
                .build();
    }

    public Member getkakaoInfo(final String accessToken){
         Map<String, Object> response = webClient.get()
                .header("Authorization", "Bearer " + accessToken)
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<Map<String, Object>>() {})
                .block();

         Map<String, Object> kakao_account = (Map<String, Object>) response.get("kakao_account");
         Map<String, Object> profile = (Map<String, Object>) kakao_account.get("profile");
         String nickname = (String) profile.get("nickname");
         String profileImage = (String) profile.get("profile_image_url");
         String email = (String) kakao_account.get("email");

        //Todo:Error handling
//        if(response == null)
//            throw new GeneralException()

        return Member.builder()
                .nickname(nickname)
                .email(email)
                .profileImage(profileImage)
                .build();
    }
}

package umc.wantPlant.config.security.oauth;

import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import umc.wantPlant.login.domain.Member;
import umc.wantPlant.login.repositiory.MemberRepository;

import java.util.Map;

@RequiredArgsConstructor
@Service
public class OAuth2UserCustomService extends DefaultOAuth2UserService {
    private final MemberRepository memberRepository;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException{
        OAuth2User member = super.loadUser(userRequest);
        saveOrUpdate(member);
        return member;
    }

    private Member saveOrUpdate(OAuth2User oAuth2User){
        Map<String, Object> attributes = oAuth2User.getAttributes();
//        String email = (String) attributes.get("email");
        String nickname = (String) attributes.get("profile_nickname");
        Member member = memberRepository.findByNickname(nickname)
                .map(entity -> entity.update(nickname))
                .orElse(Member.builder()
                        .email("test@email.com")
                        .nickname(nickname)
                        .build());
        return memberRepository.save(member);
    }

}

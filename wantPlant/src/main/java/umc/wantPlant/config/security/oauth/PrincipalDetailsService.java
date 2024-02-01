package umc.wantPlant.config.security.oauth;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import umc.wantPlant.apipayload.code.status.ErrorStatus;
import umc.wantPlant.apipayload.exceptions.GeneralException;
import umc.wantPlant.config.security.oauth.PrincipalDetails;
import umc.wantPlant.member.domain.Member;
import umc.wantPlant.member.repository.MemberRepository;

@Service
@RequiredArgsConstructor
public class PrincipalDetailsService implements UserDetailsService {
    private final MemberRepository memberRepository;

    @Override
    public PrincipalDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Member memberEntity = memberRepository.findById(Long.valueOf(username))
                .orElseThrow(() -> new GeneralException(ErrorStatus.LOGIN_GENERAL_ERROR));
        return new PrincipalDetails(memberEntity);
    }
}

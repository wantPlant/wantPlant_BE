package umc.wantPlant.member.application;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.wantPlant.member.domain.Member;
import umc.wantPlant.member.domain.dto.response.MemberInfoResponseDTO;
import umc.wantPlant.member.repository.MemberRepository;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;

    @Transactional
    public MemberInfoResponseDTO getMemberInfo(Long id){
        Member member = memberRepository.findById(id).orElseThrow(() -> new RuntimeException());

        return MemberInfoResponseDTO.builder()
                .id(id)
                .nickname(member.getNickname())
                .email(member.getEmail())
                .profileImage(member.getProfileImage())
                .build();
    }
}

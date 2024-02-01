package umc.wantPlant.member.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 40)
    private String nickname;

    @Column(nullable = false, length = 40)
    private String email;

    @Column(nullable = false, length = 100)
    private String profileImage;

    public Member update(String nickname, String profileImage){
        this.nickname = nickname;
        this.profileImage = profileImage;

        return this;
    }

}

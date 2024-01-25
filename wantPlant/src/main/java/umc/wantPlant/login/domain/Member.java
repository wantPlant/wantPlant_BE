package umc.wantPlant.login.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 40)
    private String nickname;

    @Column(nullable = false, length = 40)
    private String email;

    public Member update(String nickname) {
        this.nickname = nickname;

        return this;
    }
}

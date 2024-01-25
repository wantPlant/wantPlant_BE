package umc.wantPlant.goal.domain;

import jakarta.persistence.*;
import lombok.*;
import umc.wantPlant.pot.domain.Pot;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Goal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long goalId;

    @Column(nullable= false, length = 40)
    private String goalTitle;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pot_id")
    private Pot pot;

    public void setGoalTitle(String goalTitle){
        this.goalTitle =goalTitle;
    }
}

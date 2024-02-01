package umc.wantPlant.completedPot.domain;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import umc.wantPlant.garden.domain.Garden;
import umc.wantPlant.pot.domain.enums.PotTagColor;
import umc.wantPlant.pot.domain.enums.PotType;

import java.time.LocalDate;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class CompletedPot {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long completedPotId;

    @Column(unique = true) // 유니크 제약 조건 추가
    private Long potId;

    @Column(nullable = false, length = 9)
    private String potName;

    @Enumerated(EnumType.STRING)
    private PotType potType;

    @Column(nullable = false)
    private String potImageUrl;

    @Column(nullable = false)
    private LocalDate startAt;

    @CreatedDate
    private LocalDate completeAt;

    private String todoTitle1;
    private String todoTitle2;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "garden_id")
    private Garden garden;
}
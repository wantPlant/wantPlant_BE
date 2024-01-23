package umc.wantPlant.pot.domain;

import jakarta.persistence.*;
import lombok.*;
import umc.wantPlant.garden.domain.Garden;
import umc.wantPlant.pot.domain.enums.PotTagColor;
import umc.wantPlant.pot.domain.enums.PotType;

import java.time.LocalDate;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Pot {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long potId;

    @Column(nullable = false, length = 9)
    private String potName;

    @Enumerated(EnumType.STRING)
    private PotType potType;

    @Column(nullable = false)
    private int proceed;

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "VARCHAR(15) DEFAULT 'PURPLE'")
    private PotTagColor potTagColor;

    @Column(nullable = false)
    private String potImageUrl;

    @Column(nullable = false)
    private LocalDate startAt;

    private LocalDate completeAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "garden_id")
    private Garden garden;

    public void setPotName(String potName){
        this.potName = potName;
    }
}

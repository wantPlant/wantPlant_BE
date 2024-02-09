package umc.wantPlant.pot.domain;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "garden_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Garden garden;

    public void setPotName(String potName){
        this.potName = potName;
    }
    public void setProceed(int proceed){
        this.proceed = proceed;
    }
    public void updatePotProceed(boolean todoIsComplete){
        if(todoIsComplete){
            this.proceed +=1;
        }else{
            this.proceed -=1;
        }
    }
    public void setPotImgUrl(String imgUrl){
        this.potImageUrl = imgUrl;
    }

}

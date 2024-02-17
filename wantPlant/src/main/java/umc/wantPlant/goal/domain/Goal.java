package umc.wantPlant.goal.domain;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
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

	@Column(nullable = false, length = 40)
	private String goalTitle;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "pot_id")
	@OnDelete(action = OnDeleteAction.CASCADE)
	private Pot pot;

	public void setGoalTitle(String goalTitle) {
		this.goalTitle = goalTitle;
	}
}

package umc.wantPlant.goal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import umc.wantPlant.goal.domain.Goal;

public interface GoalRepository extends JpaRepository<Goal, Long> {
}

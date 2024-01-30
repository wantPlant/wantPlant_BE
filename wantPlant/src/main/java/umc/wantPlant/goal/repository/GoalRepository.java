package umc.wantPlant.goal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import umc.wantPlant.goal.domain.Goal;
import umc.wantPlant.pot.domain.Pot;

import java.util.List;
import java.util.Optional;

public interface GoalRepository extends JpaRepository<Goal, Long> {
    Optional<List<Goal>> findAllByPot(Pot pot);
    void deleteAllByPot(Pot pot);
}

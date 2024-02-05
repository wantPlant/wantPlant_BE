package umc.wantPlant.goal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import umc.wantPlant.goal.domain.Goal;
import umc.wantPlant.pot.domain.Pot;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface GoalRepository extends JpaRepository<Goal, Long> {
    Optional<List<Goal>> findAllByPot(Pot pot);
    @Query("select g from Goal g left join Pot p on g.pot.potId = p.potId"
        + " left join Todo t on t.goal.goalId = g.goalId"
        + " where t.date = :date and p.potId = :potId")
    Optional<List<Goal>> findAllByPotAndTodoDate(@Param("potId")Long potId
        , @Param("date")LocalDate date);
    void deleteAllByPot(Pot pot);
}

package umc.wantPlant.todo.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import umc.wantPlant.garden.domain.Garden;
import umc.wantPlant.goal.domain.Goal;
import umc.wantPlant.pot.domain.Pot;
import umc.wantPlant.todo.domain.Todo;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface TodoRepository extends JpaRepository <Todo,Long>{
    Optional<Todo> findById(Long id);

    Optional<List<Todo>> findAllByGoal(Goal goal);

    @Query("select t from Todo t where t.startAt >= :minTime and t.startAt <= :maxTime")
    Optional<List<Todo>> findAllByStartDate(@Param("minTime")LocalDateTime startDateMinTime
            , @Param("maxTime")LocalDateTime startDateMaxTime);

    @Query("select t.title from Todo t left join Goal g on t.goal.goalId = g.goalId" +
            "         left join Pot p on p.potId = g.pot.potId" +
            "         where p.potId = :potId" +
            "         order by t.startAt" +
            "         limit 2")
    Optional<List<String>> findFirstTwoTodoByPot(@Param("potId") Long potId);

    Optional<List<Todo>> findAllByDateAndGoal(LocalDate date, Goal goal);
}

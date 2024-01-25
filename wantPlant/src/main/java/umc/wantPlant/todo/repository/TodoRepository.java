package umc.wantPlant.todo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import umc.wantPlant.goal.domain.Goal;
import umc.wantPlant.todo.domain.Todo;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface TodoRepository extends JpaRepository <Todo,Long>{
    Optional<Todo> findById(Long id);
    Optional<List<Todo>> findAllByGoal(Goal goal);

    @Query("select t from Todo t where t.startTime >= :minTime and t.startTime <= :maxTime")
    Optional<List<Todo>> findAllByStartDate(@Param("minTime")LocalDateTime startDateMinTime
            , @Param("maxTime")LocalDateTime startDateMaxTime);
}

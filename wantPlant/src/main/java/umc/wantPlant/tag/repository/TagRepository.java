package umc.wantPlant.tag.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import umc.wantPlant.tag.domain.Tag;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface TagRepository extends JpaRepository<Tag, Long> {

    Optional<Tag> findById(Long id);

    @Query("SELECT t FROM Tag t WHERE YEAR(t.date) = :year AND MONTH(t.date) = :month")
    List<Tag> findTagByMonth(@Param("year") int year, @Param("month") int month);

    @Query("SELECT t FROM Tag t WHERE YEAR(t.date) = :year AND MONTH(t.date) = :month AND DAY(t.date) = :day")
    List<Tag> findTagByDay(@Param("year") int year, @Param("month") int month, @Param("day") int day);
}

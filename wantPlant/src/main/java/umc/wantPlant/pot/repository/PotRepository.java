package umc.wantPlant.pot.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import umc.wantPlant.garden.domain.Garden;
import umc.wantPlant.pot.domain.Pot;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface PotRepository extends JpaRepository<Pot, Long> {
    Optional<List<Pot>> findAllByGarden(Garden garden);
    Page<Pot> findAllByGarden(Garden garden, PageRequest page);
    Optional<List<Pot>> findAllByStartAt(LocalDate startAt);
    Optional<Pot> findByPotId(Long potId);
//    @Query("select p from Pot p where p.garden = :garden and p.completeAt is not null")
//    Optional<List<Pot>> findAllCompletePotsByGarden(@Param("garden")Garden garden);
//
//    @Query("select p from Pot p where p.completeAt is not null")
//    Optional<List<Pot>> findAllByCompletedAt();
    void deleteByPotId(Long potId);
}

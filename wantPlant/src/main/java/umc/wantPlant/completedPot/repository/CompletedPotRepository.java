package umc.wantPlant.completedPot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import umc.wantPlant.completedPot.domain.CompletedPot;
import umc.wantPlant.garden.domain.Garden;

import java.util.List;
import java.util.Optional;

public interface CompletedPotRepository extends JpaRepository<CompletedPot, Long> {

    Optional<List<CompletedPot>> findAllByGarden(Garden garden);
    @Query("select count(p) from CompletedPot p where p.potId = :potId")
    int isPotIdUnique(Long potId);

    void deleteByPotId(Long potId);
}

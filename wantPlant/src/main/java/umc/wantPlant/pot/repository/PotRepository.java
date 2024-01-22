package umc.wantPlant.pot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import umc.wantPlant.pot.domain.Pot;

public interface PotRepository extends JpaRepository<Pot, Long> {

}

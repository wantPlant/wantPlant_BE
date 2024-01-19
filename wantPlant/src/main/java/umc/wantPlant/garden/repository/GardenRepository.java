package umc.wantPlant.garden.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import umc.wantPlant.garden.domain.Garden;

public interface GardenRepository extends JpaRepository<Garden, Long> {
}

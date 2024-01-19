package umc.wantPlant.tag.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import umc.wantPlant.tag.domain.Tag;

import java.util.Optional;

public interface TagRepository extends JpaRepository<Tag, Long> {

    Optional<Tag> findById(Long id);
}

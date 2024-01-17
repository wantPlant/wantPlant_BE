package umc.wantPlant.tag.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import umc.wantPlant.tag.domain.Tag;

public interface TagRepository extends JpaRepository<Tag, Long> {
}

package umc.wantPlant.todo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import umc.wantPlant.todo.domain.Todo;

import java.util.Optional;

@Repository
public interface TodoRepository extends JpaRepository <Todo,Long>{
    Optional<Todo> findById(Long id);
}

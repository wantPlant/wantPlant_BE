package umc.wantPlant.todo.application;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import umc.wantPlant.todo.domain.Todo;
import umc.wantPlant.todo.domain.dto.TodoRequestDTO;
import umc.wantPlant.todo.repository.TodoRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.time.LocalDate;
import java.time.LocalTime;

@RequiredArgsConstructor
@Service
public class TodoService {
    private final TodoRepository todoRepository;

    public Todo addTodo(TodoRequestDTO.TodoCreateDTO createDTO){
        String title = createDTO.getTitle();
        LocalDateTime startTime = LocalDateTime.from(createDTO.getStartTime());
        LocalDateTime endTime = LocalDateTime.from(createDTO.getEndTime());
        Boolean isComplete = false;

        return todoRepository.save(Todo.builder()
                .title(title)
                .startTime(startTime)
                .endTime(endTime)
                .isComplete(isComplete)
                .build());
    }

    public List<Todo> getTodoList() {return todoRepository.findAll();}

    public Todo getTodoById(Long todoId){return todoRepository.findById(todoId).orElseThrow();}

    public ResponseEntity<Void> updateTodo(Long todoId, TodoRequestDTO.TodoCreateDTO todoCreateDTO){
        Todo todo = getTodoById(todoId);

        String newTitle = todoCreateDTO.getTitle();
        LocalDateTime newStartTime = LocalDateTime.from(todoCreateDTO.getStartTime());
        LocalDateTime newEndTime = LocalDateTime.from(todoCreateDTO.getEndTime());
        Boolean newisComplete = todoCreateDTO.getIsComplete();

        if(newTitle == null){
            newTitle = todo.getTitle();
        }
        if(newStartTime == null){
            newStartTime = todo.getStartTime();
        }
        if(newEndTime == null){
            newEndTime = todo.getEndTime();
        }
        if(newisComplete == null){
            newisComplete = todo.getIsComplete();
        }


        todo.updateTodoDetail(newTitle,newStartTime, newEndTime,newisComplete);
        todoRepository.save(todo);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    public void deleteTodo(Long todoId){
        Todo todo = getTodoById(todoId);
        todoRepository.delete(todo);
    }
}

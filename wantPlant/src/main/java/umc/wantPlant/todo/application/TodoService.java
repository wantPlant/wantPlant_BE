package umc.wantPlant.todo.application;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import umc.wantPlant.tag.domain.dto.response.TagResponseDto;
import umc.wantPlant.todo.domain.Todo;
import umc.wantPlant.todo.domain.dto.TodoRequestDTO;
import umc.wantPlant.todo.domain.dto.TodoResponseDTO;
import umc.wantPlant.todo.repository.TodoRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class TodoService {
    private final TodoRepository todoRepository;

    public Todo addTodo(TodoRequestDTO.TodoCreateDTO createDTO){
        String title = createDTO.getTitle();
        LocalDateTime startTime = createDTO.getStartTime();
        Boolean isComplete = false;

        return todoRepository.save(Todo.builder()
                .title(title)
                .startTime(startTime)
                .isComplete(isComplete)
                .build());
    }

    public List<TodoResponseDTO> getTodos() {
        List<Todo> todos = todoRepository.findAll();
        return todos.stream()
                .map(TodoResponseDTO::of)
                .collect(Collectors.toList());
    }
    public TodoResponseDTO getTodo(Long todoId) {
        return TodoResponseDTO.of(todoRepository.findById(todoId).orElseThrow());
    }

    public Todo getTodoById(Long todoId){return todoRepository.findById(todoId).orElseThrow();}

    public ResponseEntity<Void> updateTodo(Long todoId, TodoRequestDTO.TodoCreateDTO todoCreateDTO){
        Todo todo = getTodoById(todoId);

        String newTitle = todoCreateDTO.getTitle();
        LocalDateTime newStartTime = LocalDateTime.from(todoCreateDTO.getStartTime());

        todo.updateTodoDetail(newTitle,newStartTime);
        todoRepository.save(todo);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    public ResponseEntity<Void> updateTodoTitle(Long todoId, TodoRequestDTO.TodoUpdateTitleDTO updateTodoTitleDTO){
        Todo todo = getTodoById(todoId);

        String newTitle = updateTodoTitleDTO.getTitle();

        todo.updateTodoTitle(newTitle);
        todoRepository.save(todo);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    public ResponseEntity<Void> updateTodostartTime(Long todoId, TodoRequestDTO.TodoUpdateTimeDTO updateTodoTimeDTO){
        Todo todo = getTodoById(todoId);

        LocalDateTime newStartTime = updateTodoTimeDTO.getStartTime();

        todo.updateTodoTime(newStartTime);
        todoRepository.save(todo);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    public ResponseEntity<Void> updateTodoComplete(Long todoId, TodoRequestDTO.TodoUpdateCompleteDTO updateCompleteDTO){
        Todo todo = getTodoById(todoId);

        Boolean newIsComplete = updateCompleteDTO.getIsComplete();

        todo.updateTodoComplete(newIsComplete);
        todoRepository.save(todo);
        return new ResponseEntity<>(HttpStatus.OK);
    }


    public void deleteTodo(Long todoId){
        Todo todo = getTodoById(todoId);
        todoRepository.delete(todo);
    }
}

package umc.wantPlant.todo.application;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import umc.wantPlant.todo.domain.Todo;
import umc.wantPlant.todo.domain.dto.TodoRequestDTO;
import umc.wantPlant.todo.repository.TodoRepository;

import java.util.List;
import java.time.LocalDate;
import java.time.LocalTime;

@RequiredArgsConstructor
@Service
public class TodoService {
    private final TodoRepository todoRepository;

    public Todo addTodo(TodoRequestDTO.TodoCreateDTO createDTO){
        String title = createDTO.getTitle();
        LocalDate date = createDTO.getDate();
        LocalTime startTime = createDTO.getStartTime();
        LocalTime endTime = createDTO.getEndTime();
        return todoRepository.save(Todo.builder()
                .title(title)
                .date(date)
                .startTime(startTime)
                .endTime(endTime)
                .build());
    }

    public List<Todo> getTodoList() {return todoRepository.findAll();}

    public Todo getTodoById(Long todoId){return todoRepository.findById(todoId).orElseThrow();}

    public ResponseEntity<Void> updateTodo(Long todoId, TodoRequestDTO.TodoCreateDTO todoCreateDTO){
        Todo todo = getTodoById(todoId);

        String newTitle = todoCreateDTO.getTitle();
        LocalDate newDate = todoCreateDTO.getDate();
        LocalTime newStartTime = todoCreateDTO.getStartTime();
        LocalTime newEndTime = todoCreateDTO.getEndTime();
        Boolean newisComplete = todoCreateDTO.getIsComplete();

        if(newTitle == null){
            newTitle = todo.getTitle();
        }
        if(newDate == null){
            newDate = todo.getDate();
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


        todo.updateTodoDetail(newTitle, newDate, newStartTime, newEndTime,newisComplete);
        todoRepository.save(todo);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    public void deleteTodo(Long todoId){
        Todo todo = getTodoById(todoId);
        todoRepository.delete(todo);
    }
}

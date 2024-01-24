package umc.wantPlant.todo.application;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import umc.wantPlant.goal.domain.Goal;
import umc.wantPlant.goal.domain.dto.GoalRequestDTO;
import umc.wantPlant.pot.domain.dto.PotRequestDTO;
import umc.wantPlant.todo.domain.Todo;
import umc.wantPlant.todo.domain.dto.TodoRequestDTO;
import umc.wantPlant.todo.repository.TodoRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class TodoService {
    private final TodoRepository todoRepository;

    public Todo addTodo(TodoRequestDTO.TodoCreateDTO createDTO){
        String title = createDTO.getTitle();
        LocalDateTime startTime = LocalDateTime.from(createDTO.getStartTime());
        Boolean isComplete = false;

        return todoRepository.save(Todo.builder()
                .title(title)
                .startTime(startTime)
                .isComplete(isComplete)
                .build());
    }

    public List<Todo> getTodoList() {return todoRepository.findAll();}

    public Todo getTodoById(Long todoId){return todoRepository.findById(todoId).orElseThrow();}

    public ResponseEntity<Void> updateTodo(Long todoId, TodoRequestDTO.TodoCreateDTO todoCreateDTO){
        Todo todo = getTodoById(todoId);

        String newTitle = todoCreateDTO.getTitle();
        LocalDateTime newStartTime = LocalDateTime.from(todoCreateDTO.getStartTime());
        Boolean newisComplete = todoCreateDTO.getIsComplete();

        if(newTitle == null){
            newTitle = todo.getTitle();
        }
        if(newStartTime == null){
            newStartTime = todo.getStartTime();
        }
        if(newisComplete == null){
            newisComplete = todo.getIsComplete();
        }


        todo.updateTodoDetail(newTitle,newStartTime,newisComplete);
        todoRepository.save(todo);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    public void deleteTodo(Long todoId){
        Todo todo = getTodoById(todoId);
        todoRepository.delete(todo);
    }


    //goalCommandService에서 todo생성할 때 사용
    @Transactional
    public void createTodo(Goal goal, GoalRequestDTO.TodoDTO request){
        Todo newTodo = Todo.builder()
                .title(request.getTodoTitle())
                .startTime(request.getStartAt())
                .isComplete(false)
                .goal(goal)
                .build();
        todoRepository.save(newTodo);
    }
    //goalCommandService에서 todoList생성 요청
    @Transactional
    public void createTodos(Goal tGoal, List<PotRequestDTO.TodoDTO> request){
        List<Todo> todos = request.stream().map(todoDTO ->
                Todo.builder()
                        .title(todoDTO.getTodoTitle())
                        .startTime(todoDTO.getStartAt())
                        .isComplete(false)
                        .goal(tGoal)
                        .build()).collect(Collectors.toList());
        todoRepository.saveAll(todos);
    }
    //goal로 todo찾기
    public List<Todo> getTodosByGoal(Goal goal){
        return todoRepository.findAllByGoal(goal).get();
    }
    //startAt으로 todos찾기
    public List<Todo> getTodosByStartDate(LocalDate startDate){
        LocalDateTime startDateMinTime = startDate.atStartOfDay();
        LocalDateTime startDateMaxTime = startDate.atTime(LocalTime.MAX);
        return todoRepository.findAllByStartDate(startDateMinTime, startDateMaxTime).get();
    }
    //goal로 하위 todos지우기
    public void deleteTodosByGoal(Goal goal){
        List<Todo> todos = todoRepository.findAllByGoal(goal).get();
        todoRepository.deleteAll(todos);
    }

}

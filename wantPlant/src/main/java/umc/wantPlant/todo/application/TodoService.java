package umc.wantPlant.todo.application;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import umc.wantPlant.goal.domain.Goal;
import umc.wantPlant.goal.domain.dto.GoalRequestDTO;
import umc.wantPlant.pot.application.PotCommandService;
import umc.wantPlant.pot.domain.Pot;
import umc.wantPlant.pot.domain.dto.PotRequestDTO;
import umc.wantPlant.pot.domain.dto.PotResponseDTO;
import umc.wantPlant.todo.domain.Todo;
import umc.wantPlant.todo.domain.dto.TodoRequestDTO;
import umc.wantPlant.todo.domain.dto.TodoResponseDTO;
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
//    private final PotCommandService potCommandService;

    private PotCommandService potCommandService;

    @Autowired //순환참조때문에 setter로 주입 -> 양방향으로 해결하는게 제일 깔끔할 것
    public void setPotCommandService(@Lazy PotCommandService potCommandService){
        this.potCommandService = potCommandService;
    }

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

    @Transactional
    public ResponseEntity<Void> updateTodoComplete(Long todoId, TodoRequestDTO.TodoUpdateCompleteDTO updateCompleteDTO){
        Todo todo = getTodoById(todoId);

        Boolean newIsComplete = updateCompleteDTO.getIsComplete();
        //false -> false 등 같은 값으로 바뀔땐 실행 x
        if(newIsComplete != todo.getIsComplete()) {
            todo.updateTodoComplete(newIsComplete);
            todoRepository.save(todo);
            potCommandService.updatePotByTodo(todo);
        }
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
    //potService에서 요청
    //처음 생성된 두개 투두 조회
    public List<String> getFirstTwoTodo(Pot pot){
        return todoRepository.findFirstTwoTodoByPot(pot.getPotId()).get();
    }
    //goal로 하위 todos지우기
    public void deleteTodosByGoal(Goal goal){
        List<Todo> todos = todoRepository.findAllByGoal(goal).get();
        todoRepository.deleteAll(todos);
    }

}

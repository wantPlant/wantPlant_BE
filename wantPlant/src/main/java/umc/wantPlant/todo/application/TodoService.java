package umc.wantPlant.todo.application;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import umc.wantPlant.apipayload.code.status.ErrorStatus;
import umc.wantPlant.apipayload.exceptions.handler.GardenHandler;
import umc.wantPlant.garden.domain.Garden;
import umc.wantPlant.garden.domain.dto.GardenResponseDTO;
import umc.wantPlant.goal.application.GoalQueryService;
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
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class TodoService {
    private final TodoRepository todoRepository;
    private GoalQueryService goalQueryService;
    private PotCommandService potCommandService;
    @Autowired
    public void setGoalQueryService(@Lazy GoalQueryService goalQueryService) {
        this.goalQueryService = goalQueryService;
    }

    @Autowired //순환참조때문에 setter로 주입 ->
    public void setPotCommandService(@Lazy PotCommandService potCommandService){
        this.potCommandService = potCommandService;
    }

    public Todo addTodo(TodoRequestDTO.TodoCreateDTO createDTO){

        Goal goal = goalQueryService.getGoalById(createDTO.getGoalID()).orElseThrow(
                ()->new GardenHandler(ErrorStatus.GARDEN_NOT_FOUND)
        );
        String title = createDTO.getTitle();
        LocalDate date = createDTO.getDate();
        LocalTime time = createDTO.getTime();
        Boolean isComplete = false;
        LocalDateTime startAt = LocalDateTime.of(date, time);

        return todoRepository.save(Todo.builder()
                .title(title)
                .date(date)
                .time(time)
                .isComplete(isComplete)
                .goal(goal)
                .startAt(startAt)
                .build());
    }
    public TodoResponseDTO.TodoListDTO getTodos() {
        List<Todo> todos = todoRepository.findAll();
        List<TodoResponseDTO.TodoResultDTO> todoList = todos.stream()
                .map((Todo todoId) -> getTodo(todoId.getId()))
                .collect(Collectors.toList());
        return TodoResponseDTO.TodoListDTO
                .builder()
                .todoList(todoList)
                .build();
    }
    public TodoResponseDTO.TodoResultDTO getTodo(Long todoId) {
        Todo todo = getTodoById(todoId);
        return TodoResponseDTO.TodoResultDTO
                .builder()
                .id(todo.getId())
                .title(todo.getTitle())
                .date(todo.getDate())
                .time(todo.getTime())
                .isComplete(todo.getIsComplete())
                .build();
    }

    public Todo getTodoById(Long todoId){return todoRepository.findById(todoId).orElseThrow();}

    public Todo updateTodo(Long todoId, TodoRequestDTO.TodoUpdateDTO todoUpdateDTO){
        Todo todo = getTodoById(todoId);

        String newTitle = todoUpdateDTO.getTitle();
        LocalDate newDate = todoUpdateDTO.getDate();
        LocalTime newTime = todoUpdateDTO.getTime();

        todo.updateTodoDetail(newTitle,newDate, newTime);
        return todoRepository.save(todo);
    }

    public Todo updateTodoTitle(Long todoId, TodoRequestDTO.TodoUpdateTitleDTO updateTodoTitleDTO){
        Todo todo = getTodoById(todoId);

        String newTitle = updateTodoTitleDTO.getTitle();
        todo.updateTodoTitle(newTitle);

        return todoRepository.save(todo);
    }

    public Todo updateTodostartDate(Long todoId, TodoRequestDTO.TodoUpdateDateDTO updateTodoDateDTO){
        Todo todo = getTodoById(todoId);

        LocalDate newStartDate = updateTodoDateDTO.getDate();

        todo.updateTodoDate(newStartDate);
        return todoRepository.save(todo);
    }

    public Todo updateTodostartTime(Long todoId, TodoRequestDTO.TodoUpdateTimeDTO updateTodoTimeDTO){
        Todo todo = getTodoById(todoId);

        LocalTime newStartTime = updateTodoTimeDTO.getTime();

        todo.updateTodoTime(newStartTime);
        return todoRepository.save(todo);
    }

    @Transactional
    public Todo updateTodoComplete(Long todoId, TodoRequestDTO.TodoUpdateCompleteDTO updateCompleteDTO){
        Todo todo = getTodoById(todoId);

        Boolean newIsComplete = updateCompleteDTO.getIsComplete();
        //false -> false 등 같은 값으로 바뀔땐 실행 x
        if(newIsComplete != todo.getIsComplete()) {
            todo.updateTodoComplete(newIsComplete);
            todoRepository.save(todo);
            potCommandService.updatePotByTodo(todo);
        }
       return todoRepository.save(todo);
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
                .date(request.getDate())
                .time(request.getTime())
                .isComplete(false)
                .goal(goal)
                .startAt(LocalDateTime.of(request.getDate(),request.getTime()))
                .build();
        todoRepository.save(newTodo);
    }
    //goalCommandService에서 todoList생성 요청
    @Transactional
    public void createTodos(Goal tGoal, List<PotRequestDTO.TodoDTO> request){
        List<Todo> todos = request.stream().map(todoDTO ->
                Todo.builder()
                        .title(todoDTO.getTodoTitle())
                        .date(todoDTO.getDate())
                        .time(todoDTO.getTime())
                        .isComplete(false)
                        .goal(tGoal)
                        .startAt(LocalDateTime.of(todoDTO.getDate(),todoDTO.getTime()))
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

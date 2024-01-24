package umc.wantPlant.todo.ui;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import umc.wantPlant.todo.application.TodoService;
import umc.wantPlant.todo.domain.Todo;
import umc.wantPlant.todo.domain.dto.TodoRequestDTO;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/todos")
public class TodoController {
    private final TodoService todoService;

    @PostMapping
    @Operation(summary = "투두 생성 API")
    public ResponseEntity<Todo> addTodo(@RequestBody TodoRequestDTO.TodoCreateDTO todoCreateDTO){
        Todo savedTodo = todoService.addTodo(todoCreateDTO);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(savedTodo);
    }

    @GetMapping
    @Operation(summary = "투두 전체 조회 API")
    public List<Todo> getTodoList() {return todoService.getTodoList();}

    @GetMapping("/{todoId}")
    @Operation(summary = "특정 투두 조회 API")
    public Todo getTodo(@PathVariable Long todoId){
        return todoService.getTodoById(todoId);
    }

    @PutMapping("/{todoId}")
    @Operation(summary = "투두 수정 API")
    public ResponseEntity<Void> updateTodo (@PathVariable("todoId") Long todoId, @RequestBody TodoRequestDTO.TodoCreateDTO todoCreateDTO){
        todoService.updateTodo(todoId,todoCreateDTO);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{todoId}")
    @Operation(summary = "투두 삭제 API")
    public ResponseEntity<Void> deleteTodo(@PathVariable("todoId")Long todoId){
        todoService.deleteTodo(todoId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}

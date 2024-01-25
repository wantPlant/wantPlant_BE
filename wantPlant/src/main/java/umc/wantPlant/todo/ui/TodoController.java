package umc.wantPlant.todo.ui;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import umc.wantPlant.tag.domain.dto.response.TagResponseDto;
import umc.wantPlant.todo.application.TodoService;
import umc.wantPlant.todo.domain.Todo;
import umc.wantPlant.todo.domain.dto.TodoRequestDTO;
import umc.wantPlant.todo.domain.dto.TodoResponseDTO;

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
    public List<TodoResponseDTO> getTodoList() {return todoService.getTodos();}

    @GetMapping("/{todoId}")
    @Operation(summary = "특정 투두 조회 API")
    public TodoResponseDTO getTodo(@PathVariable Long todoId){
        return todoService.getTodo(todoId);
    }

    @PatchMapping("/{todoId}")
    @Operation(summary = "투두 제목,시간수정 API")
    public ResponseEntity<Void> updateTodo (@PathVariable("todoId") Long todoId, @RequestBody TodoRequestDTO.TodoCreateDTO todoCreateDTO){
        todoService.updateTodo(todoId,todoCreateDTO);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PatchMapping("/{todoId}/title")
    @Operation(summary = "투두 제목수정 API")
    public ResponseEntity<Void> updateTodoTitle (@PathVariable("todoId") Long todoId, @RequestBody TodoRequestDTO.TodoUpdateTitleDTO todoUpdateTitleDTO){
        todoService.updateTodoTitle(todoId, todoUpdateTitleDTO);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PatchMapping("/{todoId}/time")
    @Operation(summary = "투두 시간수정 API")
    public ResponseEntity<Void> updateTodoTime (@PathVariable("todoId") Long todoId, @RequestBody TodoRequestDTO.TodoUpdateTimeDTO todoUpdateTimeDTO){
        todoService.updateTodostartTime(todoId, todoUpdateTimeDTO);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PatchMapping("/{todoId}/complete")
    @Operation(summary = "투두 완료여부수정 API")
    public ResponseEntity<Void> updateTodoComplete (@PathVariable("todoId") Long todoId, @RequestBody TodoRequestDTO.TodoUpdateCompleteDTO todoUpdateCompleteDTO){
        todoService.updateTodoComplete(todoId, todoUpdateCompleteDTO);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{todoId}")
    @Operation(summary = "투두 삭제 API")
    public ResponseEntity<Void> deleteTodo(@PathVariable("todoId")Long todoId){
        todoService.deleteTodo(todoId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}

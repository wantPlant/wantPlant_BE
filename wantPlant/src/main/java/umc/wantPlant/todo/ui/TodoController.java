package umc.wantPlant.todo.ui;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import umc.wantPlant.apipayload.ApiResponse;
import umc.wantPlant.todo.application.TodoService;
import umc.wantPlant.todo.domain.Todo;
import umc.wantPlant.todo.domain.dto.TodoRequestDTO;
import umc.wantPlant.todo.domain.dto.TodoResponseDTO;

import java.util.Optional;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/todos")
public class TodoController {
    private final TodoService todoService;

    @PostMapping
    @Operation(summary = "투두 생성 API")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200", description = "OK, 성공")})
    public ApiResponse<String> addTodo(@RequestBody TodoRequestDTO.TodoCreateDTO todoCreateDTO){
        Todo todo = todoService.addTodo(todoCreateDTO);
        return ApiResponse.onSuccess(todo.getId()+"번 투두생성완료");
    }

    @GetMapping
    @Operation(summary = "투두 전체 조회 API")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200", description = "OK, 성공")})
    public ApiResponse<TodoResponseDTO.TodoListDTO> getTodoList() {
        TodoResponseDTO.TodoListDTO result = todoService.getTodos();
        return ApiResponse.onSuccess(result);
    }

    @GetMapping("/{todoId}")
    @Operation(summary = "특정 투두 조회 API")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200", description = "OK, 성공")})
    public ApiResponse<TodoResponseDTO.TodoResultDTO> getTodo(@PathVariable Long todoId){
        TodoResponseDTO.TodoResultDTO result = todoService.getTodo(todoId);
        return ApiResponse.onSuccess(result);
    }

    @PatchMapping("/{todoId}")
    @Operation(summary = "투두 제목,날짜,시간수정 API")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200", description = "OK, 성공")})
    public ApiResponse<String> updateTodo (@PathVariable("todoId") Long todoId, @RequestBody TodoRequestDTO.TodoUpdateDTO todoUpdateDTO){
        Todo todo = todoService.updateTodo(todoId,todoUpdateDTO);
        return ApiResponse.onSuccess(todo.getId() + "번 투두수정완료");
    }

    @PatchMapping("/{todoId}/title")
    @Operation(summary = "투두 제목수정 API")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200", description = "OK, 성공")})
    public  ApiResponse<String> updateTodoTitle (@PathVariable("todoId") Long todoId, @RequestBody TodoRequestDTO.TodoUpdateTitleDTO todoUpdateTitleDTO){
        Todo todo = todoService.updateTodoTitle(todoId, todoUpdateTitleDTO);
        return ApiResponse.onSuccess(todo.getId() + "번 투두제목 수정완료");
    }

    @PatchMapping("/{todoId}/time")
    @Operation(summary = "투두 시간수정 API")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200", description = "OK, 성공")})
    public  ApiResponse<String> updateTodoTime (@PathVariable("todoId") Long todoId, @RequestBody TodoRequestDTO.TodoUpdateTimeDTO todoUpdateTimeDTO){
        Todo todo = todoService.updateTodostartTime(todoId, todoUpdateTimeDTO);
        return ApiResponse.onSuccess(todo.getId() + "번 투두시간 수정완료");
    }

    @PatchMapping("/{todoId}/date")
    @Operation(summary = "투두 날짜수정 API")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200", description = "OK, 성공")})
    public  ApiResponse<String> updateTodoDate (@PathVariable("todoId") Long todoId, @RequestBody TodoRequestDTO.TodoUpdateDateDTO todoUpdateDateDTO){
        Todo todo = todoService.updateTodostartDate(todoId, todoUpdateDateDTO);
        return ApiResponse.onSuccess(todo.getId() + "번 투두날짜 수정완료");
    }

    @PatchMapping("/{todoId}/complete")
    @Operation(summary = "투두 완료여부수정 API")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200", description = "OK, 성공")})
    public ApiResponse<String> updateTodoComplete (@PathVariable("todoId") Long todoId, @RequestBody TodoRequestDTO.TodoUpdateCompleteDTO todoUpdateCompleteDTO){
        Todo todo = todoService.updateTodoComplete(todoId, todoUpdateCompleteDTO);
        return ApiResponse.onSuccess(todo.getId() + "번 투두완료여부 수정완료");
    }

    @DeleteMapping("/{todoId}")
    @Operation(summary = "투두 삭제 API")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200", description = "OK, 성공")})
    public ApiResponse<String> deleteTodo(@PathVariable("todoId")Long todoId){
        todoService.deleteTodo(todoId);
        return ApiResponse.onSuccess(todoId + "번 투두삭제완료");
    }
}

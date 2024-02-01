package umc.wantPlant.pot.application;

import umc.wantPlant.pot.domain.Pot;
import umc.wantPlant.pot.domain.dto.PotRequestDTO;
import umc.wantPlant.todo.domain.Todo;

public interface PotCommandService {
    public Pot createPot(PotRequestDTO.PostPotDTO request);
    public Pot createPotGoalsTodos(PotRequestDTO.PostPotGoalTodoDTO request);
    public Pot updatePot(Long potId, PotRequestDTO.PatchPotDTO request);
    public Pot updatePotByTodo(Todo todo);
    public void deletePot(Long potId);
}

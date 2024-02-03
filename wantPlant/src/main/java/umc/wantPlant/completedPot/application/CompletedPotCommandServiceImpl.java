package umc.wantPlant.completedPot.application;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import umc.wantPlant.completedPot.domain.CompletedPot;
import umc.wantPlant.completedPot.repository.CompletedPotRepository;
import umc.wantPlant.pot.domain.Pot;
import umc.wantPlant.todo.application.TodoService;
import umc.wantPlant.todo.domain.Todo;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CompletedPotCommandServiceImpl implements CompletedPotCommandService{
    private final CompletedPotRepository completedPotRepository;
    private final TodoService todoService;
    @Override
    public void saveCompletedPotFromPot(Pot pot) {
        List<String> todoTitles = todoService.getFirstTwoTodo(pot);

        CompletedPot completedPot = CompletedPot.builder()
                .potId(pot.getPotId())
                .potName(pot.getPotName())
                .potType(pot.getPotType())
                .potImageUrl(pot.getPotImageUrl())
                .startAt(pot.getStartAt())
                .completeAt(LocalDate.now())
                .todoTitle1(todoTitles.get(0))
                .todoTitle2(todoTitles.get(1))
                .garden(pot.getGarden())
                .build();

        //pot당 도감은 한개이므로 유니크한지 체크
        if(completedPotRepository.isPotIdUnique(completedPot.getPotId()) == 0){
            completedPotRepository.save(completedPot);
        }
    }

    @Override
    public void deleteCompletedPotFromPot(Pot pot) {
        completedPotRepository.deleteByPotId(pot.getPotId());
    }
}

package umc.wantPlant.pot.application;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.wantPlant.apipayload.code.status.ErrorStatus;
import umc.wantPlant.apipayload.exceptions.GeneralException;
import umc.wantPlant.apipayload.exceptions.handler.GardenHandler;
import umc.wantPlant.apipayload.exceptions.handler.PotHandler;
import umc.wantPlant.garden.application.GardenQueryService;
import umc.wantPlant.garden.domain.Garden;
import umc.wantPlant.garden.repository.GardenRepository;
import umc.wantPlant.goal.application.GoalCommandService;
import umc.wantPlant.goal.application.GoalQueryService;
import umc.wantPlant.goal.domain.Goal;
import umc.wantPlant.pot.domain.Pot;
import umc.wantPlant.pot.domain.dto.PotRequestDTO;
import umc.wantPlant.pot.domain.enums.PotTagColor;
import umc.wantPlant.pot.domain.enums.PotType;
import umc.wantPlant.pot.repository.PotRepository;
import umc.wantPlant.todo.application.TodoService;
import umc.wantPlant.todo.domain.Todo;
import umc.wantPlant.todo.repository.TodoRepository;

import java.util.List;


@Service
@RequiredArgsConstructor
public class PotCommandServiceImpl implements PotCommandService{
    private final PotRepository potRepository;
    private final GardenQueryService gardenQueryService;
    private final GoalCommandService goalCommandService;
    private final GoalQueryService goalQueryService;
    private final TodoService todoService;

//    private final AmazonS3 amazonS3; //todo 이미지 처리


    @Override
    @Transactional
    public Pot createPot(PotRequestDTO.PostPotDTO request) {
        String keyName = "potType/"+request.getPotType()+"-"+0;;
        String potImgUrl = "";
        //potImgUrl = amazonS3.getUrl(amazonConfig.getBucket(), keyName).toString();//todo 이미지 처리


        Garden garden = gardenQueryService.getGardenById(request.getGardenId()).orElseThrow(
                ()->new GardenHandler(ErrorStatus.GARDEN_NOT_FOUND)
        );
        Pot newPot = Pot.builder()
                .potName(request.getPotName())
                .potType(request.getPotType())
                .proceed(0)
                .potTagColor(request.getPotTageColor())
                .potImageUrl(potImgUrl)
                .startAt(request.getStartAt())
                .completeAt(null)
                .garden(garden)
                .build();

        return potRepository.save(newPot);
    }

    @Override //앱용
    public Pot createPotGoalsTodos(PotRequestDTO.PostPotGoalTodoDTO request) {
        String keyName = "potType/"+request.getPotType()+"-"+0;;
        String potImgUrl = "";
        //potImgUrl = amazonS3.getUrl(amazonConfig.getBucket(), keyName).toString();//todo 이미지 처리

        Garden garden = gardenQueryService.getGardenById(request.getGardenId()).orElseThrow(
                ()->new GardenHandler(ErrorStatus.GARDEN_NOT_FOUND)
        );

        Pot newPot = Pot.builder()
                .potName(request.getPotName())
                .potType(request.getPotType())
                .proceed(0)
                .potTagColor(PotTagColor.PURPLE)
                .potImageUrl(potImgUrl)
                .startAt(request.getStartAt())
                .completeAt(null)
                .garden(garden)
                .build();
        potRepository.save(newPot);

        //goals&todos생성
        goalCommandService.createGoalsTodos(newPot, request.getGoalList());

        return newPot;
    }


    @Override
    @Transactional
    public Pot modifyPot(Long potId, PotRequestDTO.PatchPotDTO request) {
        Pot pot = potRepository.findById(potId).orElseThrow(
                ()->new PotHandler(ErrorStatus.POT_NOT_FOUND)
        );
        pot.setPotName(request.getPotName());
        return potRepository.save(pot);
    }

    @Override
    @Transactional
    public void deletePot(Long potId) {
        //goals 삭제
        Pot pot = potRepository.findById(potId).get();
        goalCommandService.deleteAllByPot(pot);
        //todos 삭제
        List<Goal> goals = goalQueryService.findAllByPot(pot);
        for(Goal goal:goals){
            todoService.deleteTodosByGoal(goal);
        }

        potRepository.deleteById(potId);
    }
}

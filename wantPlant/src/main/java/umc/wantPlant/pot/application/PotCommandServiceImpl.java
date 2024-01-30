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

import java.time.LocalDate;
import java.time.ZoneId;
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
        PotType potType = PotType.getRandom();
        String keyName = "potType/"+potType+"-"+0;;
        String potImgUrl = "";
        //potImgUrl = amazonS3.getUrl(amazonConfig.getBucket(), keyName).toString();//todo 이미지 처리


        Garden garden = gardenQueryService.getGardenById(request.getGardenId()).orElseThrow(
                ()->new GardenHandler(ErrorStatus.GARDEN_NOT_FOUND)
        );
        Pot newPot = Pot.builder()
                .potName(request.getPotName())
                .potType(potType)
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
        PotType potType = PotType.getRandom();
        String keyName = "potType/"+potType+"-"+0;;
        String potImgUrl = "";
        //potImgUrl = amazonS3.getUrl(amazonConfig.getBucket(), keyName).toString();//todo 이미지 처리


        Garden garden = gardenQueryService.getGardenById(request.getGardenId()).orElseThrow(
                ()->new GardenHandler(ErrorStatus.GARDEN_NOT_FOUND)
        );

        Pot newPot = Pot.builder()
                .potName(request.getPotName())
                .potType(potType)
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
    public Pot updatePot(Long potId, PotRequestDTO.PatchPotDTO request) {
        Pot pot = potRepository.findById(potId).orElseThrow(
                ()->new PotHandler(ErrorStatus.POT_NOT_FOUND)
        );
        pot.setPotName(request.getPotName());
        return potRepository.save(pot);
    }

    @Override
    @Transactional
    public Pot updatePot(Todo todo) {
        Pot pot = potRepository.findByPotId(todo.getGoal().getPot().getPotId()).get();

        //proceed update
        pot.updatePotProceed(todo.getIsComplete());

        //url update
        String keyName = "potType/"+pot.getPotType()+"-"+0;;
        String potImgUrl = "";
        switch (pot.getProceed()/10){
            case 0 -> keyName = "potType/"+pot.getPotType()+"-"+0;
            case 1 -> keyName = "potType/"+pot.getPotType()+"-"+1;
            case 2 -> keyName = "potType/"+pot.getPotType()+"-"+2;
            case 3 -> keyName = "potType/"+pot.getPotType()+"-"+3;
        }
        //potImgUrl = amazonS3.getUrl(amazonConfig.getBucket(), keyName).toString();//todo 이미지 처리
        pot.setPotImgUrl(potImgUrl);

        //completedAt update
        if(pot.getProceed() >= 30)
            pot.setCompleteAt(LocalDate.now());
        else
            pot.setCompleteAt(null);

        return potRepository.save(pot);
    }

    @Override
    @Transactional
    public void deletePot(Long potId) {
        //todos 삭제
        Pot pot = potRepository.findById(potId).get();
        List<Goal> goals = goalQueryService.findAllByPot(pot);
        for(Goal goal:goals){
            todoService.deleteTodosByGoal(goal);
        }
        //goals 삭제
        goalCommandService.deleteAllByPot(pot);


        potRepository.deleteByPotId(potId);
    }
}

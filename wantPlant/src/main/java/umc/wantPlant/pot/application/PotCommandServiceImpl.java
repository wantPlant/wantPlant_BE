package umc.wantPlant.pot.application;

import com.amazonaws.services.s3.AmazonS3;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import umc.wantPlant.apipayload.code.status.ErrorStatus;
import umc.wantPlant.apipayload.exceptions.handler.GardenHandler;
import umc.wantPlant.apipayload.exceptions.handler.PotHandler;
import umc.wantPlant.completedPot.application.CompletedPotCommandService;
import umc.wantPlant.file.service.AmazonS3Service;
import umc.wantPlant.garden.application.GardenQueryService;
import umc.wantPlant.garden.domain.Garden;
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
    private final CompletedPotCommandService completedPotCommandService;
    private final AmazonS3Service amazonS3Service;


    @Override
    @Transactional
    public Pot createPot(PotRequestDTO.PostPotDTO request) {
        PotType potType = PotType.getRandom();
        String keyName = "potType/"+potType+"-"+0;;
        String potImgUrl = "";
//        potImgUrl = amazonS3.getUrl(amazonConfig.getBucket(), keyName).toString();//todo 이미지 처리
//
//        potImgUrl = amazonS3Service.getUploadFileUrlByOrginalFileName(keyName);

        Garden garden = gardenQueryService.getGardenById(request.getGardenId());
        Pot newPot = Pot.builder()
                .potName(request.getPotName())
                .potType(potType)
                .proceed(0)
                .potTagColor(request.getPotTageColor())
                .potImageUrl(potImgUrl)
                .startAt(request.getStartAt())
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


        Garden garden = gardenQueryService.getGardenById(request.getGardenId());


        Pot newPot = Pot.builder()
                .potName(request.getPotName())
                .potType(potType)
                .proceed(0)
                .potTagColor(PotTagColor.PURPLE)
                .potImageUrl(potImgUrl)
                .startAt(request.getStartAt())
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
        Pot pot = potRepository.findById(potId).get();
        pot.setPotName(request.getPotName());
        return potRepository.save(pot);
    }

    @Override
    @Transactional
    public Pot updatePotByTodo(Todo todo) {
        Pot pot = potRepository.findByPotId(todo.getGoal().getPot().getPotId()).get();

        //proceed update
        pot.updatePotProceed(todo.getIsComplete());

        //url update
        String keyName = "potType/"+pot.getPotType()+"-"+0;;
        String potImgUrl = "";
        switch (pot.getProceed()/15){
            case 0 -> keyName = "potType/"+pot.getPotType()+"-"+0;
            case 1 -> keyName = "potType/"+pot.getPotType()+"-"+1;
            case 2 -> keyName = "potType/"+pot.getPotType()+"-"+2;
        }
        //potImgUrl = amazonS3.getUrl(amazonConfig.getBucket(), keyName).toString();//todo 이미지 처리
        pot.setPotImgUrl(potImgUrl);

        //완료한 화분으로 옮기기
        if(pot.getProceed() == 30) {//30개 되면 save
            completedPotCommandService.saveCompletedPotFromPot(pot);
        }

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


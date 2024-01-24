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
import umc.wantPlant.goal.domain.Goal;
import umc.wantPlant.pot.domain.Pot;
import umc.wantPlant.pot.domain.dto.PotRequestDTO;
import umc.wantPlant.pot.domain.enums.PotTagColor;
import umc.wantPlant.pot.domain.enums.PotType;
import umc.wantPlant.pot.repository.PotRepository;

import java.util.List;


@Service
@RequiredArgsConstructor
public class PotCommandServiceImpl implements PotCommandService{
    private final PotRepository potRepository;
    private final GardenQueryService gardenQueryService;
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

        //todo: goal, todo 합치면 주석 해제하기
//        List<Goal> goals = request.getGoalList().stream().map(goal -> {
//            Goal newGoal = Goal.builder()
//                    .pot(newPot)
//                    .goalTitle(goal.getGoalTitle())
//                    .goalDescription(goal.getGoalDescription())
//                    .build();
//            List<Todo> todos = goal.getTodoList().stream().map(todo ->
//                    Todo.builder()
//                            .title(todo.getTodoTitle())
//                            .content(todo.getContent())
//                            .startAt(todo.getStartAt())
//                            .isComplete(todo.getComlete())
//                            .goal(newGoal)
//                            .build()).toList();
//            todoRepository.saveAll(todos);
//            return newGoal;
//        }).toList();
//        goalRepository.saveAll(goals);
//
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
        //todo: todo 랑 합치고 주석 해제
        //goals, todos도 같이 삭제해주기
//        Pot pot = potRepository.findById(potId).get();
//        List<Goal> goals = goalQueryService.findAllByPot(pot);
//        goalCommandService.deleteAllByPot(pot);
//        todoCommandService.deleteAllByGoals(goals);

        potRepository.deleteById(potId);
    }
}

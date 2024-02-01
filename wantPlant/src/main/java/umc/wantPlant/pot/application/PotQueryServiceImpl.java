package umc.wantPlant.pot.application;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.wantPlant.apipayload.code.status.ErrorStatus;
import umc.wantPlant.apipayload.exceptions.handler.GardenHandler;
import umc.wantPlant.apipayload.exceptions.handler.PotHandler;
import umc.wantPlant.garden.application.GardenQueryService;
import umc.wantPlant.garden.domain.enums.GardenCategories;
import umc.wantPlant.pot.domain.Pot;
import umc.wantPlant.pot.domain.dto.PotResponseDTO;
import umc.wantPlant.pot.repository.PotRepository;
import umc.wantPlant.todo.application.TodoService;
import umc.wantPlant.todo.domain.Todo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

//get 요청 처리
@Service
@RequiredArgsConstructor
public class PotQueryServiceImpl implements PotQueryService{
    private final PotRepository potRepository;
    private final GardenQueryService gardenQueryService;
    private final TodoService todoService;

    @Override
    public PotResponseDTO.GetPotNamesResultDTO getPotNamesByGardenId(Long gardenId) {
        List<Pot> pots = potRepository.findAllByGarden(gardenQueryService.getGardenById(gardenId)).orElseThrow(
                ()->new PotHandler(ErrorStatus.POT_NOT_FOUND)
        );

        //name 리스트 만들기
        List<PotResponseDTO.PotNameDTO> potNameDTOS = pots.stream()
                .map(pot-> PotResponseDTO.PotNameDTO.builder()
                        .potName(pot.getPotName())
                        .potId(pot.getPotId())
                        .build()).toList();

        return PotResponseDTO.GetPotNamesResultDTO.builder()
                .potNames(potNameDTOS)
                .build();
    }

    @Override
    public PotResponseDTO.GetPotImagesResultDTO getPotImagesByGardenId(Long gardenId) {
        List<Pot> pots = potRepository.findAllByGarden(gardenQueryService.getGardenById(gardenId))
            .orElseThrow(
                ()->new PotHandler(ErrorStatus.POT_NOT_FOUND)
        );

        List<PotResponseDTO.PotImageDTO> potImageDTOS = pots.stream().map(
                pot-> PotResponseDTO.PotImageDTO.builder()
                        .potImageUrl(pot.getPotImageUrl())
                        .potId(pot.getPotId())
                        .build()).toList();
        return PotResponseDTO.GetPotImagesResultDTO.builder()
                .potImages(potImageDTOS)
                .build();
    }

    @Override
    public PotResponseDTO.GetPotsResultDTO getPotsByGardenId(Long gardenId, int page) {
        Page<Pot> pots = potRepository.findAllByGarden(gardenQueryService.getGardenById(gardenId), PageRequest.of(page, 4));

        //List<PotResponseDTO.PotDTO> 만들기
        List<PotResponseDTO.PotDTO> potDTOS = pots.stream().map(pot->
                PotResponseDTO.PotDTO.builder()
                        .potId(pot.getPotId())
                        .potName(pot.getPotName())
                        .proceed(pot.getProceed()%30)
                        .potImageUrl(pot.getPotImageUrl())
                        .startAt(pot.getStartAt())
                        .build()).toList();

        return PotResponseDTO.GetPotsResultDTO.builder()
                .pots(potDTOS)
                .listSize(pots.getSize())
                .totalPage(pots.getTotalPages())
                .totalElements(pots.getTotalElements())
                .isFirst(pots.isFirst())
                .isLast(pots.isLast())
                .build();
    }

    @Override//날짜 별 카테고리&화분&todos 리스트 조회
    public PotResponseDTO.GetCategoryPotTodoPerDateDTO getCategoryPotTodoByDate(LocalDate date) {

        List<Todo> todos = todoService.getTodosByStartDate(date);

        List<PotResponseDTO.TodoPerDateDTO> todoPerDateDTOS = todos.stream().map(todo ->
        {
            Pot pot = todo.getGoal().getPot();
            return PotResponseDTO.TodoPerDateDTO.builder()
                    .category(pot.getGarden().getCategory())
                    .potId(pot.getPotId())
                    .potName(pot.getPotName())
                    .potTagColor(pot.getPotTagColor())
                    .todoId(todo.getId())
                    .todoTitle(todo.getTitle())
                    .isComplete(todo.getIsComplete())
                    .build();
        }).collect(Collectors.toList());

        return PotResponseDTO.GetCategoryPotTodoPerDateDTO.builder()
                .todos(todoPerDateDTOS)
                .build();
    }

    @Override //pot 상세조회
    public PotResponseDTO.GetPotDetailResultDTO getPotDetailByPotId(Long potId) {
        Pot pot = potRepository.findByPotId(potId).orElseThrow(
                ()->new PotHandler(ErrorStatus.POT_NOT_FOUND)
        );

        return PotResponseDTO.GetPotDetailResultDTO.builder()
                .gardenName(pot.getGarden().getName())
                .potId(pot.getPotId())
                .potName(pot.getPotName())
                .proceed(pot.getProceed()%30)
                .potImageUrl(pot.getPotImageUrl())
                .build();
    }

    @Override
    public Pot getPotByPotId(Long potId) {

        return potRepository.findByPotId(potId).orElseThrow(
                ()->new PotHandler(ErrorStatus.POT_NOT_FOUND));
    }
}

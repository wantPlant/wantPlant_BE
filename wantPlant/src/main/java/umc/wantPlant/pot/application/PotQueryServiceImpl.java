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
import umc.wantPlant.pot.domain.Pot;
import umc.wantPlant.pot.domain.dto.PotResponseDTO;
import umc.wantPlant.pot.repository.PotRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

//get 요청 처리
@Service
@RequiredArgsConstructor
public class PotQueryServiceImpl implements PotQueryService{
    private final PotRepository potRepository;
    private final GardenQueryService gardenQueryService;

    @Override
    @Transactional
    public PotResponseDTO.GetPotNamesResultDTO getPotNamesByGardenId(Long gardenId) {
        List<Pot> pots = potRepository.findAllByGarden(gardenQueryService.getGardenById(gardenId).orElseThrow(
                ()->new GardenHandler(ErrorStatus.GARDEN_NOT_FOUND)
        )).orElseThrow(
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
    @Transactional
    public PotResponseDTO.GetPotImagesResultDTO getPotImagesByGardenId(Long gardenId) {
        List<Pot> pots = potRepository.findAllByGarden(gardenQueryService.getGardenById(gardenId).orElseThrow(
                ()->new GardenHandler(ErrorStatus.GARDEN_NOT_FOUND))
        ).orElseThrow(
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
    @Transactional
    public PotResponseDTO.GetPotsResultDTO getPotsByGardenId(Long gardenId, int page) {
        Page<Pot> pots = potRepository.findAllByGarden(gardenQueryService.getGardenById(gardenId).orElseThrow(
                ()->new GardenHandler(ErrorStatus.GARDEN_NOT_FOUND)
        ), PageRequest.of(page, 4));

        //List<PotResponseDTO.PotDTO> 만들기
        List<PotResponseDTO.PotDTO> potDTOS = pots.stream().map(pot->
                PotResponseDTO.PotDTO.builder()
                        .potId(pot.getPotId())
                        .potName(pot.getPotName())
                        .proceed(pot.getProceed())
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

    @Override
    @Transactional
    public PotResponseDTO.GetCategoryPotTodoPerDateDTO getCategoryPotTodoByDate(LocalDate date) {
        List<Pot> pots = potRepository.findAllByStartAt(date).orElseThrow(
                ()->new PotHandler(ErrorStatus.POT_NOT_FOUND)
        );
        return null;
    }

    @Override //pot 상세조회
    @Transactional
    public PotResponseDTO.GetPotDetailResultDTO getPotDetailByPotId(Long potId) {
        Pot pot = potRepository.findById(potId).orElseThrow(
                ()->new PotHandler(ErrorStatus.POT_NOT_FOUND)
        );

        return PotResponseDTO.GetPotDetailResultDTO.builder()
                .gardenName(pot.getGarden().getName())
                .potId(pot.getPotId())
                .potName(pot.getPotName())
                .proceed(pot.getProceed())
                .potImageUrl(pot.getPotImageUrl())
                .build();
    }

    @Override
    @Transactional
    public PotResponseDTO.GetCompletedPotsResultDTO getCompletedPotsByGardenId(Long gardenId) {
        List<Pot> pots = potRepository.findAllCompletePotsByGarden(gardenQueryService.getGardenById(gardenId).orElseThrow(
                ()->new GardenHandler(ErrorStatus.GARDEN_NOT_FOUND)
        )).orElseThrow(
                ()->new PotHandler(ErrorStatus.POT_NOT_FOUND)
        );

        List<PotResponseDTO.PotCompleteDTO> potCompleteDTOS = pots.stream().map(pot ->
                PotResponseDTO.PotCompleteDTO.builder()
                        .potName(pot.getPotName())
                        .potImageUrl(pot.getPotImageUrl())
                        .startAt(pot.getStartAt())
                        .completeAt(pot.getCompleteAt())
                        .build()).toList();

        return PotResponseDTO.GetCompletedPotsResultDTO.builder()
                .pots(potCompleteDTOS)
                .build();
    }
}

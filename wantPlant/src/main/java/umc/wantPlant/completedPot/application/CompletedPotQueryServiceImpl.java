package umc.wantPlant.completedPot.application;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import umc.wantPlant.apipayload.code.status.ErrorStatus;
import umc.wantPlant.apipayload.exceptions.handler.GardenHandler;
import umc.wantPlant.apipayload.exceptions.handler.PotHandler;
import umc.wantPlant.completedPot.domain.CompletedPot;
import umc.wantPlant.completedPot.domain.dto.CompletedPotResponseDTO;
import umc.wantPlant.completedPot.repository.CompletedPotRepository;
import umc.wantPlant.garden.application.GardenQueryService;
import umc.wantPlant.pot.domain.Pot;
import umc.wantPlant.pot.domain.dto.PotResponseDTO;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CompletedPotQueryServiceImpl implements CompletedPotQueryService {
    private final CompletedPotRepository completedPotRepository;
    private final GardenQueryService gardenQueryService;
    @Override //완료한 화분 조회
    public CompletedPotResponseDTO.GetCompletedPotsResultDTO getCompletedPotsByGardenId(Long gardenId) {
        List<CompletedPot> pots = completedPotRepository.findAllByGarden(gardenQueryService.getGardenById(gardenId))
                .get();

        List<CompletedPotResponseDTO.CompletedPotDTO> potCompleteDTOS = pots.stream().map(pot ->
                CompletedPotResponseDTO.CompletedPotDTO.builder()
                        .potName(pot.getPotName())
                        .potImageUrl(pot.getPotImageUrl())
                        .startAt(pot.getStartAt())
                        .completeAt(pot.getCompleteAt())
                        .build()).collect(Collectors.toList());

        return CompletedPotResponseDTO.GetCompletedPotsResultDTO.builder()
                .pots(potCompleteDTOS)
                .build();
    }

    @Override //웹을 위한 완료한 화분 조회
    public CompletedPotResponseDTO.GetCompletedPotsForWebResultDTO getCompletedPotsForWeb() {
        List<CompletedPot> completedPot = completedPotRepository.findAll();

        List<CompletedPotResponseDTO.CompletedPotForWebDTO> completedPotForWebDTOS =
                completedPot.stream().map(pot ->
                        CompletedPotResponseDTO.CompletedPotForWebDTO.builder()
                                .potName(pot.getPotName())
                                .GardenName(pot.getGarden().getName())
                                .gardenCategory(pot.getGarden().getCategory())
                                .startAt(pot.getStartAt())
                                .completedAt(pot.getCompleteAt())
                                .potImgUrl(pot.getPotImageUrl())
                                .todoTitle1(pot.getTodoTitle1())
                                .todoTitle2(pot.getTodoTitle2())
                                .build()).collect(Collectors.toList());

        return CompletedPotResponseDTO.GetCompletedPotsForWebResultDTO.builder()
                .pots(completedPotForWebDTOS)
                .build();
    }
}

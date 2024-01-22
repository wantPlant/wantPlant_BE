package umc.wantPlant.pot.application;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import umc.wantPlant.apipayload.code.status.ErrorStatus;
import umc.wantPlant.apipayload.exceptions.GeneralException;
import umc.wantPlant.apipayload.exceptions.handler.GardenHandler;
import umc.wantPlant.garden.application.GardenQueryService;
import umc.wantPlant.garden.domain.Garden;
import umc.wantPlant.garden.repository.GardenRepository;
import umc.wantPlant.pot.domain.Pot;
import umc.wantPlant.pot.domain.dto.PotRequestDTO;
import umc.wantPlant.pot.domain.enums.PotTagColor;
import umc.wantPlant.pot.domain.enums.PotType;
import umc.wantPlant.pot.repository.PotRepository;

@Service
@RequiredArgsConstructor
public class PotCommandServiceImpl implements PotCommandService{
    private final PotRepository potRepository;
    private final GardenQueryService gardenQueryService;

    @Override
    public Pot createPot(PotRequestDTO.PostPotDTO request) {

        Garden garden = gardenQueryService.getGardenById(request.getGardenId()).orElseThrow(
                ()->new GardenHandler(ErrorStatus.GARDEN_NOT_FOUND)
        );
        Pot newPot = Pot.builder()
                .potName(request.getPotName())
                .potType(PotType.valueOf(request.getPotType()))
                .proceed(0)
                .potTagColor(PotTagColor.valueOf(request.getPotTageColor()))
                .potImageUrl("")
                .startAt(request.getStartAt())
                .completeAt(null)
                .garden(garden)
                .build();

        return potRepository.save(newPot);
    }
}

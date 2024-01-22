package umc.wantPlant.pot.application;

import umc.wantPlant.pot.domain.Pot;
import umc.wantPlant.pot.domain.dto.PotRequestDTO;

public interface PotCommandService {
    public Pot createPot(PotRequestDTO.PostPotDTO request);
}

package umc.wantPlant.completedPot.application;

import umc.wantPlant.completedPot.domain.dto.CompletedPotResponseDTO;

public interface CompletedPotQueryService {
    public CompletedPotResponseDTO.GetCompletedPotsResultDTO getCompletedPotsByGardenId(Long gardenId);
    public CompletedPotResponseDTO.GetCompletedPotsForWebResultDTO getCompletedPotsForWeb();
}

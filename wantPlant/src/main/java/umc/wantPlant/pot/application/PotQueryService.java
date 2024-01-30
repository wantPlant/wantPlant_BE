package umc.wantPlant.pot.application;

import umc.wantPlant.pot.domain.Pot;
import umc.wantPlant.pot.domain.dto.PotResponseDTO;

import java.time.LocalDate;

//get요청 처리
public interface PotQueryService {
    public PotResponseDTO.GetPotNamesResultDTO getPotNamesByGardenId(Long gardenId);
    public PotResponseDTO.GetPotImagesResultDTO getPotImagesByGardenId(Long gardenId);
    public PotResponseDTO.GetPotsResultDTO getPotsByGardenId(Long gardenId, int page);
    public PotResponseDTO.GetCategoryPotTodoPerDateDTO getCategoryPotTodoByDate(LocalDate date);
    public PotResponseDTO.GetPotDetailResultDTO getPotDetailByPotId(Long potId);
    public PotResponseDTO.GetCompletedPotsResultDTO getCompletedPotsByGardenId(Long gardenId);
    public PotResponseDTO.GetCompletedPotsForWebResultDTO getCompletedPotsForWeb();
    public Pot getPotByPotId(Long potId);
}

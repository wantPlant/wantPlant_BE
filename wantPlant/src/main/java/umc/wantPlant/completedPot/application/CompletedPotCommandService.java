package umc.wantPlant.completedPot.application;

import umc.wantPlant.pot.domain.Pot;

public interface CompletedPotCommandService {
    public void saveCompletedPotFromPot(Pot pot);
    public void deleteCompletedPotFromPot(Pot pot);
}

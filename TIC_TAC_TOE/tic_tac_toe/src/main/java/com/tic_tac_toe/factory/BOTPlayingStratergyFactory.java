package com.tic_tac_toe.factory;

import com.tic_tac_toe.enums.BOTDifficultyLevel;
import com.tic_tac_toe.stratergy.BOTStratergies.BOTPlayingStratergy;
import com.tic_tac_toe.stratergy.BOTStratergies.EasyBOTPlayingStratergy;
import com.tic_tac_toe.stratergy.BOTStratergies.HardBOTPlayingStratergy;
import com.tic_tac_toe.stratergy.BOTStratergies.MediumBOTPlayingStratergy;

public class BOTPlayingStratergyFactory {
    public static BOTPlayingStratergy getBotPlayingStratergy(BOTDifficultyLevel level)
    {
        return switch (level) {
            case EASY -> new EasyBOTPlayingStratergy();
            case MEDIUM -> new MediumBOTPlayingStratergy();
            default -> new HardBOTPlayingStratergy();
        };
    }
    
}

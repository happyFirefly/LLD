package com.tic_tac_toe.models;

import com.tic_tac_toe.enums.BOTDifficultyLevel;
import com.tic_tac_toe.enums.PlayerType;
import com.tic_tac_toe.factory.BOTPlayingStratergyFactory;
import com.tic_tac_toe.stratergy.BOTStratergies.BOTPlayingStratergy;

public class Bot extends Player {
    private BOTDifficultyLevel BOTDifficultyLevel;
    private final BOTPlayingStratergy botPlayingStratergy;

    public BOTDifficultyLevel getBOTDifficultyLevel() {
        return BOTDifficultyLevel;
    }

    public void setBOTDifficultyLevel(BOTDifficultyLevel bOTDifficultyLevel) {
        BOTDifficultyLevel = bOTDifficultyLevel;
    }



    public Bot(String name, Symbol symbol, PlayerType playerType, BOTDifficultyLevel difficultyLevel) 
    {
        super(name, symbol, playerType);
        this.BOTDifficultyLevel = difficultyLevel;
        this.botPlayingStratergy = BOTPlayingStratergyFactory.getBotPlayingStratergy(difficultyLevel);
    }
    
}

package com.tic_tac_toe.stratergy.BOTStratergies;

import com.tic_tac_toe.models.Board;
import com.tic_tac_toe.models.Cell;

public interface BOTPlayingStratergy {
    Cell chooseToCellPlay(Board board);
}

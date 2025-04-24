package com.tic_tac_toe.stratergy.WinnerStratergy;

import com.tic_tac_toe.models.Move;

public interface WinnerStratergy {
    boolean checkWinner(Move move,int N);

    void handleUndo(Move move, int n);
}

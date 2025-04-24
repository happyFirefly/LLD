package com.tic_tac_toe.controllers;

import java.util.List;

import com.tic_tac_toe.exceptions.BOTCountInvalidException;
import com.tic_tac_toe.exceptions.PlayerCountNotValidException;
import com.tic_tac_toe.models.Game;
import com.tic_tac_toe.models.Player;
import com.tic_tac_toe.stratergy.WinnerStratergy.WinnerStratergy;

public class GameController {
    
    public Game startGame(int dimension, List<Player> players, List<WinnerStratergy> winningStratergy) throws BOTCountInvalidException, PlayerCountNotValidException
    {
        return Game.getBuilder().setDimension(dimension).setPlayers(players).setWinningStratergys(winningStratergy).build();
    }
    public void makeMove(Game game)
    {
        game.makeMove();
    }

    public void doUndo(Game game)
    {
        game.undo();
    }
}

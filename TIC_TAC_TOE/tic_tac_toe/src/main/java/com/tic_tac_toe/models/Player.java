package com.tic_tac_toe.models;

import java.util.Scanner;

import com.tic_tac_toe.enums.PlayerType;

public class Player {
    private String name;
    private Symbol symbol;
    private PlayerType playertype;
    private final Scanner scanner = new Scanner(System.in);

    public Player(String name, Symbol symbol, PlayerType playerType)
    {
        this.name = name;
        this.playertype = playerType;
        this.symbol = symbol;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Symbol getSymbol() {
        return symbol;
    }
    public void setSymbol(Symbol symbol) {
        this.symbol = symbol;
    }
    public PlayerType getPlayertype() {
        return playertype;
    }
    public void setPlayertype(PlayerType playertype) {
        this.playertype = playertype;
    }

    public Cell chooseCellToPlay()
    {
        int row = scanner.nextInt();
        int col = scanner.nextInt();
        return new Cell(row,col);
    }
    
}

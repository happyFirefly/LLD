package com.tic_tac_toe.models;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import com.tic_tac_toe.enums.CellState;
import com.tic_tac_toe.enums.GameState;
import com.tic_tac_toe.enums.PlayerType;
import com.tic_tac_toe.exceptions.BOTCountInvalidException;
import com.tic_tac_toe.exceptions.PlayerCountNotValidException;
import com.tic_tac_toe.stratergy.WinnerStratergy.WinnerStratergy;

public class Game {
    private Board board;
    private List<Player> player;
    private List<Move> moves;
    private Player winner;
    private int nextPlayerTurnIndex;
    private GameState gameState;
    List<WinnerStratergy> winningStratergys;

    private Game(int dimension, List<Player> player, List<WinnerStratergy> winnerStratergies )
    {
        this.board = new Board(dimension);
        this.player = player;
        this.winningStratergys = winnerStratergies;
        this.moves = new ArrayList<>();
        this.winner = null;
        this.gameState = GameState.IN_PROGRESS;
        this.nextPlayerTurnIndex = 0;

    }
    public Board getBoard() {
        return board;
    }
    public void setBoard(Board board) {
        this.board = board;
    }
    public List<Player> getPlayer() {
        return player;
    }
    public void setPlayer(List<Player> player) {
        this.player = player;
    }
    public List<Move> getMoves() {
        return moves;
    }
    public void setMoves(List<Move> moves) {
        this.moves = moves;
    }
    public Player getWinner() {
        return winner;
    }
    public void setWinner(Player winner) {
        this.winner = winner;
    }
    public int getNextPlayerTurnIndex() {
        return nextPlayerTurnIndex;
    }
    public void setNextPlayerTurnIndex(int nextPlayerTurnIndex) {
        this.nextPlayerTurnIndex = nextPlayerTurnIndex;
    }
    public void printBoard(){}

    public void undo(){
        if(moves.size() == 0) return;

        Move lastMove = moves.get(moves.size() -1);
        moves.remove(moves.size() -1);

        Cell cell = lastMove.getCell();
        cell.setCellstate(CellState.EMPTY);
        cell.setPlayer(null);

        nextPlayerTurnIndex = (nextPlayerTurnIndex - 1 + player.size())%player.size();

    }

    private boolean validateMove(int col, int row)
    {
        if( row < 0 || col < 0 || col >= board.getDimension() || row >= board.getDimension())
        {
            return false;
        }
        if(board.getBoard().get(row).get(col).getCellstate().equals(CellState.FILLED)) return false;
        return true;
    }
    public void makeMove()
    {
        Player currPlayer = player.get(nextPlayerTurnIndex);
        System.out.println("Its curr player turn : "+currPlayer.getName());

        Cell dummyCell = currPlayer.chooseCellToPlay();
        
        if(!validateMove(dummyCell.getCol(), dummyCell.getRow())){
        System.out.println("Invalid Move");
        return;
        }

        Cell cell = board.getBoard().get(dummyCell.getRow()).get(dummyCell.getCol());
        cell.setCellstate(CellState.FILLED);
        cell.setPlayer(currPlayer);

        Move move = new Move(cell, currPlayer);
        moves.add(move);

        nextPlayerTurnIndex = (nextPlayerTurnIndex+1)% player.size();

        if(checkWinner(move))
        {
            gameState = GameState.ENDED;
            winner = currPlayer;
        }else if(moves.size() == board.getDimension()*board.getDimension())
        {
            gameState = GameState.DRAW;
        }
        return;

    }

    private boolean checkWinner(Move move)
    {
        for(WinnerStratergy winning : winningStratergys)
        {
            if(winning.checkWinner(move, board.getDimension()))return true;
        }
        return false;
    }

    // CREATE A BUILDER CLASS
    public static Builder getBuilder()
    {
        return new Builder();
    }
    public List<WinnerStratergy> getWinningStratergys() {
        return winningStratergys;
    }
    public void setWinningStratergys(List<WinnerStratergy> winningStratergys) {
        this.winningStratergys = winningStratergys;
    }

    public GameState getGameState() {
        return gameState;
    }

    public void setGameState(GameState gameState) {
        this.gameState = gameState;
    }

    public static class Builder
    {
        private int dimension;
        private List<Player> players;
        private List<WinnerStratergy> winningStratergys;

        public int getDimension() {
            return dimension;
        }

        public Builder setDimension(int dimension) {
            this.dimension = dimension;
            return this;
        }

        public List<Player> getPlayers() {
            return players;
        }

        public Builder setPlayers(List<Player> players) {
            this.players = players;
            return this;
        }

        public List<WinnerStratergy> getWinningStratergys() {
            return winningStratergys;
        }

        public Builder setWinningStratergys(List<WinnerStratergy> winningStratergys) {
            this.winningStratergys = winningStratergys;
            return this;
        }
        private void validatePlayerCount() throws PlayerCountNotValidException
        {
            if(players.size() != dimension-1)
            {
                throw new PlayerCountNotValidException("Players Count is Invalid");

            }
        }
        private void validateBotCount() throws BOTCountInvalidException
        {
            int botCount = 0;
            for(Player player: players)
            {
                if(player.getPlayertype().equals(PlayerType.BOT)) botCount++;
            }
            if(botCount > dimension - 2)
            {
                throw new BOTCountInvalidException("INVALID BOT COUNT");
            }
        }

        private void validateUniqueSymbol()
        {
            HashSet<Character> symbols = new HashSet<>();

            for(Player player : players)
            {
                Character sym = player.getSymbol().getSymbolChar();
                if(symbols.contains(sym))
                {
                    throw new RuntimeException("Need Unique Symbol Change the SYmbol");
                }
                symbols.add(player.getSymbol().getSymbolChar());
            }
            
        }
        
        private void validate() throws BOTCountInvalidException, PlayerCountNotValidException
        {
            validatePlayerCount();
            validateBotCount();
            validateUniqueSymbol();    
        }
        public Game build() throws BOTCountInvalidException, PlayerCountNotValidException
        {
            validate();
            return new Game(dimension,players,winningStratergys);
        }

        
    }




}

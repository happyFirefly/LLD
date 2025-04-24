package com.tic_tac_toe.stratergy.BOTStratergies;

import java.util.List;

import com.tic_tac_toe.enums.CellState;
import com.tic_tac_toe.models.Board;
import com.tic_tac_toe.models.Cell;

public class EasyBOTPlayingStratergy implements BOTPlayingStratergy {

    @Override
    public Cell chooseToCellPlay(Board board) {
        for(List<Cell> cells: board.getBoard())
        {
            for(Cell cell: cells)
            {
                if(cell.getCellstate() == CellState.EMPTY) return cell;
            }
        }
        return null;
    }
    
}

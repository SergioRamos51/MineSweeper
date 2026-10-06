package trabs.trab3.minesweeper.cells;

import trabs.trab3.minesweeper.Board;
import trabs.trab3.minesweeper.GameListener;

/**
 * A célula Empty quando é destapada expande
 * a açăo às oito células adjacentes.
 * Quando é informada que tem um mina adjacente coloca
 * uma célula adjacente a minas na sua posiçăo.
 */
public class Empty extends DummyCell {

    @Override
    public void adjacentMine(Board board, int line, int column) {
        board.coveredCell(line, column, new Adjacent());
    }

    @Override
    public void notifyEvent(GameListener gameListener, int line, int column) {
        gameListener.cellUncover(line, column);
    }

    @Override
    public int uncover(Board board, int line, int column){
        board.uncoveredCell(line, column, this);
        int uncovered = 0;
        for (int i = line-1; i <= line+1; i++){
            for (int j = column - 1; j <= column + 1; j++){
                    uncovered += board.getCell(i,j).uncover(board, i, j);
            }
        }
        return 1 + uncovered;
    }
}

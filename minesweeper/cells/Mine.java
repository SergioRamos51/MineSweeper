package trabs.trab3.minesweeper.cells;

import trabs.trab3.minesweeper.Board;
import trabs.trab3.minesweeper.GameListener;

/**
 * A célula Mine implementa as operaçőes correspondentes à mina.
 * Quando destapada informa o tabuleiro que foi destapada.
 * É passiva quanto à expansăo ou à informaçăo de que existe
 * uma mina adjacente.
 */
public class Mine extends DummyCell {

    @Override
    public int uncover(Board board, int line, int column) {
        board.uncoveredMine(line, column, this);
        return -1;
    }

    @Override
    public void notifyEvent(GameListener gameListener, int line, int column) {
        gameListener.mineUncover(line, column);
    }
}

package trabs.trab3.minesweeper.cells;

import trabs.trab3.minesweeper.Board;
import trabs.trab3.minesweeper.GameListener;


/**
 * Célula base, fornecem implementações "vazias" (métodos
 * com corpo vazio) da interface Cell.
 * As classes que a estedem apenas têm de implementar
 * os métodos que realmente precisam, sem precisar de
 * implementar todos.
 */
public class DummyCell implements Cell {
    @Override
    public int uncover(Board board, int line, int column) {
        return 0;
    }

    @Override
    public int turnBanner(Board board, int line, int column) {
         return 0;
    }

    @Override
    public void adjacentMine(Board board, int line, int column) { }

    @Override
    public void notifyEvent(GameListener gameListener, int line, int column) {}
}

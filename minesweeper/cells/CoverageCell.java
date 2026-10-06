package trabs.trab3.minesweeper.cells;

/**
 * Célula base para a células que estão por cima das
 * celulas do jogo (CoveredCell, uncoveredCell, e Banner).
 *
 * Memoriza a célula que está por baixo.
 */
public abstract class CoverageCell extends DummyCell {
    protected Cell cellDown;
    protected CoverageCell( Cell cellDown ){
        this.cellDown= cellDown;
    }
}

package trabs.trab3.minesweeper.cells;

import trabs.trab3.minesweeper.Board;
import trabs.trab3.minesweeper.GameListener;

/**
 * A célula Banner tem o comportamento de uma célula
 * destapada exceto no que se refere à operaçăo turnBanner.
 * A operação de voltar a bandeira coloca no tabuleiro
 * a que estava por baixo.
 */
public class Banner extends CoverageCell {

	public Banner(Cell cellDown) {
		super(cellDown);
	}

	@Override
	public int turnBanner(Board board, int line, int column) {
		board.setCell(line, column, cellDown);
		return -1;
	}

	@Override
	public void notifyEvent(GameListener gameListener, int line, int column) {
		gameListener.bannerPlaced(line, column);
	}
}

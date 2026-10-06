package trabs.trab3.minesweeper.cells;

import trabs.trab3.minesweeper.Board;
import trabs.trab3.minesweeper.GameListener;

/**
 * Qualquer operação efetuada sobre a célula coberta é
 * executada na célula que cobre, exceto as operações
 * de notificação e de troca de bandeira.
 * A operação de troca de bandeira coloca a celula bandeira
 * no tabuleiro.
 */
public class CoveredCell extends CoverageCell {
	public CoveredCell(Cell down ) {
		super(down);
	}

	@Override
	public int uncover(Board board, int line, int column)  {
		return cellDown.uncover( board, line, column );
	}

	@Override
	public void adjacentMine(Board board, int line, int column) {
		cellDown.adjacentMine(board, line, column);
	}

	@Override
	public int turnBanner(Board board, int line, int column) {
		board.setCell(line, column, new Banner( this ) );
		return 1;
	}

	@Override
	public void notifyEvent(GameListener gameListener, int line, int column) {
		gameListener.cellCovered(line, column);
	}
	}

package trabs.trab3.minesweeper.cells;

import trabs.trab3.minesweeper.GameListener;

/**
 * Qualquer operação sobre uma célula descoberta năo tem efeito.
 * Quem sabe notificar é a célula que está descoberta.
 */
public class UncoveredCell extends CoverageCell {
	
    /**
	 * Constrói a DiscoveryCell associando-lhe a célula que foi
	 * descoberta.
	 * @param uncovered Célula descoberta.
	 */
	public UncoveredCell(Cell uncovered ) {
		super(uncovered);
	}

	/**
	 * Informa que a célula foi descoberta.
	 */
	@Override
	public void notifyEvent(GameListener gameListener, int line, int column) {
		cellDown.notifyEvent( gameListener, line, column );
	}
}

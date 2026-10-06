package trabs.trab3.minesweeper;

import trabs.trab3.minesweeper.cells.Cell;

/**
 * A interface Board define os métodos que as classes que implementam o
 * tabuleiro do jogo devem implementar.
 * 
 * No tabuleiro săo colocadas células vazias, minas e células
 * que contęm o número de minas adjacentes, todas elas tapadas. Durante
 * o jogo săo destapadas células (uncoveredCell ) e minas (uncoveredMine),
 * săo colocadas e retiradas bandeiras.
 *
 */
public interface Board {
	/**
	 * Retorna a célula que se encontra em determinada coluna duma
	 * determinada linha.
	 * @param line Linha
	 * @param column Coluna
	 * @return A célula que se encontra na linha/coluna especificada.
	 */
	Cell getCell(int line, int column);

	/**
	 * Coloca a célula em determinada coluna duma determinada linha.
	 * @param line Linha
	 * @param column Coluna
	 * @param cell Célula a colocar.
	 */
	void setCell(int line, int column, Cell cell);

	/**
	 * Coloca uma célula tapada na posicăo (linha e coluna) especificada.
	 * @param line Linha
	 * @param column Coluna
	 * @param cell A célula a colocar.
	 */
	void coveredCell(int line, int column, Cell cell);

	/**
	 * Coloca uma célula destapada na posicăo (linha e coluna) especificada.
	 * @param line Linha
	 * @param column Coluna
	 * @param uncovered A mina que foi destapada.
	 */
	void uncoveredCell(int line, int column, Cell uncovered);

	/**
	 * Executa as ações correspondentes a mina destapada
	 * na posição (linha e coluna) especificada.
	 *
	 * @param line      Linha
	 * @param column    Coluna
	 * @param uncovered A mina que foi destapada.
	 */
	void uncoveredMine(int line, int column, Cell uncovered);
}

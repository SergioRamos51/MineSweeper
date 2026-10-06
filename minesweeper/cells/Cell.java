package trabs.trab3.minesweeper.cells;

import trabs.trab3.minesweeper.Board;
import trabs.trab3.minesweeper.GameListener;

/**
 * A interface Cell define as operaçőes que podem
 * ser executados sobre cada célula do tabuleiro. 
 * As açőes possíveis sobre células săo: destapar, 
 * colocar bandeira, expandir e informar a modificaçăo.
 */
public interface Cell {
	/** Método chamado para destapar a célula. 
	 *
	 * @param board  Tabuleiro.
	 * @param line   Linha da quadricula a destapar.
	 * @param column Coluna da quadricula a destapar.
	 * @return       Retorna o número de células descobertas.
	 */
	int uncover(Board board, int line, int column);

	/**
	 * Método chamado para colocar ou retirar bandeira. 
	 * 
	 * @param board   Tabuleiro.
	 * @param line    Linha a colocar ou retirar bandeira.
	 * @param column  Coluna a colocar ou retirar a bandeira. 
	 * @return  O se a célula já está descoberta ou
     *          1 ou -1 conforme a bandeira tenha sido colocada ou retirada;
	 */
	int turnBanner(Board board, int line, int column);
	

	/** Método a informar que a célula é adjacente a uma mina
	 *  (chamado na construçăo do tabuleiro sobre as células que
	 *  estăo a  djacentes a minas).
	 * 
	 * @param board  Tabuleiro.
	 * @param line   Linha da célula adjacente ŕ mina.
	 * @param column Coluna da célula adjacente ŕ mina.
	 * @throws IllegalStateException - se a quadricula já estiver destapada.   
	 */
	void adjacentMine(Board board, int line, int column);
	
	/** Método para notificar  a mudança de estado da célula.
	 * 
	 * @param gameListener Listener do jogo. É notificado quando as células se modificam.
	 * @param line   Linha.
	 * @param column Coluna.
	 */
	void notifyEvent(GameListener gameListener, int line, int column);


}

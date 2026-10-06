package trabs.trab3.minesweeper;

/**
 * <p>A interface GameListener define os métodos que as classes que
 * querem ser notificadas das modificaçőes no jogo minesweeper devem
 * implementar.</p>
 * 
 * <p>Tem métodos especificos de modificaçăo das peças, vazia,
 * número minas adjacentes e mina, assim como, dos parâmetros que văo 
 * mudando ao longo do jogo tais como número de quadrículas, e número de
 * quadrículas destapadas.</p>
 * 
 * <p>Também define métodos para informar que o jogo começou ou terminou.
 * Tem dois métodos distintos para quando o jogo termina. O jogo tanto 
 * pode terminar quando o jogador ganha como quando o jogador perde. </p>
 * 
 */
public interface GameListener {
	/**
	 * O jogo foi iniciado.
	 * @param game Referęncia para o jogo que se inicia.
	 */
	void gameStart(Game game );

	/**
	 * O numero de linhas e colunas mudou.
	 * @param game Referęncia para o jogo que mudou de dimensões.
	 */
	void dimensionsChanged(Game game );

	/**
	 * Na posiçăo (linha, coluna) especificada a quadrícula foi tapada.
	 * @param line    Linha do tabuleiro da quadrícula coberta.
	 * @param column  Coluna do tabuleiro da quadrícula coberta.
	 */
	void cellCovered(int line, int column);

	/**
	 * Na posiçăo especificada(linha, coluna) foi destapada uma
	 * quadrícula vazia .
	 * @param line    Linha do tabuleirp da quadrícula vazia.
	 * @param column  Coluna do tabuleiro da quadrícula vazia.
	 */
	void cellUncover(int line, int column);

	/**
	 * Na posiçăo especificada (linha, coluna) foi destapada uma
	 * quadrícula com determinado número minas adjacentes.
	 * @param line    Linha do tabuleiro da quadrícula com minas adjacentes.
	 * @param column  Coluna do tabuleiro da quadrícula com minas adjacentes.
	 * @param number   Número de minas adjacentes.
	 */
	void cellUncover(int line, int column, int number);

	/**
	 * Na posiçăo especificada (linha, coluna) foi destapada uma mina.
	 * @param line    Linha do tabuleiro da quadrícula com mina.
	 * @param column  Coluna do tabuleiro da quadrícula com mina.
	 * 
	 */
	void mineUncover(int line, int column);
	
	/**
	 * Na posiçăo (linha, coluna) especificada foi colocada uma bandeira.
	 * @param line    Linha do tabuleiro da quadrícula com bandeira.
	 * @param column  Coluna do tabuleiro da quadrícula com bandeira.
	 */
	void bannerPlaced(int line, int column);

	/**
	 * O número de bandeiras marcadas no tabuleiro mudou.
	 * @param number Número de bandeiras marcadas.
	 */
	void numberOfBannersChanged(int number);

	/**
	 * O jogo terminou porque o jogador ganhou.
	 * 
	 */
	void playerWin();
	
	/**
	 * O jogo terminou porque o jogador perdeu.
	 * 
	 */
	void playerLose();
}

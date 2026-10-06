package trabs.trab3.minesweeper;

/**
 * <p>A interface Game define os métodos que as classes que implementam a
 * lógica do jogo minesweeper devem implementar.</p>
 * 
 * <p>O jogo desenrolasse num tabuleiro de NxM quadrículas, 
 * em que N (número linhas) e M (número de colunas) dependem do nível 
 * de dificuldade. Inicialmente as quadrículas estăo todas cobertas, 
 * embora possam conter minas ou números que identificam o número de 
 * minas nas oito quadrículas adjacentes.</p>
 * 
 * <p>O objetivo do jogo é detetar a localizaçăo de minas. Para atingir este
 * objetivo o jogador terá que destapar (uncover) todas as quadrículas
 * evitando as minas.</p>
 * 
 * <p>O jogador pode marcar ou desmarcar uma quadrícula, colocando ou 
 * retirando uma bandeira (turnBanner). Coloca a bandeira quando 
 * suspeita que a quadrícula esconde uma mina, para năo a destapar 
 * inadvertidamente. Retira a bandeira quando verifica que a suspeita
 * era infundada.</p>
 * 
 * <p>O jogo permite que sejam registados o listeners (GameLister) os
 * quais săo informados sobre as modificaçőes que ocorrem no
 * tabuleiro desde o início até ao fim do jogo. 
 * </p>
 * 
 */
public interface Game {

	/**
	 * Inicia um novo jogo contendo um tabuleiro com o número de linhas,
	 * número de colunas e número de minas especificadas.
	 * 
	 * @param numberOfLines   Número de linhas do novo jogo.
	 * @param numberOfColumns Número de colunas do novo jogo.
	 * @param numberOfMines   Número de minas do novo jogo.
	 */
	void start(int numberOfLines, int numberOfColumns, int numberOfMines);

	/**
	 * Executa as açőes que correspondem a destapar uma quadrícula.
	 * Quando o jogador destapa uma quadrícula, caso esta seja:
	 *    - Mina, termina o jogo.
	 *    - Quadrícula vazia, é destapada a quadrícula e todas as 
	 *      quadrículas adjacentes que năo contenham minas de forma 
	 *      recorrente (expande a acçăo pelos adjacentes).
	 *    - Número, só é descoberto o próprio número.
	 * A linha da quadrícula a destapar varia entre 0 e getNumberOfLines()-1,
	 * a coluna varia entre 0 e getNumberOfColumns()-1.
	 * 
	 * @param line     Linha da quadrícula que se quer destapar.
	 * @param column   Coluna da quadrícula que se quer destapar.
	 * @return         true se destapou a quadrícula, false caso contrário.
	 */
	boolean uncover(int line, int column);

	/**
	 * Marca e desmarca com a bandeira (virar bandeira) uma quadrícula
	 * A linha varia entre 0 e getNmberOfLines() - 1, a coluna varia entre
	 * 0 e getNumberOfColumns() - 1.
	 * @param line    Linha da quadrícula que se quer virar.
	 * @param column  Coluna da quadrícula que se quer virar.
	 * @return        true se foi colocada a bandeira.
	 */
	boolean turnBanner(int line, int column);
	
	/**
	 * Regista o visualizador do jogo.
	 * @param gameListener  Visualizador do jogo.
	 * @throws NullPointerException Se viewer == null.
	 */
	void addGameListener(GameListener gameListener);

	/**
	 * Retorna o número de linhas do tabuleiro.
	 * @return Número de linhas.
	 */
	int getNumberOfLines();

	/**
	 * Retorna o número de colunas do tabuleiro.
	 * @return Número de colunas.
	 */
	int getNumberOfColumns();

	/**
	 * Retorna o número de minas existentes no tabuleiro.
	 * @return Número de minas.
	 */
	int getNumberOfMines();  
	
	/**
	 * Retorna o número de bandeiras marcadas no tabuleiro.
	 * @return Número de bandeiras.
	 */
	int getNumberOfBanners();  
	
	/**
	 * Retorna o número de quadrículas tapadas.
	 * @return Número de quadrículas tapadas.
	 */
	int getNumberOfHidden();
}

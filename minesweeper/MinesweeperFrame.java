package trabs.trab3.minesweeper;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;

import java.util.function.BiConsumer;

import trabs.trab3.minesweeper.gui.*;
import static trabs.trab3.minesweeper.gui.Utils.*;

/**
 * <p> Implementa um visualizador em modo grafico.</p>
 * <p> O tabuleiro do jogo é implementado com um JPanel contendo
 *     componentes graficas do tipo Square. O layout é um GridLayout
 *     com o numero de linhas e de colunas do objecto Game ao qual é
 *     associado na construcao, e no inicio de cada jogo.
 * </p>
 * <p> Quando  é informado que algo mudou no jogo (vazia, mina,
 *     adjacent a mina, ou bandeira) altera o caracter da matriz de
 *     acordo com o tipo de pedido e o aspecto visual da quadricula.
 * </p>
 * <p> Quando é informado do inicio do jogo, caso a dimensao do tabuleiro
 *     seja alterada cria um novo tabuleiro.
 * </p>
 * <p> Quando o jogo termina quer seja porque o jogador tenha ganho, ou
 *     porque tenha perdido informa o jogador do fim do jogo.</p>
 *     
 * <p> Alem de ser um visualizador, com esta interface grafica tambem é
 *     possivel desencadear as operações de descobrir quadriculas, colocar e
 *     retirar bandeiras, ou iniciar o jogo.
 * </p>
 * <p> Fazendo um click com o botão esquerdo do mouse sobre uma quadricula,
 *     é possivel enviar a ordem ao jogo para descobrir a quadricula. </p>
 * <p> Fazendo um click com o botão direito do mouse sobre uma quadricula,
 *     é possivel enviar a ordem ao jogo para virar a bandeira. </p>
 * <p> Usando as opções do menu bar é possivel enviar a ordem ao jogo de
 *     inicio de jogo, com ou sem mudança de dimensões.
 * 
 */
public class MinesweeperFrame extends JFrame implements GameListener {
	public static final Border INSIDE_BORDER =
			new CompoundBorder( new BevelBorder(BevelBorder.LOWERED),
					new EmptyBorder(2,2,2,2)
			);
	public static final Border OUTSIDE_BORDER = new CompoundBorder(
			new BevelBorder(BevelBorder.RAISED),
			new CompoundBorder( new EmptyBorder(1,1,1,1),
					new BevelBorder(BevelBorder.LOWERED)));
	private static final ImageIcon SMILE_ICON = getImageIcon("smile.jpg", "smile");
	private static final ImageIcon MOAN_ICON = getImageIcon("moan.jpg", "moan");
	private static final ImageIcon ZERO_ICON = getImageIcon("0.gif", "zero");

	private JLabel[] digitLabelsTimer = new JLabel[3];
	private JLabel[] digitLabelsBanners = new JLabel[3];
	private ImageIcon[] digitsBanner = new ImageIcon[10];
	private JPanel leftPanel = new JPanel(new GridLayout(1, 3));
	private JPanel rightPanel = new JPanel(new GridLayout(1, 3));

	private Timer timer;

	private BiConsumer<Integer, Integer> showAction(String filename, String text){
		ImageIcon img = getImageIcon(filename, text);
		return img == null
				? (l, c)-> getSquare(l, c).show(text)
				: (l, c)-> getSquare(l, c).show(img);
	}
	private final BiConsumer<Integer, Integer> showMine = showAction("mine.jpg", "M");
	private BiConsumer<Integer, Integer> showBanner = showAction("banner.jpg", "B");

	private JComponent board;
	private Game game;

	private long initialTime = System.currentTimeMillis();

	public MinesweeperFrame(Game g ){
		super("Minesweeper");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		game = g;

		Container cp = getContentPane();
		((JComponent)cp).setBorder(OUTSIDE_BORDER);
		cp.add( board = makeBoardComponent(game), BorderLayout.CENTER);

		game.addGameListener(this);

		// Acrescentar o component de informaçăo com: o número de bandeiras;
		// o botăo de start; e o tempo de jogo em segundos.
		cp.add( makeInfoComponent(), BorderLayout.NORTH);
		// TODO - Acrescentar o menu
		JMenuBar menuBar = new JMenuBar();
		menuBar.add(makeMenuOption(game));
		setJMenuBar(menuBar);
		this.setResizable(false);
		pack();
	}

	/**
	 * Cria o Panel superior. No lado superior esquerdo o número
	 * de  bandeiras que  estão marcadas, no  meio um  botão que
	 * permite o  início do  jogo, e no  lado  superior esquerdo
	 * o número de minutos de jogo.
	 */
	private JComponent makeInfoComponent() {
		JPanel infoPanel = new JPanel();
		infoPanel.setLayout(new BorderLayout());

		// Painel da esquerda (contador de banners)
		int numberOfBanners = game.getNumberOfMines() - game.getNumberOfBanners();
		for (int i = 0; i < 3; i++) {
			leftPanel.add(digitLabelsBanners[i]);
		}

		for (int i = 0; i < 10; i++) {
			digitsBanner[i] = getImageIcon(i + ".GIF", Integer.toString(i));
		}
		digitLabelsBanners[0].setIcon(digitsBanner[(int) (numberOfBanners / 100) % 10]);
		digitLabelsBanners[1].setIcon(digitsBanner[(int) (numberOfBanners / 10) % 10]);
		digitLabelsBanners[2].setIcon(digitsBanner[(int) numberOfBanners % 10]);

		infoPanel.add(leftPanel, BorderLayout.WEST);

		// Painel da direita (contador de tempo)
		for (int i = 0; i < 3; i++) {
			digitLabelsTimer[i] = new JLabel(ZERO_ICON);
			rightPanel.add(digitLabelsTimer[i]);
		}
		infoPanel.add(rightPanel, BorderLayout.EAST);

		timer = new Timer(1000, e-> {
			long currentTime = System.currentTimeMillis();
			long elapsedTime = (currentTime - initialTime) / 1000;
			ImageIcon[] digits = new ImageIcon[10];
			for (int i = 0; i < 10; i++) {
				digits[i] = getImageIcon(i + ".GIF", Integer.toString(i));
			}
			digitLabelsTimer[0].setIcon(digits[(int) (elapsedTime / 100) % 10]);
			digitLabelsTimer[1].setIcon(digits[(int) (elapsedTime / 10) % 10]);
			digitLabelsTimer[2].setIcon(digits[(int) elapsedTime % 10]);
		});

		// Botão central (ícone de smile)
		JButton startButton = new JButton(SMILE_ICON);
		startButton.addActionListener(e -> timer.start());
		startButton.addActionListener(e -> game.start(game.getNumberOfLines(), game.getNumberOfColumns(), game.getNumberOfMines()));
		infoPanel.add(startButton, BorderLayout.CENTER);

		return infoPanel;
	}

	/**
	 * Cria um menu com os itens de iniciaçăo de um novo jogo
	 * (iniciar o jogo corrente, e as tręs diferentes
	 * dimensőes de tabuleiro):
	 *  - "beginner" 9 x 9, 10 mines
	 *  - "intermediate" 16 x 16, 40 mines
	 *  - "advanced" 16 x 30, 99 mines
	 * @param game jogo
	 * @return A referęncia para o JMenu
	 */
	protected JMenu makeMenuOption(Game game) {
		JMenu optionsMenu = new JMenu( "options" );
		// TODO Auto-generated method stub
		JMenuItem beginner = new JMenuItem("beginner");
		JMenuItem intermediate = new JMenuItem("intermediate");
		JMenuItem advanced = new JMenuItem("advanced");
		optionsMenu.add(beginner);
		optionsMenu.add(intermediate);
		optionsMenu.add(advanced);
		beginner.addActionListener( e -> game.start(9, 9, 10));
		intermediate.addActionListener( e -> game.start(16, 16, 40));
		advanced.addActionListener( e -> game.start(16, 30, 99));
		return optionsMenu;
	}

	/**
	 * Cria o componente quadricula na linha coluna especificada e
	 * adiciona o mouse listener para que quando for premido
	 * o botão esquerdo do mouse seja descoberta uma quadricula,
	 * quando premido o botão direiro coloca ou retira a bandeira.
	 */
	private Square makeSquare(int l, int c) {
		Square square = new Square( l, c );
		square.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent em) {
				if (em.getButton() == MouseEvent.BUTTON1 )
					game.uncover( l, c );
				else
					game.turnBanner(l, c );
			}
		});
		return square;
	}
	/**
	 * <p>Cria o componente gráfico (tabuleiro) para
	 * visualização das quadriculas e os respectivos componentes gráficos
	 * que representam cada quadricula. </p>
	 * <p>Adiciona a cada quadricula um mouse listener. Quando for premido
	 * o botão esquerdo do mouse seja descoberta uma quadricula, quando
	 * premido o botão direiro coloca ou retira a bandeira.</p>
	 */
	protected JComponent makeBoardComponent(  Game game ) {
		int numberOfLines = game.getNumberOfLines();
		int numberOfColumns = game.getNumberOfColumns();
		// Criar o componente tabuleiro.
		JPanel board = new JPanel( new GridLayout( numberOfLines, numberOfColumns) );
		board.setBorder(INSIDE_BORDER);
		for (int l = 0; l < numberOfLines; ++l)
			for ( int c= 0; c < numberOfColumns; ++c)
				// Adicionar a quadricula ao tabuleiro
				board.add( makeSquare( l, c ) );
		return board;
	}

	@Override
	public void gameStart( Game game ) {
		// TODO -
		// Iniciar o timer
		initialTime = System.currentTimeMillis();
		timer.restart();
	}

	@Override
	public void dimensionsChanged(Game game) {
		// TODO -
		// Refazer o tabuleiro com as nova dimensoes
		getContentPane().remove(board);
		getContentPane().add( board = makeBoardComponent(game), BorderLayout.CENTER);
		pack();
	}

	/**
	 * Obter o componente quadricula que se encontra no na linha e na coluna
	 * especificada.
	 * @param line Linha.
	 * @param column Coluna
	 * @return A referencia para o componente gráfico que representa
	 *         a quadricula.
	 */
	private Square getSquare(int line, int column) {
		return (Square)board.getComponent( line*game.getNumberOfColumns() + column );
	}

	@Override
	public void cellCovered(int line, int column) {
		getSquare(line, column).hide();
	}

	@Override
	public void cellUncover(int line, int column) {
		getSquare(line, column).show();
	}


	@Override
	public void cellUncover(int line, int column, int value) {
		getSquare(line,column).show(Integer.toString(value) );
	}

	@Override
	public void mineUncover(int line, int column) {
		showMine.accept( line, column);
	}

	@Override
	public void bannerPlaced(int line, int column) {
		//TODO Auto-generated method stub
		showBanner.accept( line, column);
	}

	@Override
	public void numberOfBannersChanged(int number) {
		//TODO Auto-generated method stub
		number = game.getNumberOfMines() - game.getNumberOfBanners();

		leftPanel.removeAll();
		for (int i = 0; i < 3; i++) {
			digitLabelsBanners[i] = new JLabel(ZERO_ICON);
			leftPanel.add(digitLabelsBanners[i]);
		}

		digitLabelsBanners[0].setIcon(digitsBanner[(int) (number / 100) % 10]);
		digitLabelsBanners[1].setIcon(digitsBanner[(int) (number / 10) % 10]);
		digitLabelsBanners[2].setIcon(digitsBanner[(int) number % 10]);

	}
	@Override
	public void playerWin() {
		//TODO Auto-generated method stub
		timer.stop();
		JOptionPane.showMessageDialog(this, "You won", "Game Over", JOptionPane.INFORMATION_MESSAGE, SMILE_ICON);
	}

	@Override
	public void playerLose() {
		//TODO Auto-generated method stub
		timer.stop();
		JOptionPane.showMessageDialog(this, "You lost", "Game Over", JOptionPane.INFORMATION_MESSAGE, MOAN_ICON);
	}
}

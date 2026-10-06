package trabs.trab3.minesweeper.gui;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;
import java.awt.*;

/**
 * <p> Componente gráfico para visualizar cada quadricula do jogo.</p>
 * <p> Associado a cada quadricula está a linha e a coluna em que se 
 *     encontra no tabuleiro.<p>
 * <p> Disponibiliza métodos para colocar e esconder o texto da quadrícula.</p>
 * <p> Quando é pedido para colocar texto na quadricula, é colocada uma 
 *     cercadura no componente gráfico de forma a que a quadricúla 
 *     fique para baixo como se tivesse sido premida.</p> 
 * <p> Quando é pedido para esconder o texto, é retirado o texto ou a imagem
 *     e colocada uma cercadura de forma a que a quadricúla fique em
 *     relevo.</p> 
 */
public class Square extends JLabel {
	private static final int WIDTH = 26;
	private static final int HEIGHT = 29;
	private static final Border SHOW_BORDER = new BevelBorder(BevelBorder.LOWERED);
	private static final  Border HIDE_BORDER = new BevelBorder(BevelBorder.RAISED);
	public Square(int l, int c ) {
		this.setPreferredSize( new Dimension(WIDTH, HEIGHT) );
		this.setHorizontalAlignment(CENTER);
		this.setVerticalAlignment(CENTER);
	}

	/**
	 * Destapa a quadrícula e coloca a imagem especificada.
	 * @param img TImagem a colocar.
	 */
	public void show(ImageIcon img) {
		this.setBorder(SHOW_BORDER);
		this.setIcon( img );
	}

	/**
	 * Destapa a quadrícula e no centro coloca o texto especificado.
	 * @param text Texto a ser colocado na quadrícula.
	 */
	public void show(String text) {
		this.setBorder(SHOW_BORDER);
		this.setText( text );
	}

	/**
	 * Tapa a quadrícula e retira o texto e a imagem.
	 */
	public void hide() {
		this.setBorder(HIDE_BORDER);
		this.setIcon( null );
		this.setText("");
	}
}

package trabs.trab3.minesweeper.gui;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class Display extends JPanel {
	public static String EXT = ".gif";
	private static ImageIcon imgDigits[] = initializeIcons();
	private static class Digit extends JLabel {
		public Digit() {
			setHorizontalAlignment(JLabel.CENTER);
			setVerticalAlignment(JLabel.CENTER);
			setBorder(new EmptyBorder(1, 3, 1, 3));
			setBackground(Color.BLACK);
			setForeground(Color.RED);
			setFont(getFont().deriveFont(26.0F));
		}
		public void setDigit( int v ) {
			if (imgDigits != null)
				setIcon( imgDigits[v]);
			else
				setText( Integer.toString( v ));
		}
	}

	private Digit[] digits;
	public Display(int nd, int v ) {
		digits = new Digit[nd];
		setLayout(new BoxLayout(this, BoxLayout.X_AXIS) );
		setBackground(Color.BLACK);
		for (int i = 0; i < digits.length; ++i) {
			digits[i] = new Digit();
			add( (JComponent) digits[i] );
		}
		setValue( v );
	}

	public void setValue(int v) {
		for (int i = digits.length-1; i >= 0; --i) {
			digits[i].setDigit(v%10);
			v = v/10;
		}
	}

	private static ImageIcon[] initializeIcons() {
		ImageIcon[] imgDigits = new ImageIcon[10];
		for (int i= 0; i < imgDigits.length; ++i) {
			imgDigits[i] = Utils.getImageIcon(i + EXT,i + "");
			if ( imgDigits[i] == null ){
				return null;
			}
		}
		return imgDigits;
	}


	public static void main(String[] s) {
		JFrame f = new JFrame("VISOR DIGITAL");
		f.setLayout( new FlowLayout());
		f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		f.add( new Display( 2, 10 ));
		f.add( new Display( 3, 123 ));
		f.pack();
		f.setVisible(true);
	}
}

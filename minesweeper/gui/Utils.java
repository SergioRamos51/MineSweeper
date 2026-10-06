package trabs.trab3.minesweeper.gui;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionListener;
import java.io.File;


public class Utils {
    public static String directory = "C:\\Users\\sergi\\IdeaProjects\\PG3\\src\\trabs\\trab3\\minesweeperImages\\";

    public static ImageIcon getImageIcon(String path, String description ) {
        File f = new File( directory + path );
        if ( !f.exists() ) return null ;
        return new ImageIcon(directory + path, description);
    }

    /**
     * Cria um item de menu qi associar a açăo.
     * @param text - Texto do item.
     * @param action - Açăo.
     * @return o item
     */
    public static JMenuItem makeJMenuItem(String text, ActionListener action) {
        JMenuItem mi = new JMenuItem( text );
        mi.addActionListener( action );
        return mi;
    }

}

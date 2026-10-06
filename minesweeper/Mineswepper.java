package trabs.trab3.minesweeper;


public class Mineswepper {
    public static void main(String[] args ) {
        new MinesweeperFrame(new GameMinesweeper(9, 9, 10)).setVisible(true);
    }
}

import java.awt.Component;
import javax.swing.JFrame;

class Main {
  public static void main(String[] args) {
    JFrame frame = new JFrame("Sokoban Game");
    frame.setSize(32 * 18 + 10, 32 * 12 + 29);
    Game theGame = new Game();
    ((Component) theGame).setFocusable(true);

    frame.add(theGame);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    frame.setVisible(true);
    frame.setResizable(false);
    frame.pack();
  }
}
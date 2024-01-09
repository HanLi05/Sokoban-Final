import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.io.IOException;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class Game extends Canvas implements KeyListener, Runnable {
  private boolean[] keys;
  private Level level;
  private BufferedImage back;
  private Stats stats;
  private Person playerCharacter;

  public Game() {
    keys = new boolean[8];
    playerCharacter = new Person(0, 0);
    try {
      level = new Level(LevelReader.getLevel(0), playerCharacter);
      stats = new Stats(LevelReader.getTotalLevels());

    } catch (Exception e) {
      System.out.println("Exception !! " + e);
    }
    this.addKeyListener(this);
    new Thread(this).start();
    setVisible(true);

  }

  public void update(Graphics window) {
    paint(window);
  }

  public void loadLevel(int num) throws UnsupportedAudioFileException, IOException, LineUnavailableException {
    if (level.isSolved()) {
      stats.levelOver();
      // aud.changeSong();
    } else if (stats.getLevel() != num) {
      // aud.changeSong();
    }
    stats.resetScore();
    stats.setLevel(num);
    try {
      level = new Level(LevelReader.getLevel(stats.getLevel()), playerCharacter);
    } catch (Exception e) {
      System.out.println(e + "\nError loading level!!");
    }
  }

  public void paint(Graphics window) {
    // set up the double buffering to make the game animation nice and smooth
    Graphics2D twoDGraph = (Graphics2D) window;

    // take a snap shop of the current screen and same it as an image
    // that is the exact same width and height as the current screen
    if (back == null)
      back = (BufferedImage) (createImage(getWidth(), getHeight()));

    // create a graphics reference to the back ground image
    // we will draw all changes on the background image
    Graphics graphToBack = back.createGraphics();
    level.draw(graphToBack);

    if (keys[0]) {
      if (level.move("LEFT")) {
        stats.incrementScore();
        /*
         * if (level.getPerson().getFacing().equals("RIGHT"))
         * level.getPerson().changeFacing("LEFT");
         */
      }
      keys[0] = false;
    }
    if (keys[1]) {
      if (level.move("RIGHT"))
        stats.incrementScore();
      /*
       * if (level.getPerson().getFacing().equals("LEFT"))
       * level.getPerson().changeFacing("RIGHT");
       */
      keys[1] = false;
    }
    if (keys[2]) {
      if (level.move("UP"))
        stats.incrementScore();
      keys[2] = false;
    }
    if (keys[3]) {
      if (level.move("DOWN"))
        stats.incrementScore();
      keys[3] = false;
    }
    try {
      if (keys[4]) {
        System.out.println("Restarting level " + stats.getLevel() + " (Restarting)");
        keys[4] = false;
        loadLevel(stats.getLevel());
      }
      if (keys[5]) {
        System.out.println("Playing bell sound");
        // bell.play();
        level.getPerson().changeSkin();
        keys[5] = false;
      }
      if (keys[6]) {
        loadLevel(stats.getLevel() + 1);
        keys[6] = false;
      }
      if (keys[7]) {
        loadLevel(stats.getLevel() - 1);
        keys[7] = false;
      }
      if (level.isSolved()) {
        System.out.println("Done with level " + stats.getLevel() + " (Solved)");
        try {
          loadLevel(stats.getLevel() + 1);
        } catch (Exception e) {
          System.out.println(e);
        }

      }
    } catch (Exception e) {
      System.out.println("You messed up dummy " + e);
    }

    // level.writeScore(graphToBack);
    stats.draw(graphToBack);
    twoDGraph.drawImage(back, null, 0, 0);
  }

  public void keyPressed(KeyEvent e) {
    if (e.getKeyCode() == KeyEvent.VK_LEFT) {
      keys[0] = true;
    }
    if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
      keys[1] = true;
    }
    if (e.getKeyCode() == KeyEvent.VK_UP) {
      keys[2] = true;
    }
    if (e.getKeyCode() == KeyEvent.VK_DOWN) {
      keys[3] = true;
    }
    if (e.getKeyCode() == KeyEvent.VK_R) {
      keys[4] = true;
    }
    if (e.getKeyCode() == KeyEvent.VK_P) {
      keys[5] = true;
    }
    if (e.getKeyCode() == KeyEvent.VK_N) {
      keys[6] = true;
    }
    if (e.getKeyCode() == KeyEvent.VK_B) {
      keys[7] = true;
    }
    repaint();
  }

  public void keyReleased(KeyEvent e) {
    if (e.getKeyCode() == KeyEvent.VK_LEFT) {
      keys[0] = false;
    }
    if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
      keys[1] = false;
    }
    if (e.getKeyCode() == KeyEvent.VK_UP) {
      keys[2] = false;
    }
    if (e.getKeyCode() == KeyEvent.VK_DOWN) {
      keys[3] = false;
    }
    if (e.getKeyCode() == KeyEvent.VK_R) {
      keys[4] = false;
    }
    if (e.getKeyCode() == KeyEvent.VK_P) {
      keys[5] = false;
    }
    repaint();
  }

  public void keyTyped(KeyEvent e) {

  }

  public void run() {
    try {
      while (true) {
        Thread.currentThread();
        Thread.sleep(5);
        repaint();
      }
    } catch (Exception e) {
    }
  }

}
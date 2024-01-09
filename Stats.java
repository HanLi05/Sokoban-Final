import java.awt.Graphics;
import java.awt.Font;
import java.awt.Color;

public class Stats {
  private int currentScore;
  private int highestScore;
  private int currentLevel;
  private int highestLevel;
  private HighScores highScores;

  public Stats(int highestLevel) {
    this.highestLevel = highestLevel;
    try {
      highScores = new HighScores(highestLevel);
    } catch (Exception e) {
      System.out.println(e);
    }
    setLevel(0);
  }

  public void setHighest() {
    highestScore = highScores.getHighScore(currentLevel);
  }

  public void incrementScore() {
    currentScore++;
  }

  public void resetScore() {
    currentScore = 0;
  }

  public void setLevel(int num) {
    if (num > highestLevel || num < 0) {
      return;
    }
    currentLevel = num;
    resetScore();
    setHighest();
  }

  public void incrementLevel() {
    setLevel(currentLevel + 1);
  }

  public void decrementLevel() {
    setLevel(currentLevel - 1);
  }

  public void levelOver() {
    if (currentScore < highestScore || highestScore == -1) {
      try {
        int hi[] = highScores.getHighScores(highestLevel + 1);
        hi[currentLevel] = currentScore;
        highScores.setHighScores(hi);
      } catch (Exception e) {
        System.out.println(e);
      }
    }
  }

  public int getLevel() {
    return currentLevel;
  }

  public void draw(Graphics window) {
    window.setColor(Color.gray);
    window.setFont(new Font("Monospace", Font.BOLD, 16));
    window.drawString("Your Score: " + currentScore, 5, 30);
    window.setColor(Color.yellow);
    window.setFont(new Font("Monospace", Font.BOLD, 16));
    window.drawString("Your Score: " + currentScore, 6, 29);
    int fontHeight = window.getFontMetrics().getHeight();
    window.setColor(Color.gray);
    window.setFont(new Font("Monospace", Font.BOLD, 16));
    window.drawString("Highest Score: " + highestScore, 5, 30 + fontHeight);
    window.setColor(Color.yellow);
    window.setFont(new Font("Monospace", Font.BOLD, 16));
    window.drawString("Highest Score: " + highestScore, 6, 29 + fontHeight);
    window.setColor(Color.gray);
    window.setFont(new Font("Monospace", Font.BOLD, 16));
    window.drawString("Level " + currentLevel + " out of " + highestLevel, 5, 30 + fontHeight * 2);
    window.setColor(Color.yellow);
    window.setFont(new Font("Monospace", Font.BOLD, 16));
    window.drawString("Level " + currentLevel + " out of " + highestLevel, 6, 29 + fontHeight * 2);
  }
}
import java.util.Scanner;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class HighScores {
  private int[] highScores;

  // returns the score class for the level number (first level is lvlNum = 0)
  public int getHighScore(int lvlNum) {
    return highScores[lvlNum];
  }

  // initializes the scores list and writes to the highscores file if it is empty
  public HighScores(int numLevels) throws IOException {
    try {
      highScores = getHighScores(numLevels);
      setHighScores(highScores);
    } catch (Exception e) {
      System.out.println(e);
    }
  }

  public void setHighScores(int[] highScoresList) throws IOException {
    File scoreText = new File("highscores.txt");
    FileWriter writer = new FileWriter(scoreText);
    for (int i = 0; i < highScoresList.length; i++) {
      if (i == highScoresList.length - 1) {
        writer.write(highScoresList[i] + "");
      } else {
        writer.write(highScoresList[i] + "\n");
      }
    }
    writer.close();
  }

  public int[] getHighScores(int numLevels) throws IOException {
    highScores = new int[numLevels + 1];
    File scoreText = new File("highscores.txt");
    Scanner s = new Scanner(scoreText);
    for (int i = 0; i <= numLevels; i++) {
      if (!s.hasNextInt()) {
        highScores[i] = -1;
      } else {
        highScores[i] = s.nextInt();
      }
    }
    s.close();
    return highScores;
  }

  public int[] getHighScoresList() {
    return highScores;
  }

  // updates the highscore for all levels and returns the amount of new highscores
  // (i don't know if we need to return the # of new highscores so feel free to
  // get rid of that feature)
  public int updateHighScores() throws IOException {
    int newHighScores = 0;
    int temp;
    File scoreText = new File("highscores.txt");
    Scanner s = new Scanner(scoreText);
    FileWriter writer = new FileWriter("highscores.txt");
    for (int i = 0; i < highScores.length; i++) {
      temp = s.nextInt();
      if (temp > highScores[i])
        newHighScores++;
      writer.append("" + highScores[i] + "\n");
    }
    writer.flush();
    s.close();
    writer.close();

    return newHighScores;
  }
}
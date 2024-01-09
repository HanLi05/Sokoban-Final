import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class LevelReader {
  public static int levels;

  public static char[][] getLevel(int x) throws IOException {
    Scanner file = new Scanner(new File("levels.txt"));
    int num = 13 * x + 10;
    char[][] output = new char[12][18];
    String str = "";
    for (int i = 1; i < num; i++) {
      str = file.nextLine();
    }
    str = file.nextLine();
    for (int i = 0; i < 12; i++) {
      for (int j = 0; j < str.length(); j++) {
        output[i][j] = str.charAt(j);
      }
      str = file.nextLine();
    }
    for (char[] ys : output) {
      for (char xs : ys) {
        System.out.print(xs);
      }
      System.out.println();
    }
    return output;

  }

  public static int getTotalLevels() throws IOException {
    int num = 0;
    Scanner file = new Scanner(new File("levels.txt"));
    while (file.hasNext()) {
      if (file.hasNextInt())
        num = file.nextInt();
      else
        file.next();
    }
    return num;
  }
}
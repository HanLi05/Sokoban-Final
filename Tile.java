import java.awt.Graphics;
import java.awt.Image;
import java.net.URL;
import javax.imageio.ImageIO;
import java.util.regex.Pattern;
import java.lang.IllegalArgumentException;

public abstract class Tile {
  private int xPos;
  private int yPos;
  private int width;
  private int height;
  private Image image;

  public Tile(int x, int y, String imageURL) {
    xPos = x;
    yPos = y;
    width = 32;
    height = 32;
    setImage(imageURL);
  }

  public void setPos(int x, int y) {
    xPos = x;
    yPos = y;
  }

  public void setX(int x) {
    xPos = x;
  }

  public void setY(int y) {
    yPos = y;
  }

  public int getX() {
    return xPos;
  }

  public int getY() {
    return yPos;
  }

  public int getWidth() {
    return width;
  }

  public int getHeight() {
    return height;
  }

  public Image getImage() {
    return image;
  }

  public void setWidth(int s) {
    width = s;
  }

  public void setHeight(int h) {
    height = h;
  }

  public void setImage(String newImgStr) {

    try {
      checkValidURL(newImgStr);
      URL url = getClass().getResource(newImgStr);
      image = ImageIO.read(url);
    } catch (Exception e) {
      System.out.println(e);
    }
  }

  public void checkValidURL(String newImageStr) throws IllegalArgumentException {
    if (!newImageStr.matches(".+.[png|jpg]")) {
      throw new IllegalArgumentException("Invalid Image URL");
    }
  }

  public void draw(Graphics window, int row, int col) {
    window.drawImage(image, row * width, col * height, width, height, null);
  }

  public String toString() {
    return getX() + " " + getY();
  }
}
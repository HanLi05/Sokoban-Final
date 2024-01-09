import java.awt.Graphics;

public class Crate extends Tile {
  public Crate(int x, int y) {
    super(x, y, "images/crate.png");
  }

  @Override
  public void draw(Graphics window, int row, int col) {
    window.drawImage(super.getImage(), super.getWidth() / 4 + row * super.getWidth(),
        super.getHeight() / 4 + super.getHeight() * col, super.getWidth() / 2, super.getHeight() / 2, null);
  }
}
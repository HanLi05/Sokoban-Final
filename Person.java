import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;

import java.net.URL;

public class Person extends Tile {
  private final String BUMPY = "images/bumpy.png";
  private final String STEVE = "images/steve.png";
  private final String SONIC = "images/sonic.png";
  private int counter;
  private String facing = "RIGHT";

  public Person(int x, int y) {
    super(x, y, "images/bumpy.png");
    counter = 0;
  }

  public void changeFacing(String dir) {
    BufferedImage image;
    String current = "";
    try {
      if (counter % 3 == 0) {
        image = ImageIO.read(getClass().getResource(BUMPY));
        current = BUMPY;
      } else if (counter % 3 == 1) {
        image = ImageIO.read(getClass().getResource(STEVE));
        current = STEVE;
      } else {
        image = ImageIO.read(getClass().getResource(SONIC));
        current = SONIC;
      }

      AffineTransform tx = AffineTransform.getScaleInstance(-1, 1);
      tx.translate(-image.getWidth(null), 0);
      AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_NEAREST_NEIGHBOR);
      image = op.filter(image, null);
      File outputFile = new File("images/dynamic.png");
      ImageIO.write(image, "png", outputFile);
      if (dir.equals("LEFT")) {
        setImage("images/dynamic.png");
        facing = "LEFT";
      }
      if (dir.equals("RIGHT")) {
        setImage(current);
        facing = "RIGHT";
      }
    } catch (Exception e) {
      System.out.println("ERROR in ImageIO");
    }
  }

  public void manualChangeFacing() {
    try {
      BufferedImage image;
      image = ImageIO.read(getClass().getResource(STEVE));
      AffineTransform tx = AffineTransform.getScaleInstance(-1, 1);
      tx.translate(-image.getWidth(null), 0);
      AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_NEAREST_NEIGHBOR);
      image = op.filter(image, null);
      File outputFile = new File("images/realsteve.png");
      ImageIO.write(image, "png", outputFile);
    } catch (Exception e) {

    }

  }

  public String getFacing() {
    return facing;
  }

  public void changeSkin() {
    counter++;
    if (counter % 3 == 0) {
      if (facing.equals("RIGHT"))
        changeFacing("RIGHT");
      else
        changeFacing("LEFT");
    }
    if (counter % 3 == 1) {
      if (facing.equals("RIGHT"))
        changeFacing("RIGHT");
      else
        changeFacing("LEFT");
    }
    if (counter % 3 == 2) {
      if (facing.equals("RIGHT"))
        changeFacing("RIGHT");
      else
        changeFacing("LEFT");
    }
  }
}
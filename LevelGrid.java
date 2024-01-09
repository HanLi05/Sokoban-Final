import java.awt.Graphics;

public class LevelGrid {
  // First layer of objects(paths, goals)
  private Tile[][] layerBottom;
  // Secondary Layer of objects(Persons, goals crates)
  private Tile[][] layerTop;
  private Person playerCharacter;

  public LevelGrid(char[][] items, Person person) {
    playerCharacter = person;
    layerBottom = new Tile[items.length][items[0].length];
    layerTop = new Tile[items.length][items[0].length];
    for (int row = 0; row < items.length; row++) {
      for (int col = 0; col < items[row].length; col++) {
        switch (items[row][col]) {
          case '#':
            layerBottom[row][col] = new Path(row, col);
            layerTop[row][col] = new Wall(row, col);
            break;
          case '_':
            layerBottom[row][col] = new Path(row, col);
            break;
          case '.':
            layerBottom[row][col] = new Goal(row, col);
            break;
          case 'm':
            layerBottom[row][col] = new Path(row, col);
            layerTop[row][col] = new Crate(row, col);
            break;
          case 'M':
            layerBottom[row][col] = new Goal(row, col);
            layerTop[row][col] = new Crate(row, col);
            break;
          case 'x':
            layerBottom[row][col] = new Path(row, col);
            layerTop[row][col] = playerCharacter;
            playerCharacter.setX(row);
            playerCharacter.setY(col);
            break;
          case 'X':
            layerBottom[row][col] = new Goal(row, col);
            layerTop[row][col] = playerCharacter;
            playerCharacter.setX(row);
            playerCharacter.setY(col);
            break;
        }
      }
    }
  }

  public Person getPerson() {
    return playerCharacter;
  }

  public Tile getLayerBottom(int row, int col) {
    return layerBottom[row][col];
  }

  public Tile getLayerTop(int row, int col) {
    return layerTop[row][col];
  }

  public void setLayerBottom(Tile[][] layerBottom) {
    this.layerBottom = layerBottom;
  }

  public void setLayerTop(int row, int col, Tile tile) {
    layerTop[row][col] = tile;
  }

  public boolean isInBounds(int row, int col) {
    return row >= 0 && row < layerBottom.length && col >= 0 && col < layerBottom[row].length;
  }

  public void draw(Graphics window) {
    for (int row = 0; row < layerBottom.length; row++) {
      for (int col = 0; col < layerBottom[row].length; col++) {
        if (layerBottom[row][col] instanceof Tile)
          layerBottom[row][col].draw(window, col, row);
      }
    }
    for (int row = 0; row < layerTop.length; row++) {
      for (int col = 0; col < layerTop[row].length; col++) {
        if (layerTop[row][col] instanceof Tile)
          layerTop[row][col].draw(window, col, row);
      }
    }
  }

  public boolean isSolved() {
    for (int row = 0; row < layerBottom.length; row++) {
      for (int col = 0; col < layerBottom[row].length; col++) {
        if (layerBottom[row][col] instanceof Goal && !(layerTop[row][col] instanceof Crate)) {
          return false;
        }
      }
    }
    return true;
  }
}
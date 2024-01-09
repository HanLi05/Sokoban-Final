public class Level extends LevelGrid {
  private int personRow;
  private int personCol;

  public Level(char[][] items, Person person) {
    super(items, person);
    for (int row = 0; row < items.length; row++) {
      for (int col = 0; col < items[row].length; col++) {
        switch (items[row][col]) {
          case 'x':
          case 'X':
            person.setX(row);
            person.setY(col);
            personRow = row;
            personCol = col;
            break;
        }
      }
    }
  }

  public int getPersonRow() {
    return personRow;
  }

  public int getPersonCol() {
    return personCol;
  }

  public boolean move(String direction) {
    if (getLayerTop(personRow, personCol) instanceof Wall || getLayerTop(personRow, personCol) == null) {
      return false;
    }
    Tile currentTile = getLayerTop(personRow, personCol);
    Tile otherTile;
    if (direction.equals("UP")) {
      if (canMoveDirectly(personRow - 1, personCol)) {
        setLayerTop(personRow, personCol, null);
        setLayerTop(personRow - 1, personCol, currentTile);
        personRow--;
        return true;
      } else if (canPushBox(personRow - 1, personCol, direction)) {
        otherTile = getLayerTop(personRow - 1, personCol);
        setLayerTop(personRow, personCol, null);
        setLayerTop(personRow - 1, personCol, currentTile);
        setLayerTop(personRow - 2, personCol, otherTile);
        personRow--;
        return true;
      }
    } else if (direction.equals("DOWN")) {
      if (canMoveDirectly(personRow + 1, personCol)) {
        setLayerTop(personRow, personCol, null);
        setLayerTop(personRow + 1, personCol, currentTile);
        personRow++;
        return true;
      } else if (canPushBox(personRow + 1, personCol, direction)) {
        otherTile = getLayerTop(personRow + 1, personCol);
        setLayerTop(personRow, personCol, null);
        setLayerTop(personRow + 1, personCol, currentTile);
        setLayerTop(personRow + 2, personCol, otherTile);
        personRow++;
        return true;
      }
    } else if (direction.equals("LEFT")) {
      if (canMoveDirectly(personRow, personCol - 1)) {
        setLayerTop(personRow, personCol, null);
        setLayerTop(personRow, personCol - 1, currentTile);
        personCol--;
        if (getPerson().getFacing().equals("RIGHT"))
          getPerson().changeFacing("LEFT");
        return true;
      } else if (canPushBox(personRow, personCol - 1, direction)) {
        otherTile = getLayerTop(personRow, personCol - 1);
        setLayerTop(personRow, personCol, null);
        setLayerTop(personRow, personCol - 1, currentTile);
        setLayerTop(personRow, personCol - 2, otherTile);
        personCol--;
        if (getPerson().getFacing().equals("RIGHT"))
          getPerson().changeFacing("LEFT");
      }
    } else if (direction.equals("RIGHT")) {
      if (canMoveDirectly(personRow, personCol + 1)) {
        setLayerTop(personRow, personCol, null);
        setLayerTop(personRow, personCol + 1, currentTile);
        personCol++;
        if (getPerson().getFacing().equals("LEFT"))
          getPerson().changeFacing("RIGHT");
        return true;
      } else if (canPushBox(personRow, personCol + 1, direction)) {
        otherTile = getLayerTop(personRow, personCol + 1);
        setLayerTop(personRow, personCol, null);
        setLayerTop(personRow, personCol + 1, currentTile);
        setLayerTop(personRow, personCol + 2, otherTile);
        personCol++;
        if (getPerson().getFacing().equals("LEFT"))
          getPerson().changeFacing("RIGHT");
        return true;
      }
    }
    return false;
  }

  public boolean canMoveDirectly(int row, int col) {
    if (isInBounds(row, col) && getLayerTop(row, col) == null) {
      return true;
    }
    return false;
  }

  public boolean canPushBox(int row, int col, String direction) {
    if (isInBounds(row, col) && getLayerTop(row, col) instanceof Crate) {
      if (direction.equals("UP")) {
        return canMoveDirectly(row - 1, col);
      } else if (direction.equals("DOWN")) {
        return canMoveDirectly(row + 1, col);
      } else if (direction.equals("LEFT")) {
        return canMoveDirectly(row, col - 1);
      } else if (direction.equals("RIGHT")) {
        return canMoveDirectly(row, col + 1);
      }
    }
    return false;
  }
}
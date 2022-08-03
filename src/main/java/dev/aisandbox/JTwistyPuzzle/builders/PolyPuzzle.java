package dev.aisandbox.JTwistyPuzzle.builders;

import dev.aisandbox.JTwistyPuzzle.TwistyPuzzle;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public abstract class PolyPuzzle implements TwistyPuzzle {

  protected List<Cell> cellList = new ArrayList<>();

  @Override
  public String getName() {
    return null;
  }

  @Override
  public String getState() {
    return null;
  }

  @Override
  public void setState(String state) {

  }

  @Override
  public BufferedImage visualise() {
    return null;
  }

  @Override
  public List<String> listPossibleMoves() {
    return null;
  }

  @Override
  public int applyMoves(String moves) {
    return 0;
  }

  @Override
  public int calculateMoveCosts(String moves) {
    return 0;
  }

  @Override
  public boolean isSolved() {
    return false;
  }

  protected Rectangle2D getCellBounds() {
    Rectangle2D result = null;
    for (Cell cell : cellList) {
      if (result==null) {

      }
    }
  }

}

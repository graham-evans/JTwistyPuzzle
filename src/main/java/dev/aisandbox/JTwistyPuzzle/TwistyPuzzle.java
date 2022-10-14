package dev.aisandbox.JTwistyPuzzle;

import java.awt.image.BufferedImage;
import java.util.List;

public interface TwistyPuzzle {

  public String getPuzzleName();
  public String getState();
  public void setState(String state);
  public BufferedImage visualise();
  public BufferedImage visualiseIDs();
  public List<String> listPossibleMoves();
  public int applyMoves(String moves);
  public int calculateMoveCosts(String moves);
  public boolean isSolved();

}

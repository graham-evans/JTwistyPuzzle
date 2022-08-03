package dev.aisandbox.JTwistyPuzzle.builders;

import dev.aisandbox.JTwistyPuzzle.ColourEnum;
import java.awt.geom.Rectangle2D;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SquareCell implements Cell {

  private double locationX;
  private double locationY;
  private double scale;
  private ColourEnum colour;


  protected Rectangle2D getBounds() {
    return new Rectangle2D.Double(locationX,locationY,scale,scale);
  }
}

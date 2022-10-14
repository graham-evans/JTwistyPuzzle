package dev.aisandbox.JTwistyPuzzle.builders;

import dev.aisandbox.JTwistyPuzzle.ColourEnum;
import dev.aisandbox.JTwistyPuzzle.TwistyPuzzle;
import java.awt.geom.Rectangle2D;
import lombok.Builder;
import lombok.experimental.UtilityClass;
import lombok.extern.java.Log;

import java.util.ArrayList;
import java.util.List;


@Log
public class CuboidPuzzleBuilder {

  private static final double scale = 50.0;
  private static final double gap = 2.0;

  public static PolyPuzzle createCuboidPuzzle(int width, int height, int depth,
      Scoring scoringSystem) {
    log.info("Building cube " + width + "*" + height + "*" + depth);

    // create 6 grids of square cells
    // create white (top) grid
    List<SquareCell> topGrid = createGrid(0, 0, scale, width, depth, ColourEnum.WHITE);
    // create orange (left) grid
    List<SquareCell> leftGrid =
        createGrid(
            -depth * scale - gap, depth * scale + gap, scale, depth, height, ColourEnum.ORANGE);
    // create green (front) grid
    List<SquareCell> frontGrid = createGrid(0, depth * scale + gap, scale, width, height,
        ColourEnum.GREEN);
    // create red (right) grid
    List<SquareCell> rightGrid =
        createGrid(
            width * scale + gap, depth * scale + gap, scale, depth, height, ColourEnum.RED);
    // create blue (back) grid
    List<SquareCell> backGrid =
        createGrid(
            (width + depth) * scale + gap * 2,
            depth * scale + gap,
            scale,
            width,
            height,
            ColourEnum.BLUE);
    // create yellow (bottom) grid
    List<SquareCell> bottomGrid =
        createGrid(0, (depth + height) * scale + gap * 2, scale, width, depth, ColourEnum.YELLOW);
    // add all cells to puzzle
    List<Cell> cellList = new ArrayList<>();
    cellList.addAll(topGrid);
    cellList.addAll(leftGrid);
    cellList.addAll(frontGrid);
    cellList.addAll(rightGrid);
    cellList.addAll(backGrid);
    cellList.addAll(bottomGrid);
    // move cells to the origin
        centerAndScale(cellList);
    return new PolyPuzzle(createName(width, height, depth, scoringSystem), cellList, "");
  }

  private static String createName(int width, int height, int depth,
      Scoring scoringSystem) {
    StringBuilder sb = new StringBuilder();
    sb.append(width);
    sb.append("x");
    sb.append(height);
    sb.append("x");
    sb.append(depth);
    sb.append(" Cube (");
    sb.append(scoringSystem.name());
    sb.append(")");
    return sb.toString();
  }

  private static List<SquareCell> createGrid(double startX, double startY, double size, int width,
      int height,
      ColourEnum colour) {
    List<SquareCell> cellList = new ArrayList<>();
    for (int y = 0; y < height; y++) {
      for (int x = 0; x < width; x++) {
        cellList.add(new SquareCell(startX + size * x, startY + size * y, size, colour));
      }
    }
    return cellList;
  }

  private static void centerAndScale(List<Cell> cellList) {
    Rectangle2D originalBounds = getCellBounds(cellList);
    // work out translations
    double ox = -originalBounds.getX();
    double oy = -originalBounds.getY();
    double scale = Math.min(PolyPuzzle.VISUALISATION_HEIGHT / originalBounds.getHeight(),
        PolyPuzzle.VISUALISATION_WIDTH / originalBounds.getWidth()) * 0.98;
    double dx = (PolyPuzzle.VISUALISATION_WIDTH - originalBounds.getWidth() * scale) / 2.0;
    double dy = (PolyPuzzle.VISUALISATION_HEIGHT - originalBounds.getHeight() * scale) / 2.0;
    log.info("Cell bounds = " + originalBounds);
    for (Cell c : cellList) {
      // move to origin
      c.translate(ox, oy);
      // scale
      c.scale(scale);
      // move to center
      c.translate(dx, dy);
    }
  }

  private static Rectangle2D getCellBounds(List<Cell>cellList) {
    Rectangle2D result = null;
    for (Cell cell : cellList) {
      Rectangle2D cellBounds = cell.getBounds();
      if (result == null) {
        result = cellBounds;
      } else {
        result = result.createUnion(cellBounds);
      }
    }
    return result;
  }

  public enum Scoring {
    OTGM, IBM
  }

}

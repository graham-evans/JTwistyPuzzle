package dev.aisandbox.JTwistyPuzzle.builders;

import dev.aisandbox.JTwistyPuzzle.ColourEnum;
import dev.aisandbox.JTwistyPuzzle.TwistyPuzzle;
import lombok.Builder;
import lombok.extern.java.Log;

import java.util.ArrayList;
import java.util.List;

@Builder
@Log
public class CuboidPuzzle extends PolyPuzzle implements TwistyPuzzle {

    private int width = 3;
    private int height = 3;
    private int depth = 3;
    private Scoring scoringSystem = Scoring.OTGM;
    private static double scale = 50.0;
    private static double gap = 2.0;

    public CuboidPuzzle(int width, int height, int depth,
                        Scoring scoringSystem) {
        log.info("Building cube " + width + "*" + height + "*" + depth);
        this.width = width;
        this.height = height;
        this.depth = depth;
        this.scoringSystem = scoringSystem;
        // create 6 grids of square cells
        // create white (top) grid
        List<SquareCell> topGrid = createGrid(0, 0, scale, width, depth, ColourEnum.WHITE);
        // create orange (left) grid
        List<SquareCell> leftGrid =
                createGrid(
                        -depth * scale - gap, depth * scale + gap, scale, depth, height, ColourEnum.ORANGE);
        // create green (front) grid
        List<SquareCell> frontGrid = createGrid(0, depth * scale + gap, scale, width, height, ColourEnum.GREEN);
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
        cellList.addAll(topGrid);
        cellList.addAll(leftGrid);
        cellList.addAll(frontGrid);
        cellList.addAll(rightGrid);
        cellList.addAll(backGrid);
        cellList.addAll(bottomGrid);
        // move cells to the origin
        centerAndScale();
    }

    private List<SquareCell> createGrid(double startX, double startY, double size, int width, int height,
                                        ColourEnum colour) {
        List<SquareCell> cellList = new ArrayList<>();
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                cellList.add(new SquareCell(startX + size * x, startY + size * y, size, colour));
            }
        }
        return cellList;
    }

    public enum Scoring {
        OTGM, IBM
    }

}

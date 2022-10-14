package dev.aisandbox.JTwistyPuzzle.builders;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import dev.aisandbox.JTwistyPuzzle.TwistyPuzzle;
import dev.aisandbox.JTwistyPuzzle.builders.CuboidPuzzleBuilder.Scoring;
import org.junit.jupiter.api.Test;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class CuboidTest {

  @Test
  public void constructorNameTest() {
    TwistyPuzzle cube = CuboidPuzzleBuilder.createCuboidPuzzle(3,3,3,Scoring.OTGM);
    assertEquals("3x3x3 Cube (OTGM)",cube.getPuzzleName());
  }

  @Test
  public void solvedCubeTest() {
    TwistyPuzzle cube = CuboidPuzzleBuilder.createCuboidPuzzle(3,3,3,Scoring.OTGM);
    assertTrue(cube.isSolved());
  }

  @Test
  public void drawStartTest() throws IOException {
    TwistyPuzzle cube = CuboidPuzzleBuilder.createCuboidPuzzle(3,3,3,Scoring.OTGM);
    BufferedImage image = cube.visualise();
    File outputFile = new File("cube3x3x3.png");
    ImageIO.write(image,"png",outputFile);
    assertTrue(outputFile.isFile());
  }

}

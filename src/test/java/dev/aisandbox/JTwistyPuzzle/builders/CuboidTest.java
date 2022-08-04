package dev.aisandbox.JTwistyPuzzle.builders;

import static org.junit.jupiter.api.Assertions.assertTrue;

import dev.aisandbox.JTwistyPuzzle.TwistyPuzzle;
import dev.aisandbox.JTwistyPuzzle.builders.CuboidPuzzle.Scoring;
import org.junit.jupiter.api.Test;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class CuboidTest {

  @Test
  public void constructorInitialisationTest() {
    TwistyPuzzle cube = new CuboidPuzzle(3,3,3,Scoring.OTGM);
    assertTrue(cube.isSolved());

  }

  @Test
  public void solvedCubeTest() {
    TwistyPuzzle cube = CuboidPuzzle.builder().depth(3).height(3).width(3).build();
    assertTrue(cube.isSolved());
  }

  @Test
  public void drawStartTest() throws IOException {
    TwistyPuzzle cube = CuboidPuzzle.builder().depth(3).height(3).width(3).build();
    BufferedImage image = cube.visualise();
    ImageIO.write(image,"png",new File("cube3x3x3.png"));
  }

}

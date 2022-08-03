package dev.aisandbox.JTwistyPuzzle.builders;

import static org.junit.jupiter.api.Assertions.assertTrue;

import dev.aisandbox.JTwistyPuzzle.TwistyPuzzle;
import dev.aisandbox.JTwistyPuzzle.builders.CuboidPuzzle.Scoring;
import org.junit.jupiter.api.Test;

public class CuboidTest {

  @Test
  public void constructorInitialisationTest() {
    TwistyPuzzle cube = new CuboidPuzzle(3,3,3,Scoring.OTGM);
    assertTrue(cube.isSolved());

  }

  @Test
  public void solvedCubeTest() {
    TwistyPuzzle cube = CuboidPuzzle.builder().build();
    assertTrue(cube.isSolved());
  }

}

package dev.aisandbox.JTwistyPuzzle.builders;

import static org.junit.jupiter.api.Assertions.assertTrue;

import dev.aisandbox.JTwistyPuzzle.TwistyPuzzle;
import dev.aisandbox.JTwistyPuzzle.builders.CuboidPuzzleBuilder.Scoring;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import org.junit.jupiter.api.Test;

public class StateImageTests {

  @Test
  public void generateCuboidStateImagesTest() throws IOException {
    for (int width=2;width<11;width++) {
      for (int height=2;height<11;height++) {
        for (int depth=2;depth<11;depth++) {
          TwistyPuzzle puzzle = CuboidPuzzleBuilder.createCuboidPuzzle(width,height,depth, Scoring.OTGM);
          File fout = new File("cube"+width+"x"+height+"x"+depth+"State.png");
          ImageIO.write(puzzle.visualiseIDs(),"PNG",fout);
          assertTrue(fout.isFile());
        }
      }
    }
  }

}

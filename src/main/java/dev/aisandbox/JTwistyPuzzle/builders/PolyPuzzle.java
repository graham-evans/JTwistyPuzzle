package dev.aisandbox.JTwistyPuzzle.builders;

import dev.aisandbox.JTwistyPuzzle.TwistyPuzzle;

import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Path2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.util.List;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.java.Log;

@Log
public class PolyPuzzle implements TwistyPuzzle {

    public static final int VISUALISATION_WIDTH = 1280;
    public static final int VISUALISATION_HEIGHT = 720;

    @Getter
    private final String puzzleName;
    private final List<Cell> cellList;
    @Getter
    @Setter
    private String state;

    protected PolyPuzzle(String puzzleName, List<Cell> cellList, String initialState) {
        this.puzzleName = puzzleName;
        this.cellList = cellList;
        state = initialState;
    }


    @Override
    public BufferedImage visualise() {
        log.info("Visualising " + cellList.size() + " cells");
        BufferedImage image = new BufferedImage(VISUALISATION_WIDTH, VISUALISATION_HEIGHT,
                BufferedImage.TYPE_INT_RGB);
        Graphics2D g = image.createGraphics();
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, VISUALISATION_WIDTH, VISUALISATION_WIDTH);
        for (Cell c : cellList) {
            Path2D path = c.getPolygon();
            g.setColor(c.getColour().getAwtColour());
            g.fill(c.getPolygon());
            g.setColor(Color.BLACK);
            g.draw(c.getPolygon());
        }
        return image;
    }

    @Override
    public BufferedImage visualiseIDs() {
        BufferedImage image = visualise();
        Graphics2D g = image.createGraphics();
        Font baseFont = new Font("Arial", Font.BOLD, 20);
        for (int i = 0; i < cellList.size(); i++) {
            Cell c = cellList.get(i);
            // work out center of cell
            Rectangle2D bounds = c.getPolygon().getBounds2D();
            double cx = bounds.getCenterX();
            double cy = bounds.getCenterY();
            double size = Math.min(bounds.getWidth(), bounds.getHeight()) / 2.2;
            Shape circle = new Ellipse2D.Double(cx - size, cy - size, size * 2.0, size * 2.0);
            g.setColor(Color.DARK_GRAY);
            g.fill(circle);
            g.setColor(Color.WHITE);
            Font font = baseFont.deriveFont((float) size);
            String text = Integer.toString(i + 1);
            g.setFont(font);
            FontMetrics fm = g.getFontMetrics();
            g.drawString(text, (int) (cx - fm.stringWidth(text) / 2), (int) (cy + fm.getAscent() / 2));
        }
        return image;
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


}

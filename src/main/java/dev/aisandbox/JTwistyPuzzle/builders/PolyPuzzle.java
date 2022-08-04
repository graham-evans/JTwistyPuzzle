package dev.aisandbox.JTwistyPuzzle.builders;

import dev.aisandbox.JTwistyPuzzle.TwistyPuzzle;
import lombok.extern.java.Log;

import java.awt.*;
import java.awt.geom.Path2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

@Log
public abstract class PolyPuzzle implements TwistyPuzzle {

    private static final int VISUALISATION_WIDTH = 1280;
    private static final int VISUALISATION_HEIGHT = 720;

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
        log.info("Visualising "+cellList.size()+" cells");
        BufferedImage image = new BufferedImage(VISUALISATION_WIDTH, VISUALISATION_HEIGHT, BufferedImage.TYPE_INT_RGB);
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

    private Rectangle2D getCellBounds() {
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

    protected void centerAndScale() {
        Rectangle2D originalBounds = getCellBounds();
        // work out translations
        double ox = -originalBounds.getX();
        double oy = -originalBounds.getY();
        double scale = Math.min(VISUALISATION_HEIGHT/ originalBounds.getHeight(),VISUALISATION_WIDTH/originalBounds.getWidth()) * 0.98;
        double dx = (VISUALISATION_WIDTH - originalBounds.getWidth()*scale)/2.0;
        double dy = (VISUALISATION_HEIGHT-originalBounds.getHeight()*scale)/2.0;
        log.info("Cell bounds = "+originalBounds);
        for (Cell c : cellList) {
            // move to origin
            c.translate(ox,oy);
            // scale
            c.scale(scale);
            // move to center
            c.translate(dx,dy);
        }
    }

}

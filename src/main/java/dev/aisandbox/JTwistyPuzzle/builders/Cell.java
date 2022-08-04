package dev.aisandbox.JTwistyPuzzle.builders;

import dev.aisandbox.JTwistyPuzzle.ColourEnum;

import java.awt.geom.Path2D;
import java.awt.geom.Rectangle2D;

public interface Cell {

    public ColourEnum getColour();
    public Path2D getPolygon();

    public Rectangle2D getBounds();

    public void translate(double dx, double dy);

    public void scale(double s);

}

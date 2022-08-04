package dev.aisandbox.JTwistyPuzzle.builders;

import dev.aisandbox.JTwistyPuzzle.ColourEnum;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.awt.geom.Path2D;
import java.awt.geom.Rectangle2D;

@Data
@AllArgsConstructor
public class SquareCell implements Cell {

    private double locationX;
    private double locationY;
    private double scale;
    private ColourEnum colour;

    @Override
    public Path2D getPolygon() {
        Path2D path = new Path2D.Double();
        path.moveTo(locationX, locationY);
        path.lineTo(locationX + scale, locationY);
        path.lineTo(locationX + scale, locationY + scale);
        path.lineTo(locationX, locationY + scale);
        path.closePath();
        return path;
    }

    @Override
    public void translate(double dx, double dy) {
        locationX += dx;
        locationY += dy;
    }

    @Override
    public void scale(double s) {
        scale *= s;
        locationY *= s;
        locationX *= s;
    }

    public Rectangle2D getBounds() {
        return new Rectangle2D.Double(locationX, locationY, scale, scale);
    }
}

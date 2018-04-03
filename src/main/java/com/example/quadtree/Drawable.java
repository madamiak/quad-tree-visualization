package com.example.quadtree;

import java.awt.Point;
import java.util.List;

public interface Drawable {

    enum PaintColor {
        BLACK, GREEN
    }

    void setPaintColor(PaintColor paintColor);

    void drawPoint(Point point, int width);

    void drawPoints(List<Point> points, int width, Boundary boundary);

    void drawBoundary(Boundary boundary);

    void clear(Boundary boundary);

}

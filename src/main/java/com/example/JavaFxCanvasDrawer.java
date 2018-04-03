package com.example;

import com.example.quadtree.Boundary;
import com.example.quadtree.Drawable;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.awt.Point;
import java.util.List;

public class JavaFxCanvasDrawer implements Drawable {

    private final GraphicsContext gc;

    private Drawable.PaintColor paintColor;

    public JavaFxCanvasDrawer(GraphicsContext gc) {
        this.gc = gc;
    }

    @Override
    public void setPaintColor(Drawable.PaintColor paintColor) {
        this.paintColor = paintColor;
    }

    @Override
    public void drawPoint(Point point, int width) {
        gc.setFill(toColor(paintColor));
        gc.fillOval(point.x, point.y, width, width);
    }

    @Override
    public void drawPoints(List<Point> points, int width, Boundary boundary) {
        drawBoundary(boundary);
        points.forEach(p -> drawPoint(p, width));
    }

    @Override
    public void drawBoundary(Boundary boundary) {
        gc.setStroke(toColor(paintColor));
        gc.strokeRect(boundary.startPoint.x, boundary.startPoint.y, boundary.width, boundary.height);
    }

    @Override
    public void clear(Boundary boundary) {
        gc.clearRect(boundary.startPoint.x, boundary.startPoint.y, boundary.width, boundary.height);
    }

    protected Color toColor(Drawable.PaintColor paintColor) {
        return Color.valueOf(paintColor.name());
    }
}

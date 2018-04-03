package com.example.quadtree;

import java.awt.Point;

public class Boundary {

    public Point startPoint;

    public int width;

    public int height;

    public Boundary(Point startPoint, int width, int height) {
        this.startPoint = startPoint;
        this.width = width;
        this.height = height;
    }

    public Boundary northEast() {
        int newWidth = width / 2;
        int newHeight = height / 2;
        Point newCenter = new Point(startPoint.x + newWidth, startPoint.y + newHeight);
        return new Boundary(newCenter, newWidth, newHeight);
    }

    public Boundary northWest() {
        int newWidth = width / 2;
        int newHeight = height / 2;
        Point newCenter = new Point(startPoint.x, startPoint.y + newHeight);
        return new Boundary(newCenter, newWidth, newHeight);
    }

    public Boundary southEast() {
        int newWidth = width / 2;
        int newHeight = height / 2;
        Point newCenter = new Point(startPoint.x + newWidth, startPoint.y);
        return new Boundary(newCenter, newWidth, newHeight);
    }

    public Boundary southWest() {
        int newWidth = width / 2;
        int newHeight = height / 2;
        Point newCenter = new Point(startPoint.x, startPoint.y);
        return new Boundary(newCenter, newWidth, newHeight);
    }

    public boolean contains(Point point) {
        return !(startPoint.x > point.x || startPoint.x + width < point.x
                || startPoint.y > point.y || startPoint.y + height < point.y);
    }

    public boolean intersects(Boundary boundary) {
        return !(startPoint.x > boundary.startPoint.x + boundary.width
                || startPoint.x + width < boundary.startPoint.x
                || startPoint.y > boundary.startPoint.y + boundary.height
                || startPoint.y + height < boundary.startPoint.y);
    }
}

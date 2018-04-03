package com.example.quadtree;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class QuadTree {

    private final int capacity;

    private final Boundary boundary;

    private final List<Point> points = new ArrayList<>();

    private boolean isDivided = false;

    private QuadTree northEast;

    private QuadTree northWest;

    private QuadTree southEast;

    private QuadTree southWest;

    public QuadTree(int capacity, Boundary boundary) {
        this.capacity = capacity;
        this.boundary = boundary;
    }

    public void insert(Point point) {
        if (!boundary.contains(point)) {
            return;
        }
        if (points.size() < capacity) {
            points.add(point);
            return;
        }
        if (!isDivided) {
            subdivide();
        }
        northEast.insert(point);
        northWest.insert(point);
        southEast.insert(point);
        southWest.insert(point);

    }

    private void subdivide() {
        northEast = new QuadTree(capacity, boundary.northEast());
        northWest = new QuadTree(capacity, boundary.northWest());
        southEast = new QuadTree(capacity, boundary.southEast());
        southWest = new QuadTree(capacity, boundary.southWest());
        isDivided = true;
    }

    public void draw(Drawable drawable) {
        drawable.setPaintColor(Drawable.PaintColor.BLACK);
        drawable.drawPoints(points, 2, boundary);
        if (isDivided) {
            northEast.draw(drawable);
            northWest.draw(drawable);
            southEast.draw(drawable);
            southWest.draw(drawable);
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(points.toString());
        if (isDivided) {
            sb.append("\n");
            sb.append(northEast.points);
            sb.append("\n");
            sb.append(northWest.points);
            sb.append("\n");
            sb.append(southEast.points);
            sb.append("\n");
            sb.append(southWest.points);
        }
        return sb.toString();
    }

    public List<Point> queryRange(Boundary range) {
        List<Point> result = new ArrayList<>();
        if (!range.intersects(boundary)) {
            return result;
        }
        result.addAll(points.stream().filter(range::contains).collect(Collectors.toList()));
        if (isDivided) {
            result.addAll(northEast.queryRange(range));
            result.addAll(northWest.queryRange(range));
            result.addAll(southEast.queryRange(range));
            result.addAll(southWest.queryRange(range));
        }
        return result;
    }
}

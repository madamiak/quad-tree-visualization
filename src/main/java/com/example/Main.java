package com.example;

import com.example.quadtree.Boundary;
import com.example.quadtree.Drawable;
import com.example.quadtree.QuadTree;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.awt.Point;
import java.util.List;
import java.util.Random;

public class Main extends Application {

    private static final int POINTS_NUMBER = 10000;

    private static final int CAPACITY = 5;

    private static final int WIDTH = 1000;

    private static final int HEIGHT = 1000;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("com.example.quadtree.QuadTree");
        Boundary boundary = new Boundary(new Point(0, 0), WIDTH, HEIGHT);
        Group root = new Group();
        Canvas canvas = new Canvas(WIDTH, HEIGHT);
        GraphicsContext gc = canvas.getGraphicsContext2D();
        Drawable javaFxCanvasDrawer = new JavaFxCanvasDrawer(gc);

        QuadTree quadTree = new QuadTree(CAPACITY, boundary);
        for (int i = 0; i < POINTS_NUMBER; i++) {
            quadTree.insert(new Point(new Random().nextInt(WIDTH), new Random().nextInt(HEIGHT)));
        }

        canvas.addEventHandler(MouseEvent.MOUSE_MOVED, e -> {
            javaFxCanvasDrawer.clear(boundary);
            quadTree.draw(javaFxCanvasDrawer);
            Boundary range = new Boundary(new Point((int) e.getX() - WIDTH / 6, (int) e.getY() - HEIGHT / 14), WIDTH / 3, HEIGHT / 7);
            List<Point> points = quadTree.queryRange(range);
            javaFxCanvasDrawer.setPaintColor(Drawable.PaintColor.GREEN);
            javaFxCanvasDrawer.drawPoints(points, 5, range);
        });

        root.getChildren().add(canvas);
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

}

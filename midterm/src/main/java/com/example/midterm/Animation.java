package com.example.midterm;

import javafx.animation.PathTransition;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import javafx.util.Duration;

public class Animation {

    public Pane animate() {
        Pane pane = new Pane();
        Path path = new Path();

        // moveto sets start position of path
        // lineto adds a straight line from current position to provided coordinates
        path.getElements().add(new MoveTo(20, 150));
        path.getElements().add(new LineTo(280, 150));

        Circle circle = new Circle(20, 150, 20);
        circle.setFill(Color.BLUEVIOLET);

        // PathTransition defines how each part of the path interacts
        PathTransition pathTransition = new PathTransition();

        pathTransition.setDuration(Duration.seconds(2));
        pathTransition.setDelay(Duration.seconds(1));
        pathTransition.setPath(path);
        pathTransition.setNode(circle);
        pathTransition.setCycleCount(2);
        pathTransition.setAutoReverse(true);
        pathTransition.play();

        pane.getChildren().addAll(circle);

        return pane;
    }
}

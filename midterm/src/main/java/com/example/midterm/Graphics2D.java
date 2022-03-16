package com.example.midterm;

import javafx.scene.shape.*;
import javafx.scene.paint.Color;
import javafx.scene.layout.Pane;

public class Graphics2D {

    /**
     * @return Pane containing shapes forming my initials GB
     */
    public Pane draw() {
        Pane pane = new Pane();

        Rectangle background = new Rectangle(0, 20, 270, 180);
        background.setFill(Color.BLACK);

        // drawing the shapes that form the first letter
        Rectangle g_background = new Rectangle(15, 35, 100, 150);
        g_background.setFill(Color.AQUAMARINE);
        g_background.setArcHeight(20.0);
        g_background.setArcWidth(20.0);

        Rectangle gFill_1 = new Rectangle(30,50,86,60);
        gFill_1.setFill(Color.BLACK);

        Rectangle gFill_2 = new Rectangle(30, 50, 35, 120);
        gFill_2.setFill(Color.BLACK);

        Rectangle gFill_3 = new Rectangle(30, 125, 70, 45);
        gFill_3.setFill(Color.BLACK);
        gFill_3.setArcHeight(20.0);
        gFill_3.setArcWidth(20.0);

        // drawing the shapes that form the second letter
        Rectangle b_background = new Rectangle(165, 35, 50, 150);
        b_background.setFill(Color.AQUAMARINE);

        Circle b_circle_background1 = new Circle(215, 73, 38);
        b_circle_background1.setFill(Color.AQUAMARINE);

        Circle b_circle_background2 = new Circle(215, 146, 39);
        b_circle_background2.setFill(Color.AQUAMARINE);

        Rectangle b_fill1 = new Rectangle(180, 50, 45, 40);
        b_fill1.setFill(Color.BLACK);

        Circle b_fill2 = new Circle(225, 70, 20);
        b_fill2.setFill(Color.BLACK);

        Rectangle b_fill3 = new Rectangle(180, 125, 45, 40);
        b_fill3.setFill(Color.BLACK);

        Circle b_fill4 = new Circle(225, 145, 20);
        b_fill4.setFill(Color.BLACK);

        // adding all shapes to the pane layout
        pane.getChildren().addAll(background, g_background, gFill_1, gFill_2, gFill_3, b_background,
                b_circle_background1, b_circle_background2, b_fill1, b_fill2, b_fill3, b_fill4);

        return pane;
    }
}

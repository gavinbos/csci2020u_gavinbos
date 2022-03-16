/**
 * @author Gavin Bosman
 * @studentID 100781902
 * @date 2022-03-15
 * @description this is my CSCI2020U midterm coding portion
 */
package com.example.midterm;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.scene.layout.*;

import java.io.IOException;

public class Midterm extends Application {
    // Scenes used
    Scene root, sceneAnimation, scene2D, sceneInfo;

    // outermost layout wrapping the components of each window
    BorderPane animationLayout, graphicsLayout, aboutLayout;

    // instantiating the used classes
    Animation animation = new Animation();
    Graphics2D graphics2D = new Graphics2D();
    ParseXml parseXml = new ParseXml();

    @Override
    public void start(Stage primaryStage) throws IOException {

        // Button declarations

        // upon action event adds pane layout to animation scene before switching over to it
        Button buttonAnimation = new Button("Animation");
        buttonAnimation.setOnAction(e -> {
            animationLayout.setCenter(animation.animate());
            primaryStage.setScene(sceneAnimation);
        });

        // upon action event, the button generates a pane layout and adds it to the graphics scene
        // before switching over to it
        Button button2D = new Button("2D Graphics");
        button2D.setOnAction(e -> {
            graphicsLayout.setCenter(graphics2D.draw());
            primaryStage.setScene(scene2D);
        });

        Button buttonInfo = new Button("About");
        buttonInfo.setOnAction(e -> primaryStage.setScene(sceneInfo));

        // these buttons return the user to the main scene
        Button goBack = new Button("Main Menu");
        goBack.setOnAction(e -> primaryStage.setScene(root));

        Button goBack_2 = new Button("Main Menu");
        goBack_2.setOnAction(e -> primaryStage.setScene(root));

        Button goBack_3 = new Button("Main Menu");
        goBack_3.setOnAction(e -> primaryStage.setScene(root));

        // main menu setup
        BorderPane mainLayout = new BorderPane();
        mainLayout.setPadding(new Insets(15, 15, 15, 15));
        VBox vBox = new VBox(10);
        vBox.getChildren().addAll(buttonAnimation, button2D, buttonInfo);
        vBox.setAlignment(Pos.CENTER);
        mainLayout.setCenter(vBox);

        root = new Scene(mainLayout, 300, 300);
        primaryStage.setScene(root);
        primaryStage.setTitle("CSCI2020U - Midterm");
        primaryStage.show();

        // animation page setup
        animationLayout = new BorderPane();
        HBox hBoxBottom = new HBox();
        hBoxBottom.setPadding(new Insets(15, 15, 15, 15));
        HBox hBoxTop = new HBox();
        hBoxTop.setPadding(new Insets(15, 15, 15, 15));
        hBoxBottom.setAlignment(Pos.CENTER);
        hBoxTop.setAlignment(Pos.CENTER);

        hBoxBottom.getChildren().add(goBack);
        Label labelAnimate = new Label("Animating a Shape");
        labelAnimate.setFont(new Font("Arial", 24));
        hBoxTop.getChildren().add(labelAnimate);

        animationLayout.setBottom(hBoxBottom);
        animationLayout.setTop(hBoxTop);
        sceneAnimation = new Scene(animationLayout, 300, 300);

        // 2D graphics page setup
        graphicsLayout = new BorderPane();
        graphicsLayout.setPadding(new Insets(15,15,15,15));

        HBox graphicsHboxBottom = new HBox();
        graphicsHboxBottom.setAlignment(Pos.CENTER);
        graphicsHboxBottom.getChildren().add(goBack_2);

        HBox graphicsHboxTop = new HBox();
        Label labelGraphics = new Label("Initials G.B.");
        labelGraphics.setFont(new Font("Arial", 24));
        graphicsHboxTop.setAlignment(Pos.CENTER);
        graphicsHboxTop.getChildren().add(labelGraphics);

        graphicsLayout.setTop(graphicsHboxTop);
        graphicsLayout.setBottom(graphicsHboxBottom);
        scene2D = new Scene(graphicsLayout, 300, 300);

        // about page setup
        aboutLayout = new BorderPane();
        aboutLayout.setPadding(new Insets(15,15,15,15));

        HBox hBox3 = new HBox();
        hBox3.setAlignment(Pos.CENTER);
        hBox3.getChildren().add(goBack_3);

        aboutLayout.setBottom(hBox3);
        aboutLayout.setCenter(parseXml.generateAboutPage());
        sceneInfo = new Scene(aboutLayout, 300, 300);

    }

    public static void main(String[] args) {
        launch();
    }
}
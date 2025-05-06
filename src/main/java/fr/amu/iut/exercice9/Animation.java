package fr.amu.iut.exercice9;

import javafx.animation.SequentialTransition;
import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Animation extends Application {

    @Override
    public void start(Stage primaryStage) {
        BorderPane root = new BorderPane();
        CustomButton customButton = new CustomButton();
        root.setCenter(customButton); // la boule est centrée

        Scene scene = new Scene(root, 400, 400);

        Duration duration = Duration.millis(500);

        TranslateTransition t1 = new TranslateTransition(duration, customButton);
        t1.setByX(150); // droite complète

        TranslateTransition t2 = new TranslateTransition(duration, customButton);
        t2.setByY(150); // bas complète

        TranslateTransition t3 = new TranslateTransition(duration, customButton);
        t3.setByX(-300); // tout à gauche

        TranslateTransition t4 = new TranslateTransition(duration, customButton);
        t4.setByY(-300); // tout en haut

        TranslateTransition t5 = new TranslateTransition(duration, customButton);
        t5.setByX(300); // tout à droite

        TranslateTransition t6 = new TranslateTransition(duration, customButton);
        t6.setByY(150); // bas moitié

        TranslateTransition t7 = new TranslateTransition(duration, customButton);
        t7.setByX(-150); // gauche moitié

        SequentialTransition sequence = new SequentialTransition(
                t1, t2, t3, t4, t5, t6, t7
        );

        sequence.setAutoReverse(true); // fait le chemin inverse
        sequence.setCycleCount(2);     // aller + retour

        customButton.addOnMousePressed(event -> sequence.play());

        primaryStage.setTitle("Animation complexe autour du centre");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

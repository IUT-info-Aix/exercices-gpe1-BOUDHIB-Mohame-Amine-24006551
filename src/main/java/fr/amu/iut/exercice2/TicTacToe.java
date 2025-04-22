package fr.amu.iut.exercice2;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.util.Random;

public class TicTacToe extends Application {

    @Override
    public void start(Stage primaryStage) {
        GridPane grid = new GridPane();
        Random random = new Random();

        // Charger les images une seule fois
        Image croix = new Image("exercice2/Croix.png");
        Image rond = new Image("exercice2/Rond.png");
        Image vide = new Image("exercice2/Vide.png");

        // Remplir la grille
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                int nombre = random.nextInt(3); // 0, 1 ou 2
                ImageView imageView;
                if (nombre == 0) {
                    imageView = new ImageView(croix);
                } else if (nombre == 1) {
                    imageView = new ImageView(rond);
                } else {
                    imageView = new ImageView(vide);
                }
                addCell(grid, imageView, col, row);
            }
        }

        Scene scene = new Scene(grid);
        primaryStage.setTitle("Tic Tac Toe");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void addCell(GridPane grid, ImageView image, int col, int row) {
        StackPane stack = new StackPane(image);
        stack.setStyle("-fx-border-color: black; -fx-border-width: 1;");
        grid.add(stack, col, row);
    }

    public static void main(String[] args) {
        launch(args);
    }
}

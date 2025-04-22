package fr.amu.iut.exercice1;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class FenetreLogiciel extends Application {

    @Override
    public void start(Stage primaryStage) {
        // Utilisation de la BorderPane qui est le mieux adapté pour cet exercice
        BorderPane root = new BorderPane();
        // Création du menu qui sera placé en haut (Top)
        MenuBar menuBar = new MenuBar();
        Menu fileMenu = new Menu("File");
        Menu editMenu = new Menu("Edit");
        Menu helpMenu = new Menu("Help");
        menuBar.getMenus().addAll(fileMenu, editMenu, helpMenu);
        root.setTop(menuBar);
        // Création du menu gauche avec les boutons a gauche avec utilisation d'un VBox (gauche)
        VBox leftbox = new VBox(10);
        //leftbox.setStyle("-fx-padding: 10;");
        Label boutons = new Label("Boutons :");
        Button bouton1 = new Button("Bouton 1");
        Button bouton2 = new Button("Bouton 2");
        Button bouton3 = new Button("Bouton 3");
        leftbox.setAlignment(Pos.CENTER);
        leftbox.getChildren().addAll(boutons, bouton1, bouton2, bouton3);
        // Séparateur vertical
        Separator verticalSeparator = new Separator();
        verticalSeparator.setOrientation(Orientation.VERTICAL);

// HBox contenant la colonne gauche et le séparateur
        HBox leftContainer = new HBox(leftbox, verticalSeparator);
        root.setLeft(leftContainer);

        // Création du centre en utilisant un GridPane
        GridPane centerGrid = new GridPane();
        centerGrid.setVgap(10);
        centerGrid.setHgap(10);
        centerGrid.setStyle("-fx-padding: 20;");


        centerGrid.add(new Label("Name:"), 0, 0);
        centerGrid.add(new TextField(), 1, 0);
        centerGrid.add(new Label("Email"), 0, 1);
        centerGrid.add(new TextField(), 1, 1);
        centerGrid.add(new Label("Password"), 0, 2);
        centerGrid.add(new TextField(), 1, 2);

        HBox buttonBox = new HBox(10, new Button("Submit"), new Button("Cancel"));
        centerGrid.add(buttonBox, 1, 3);
        centerGrid.setAlignment(Pos.CENTER);
        root.setCenter(centerGrid);

        // Label de bas de page
        Label bottomLabel = new Label("Ceci est un label de bas de page");

        Separator horizontalSeparator = new Separator();
        horizontalSeparator.setPrefWidth(600); // Largeur selon la fenêtre

        VBox bottomBox = new VBox(horizontalSeparator, bottomLabel);
        bottomBox.setAlignment(Pos.CENTER);
        bottomBox.setPadding(new Insets(5, 0, 5, 0));

        root.setBottom(bottomBox);
        //Affichage
        Scene scene = new Scene(root, 500, 300);
        primaryStage.setTitle("Premier exemple manipulant les conteneurs");
        primaryStage.setScene(scene);
        primaryStage.show();




    }

    public static void main(String[] args) {
        launch(args);

    }
}


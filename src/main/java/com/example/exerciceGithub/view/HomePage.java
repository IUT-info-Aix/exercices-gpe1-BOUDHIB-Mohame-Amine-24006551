package com.example.exerciceGithub.view;

import com.example.exerciceGithub.session.Session;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class HomePage {
    private final Stage stage;

    public HomePage(Stage stage) {
        this.stage = stage;
    }

    public void show() {
        VBox root = new VBox(20);
        root.setPadding(new Insets(40));
        root.setAlignment(Pos.TOP_CENTER);

        Label welcome = new Label("Bonjour, " + Session.getEmail() + " !");
        welcome.setStyle("-fx-font-size: 24px; -fx-font-weight: bold;");

        // Ajoutez ici d’autres infos : liste de repos, activités, etc.
        Label info = new Label("Voici votre tableau de bord GitHub simulé.");

        root.getChildren().addAll(welcome, info);
        Scene scene = new Scene(root, 800, 600);
        scene.getStylesheets().add( getClass().getResource("style/styles.css").toString()  );

        stage.setScene(scene);
    }
}
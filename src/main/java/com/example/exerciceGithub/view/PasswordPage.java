package com.example.exerciceGithub.view;


import com.example.exerciceGithub.Main;
import com.example.exerciceGithub.session.Session;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class PasswordPage {
    private final Stage stage;

    public PasswordPage(Stage stage) {
        this.stage = stage;
    }

    public void show() {
        VBox root = new VBox(15);
        root.setPadding(new Insets(30));
        root.setAlignment(Pos.CENTER);

        Label label = new Label("Bienvenue, " + Session.getEmail());
        PasswordField pwdField = new PasswordField();
        pwdField.setPromptText("Mot de passe");

        Button loginBtn = new Button("Connexion");
        loginBtn.setOnAction(e -> {
            // Ici vous mettriez votre logique d'authentification…
            // Pour l'exemple, on considerera que c'est toujours OK :
            new HomePage(stage).show();
        });

        root.getChildren().addAll(label, pwdField, loginBtn);
        Scene scene = new Scene(root, 400, 250);
        scene.getStylesheets().add( getClass().getResource("style/styles.css").toString()  );

        stage.setScene(scene);
    }
}
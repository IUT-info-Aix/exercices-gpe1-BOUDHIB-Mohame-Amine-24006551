package com.example.exerciceGithub.view;

import com.example.exerciceGithub.session.Session;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class SignInPage {
    private final Stage stage;

    public SignInPage(Stage stage) {
        this.stage = stage;
    }

    public void show() {
        VBox root = new VBox(15);
        root.setPadding(new Insets(30));
        root.setAlignment(Pos.CENTER);

        Label label = new Label("Connexion GitHub");
        TextField emailField = new TextField();
        emailField.setPromptText("Email");
        // préremplir si on a déjà un e-mail
        if (Session.getEmail() != null) {
            emailField.setText(Session.getEmail());
        }

        Button nextBtn = new Button("Suivant");
        nextBtn.setOnAction(e -> {
            String email = emailField.getText();
            if (email != null && !email.isBlank()) {
                Session.setEmail(email);
                new PasswordPage(stage).show();
            } else {
                new Alert(Alert.AlertType.WARNING,
                        "Entrez votre e-mail pour continuer.", ButtonType.OK).showAndWait();
            }
        });

        root.getChildren().addAll(label, emailField, nextBtn);
        Scene scene = new Scene(root, 400, 250);
        scene.getStylesheets().add( getClass().getResource("style/styles.css").toString()  );
        stage.setScene(scene);
    }
}
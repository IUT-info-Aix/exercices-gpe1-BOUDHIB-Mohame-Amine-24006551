package com.example.exerciceGithub.view;

import com.example.exerciceGithub.Main;
import com.example.exerciceGithub.view.SignInPage;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class GitHubHomePage extends BorderPane {
    public GitHubHomePage(Stage stage) {
        this.setTop(buildTopBar(stage));
        this.setCenter(buildHeroSection(stage));
        this.setBottom(buildFooter());
    }

    private HBox buildTopBar(Stage stage) {
        HBox topBar = new HBox(20);
        topBar.setPadding(new Insets(15));
        topBar.setAlignment(Pos.CENTER_LEFT);
        topBar.getStyleClass().add("top-bar");

        Label logo = new Label("GitHub");
        logo.getStyleClass().add("logo");

        TextField searchField = new TextField();
        searchField.setPromptText("Search GitHub");

        Button signInBtn = new Button("Sign in");
        signInBtn.setOnAction(e -> {
            new SignInPage(stage).show();
        });

        Button signUpBtn = new Button("Sign up");
        signUpBtn.getStyleClass().add("primary-button");
        signUpBtn.setOnAction(e -> {
            // rediriger sur la page SignIn (avec e-mail prérempli)
            new SignInPage(stage).show();
        });

        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);

        topBar.getChildren().addAll(logo, searchField, spacer, signInBtn, signUpBtn);
        return topBar;
    }

    private VBox buildHeroSection(Stage stage) {
        VBox hero = new VBox(20);
        hero.setAlignment(Pos.CENTER);
        hero.setPadding(new Insets(50));
        hero.getStyleClass().add("hero");

        Text headline = new Text("Let’s build from here");
        headline.setFont(Font.font("Arial", 36));
        headline.getStyleClass().add("headline");

        Text subText = new Text("The world’s leading AI-powered developer platform.");
        subText.getStyleClass().add("subtext");

        TextField emailField = new TextField();
        emailField.setPromptText("Enter your email");
        emailField.setMaxWidth(300);

        Button startButton = new Button("Sign up for GitHub");
        startButton.getStyleClass().add("primary-button");
        startButton.setOnAction(e -> {
            String email = emailField.getText();
            if (email != null && !email.isBlank()) {
                // mémoriser l'e-mail et passer à l'étape mot de passe
                com.example.exerciceGithub.session.Session.setEmail(email);
                new PasswordPage(stage).show();
            } else {
                // avertir l'utilisateur
                Alert alert = new Alert(Alert.AlertType.WARNING,
                        "Veuillez entrer un e-mail avant de continuer.", ButtonType.OK);
                alert.showAndWait();
            }
        });

        hero.getChildren().addAll(headline, subText, emailField, startButton);
        return hero;
    }

    private HBox buildFooter() {
        HBox footer = new HBox();
        footer.setPadding(new Insets(10));
        footer.setAlignment(Pos.CENTER);
        footer.getStyleClass().add("footer");

        Label copy = new Label("© 2025 GitHub, Inc.");
        footer.getChildren().add(copy);
        return footer;
    }
}
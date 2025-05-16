package com.example.exerciceGithub;



import com.example.exerciceGithub.view.GitHubHomePage;
import com.example.exerciceGithub.view.SignInPage;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    private Stage primaryStage;

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        showHomeUI();
        primaryStage.setTitle("GitHub – JavaFX Edition");
        primaryStage.show();
    }

    public void showHomeUI() {
        GitHubHomePage home = new GitHubHomePage(primaryStage);
        Scene scene = new Scene(home, 1000, 700);
        scene.getStylesheets().add( getClass().getResource("style/styles.css").toString()  );
    }

    public static void main(String[] args) {
        launch(args);
    }
}

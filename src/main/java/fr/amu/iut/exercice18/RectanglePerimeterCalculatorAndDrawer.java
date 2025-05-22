package fr.amu.iut.exercice18;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Classe principale pour lancer l'application JavaFX.
 * Charge le fichier FXML et affiche la fenêtre.
 */
public class RectanglePerimeterCalculatorAndDrawer extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        // Charge le layout FXML depuis le classpath
        Parent root = FXMLLoader.load(
                getClass().getResource("fenetrePerimetre.fxml")
        );
        Scene scene = new Scene(root);
        primaryStage.setTitle("Calculateur de périmètre de rectangle");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

package fr.amu.iut.exercice17;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class AppMaths extends Application {

    private final List<LigneExercice> lignes = new ArrayList<>();
    private VBox exerciceBox;
    private VBox root;
    private ComboBox<Integer> combo;

    @Override
    public void start(Stage primaryStage) {
        root = new VBox(10);
        root.setStyle("-fx-padding: 15;");

        // Ligne du ComboBox
        HBox topRow = new HBox(10);
        Label label = new Label("Combien d'exercices ?");
        combo = new ComboBox<>();
        combo.getItems().addAll(6, 9, 12, 15);
        combo.setValue(6); // valeur par défaut
        topRow.getChildren().addAll(label, combo);

        exerciceBox = new VBox(8);

        root.getChildren().addAll(topRow, exerciceBox);
        combo.setOnAction(e -> afficherExercices(combo.getValue()));
        afficherExercices(combo.getValue());

        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.setTitle("App Maths");
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    private void afficherExercices(int nombre) {
        exerciceBox.getChildren().clear();

        // Réutiliser les incorrects
        List<LigneExercice> anciensIncorrects = lignes.stream()
                .filter(l -> !l.estCorrect())
                .limit(nombre)
                .collect(Collectors.toList());

        lignes.clear();
        lignes.addAll(anciensIncorrects);

        while (lignes.size() < nombre) {
            lignes.add(new LigneExercice());
        }

        exerciceBox.getChildren().addAll(lignes);

        Button valider = new Button("Voir le résultat");
        valider.setOnAction(e -> afficherResultat());

        HBox boutonBox = new HBox(valider);
        boutonBox.setStyle("-fx-alignment: center;");
        exerciceBox.getChildren().add(boutonBox);

        Platform.runLater(() -> {
            if (root.getScene() != null && root.getScene().getWindow() != null) {
                root.getScene().getWindow().sizeToScene();
            }
        });
    }

    private void afficherResultat() {
        long corrects = lignes.stream().filter(LigneExercice::estCorrect).count();

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Résultat");
        alert.setHeaderText(null);
        alert.setContentText("Nombre de bonnes réponses : " + corrects + " / " + lignes.size());
        alert.showAndWait();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

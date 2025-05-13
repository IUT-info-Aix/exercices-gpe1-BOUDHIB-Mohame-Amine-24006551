package fr.amu.iut.exercice12;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class Palette extends Application {

    private Label texteDuHaut;
    private Label texteDuBas;
    private Pane panneau;
    private CustomButton vert, rouge, bleu;
    private CustomButton sourceOfEvent;

    @Override
    public void start(Stage primaryStage) {
        // --- Setup de la vue ---
        BorderPane root = new BorderPane();

        // Label du haut
        texteDuHaut = new Label("Choisissez une couleur");
        texteDuHaut.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        BorderPane.setAlignment(texteDuHaut, Pos.CENTER);
        root.setTop(texteDuHaut);

        // Panneau central
        panneau = new Pane();
        panneau.setPrefSize(400, 200);
        panneau.setStyle("-fx-background-color: white;");
        root.setCenter(panneau);

        // Boutons
        vert  = new CustomButton("Vert",  "#31BCA4");
        rouge = new CustomButton("Rouge", "#F21411");
        bleu  = new CustomButton("Bleu",  "#3273A4");

        HBox boutons = new HBox(10, vert, rouge, bleu);
        boutons.setAlignment(Pos.CENTER);
        boutons.setPadding(new Insets(10, 5, 10, 5));

        // Label du bas
        texteDuBas = new Label();
        texteDuBas.setFont(Font.font(14));

        VBox bas = new VBox(5, boutons, texteDuBas);
        bas.setAlignment(Pos.CENTER_RIGHT);
        root.setBottom(bas);

        // --- Gestionnaire d'événement commun ---
        EventHandler<ActionEvent> gestionnaireEvenement = evt -> {
            sourceOfEvent = (CustomButton) evt.getSource();
            // incrémentation du compteur
            sourceOfEvent.setNbClics(sourceOfEvent.getNbClics() + 1);
        };

        vert.setOnAction(gestionnaireEvenement);
        rouge.setOnAction(gestionnaireEvenement);
        bleu.setOnAction(gestionnaireEvenement);

        // --- Listener sur les nbClics ---
        ChangeListener<Number> nbClicsListener = new ChangeListener<>() {
            @Override
            public void changed(ObservableValue<? extends Number> obs, Number oldVal, Number newVal) {
                int count = newVal.intValue();
                String nom = sourceOfEvent.getText();
                String col = sourceOfEvent.getCouleur();

                // Haut
                texteDuHaut.setText(nom + " choisi " + count + " fois");
                // panneau
                panneau.setStyle("-fx-background-color: " + col + ";");
                // bas
                texteDuBas.setText("Le " + nom + " est une jolie couleur !");
                texteDuBas.setTextFill(Color.web(col));
            }
        };

        // On attache le listener à chacun des boutons
        vert.nbClicsProperty().addListener(nbClicsListener);
        rouge.nbClicsProperty().addListener(nbClicsListener);
        bleu.nbClicsProperty().addListener(nbClicsListener);

        // --- Finalisation ---
        Scene scene = new Scene(root);
        primaryStage.setTitle("Palette personnalisée");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

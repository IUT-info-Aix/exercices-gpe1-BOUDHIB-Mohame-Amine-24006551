package fr.amu.iut.exercice4;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Palette extends Application {

    // Compteurs pour chaque couleur sélectionnée
    private int nbVert = 0;
    private int nbRouge = 0;
    private int nbBleu = 0;

    // Composants de l'interface
    private Button vert;
    private Button rouge;
    private Button bleu;
    private Label label;
    private Pane panneau;
    private HBox bas;
    private BorderPane root;

    @Override
    public void start(Stage primaryStage) throws Exception {
        // Création du conteneur principal et définition de sa taille (400 x 200)
        root = new BorderPane();
        root.setPrefSize(400, 200);

        // Création et configuration du Label (zone du haut), centré
        label = new Label("Choisissez une couleur");
        label.setStyle("-fx-font-size: 16px; -fx-font-weight: bold;");
        BorderPane.setAlignment(label, Pos.CENTER);
        root.setTop(label);

        // Création du Pane (zone centrale) avec une couleur initiale blanche
        panneau = new Pane();
        panneau.setStyle("-fx-background-color: white;");
        root.setCenter(panneau);

        // Création des boutons pour la zone basse
        vert = new Button("Vert");
        rouge = new Button("Rouge");
        bleu = new Button("Bleu");

        // Gestion de l'action pour le bouton "Vert"
        vert.setOnAction(e -> {
            nbVert++;
            label.setText("Vert choisi " + nbVert + " fois");
            panneau.setStyle("-fx-background-color: green;");
        });

        // Gestion de l'action pour le bouton "Rouge"
        rouge.setOnAction(e -> {
            nbRouge++;
            label.setText("Rouge choisi " + nbRouge + " fois");
            panneau.setStyle("-fx-background-color: red;");
        });

        // Gestion de l'action pour le bouton "Bleu"
        bleu.setOnAction(e -> {
            nbBleu++;
            label.setText("Bleu choisi " + nbBleu + " fois");
            panneau.setStyle("-fx-background-color: blue;");
        });

        // Création de l'HBox pour contenir les boutons et centrer leur affichage
        bas = new HBox(10);
        bas.setAlignment(Pos.CENTER);
        bas.getChildren().addAll(vert, rouge, bleu);
        root.setBottom(bas);

        // Création de la scène et association au stage principal
        Scene scene = new Scene(root);
        primaryStage.setTitle("Palette de couleurs");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    // Méthode principale pour lancer l'application JavaFX
    public static void main(String[] args) {
        launch(args);
    }
}

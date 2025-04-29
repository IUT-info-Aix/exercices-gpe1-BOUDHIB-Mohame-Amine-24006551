package fr.amu.iut.exercice6;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.util.ArrayList;

public class IHMPendu extends Application {

    private static final int MAX_VIES = 7;
    private int vies;
    private String motATrouver;
    private char[] motCache;
    private Dico dico = new Dico();

    private Label labelMot;
    private Label labelVies;
    private GridPane clavier;
    private ImageView imagePendu;
    private Button boutonRecommencer;

    private BorderPane root;

    @Override
    public void start(Stage primaryStage) {
        root = new BorderPane();
        initialiserJeu();

        Scene scene = new Scene(root, 600, 400);
        primaryStage.setTitle("Jeu du Pendu");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void initialiserJeu() {
        vies = MAX_VIES;
        motATrouver = dico.getMot().toUpperCase();
        motCache = new char[motATrouver.length()];
        for (int i = 0; i < motCache.length; i++) motCache[i] = '_';

        labelMot = new Label(new String(motCache));
        labelMot.setStyle("-fx-font-size: 24px;");
        labelVies = new Label("Vies restantes : " + vies);

        imagePendu = new ImageView();
        imagePendu.setFitWidth(200);
        imagePendu.setPreserveRatio(true);
        mettreAJourImage();

        clavier = new GridPane();
        clavier.setHgap(5);
        clavier.setVgap(5);
        clavier.setAlignment(Pos.CENTER);
        ajouterTouches();

        boutonRecommencer = new Button("Recommencer");
        boutonRecommencer.setOnAction(e -> {
            root.getChildren().clear();
            initialiserJeu();
        });

        VBox infoBox = new VBox(10, labelMot, labelVies, boutonRecommencer);
        infoBox.setAlignment(Pos.CENTER);

        VBox rightBox = new VBox(20, infoBox, clavier);
        rightBox.setAlignment(Pos.CENTER);
        rightBox.setPadding(new Insets(10));

        root.setLeft(imagePendu);
        root.setCenter(rightBox);
    }

    private void ajouterTouches() {
        clavier.getChildren().clear();
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        int col = 0, row = 0;
        for (char lettre : alphabet.toCharArray()) {
            Button btn = new Button(String.valueOf(lettre));
            btn.setPrefWidth(30);
            btn.setOnAction(e -> proposerLettre(btn));
            clavier.add(btn, col, row);
            col++;
            if (col == 10) {
                col = 0;
                row++;
            }
        }
    }

    private void proposerLettre(Button bouton) {
        if (vies <= 0 || new String(motCache).equals(motATrouver)) return;

        char lettre = bouton.getText().charAt(0);
        bouton.setDisable(true);
        ArrayList<Integer> positions = dico.getPositions(lettre, motATrouver);

        if (positions.isEmpty()) {
            vies--;
            mettreAJourImage();
            labelVies.setText("Vies restantes : " + vies);
        } else {
            for (int pos : positions) motCache[pos] = lettre;
            labelMot.setText(new String(motCache));
        }

        verifierFinPartie();
    }

    private void verifierFinPartie() {
        if (vies <= 0) {
            labelMot.setText("Perdu ! Le mot était : " + motATrouver);
        } else if (new String(motCache).equals(motATrouver)) {
            labelMot.setText("Bravo ! Mot trouvé : " + motATrouver);
        }
    }

    private void mettreAJourImage() {
        int index = Math.max(0, Math.min(7, vies));
        String chemin = "/exercice6/pendu" + index + ".png";
        Image image = new Image(getClass().getResourceAsStream(chemin));
        imagePendu.setImage(image);
    }

    public static void main(String[] args) {
        launch(args);
    }
}

package fr.amu.iut.exercice5;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.util.*;

public class JeuMain extends Application {

    private Scene scene;
    private BorderPane root;
    public static final List<Obstacle> obstacles = new ArrayList<>();

    private boolean jeuActif = true;
    private boolean botMode = false;
    private String deplacementJ1 = "";
    private String deplacementJ2 = "";
    private String botDeplacement = "";

    @Override
    public void start(Stage primaryStage) {
        // Choix du mode
        ChoiceDialog<String> dialog = new ChoiceDialog<>("1v1", "1v1", "1vBot");
        dialog.setTitle("Mode de jeu");
        dialog.setHeaderText("Choisissez le mode de jeu");
        dialog.setContentText("Mode :");
        Optional<String> result = dialog.showAndWait();
        result.ifPresent(mode -> botMode = mode.equals("1vBot"));

        root = new BorderPane();

        Personnage pacman = new Pacman();
        Personnage fantome = new Fantome();

        // Positionner aux extrémités
        pacman.setLayoutX(0);
        pacman.setLayoutY(0);
        fantome.setLayoutX(600);
        fantome.setLayoutY(440);

        Pane jeu = new Pane();
        jeu.setPrefSize(640, 480);

        // Obstacles
        Obstacle mur1 = new Obstacle(200, 0, 20, 300);
        Obstacle mur2 = new Obstacle(400, 180, 20, 300);
        obstacles.addAll(List.of(mur1, mur2));
        jeu.getChildren().addAll(mur1, mur2);

        jeu.getChildren().addAll(pacman, fantome);
        root.setCenter(jeu);
        scene = new Scene(root);

        // Gestion des touches
        deplacer(pacman, fantome);

        // Timer de 10 secondes
        long startTime = System.currentTimeMillis();

        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                if (!jeuActif) {
                    this.stop();
                    return;
                }

                // Déplacement automatique
                if (!deplacementJ1.isEmpty()) {
                    switch (deplacementJ1) {
                        case "gauche" -> pacman.deplacerAGauche();
                        case "droite" -> pacman.deplacerADroite(scene.getWidth());
                        case "haut" -> pacman.deplacerEnHaut();
                        case "bas" -> pacman.deplacerEnBas(scene.getHeight());
                    }
                }

                if (!botMode && !deplacementJ2.isEmpty()) {
                    switch (deplacementJ2) {
                        case "gauche" -> fantome.deplacerAGauche();
                        case "droite" -> fantome.deplacerADroite(scene.getWidth());
                        case "haut" -> fantome.deplacerEnHaut();
                        case "bas" -> fantome.deplacerEnBas(scene.getHeight());
                    }
                }

                if (botMode) {
                    // IA basique pour fuir Pacman
                    double dx = fantome.getLayoutX() - pacman.getLayoutX();
                    double dy = fantome.getLayoutY() - pacman.getLayoutY();
                    if (Math.abs(dx) > Math.abs(dy)) {
                        if (dx > 0) fantome.deplacerADroite(scene.getWidth());
                        else fantome.deplacerAGauche();
                    } else {
                        if (dy > 0) fantome.deplacerEnBas(scene.getHeight());
                        else fantome.deplacerEnHaut();
                    }
                }

                // Collision
                if (pacman.estEnCollision(fantome)) {
                    jeuActif = false;
                    showWinner("Pacman a attrapé le fantôme !");
                    this.stop();
                }

                // Fin du jeu après 10s
                if (System.currentTimeMillis() - startTime > 10000) {
                    jeuActif = false;
                    if (botMode) {
                        showWinner("Le fantôme a survécu 10 secondes !");
                    } else {
                        showWinner("Temps écoulé !");
                    }
                    this.stop();
                }
            }
        };
        timer.start();

        primaryStage.setTitle("... Pac Man ...");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void deplacer(Personnage j1, Personnage j2) {
        scene.setOnKeyPressed((KeyEvent event) -> {
            KeyCode code = event.getCode();
            switch (code) {
                case LEFT -> deplacementJ1 = "gauche";
                case RIGHT -> deplacementJ1 = "droite";
                case UP -> deplacementJ1 = "haut";
                case DOWN -> deplacementJ1 = "bas";
                case Q -> deplacementJ2 = "gauche";
                case D -> deplacementJ2 = "droite";
                case Z -> deplacementJ2 = "haut";
                case S -> deplacementJ2 = "bas";
            }
        });
    }

    private void showWinner(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Fin du jeu");
        alert.setHeaderText(message);
        alert.showAndWait();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

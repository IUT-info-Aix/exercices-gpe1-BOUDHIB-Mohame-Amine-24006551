package fr.amu.iut.exercice5;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.ArrayList;

public class JeuMain extends Application {

    private Scene scene;
    private BorderPane root;
    private final boolean[] jeuActif = {true};
    private Timeline deplacementJ1;
    private Timeline deplacementJ2;


    public static final ArrayList<Obstacle> obstacles = new ArrayList<>();

    @Override
    public void start(Stage primaryStage) {

        root = new BorderPane();

        //Acteurs du jeu
        Personnage pacman = new Pacman();
        Personnage fantome = new Fantome();

        // Pacman en haut à gauche
        pacman.setLayoutX(0);
        pacman.setLayoutY(0);

        // Fantôme en bas à droite
        fantome.setLayoutX(640 - 20); // 640 largeur - largeur fantôme (20px)
        fantome.setLayoutY(480 - 20); // 480 hauteur - hauteur fantôme (20px)

        Obstacle mur1 = new Obstacle(200, 100, 50, 200);
        Obstacle mur2 = new Obstacle(400, 50, 30, 300);

        obstacles.add(mur1);
        obstacles.add(mur2);

        // on positionne le fantôme 20 positions vers la droite
        fantome.setLayoutX(20 * 10);
        //panneau du jeu
        Pane jeu = new Pane();
        jeu.getChildren().addAll(mur1, mur2);
        jeu.setPrefSize(640, 480);
        jeu.getChildren().add(pacman);
        jeu.getChildren().add(fantome);
        root.setCenter(jeu);
        //on construit une scene 640 * 480 pixels
        scene = new Scene(root);

        //Gestion du déplacement du personnage
        deplacer(pacman, fantome);

        primaryStage.setTitle("... Pac Man ...");

        primaryStage.setScene(scene);
        primaryStage.show();
        // Lancer le compte à rebours dès que la scène s'affiche
        Timeline chrono = new Timeline(new KeyFrame(Duration.seconds(30), e -> {
            if (jeuActif[0]) {
                jeuActif[0] = false;
                System.out.println("Temps écoulé ! Le Fantôme gagne !");
                root.setStyle("-fx-background-color: darkblue;");
            }
        }));
        chrono.setCycleCount(1); // une seule exécution
        chrono.play();

    }

    /**
     * Permet de gérer les événements de type clavier, pression des touches
     * pour le j1(up,down, right, left), pour le j2( z,q,s,d)
     *
     * @param j1
     * @param j2
     */
    private void deplacer(Personnage j1, Personnage j2) {
        deplacementJ1 = new Timeline();
        deplacementJ1.setCycleCount(Timeline.INDEFINITE);

        deplacementJ2 = new Timeline();
        deplacementJ2.setCycleCount(Timeline.INDEFINITE);

        scene.setOnKeyPressed((KeyEvent event) -> {
            if (!jeuActif[0]) return; // bloquer si jeu terminé

            switch (event.getCode()) {
                // Déplacement Pacman
                case LEFT -> {
                    deplacementJ1.stop();
                    deplacementJ1.getKeyFrames().setAll(new KeyFrame(Duration.millis(150), e -> j1.deplacerAGauche()));
                    deplacementJ1.play();
                }
                case RIGHT -> {
                    deplacementJ1.stop();
                    deplacementJ1.getKeyFrames().setAll(new KeyFrame(Duration.millis(150), e -> j1.deplacerADroite(scene.getWidth())));
                    deplacementJ1.play();
                }
                case UP -> {
                    deplacementJ1.stop();
                    deplacementJ1.getKeyFrames().setAll(new KeyFrame(Duration.millis(150), e -> j1.deplacerEnHaut()));
                    deplacementJ1.play();
                }
                case DOWN -> {
                    deplacementJ1.stop();
                    deplacementJ1.getKeyFrames().setAll(new KeyFrame(Duration.millis(150), e -> j1.deplacerEnBas(scene.getHeight())));
                    deplacementJ1.play();
                }

                // Déplacement Fantôme
                case Q -> {
                    deplacementJ2.stop();
                    deplacementJ2.getKeyFrames().setAll(new KeyFrame(Duration.millis(150), e -> j2.deplacerAGauche()));
                    deplacementJ2.play();
                }
                case D -> {
                    deplacementJ2.stop();
                    deplacementJ2.getKeyFrames().setAll(new KeyFrame(Duration.millis(150), e -> j2.deplacerADroite(scene.getWidth())));
                    deplacementJ2.play();
                }
                case Z -> {
                    deplacementJ2.stop();
                    deplacementJ2.getKeyFrames().setAll(new KeyFrame(Duration.millis(150), e -> j2.deplacerEnHaut()));
                    deplacementJ2.play();
                }
                case S -> {
                    deplacementJ2.stop();
                    deplacementJ2.getKeyFrames().setAll(new KeyFrame(Duration.millis(150), e -> j2.deplacerEnBas(scene.getHeight())));
                    deplacementJ2.play();
                }
            }

            // Collision
            if (j1.estEnCollision(j2)) {
                jeuActif[0] = false;
                System.out.println("Pacman gagne ! Il a attrapé le fantôme !");
                root.setStyle("-fx-background-color: gold;");
                deplacementJ1.stop();
                deplacementJ2.stop();
            }
        });
    }




}

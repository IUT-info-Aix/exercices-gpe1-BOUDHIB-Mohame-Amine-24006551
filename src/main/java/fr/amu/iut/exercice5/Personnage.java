package fr.amu.iut.exercice5;

import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class Personnage extends Group {
    protected final static double LARGEUR_MOITIE_PERSONNAGE = 10;
    protected final static double LARGEUR_PERSONNAGE = LARGEUR_MOITIE_PERSONNAGE * 2;
    private final Circle corps;
    private String direction;

    public Personnage(String direction, Color couleurContour, Color couleurRemplissage) {
        this.direction = direction;
        corps = new Circle(10, 10, LARGEUR_MOITIE_PERSONNAGE, couleurContour);
        corps.setFill(couleurRemplissage);
        getChildren().add(corps);
    }

    public void deplacerAGauche() {
        double oldX = getLayoutX();
        if (getLayoutX() >= LARGEUR_PERSONNAGE) {
            setLayoutX(getLayoutX() - LARGEUR_PERSONNAGE);
        }
        if (enCollisionAvecObstacle()) setLayoutX(oldX);
        direction = "gauche";
    }

    public void deplacerADroite(double largeurJeu) {
        double oldX = getLayoutX();
        if (getLayoutX() < largeurJeu - LARGEUR_PERSONNAGE) {
            setLayoutX(getLayoutX() + LARGEUR_PERSONNAGE);
        }
        if (enCollisionAvecObstacle()) setLayoutX(oldX);
        direction = "droite";
    }

    public void deplacerEnBas(double hauteurJeu) {
        double oldY = getLayoutY();
        if (getLayoutY() < hauteurJeu - LARGEUR_PERSONNAGE) {
            setLayoutY(getLayoutY() + LARGEUR_PERSONNAGE);
        }
        if (enCollisionAvecObstacle()) setLayoutY(oldY);
        direction = "bas";
    }

    public void deplacerEnHaut() {
        double oldY = getLayoutY();
        if (getLayoutY() >= LARGEUR_PERSONNAGE) {
            setLayoutY(getLayoutY() - LARGEUR_PERSONNAGE);
        }
        if (enCollisionAvecObstacle()) setLayoutY(oldY);
        direction = "haut";
    }

    public boolean estEnCollision(Personnage autrePersonnage) {
        return getBoundsInParent().intersects(autrePersonnage.getBoundsInParent());
    }

    private boolean enCollisionAvecObstacle() {
        return JeuMain.obstacles.stream().anyMatch(o -> this.getBoundsInParent().intersects(o.getBoundsInParent()));
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public void startAutoDeplacement(double largeurJeu, double hauteurJeu) {
        switch (direction) {
            case "gauche":
                deplacerAGauche();
                break;
            case "droite":
                deplacerADroite(largeurJeu);
                break;
            case "haut":
                deplacerEnHaut();
                break;
            case "bas":
                deplacerEnBas(hauteurJeu);
                break;
        }
    }
}
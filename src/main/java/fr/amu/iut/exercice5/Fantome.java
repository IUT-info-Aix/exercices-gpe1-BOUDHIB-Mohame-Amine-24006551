// Fichier : Fantome.java
package fr.amu.iut.exercice5;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

public class Fantome extends Personnage {

    private Rectangle basCorps;
    private Circle oeilGauche;
    private Circle retineGauche;
    private Circle oeilDroit;
    private Circle retineDroite;

    public Fantome() {
        super("gauche", Color.BLUE, Color.BLUE);

        basCorps = new Rectangle(0, 10, 20, 10);
        basCorps.setFill(Color.BLUE);

        oeilGauche = new Circle(6, 6, 2, Color.WHITE);
        retineGauche = new Circle(7, 6, 1, Color.BLACK);

        oeilDroit = new Circle(14, 6, 2, Color.WHITE);
        retineDroite = new Circle(15, 6, 1, Color.BLACK);

        getChildren().addAll(basCorps, oeilGauche, retineGauche, oeilDroit, retineDroite);
    }

    @Override
    public void setDirection(String direction) {
        super.setDirection(direction);
        switch (direction) {
            case "gauche":
                retineGauche.setCenterX(oeilGauche.getCenterX() - 1);
                retineDroite.setCenterX(oeilDroit.getCenterX() - 1);
                break;
            case "droite":
                retineGauche.setCenterX(oeilGauche.getCenterX() + 1);
                retineDroite.setCenterX(oeilDroit.getCenterX() + 1);
                break;
            case "haut":
                retineGauche.setCenterY(oeilGauche.getCenterY() - 1);
                retineDroite.setCenterY(oeilDroit.getCenterY() - 1);
                break;
            case "bas":
                retineGauche.setCenterY(oeilGauche.getCenterY() + 1);
                retineDroite.setCenterY(oeilDroit.getCenterY() + 1);
                break;
        }
    }
}
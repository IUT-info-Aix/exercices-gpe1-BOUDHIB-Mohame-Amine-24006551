// Fichier : Pacman.java
package fr.amu.iut.exercice5;

import javafx.scene.paint.Color;
import javafx.scene.shape.Line;

public class Pacman extends Personnage {

    private Line bouche;

    public Pacman() {
        super("droite", Color.BLACK, Color.YELLOW);
        bouche = new Line(10, 10, 19.5, 10);
        bouche.setFill(Color.BLACK);
        getChildren().add(bouche);
    }

    @Override
    public void deplacerAGauche() {
        super.deplacerAGauche();
        bouche.setEndX(bouche.getStartX() - 9.5);
        bouche.setEndY(bouche.getStartY());
    }

    @Override
    public void deplacerADroite(double largeurJeu) {
        super.deplacerADroite(largeurJeu);
        bouche.setEndX(bouche.getStartX() + 9.5);
        bouche.setEndY(bouche.getStartY());
    }

    @Override
    public void deplacerEnBas(double hauteurJeu) {
        super.deplacerEnBas(hauteurJeu);
        bouche.setEndX(bouche.getStartX());
        bouche.setEndY(bouche.getStartY() + 9.5);
    }

    @Override
    public void deplacerEnHaut() {
        super.deplacerEnHaut();
        bouche.setEndX(bouche.getStartX());
        bouche.setEndY(bouche.getStartY() - 9.5);
    }
}
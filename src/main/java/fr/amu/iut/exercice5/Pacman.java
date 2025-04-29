package fr.amu.iut.exercice5;

import javafx.scene.paint.Color;
import javafx.scene.shape.Line;

public class Pacman extends Personnage {

    private Line bouche;


    public Pacman() {
        super("droite", Color.BLACK, Color.YELLOW);
        bouche = new Line(LARGEUR_MOITIE_PERSONNAGE, LARGEUR_MOITIE_PERSONNAGE, (LARGEUR_MOITIE_PERSONNAGE * 2) - .5, LARGEUR_MOITIE_PERSONNAGE);
        bouche.setFill(Color.BLACK);

        super.getChildren().add(bouche);
    }

    @Override
    public void deplacerAGauche() {
        super.deplacerAGauche();
        //sens de la bouche
        bouche.setEndX(bouche.getStartX() - LARGEUR_MOITIE_PERSONNAGE + .5);
        bouche.setEndY(bouche.getStartY());
    }

    @Override
    public void deplacerADroite(double largeurJeu) {
        super.deplacerADroite(largeurJeu);
        //sens de la bouche
        bouche.setEndX(bouche.getStartX() + LARGEUR_MOITIE_PERSONNAGE - .5);
        bouche.setEndY(bouche.getStartY());
    }

    @Override
    public void deplacerEnBas(double hauteurJeu) {
        // On effectue le déplacement vertical vers le bas
        super.deplacerEnBas(hauteurJeu);
        // Orientation de la bouche vers le bas :
        // La position de départ de la bouche (startX, startY) reste inchangée
        // On décale le second point verticalement vers le bas.
        bouche.setEndX(bouche.getStartX());
        bouche.setEndY(bouche.getStartY() + LARGEUR_MOITIE_PERSONNAGE - 0.5);
    }

    @Override
    public void deplacerEnHaut() {
        // On effectue le déplacement vertical vers le haut
        super.deplacerEnHaut();
        // Orientation de la bouche vers le haut :
        // On décale le second point verticalement vers le haut.
        bouche.setEndX(bouche.getStartX());
        bouche.setEndY(bouche.getStartY() - LARGEUR_MOITIE_PERSONNAGE + 0.5);
    }

}

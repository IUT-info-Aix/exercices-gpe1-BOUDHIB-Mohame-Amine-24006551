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
        super("droite", Color.BLUE, Color.BLUE);
        // Corps inférieur du fantôme
        basCorps = new Rectangle(0, 10, LARGEUR_PERSONNAGE, 10);
        basCorps.setFill(Color.BLUE);

        // Création des yeux
        oeilGauche = new Circle(6, 6, 2, Color.WHITE);
        // Par défaut, le fantôme regarde vers la droite
        retineGauche = new Circle(oeilGauche.getCenterX() + 1, oeilGauche.getCenterY(), 1, Color.BLACK);

        oeilDroit = new Circle(14, 6, 2, Color.WHITE);
        retineDroite = new Circle(oeilDroit.getCenterX() + 1, oeilDroit.getCenterY(), 1, Color.BLACK);

        // Ajout des éléments graphiques dans le groupe (hérité de Personnage)
        getChildren().addAll(basCorps, oeilGauche, retineGauche, oeilDroit, retineDroite);
    }

    @Override
    public void deplacerAGauche() {
        // Déplacement de base vers la gauche (contrôle des limites et mise à jour de la position)
        super.deplacerAGauche();
        // Orientation des yeux : décalage des rétines d'un pixel à gauche
        retineGauche.setCenterX(oeilGauche.getCenterX() - 1);
        retineGauche.setCenterY(oeilGauche.getCenterY());
        retineDroite.setCenterX(oeilDroit.getCenterX() - 1);
        retineDroite.setCenterY(oeilDroit.getCenterY());
    }

    @Override
    public void deplacerADroite(double largeurJeu) {
        // Déplacement de base vers la droite
        super.deplacerADroite(largeurJeu);
        // Orientation des yeux : décalage des rétines d'un pixel à droite
        retineGauche.setCenterX(oeilGauche.getCenterX() + 1);
        retineGauche.setCenterY(oeilGauche.getCenterY());
        retineDroite.setCenterX(oeilDroit.getCenterX() + 1);
        retineDroite.setCenterY(oeilDroit.getCenterY());
    }

    @Override
    public void deplacerEnHaut() {
        // Déplacement de base vers le haut
        super.deplacerEnHaut();
        // Orientation des yeux : décalage des rétines d'un pixel vers le haut
        retineGauche.setCenterX(oeilGauche.getCenterX());
        retineGauche.setCenterY(oeilGauche.getCenterY() - 1);
        retineDroite.setCenterX(oeilDroit.getCenterX());
        retineDroite.setCenterY(oeilDroit.getCenterY() - 1);
    }

    @Override
    public void deplacerEnBas(double hauteurJeu) {
        // Déplacement de base vers le bas
        super.deplacerEnBas(hauteurJeu);
        // Orientation des yeux : décalage des rétines d'un pixel vers le bas
        retineGauche.setCenterX(oeilGauche.getCenterX());
        retineGauche.setCenterY(oeilGauche.getCenterY() + 1);
        retineDroite.setCenterX(oeilDroit.getCenterX());
        retineDroite.setCenterY(oeilDroit.getCenterY() + 1);
    }
}

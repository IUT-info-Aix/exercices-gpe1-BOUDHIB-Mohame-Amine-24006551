package fr.amu.iut.exercice18;

import javafx.beans.binding.Bindings;
import javafx.beans.binding.NumberBinding;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

/**
 * Classe représentant un rectangle défini par deux sommets A et B
 * et calculant son périmètre.
 */
public class Rectangle {
    private final IntegerProperty xA;
    private final IntegerProperty yA;
    private final IntegerProperty xB;
    private final IntegerProperty yB;
    private final IntegerProperty perimetre;

    public Rectangle() {
        this.xA = new SimpleIntegerProperty(this, "xA", 0);
        this.yA = new SimpleIntegerProperty(this, "yA", 0);
        this.xB = new SimpleIntegerProperty(this, "xB", 0);
        this.yB = new SimpleIntegerProperty(this, "yB", 0);
        this.perimetre = new SimpleIntegerProperty(this, "perimetre", 0);
        createBinding();
    }

    /**
     * Met en place les bindings pour calculer la largeur, la hauteur
     * et lier le périmètre en fonction de ces valeurs.
     */
    private void createBinding() {
        // largeur = |xB - xA|
        NumberBinding largeur = Bindings.createIntegerBinding(
                () -> Math.abs(xB.get() - xA.get()),
                xA, xB
        );
        // hauteur = |yB - yA|
        NumberBinding hauteur = Bindings.createIntegerBinding(
                () -> Math.abs(yB.get() - yA.get()),
                yA, yB
        );
        // périmètre = 2 * (largeur + hauteur)
        perimetre.bind(
                Bindings.multiply(
                        Bindings.add(largeur, hauteur),
                        2
                )
        );
    }

    // Getters pour lier les propriétés depuis le contrôleur
    public IntegerProperty xAProperty() {
        return xA;
    }

    public IntegerProperty yAProperty() {
        return yA;
    }

    public IntegerProperty xBProperty() {
        return xB;
    }

    public IntegerProperty yBProperty() {
        return yB;
    }

    public IntegerProperty perimetreProperty() {
        return perimetre;
    }
}

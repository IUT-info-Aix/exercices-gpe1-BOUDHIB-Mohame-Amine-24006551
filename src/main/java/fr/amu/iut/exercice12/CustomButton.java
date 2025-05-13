package fr.amu.iut.exercice12;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.control.Button;

public class CustomButton extends Button {

    private final String couleur;
    private final IntegerProperty nbClics;

    public CustomButton(String texte, String couleur) {
        super(texte);
        this.couleur = couleur;
        this.nbClics = new SimpleIntegerProperty(0);
    }

    // Getter pour la propriété nbClics
    public IntegerProperty nbClicsProperty() {
        return nbClics;
    }

    // Getter valeur
    public int getNbClics() {
        return nbClics.get();
    }

    // Setter valeur
    public void setNbClics(int value) {
        this.nbClics.set(value);
    }

    // Getter pour la couleur
    public String getCouleur() {
        return couleur;
    }
}

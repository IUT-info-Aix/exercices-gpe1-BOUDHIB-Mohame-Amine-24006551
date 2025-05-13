package fr.amu.iut.exercice14;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Personne {

    private final String nom;
    private final IntegerProperty age;
    private final StringProperty villeDeNaissance;

    public Personne(String nom, int age) {
        this.nom = nom;
        this.age = new SimpleIntegerProperty(age);
        this.villeDeNaissance = new SimpleStringProperty("Paris");
    }

    public void setAge(int age) {
        this.age.set(age);
    }

    public void setVilleDeNaissance(String ville) {
        villeDeNaissance.set(ville);
    }

    public IntegerProperty ageProperty() {
        return age;
    }

    public StringProperty villeDeNaissanceProperty() {
        return villeDeNaissance;
    }

    public int getAge() {
        return age.get();
    }

    public String getVilleDeNaissance() {
        return villeDeNaissance.get();
    }
}

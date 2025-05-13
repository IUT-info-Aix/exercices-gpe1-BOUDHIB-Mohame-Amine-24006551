// Personne.java
package fr.amu.iut.exercice13;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Personne {

    private final StringProperty nom;
    private final IntegerProperty age;
    private final StringProperty villeDeNaissance;

    public Personne(String nom, int age) {
        this.nom = new SimpleStringProperty(nom);
        this.age = new SimpleIntegerProperty(age);
        this.villeDeNaissance = new SimpleStringProperty("Paris");
    }

    // nom
    public String getNom() {
        return nom.get();
    }
    public void setNom(String nom) {
        this.nom.set(nom);
    }
    public StringProperty nomProperty() {
        return nom;
    }

    // age
    public int getAge() {
        return age.get();
    }
    public void setAge(int age) {
        this.age.set(age);
    }
    public IntegerProperty ageProperty() {
        return age;
    }

    // villeDeNaissance
    public String getVilleDeNaissance() {
        return villeDeNaissance.get();
    }
    public void setVilleDeNaissance(String ville) {
        this.villeDeNaissance.set(ville);
    }
    public StringProperty villeDeNaissanceProperty() {
        return villeDeNaissance;
    }
}

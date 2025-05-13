// MainPersonnes.java
package fr.amu.iut.exercice13;

import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.beans.Observable;

public class MainPersonnes  {

    private static ObservableList<Personne> lesPersonnes;
    private static ListChangeListener<Personne> unChangementListener;

    public static void main(String[] args) {
        // Pour la Q1–Q2 on se contente de lister ajouts/suppressions.
        // Pour la Q3–Q5, on veut aussi écouter les changements de propriété âge :
        lesPersonnes = FXCollections.observableArrayList(
                personne -> new Observable[]{ personne.ageProperty() }
        );

        // Définition du listener unique pour toutes les questions
        unChangementListener = change -> {
            // Parcourir tous les "batchs" de changements
            while (change.next()) {
                // 1) Ajout
                if (change.wasAdded()) {
                    for (Personne p : change.getAddedSubList()) {
                        System.out.println("Ajout de : " + p.getNom());
                    }
                }
                // 2) Suppression
                if (change.wasRemoved()) {
                    for (Personne p : change.getRemoved()) {
                        System.out.println("Suppression de : " + p.getNom());
                    }
                }
                // 3) Mise à jour d'une propriété interne (ici age)
                if (change.wasUpdated()) {
                    // change.getFrom() donne l’indice modifié,
                    // on peut récupérer l’objet modifié avec getList().get(index)
                    Personne pModifie = change.getList().get(change.getFrom());
                    System.out.println(pModifie.getNom() + " a maintenant " + pModifie.getAge() + " ans");
                }
            }
        };

        lesPersonnes.addListener(unChangementListener);

        // Décommenter la question à tester :
        // question1();
        // question2();
        // question3();
        // question5();
    }

    public static void question1() {
        Personne pierre  = new Personne("Pierre", 20);
        Personne paul    = new Personne("Paul",   40);
        Personne jacques = new Personne("Jacques",60);
        lesPersonnes.add(pierre);
        lesPersonnes.add(paul);
        lesPersonnes.add(jacques);
    }

    public static void question2() {
        Personne pierre  = new Personne("Pierre", 20);
        Personne paul    = new Personne("Paul",   40);
        Personne jacques = new Personne("Jacques",60);
        lesPersonnes.addAll(pierre, paul, jacques);
        lesPersonnes.remove(paul);
    }

    public static void question3() {
        Personne pierre  = new Personne("Pierre", 20);
        Personne paul    = new Personne("Paul",   40);
        Personne jacques = new Personne("Jacques",60);
        lesPersonnes.addAll(pierre, paul, jacques);
        // Avant modification de Personne en propriété, ce setter ne déclenchait rien
        paul.setAge(5);
    }

    public static void question5() {
        Personne pierre  = new Personne("Pierre", 20);
        Personne paul    = new Personne("Paul",   40);
        Personne jacques = new Personne("Jacques",60);
        lesPersonnes.addAll(pierre, paul, jacques);
        // Changer tous les âges
        for (Personne p : lesPersonnes) {
            p.setAge(p.getAge() + 10);
        }
        // Puis supprimer plusieurs
        lesPersonnes.removeAll(paul, pierre);
    }
}

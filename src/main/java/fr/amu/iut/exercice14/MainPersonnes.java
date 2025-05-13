package fr.amu.iut.exercice14;

import javafx.beans.binding.IntegerBinding;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;

public class MainPersonnes {

    private static SimpleListProperty<Personne> lesPersonnes;
    private static IntegerProperty ageMoyen;
    private static IntegerProperty nbParisiens;

    private static IntegerBinding calculAgeMoyen;
    private static IntegerBinding calculNbParisiens;

    public static void main(String[] args) {
        lesPersonnes = new SimpleListProperty<>(FXCollections.observableArrayList());
        ageMoyen = new SimpleIntegerProperty(0);
        nbParisiens = new SimpleIntegerProperty(0);

        // Binding bas-niveau pour l'âge moyen
        calculAgeMoyen = new IntegerBinding() {
            {
                bind(lesPersonnes);
                lesPersonnes.addListener((ListChangeListener<Personne>) change -> {
                    while (change.next()) {
                        if (change.wasAdded()) {
                            for (Personne p : change.getAddedSubList()) {
                                bind(p.ageProperty());
                                p.ageProperty().addListener(obs -> invalidate());
                            }
                        }
                        if (change.wasRemoved()) {
                            invalidate();
                        }
                    }
                    invalidate();
                });
            }

            @Override
            protected int computeValue() {
                if (lesPersonnes.isEmpty()) return 0;
                int somme = 0;
                for (Personne p : lesPersonnes) {
                    somme += p.getAge();
                }
                return somme / lesPersonnes.size();
            }
        };
        ageMoyen.bind(calculAgeMoyen);

        // Binding bas-niveau pour le nombre de Parisiens
        calculNbParisiens = new IntegerBinding() {
            {
                bind(lesPersonnes);
                lesPersonnes.addListener((ListChangeListener<Personne>) change -> {
                    while (change.next()) {
                        if (change.wasAdded()) {
                            for (Personne p : change.getAddedSubList()) {
                                bind(p.villeDeNaissanceProperty());
                                p.villeDeNaissanceProperty().addListener(obs -> invalidate());
                            }
                        }
                        if (change.wasRemoved()) {
                            invalidate();
                        }
                    }
                    invalidate();
                });
            }

            @Override
            protected int computeValue() {
                int count = 0;
                for (Personne p : lesPersonnes) {
                    if ("Paris".equalsIgnoreCase(p.getVilleDeNaissance())) {
                        count++;
                    }
                }
                return count;
            }
        };
        nbParisiens.bind(calculNbParisiens);

        question1();
        question2();
    }

    public static void question1() {
        System.out.println("1 - L'age moyen est de " + ageMoyen.getValue() + " ans");
        Personne pierre = new Personne("Pierre", 20);
        lesPersonnes.add(pierre);
        System.out.println("2 - L'age moyen est de " + ageMoyen.getValue() + " ans");
        Personne paul = new Personne("Paul", 40);
        lesPersonnes.add(paul);
        System.out.println("3 - L'age moyen est de " + ageMoyen.getValue() + " ans");
        Personne jacques = new Personne("Jacques", 60);
        lesPersonnes.add(jacques);
        System.out.println("4 - L'age moyen est de " + ageMoyen.getValue() + " ans");
        paul.setAge(100);
        System.out.println("5 - L'age moyen est de " + ageMoyen.getValue() + " ans");
        lesPersonnes.remove(paul);
        System.out.println("6 - L'age moyen est de " + ageMoyen.getValue() + " ans");
    }

    public static void question2() {
        System.out.println("Il y a " + nbParisiens.getValue() + " parisiens");
        lesPersonnes.get(0).setVilleDeNaissance("Marseille");
        System.out.println("Il y a " + nbParisiens.getValue() + " parisiens");
        lesPersonnes.get(1).setVilleDeNaissance("Montpellier");
        System.out.println("Il y a " + nbParisiens.getValue() + " parisien");
        for (Personne p : lesPersonnes)
            p.setVilleDeNaissance("Paris");
        System.out.println("Il y a " + nbParisiens.getValue() + " parisiens");
    }
}

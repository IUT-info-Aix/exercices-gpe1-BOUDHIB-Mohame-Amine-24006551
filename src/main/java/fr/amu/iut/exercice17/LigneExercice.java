package fr.amu.iut.exercice17;


import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;

public class LigneExercice extends HBox {
    private final Exercice exercice;
    private final TextField saisie;
    private final BooleanProperty correct = new SimpleBooleanProperty(false);

    public LigneExercice() {
        this.exercice = new Exercice();

        Text label = new Text(exercice.getEnonce());
        label.setStyle("-fx-background-color: lightgreen; -fx-padding: 5; -fx-font-size: 14;");

        saisie = new TextField("0");
        saisie.setPrefWidth(50);

        this.setSpacing(10);
        this.getChildren().addAll(label, saisie);
    }

    public boolean estCorrect() {
        try {
            int reponse = Integer.parseInt(saisie.getText());
            correct.set(reponse == exercice.getSolution());
        } catch (NumberFormatException e) {
            correct.set(false);
        }
        return correct.get();
    }

    public BooleanProperty correctProperty() {
        return correct;
    }

    public Exercice getExercice() {
        return exercice;
    }
}

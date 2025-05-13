package fr.amu.iut.exercice15;

import javafx.beans.binding.Bindings;
import javafx.beans.binding.BooleanBinding;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

public class LoginControl extends GridPane {
    @FXML
    private TextField userId;

    @FXML
    private PasswordField pwd;

    @FXML
    private Button okButton;

    @FXML
    private Button cancelButton;

    @FXML
    private void initialize() {
        createBindings();
    }

    private void createBindings() {
        // Le champ mot de passe n'est éditable que si userId >= 6 caractères
        pwd.editableProperty()
                .bind(userId.textProperty().length().greaterThanOrEqualTo(6));

        // Le bouton cancel est désactivé tant que les deux champs sont vides
        cancelButton.disableProperty()
                .bind(
                        userId.textProperty().isEmpty()
                                .and(pwd.textProperty().isEmpty())
                );

        // Bindings pour valider le mot de passe
        BooleanBinding lengthOk = pwd.textProperty().length().greaterThanOrEqualTo(8);
        BooleanBinding hasUpper = Bindings.createBooleanBinding(
                () -> pwd.getText().chars().anyMatch(Character::isUpperCase),
                pwd.textProperty()
        );
        BooleanBinding hasDigit = Bindings.createBooleanBinding(
                () -> pwd.getText().chars().anyMatch(Character::isDigit),
                pwd.textProperty()
        );
        BooleanBinding validPassword = lengthOk.and(hasUpper).and(hasDigit);

        // Le bouton OK n'est cliquable que si le mot de passe est valide
        okButton.disableProperty().bind(validPassword.not());
    }

    @FXML
    private void okClicked() {
        System.out.print(userId.getText() + " ");
        for (char c : pwd.getText().toCharArray()) {
            System.out.print("*");
        }
        System.out.println();
    }

    @FXML
    private void cancelClicked() {
        userId.setText("");
        pwd.setText("");
    }
}
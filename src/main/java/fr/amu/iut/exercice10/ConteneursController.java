package fr.amu.iut.exercice10;

import javafx.fxml.FXML;
import javafx.scene.control.*;

public class ConteneursController {

    @FXML private TextField nameField;
    @FXML private TextField emailField;
    @FXML private TextField passwordField;

    @FXML private Button btn1;
    @FXML private Button btn2;
    @FXML private Button btn3;

    @FXML
    private void handleSubmit() {
        System.out.println("Submit clicked");
        System.out.println("Name: " + nameField.getText());
        System.out.println("Email: " + emailField.getText());
        System.out.println("Password: " + passwordField.getText());
    }

    @FXML
    private void handleCancel() {
        nameField.clear();
        emailField.clear();
        passwordField.clear();
    }
}
package fr.amu.iut.exercice16;

import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.converter.NumberStringConverter;

public class ConvertisseurTemperatures extends Application {

    @Override
    public void start(Stage primaryStage) {
        // Propriétés partagées
        DoubleProperty celsius    = new SimpleDoubleProperty(0);
        DoubleProperty fahrenheit = new SimpleDoubleProperty(32);

        // --- Panneau Celsius ---
        Label labelC = new Label("°C");
        Slider sliderC = new Slider(0, 100, 0);
        sliderC.setShowTickMarks(true);
        sliderC.setShowTickLabels(true);
        sliderC.setMajorTickUnit(10);
        // lier le slider à la propriété celsius
        celsius.bindBidirectional(sliderC.valueProperty());

        // TextField pour afficher la valeur en °C
        TextField fieldC = new TextField();
        fieldC.setPrefColumnCount(5);
        // bind bidirectionnel texte ↔ slider
        Bindings.bindBidirectional(fieldC.textProperty(),
                sliderC.valueProperty(),
                new NumberStringConverter());

        VBox panneauCelsius = new VBox(10, labelC, sliderC, fieldC);
        panneauCelsius.setPadding(new Insets(10));

        // --- Panneau Fahrenheit ---
        Label labelF = new Label("°F");
        Slider sliderF = new Slider(0, 212, 32);
        sliderF.setShowTickMarks(true);
        sliderF.setShowTickLabels(true);
        sliderF.setMajorTickUnit(20);
        // lier le slider à la propriété fahrenheit
        fahrenheit.bindBidirectional(sliderF.valueProperty());

        // TextField pour afficher la valeur en °F
        TextField fieldF = new TextField();
        fieldF.setPrefColumnCount(5);
        Bindings.bindBidirectional(fieldF.textProperty(),
                sliderF.valueProperty(),
                new NumberStringConverter());

        VBox panneauFahrenheit = new VBox(10, labelF, sliderF, fieldF);
        panneauFahrenheit.setPadding(new Insets(10));

        // --- Binding bidirectionnel entre celsius et fahrenheit ---
        // Quand celsius change, on met à jour fahrenheit
        celsius.addListener((obs, oldVal, newVal) -> {
            if (!sliderF.isValueChanging()) {
                fahrenheit.set(newVal.doubleValue() * 9 / 5 + 32);
            }
        });
        // Quand fahrenheit change, on met à jour celsius
        fahrenheit.addListener((obs, oldVal, newVal) -> {
            if (!sliderC.isValueChanging()) {
                celsius.set((newVal.doubleValue() - 32) * 5 / 9);
            }
        });

        // --- Assemblage ---
        HBox root = new HBox(30, panneauCelsius, panneauFahrenheit);
        root.setPadding(new Insets(20));

        primaryStage.setScene(new Scene(root));
        primaryStage.setTitle("Convertisseur °C ↔ °F");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

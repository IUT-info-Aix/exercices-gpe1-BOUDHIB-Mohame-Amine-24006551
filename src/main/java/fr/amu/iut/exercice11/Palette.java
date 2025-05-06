package fr.amu.iut.exercice11;

import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.BooleanBinding;
import javafx.beans.property.*;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Palette extends Application {

    private int nbVert = 0;
    private int nbRouge = 0;
    private int nbBleu = 0;

    private Button vert;
    private Button rouge;
    private Button bleu;
    private Label texteDuHaut;
    private Label texteDuBas;
    private Pane panneau;
    private HBox bas;
    private BorderPane root;

    private IntegerProperty nbFois;
    private StringProperty message;
    private StringProperty couleurPanneau;

    @Override
    public void start(Stage primaryStage) {
        nbFois = new SimpleIntegerProperty(0);
        message = new SimpleStringProperty("");
        couleurPanneau = new SimpleStringProperty("#FFFFFF");

        root = new BorderPane();
        root.setPrefSize(400, 200);

        texteDuHaut = new Label();
        texteDuHaut.setStyle("-fx-font-size: 16px; -fx-font-weight: bold;");
        BorderPane.setAlignment(texteDuHaut, Pos.CENTER);
        root.setTop(texteDuHaut);

        panneau = new Pane();
        panneau.setStyle("-fx-background-color: white;");
        root.setCenter(panneau);

        texteDuBas = new Label();
        texteDuBas.setStyle("-fx-font-size: 12px;");
        BorderPane.setAlignment(texteDuBas, Pos.CENTER_RIGHT);
        root.setBottom(new VBoxLayout());

        vert = new Button("Vert");
        rouge = new Button("Rouge");
        bleu = new Button("Bleu");

        vert.setOnAction(e -> {
            nbVert++;
            nbFois.set(nbVert);
            message.set("Le Vert est une jolie couleur !");
            couleurPanneau.set("green");
        });

        rouge.setOnAction(e -> {
            nbRouge++;
            nbFois.set(nbRouge);
            message.set("Le Rouge est une jolie couleur !");
            couleurPanneau.set("red");
        });

        bleu.setOnAction(e -> {
            nbBleu++;
            nbFois.set(nbBleu);
            message.set("Le Bleu est une jolie couleur !");
            couleurPanneau.set("blue");
        });

        bas = new HBox(10);
        bas.setAlignment(Pos.CENTER);
        bas.getChildren().addAll(vert, rouge, bleu);

        VBoxLayout layoutBas = new VBoxLayout();
        layoutBas.getChildren().addAll(bas, texteDuBas);
        root.setBottom(layoutBas);

        createBindings();

        Scene scene = new Scene(root);
        primaryStage.setTitle("Palette de couleurs");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void createBindings() {
        BooleanBinding pasEncoreDeClic = Bindings.equal(nbFois, 0);

        texteDuHaut.textProperty().bind(
                Bindings.when(pasEncoreDeClic)
                        .then("Choisissez une couleur")
                        .otherwise(Bindings.concat("Choix effectué ", nbFois.asString(), " fois"))
        );

        panneau.styleProperty().bind(
                Bindings.createStringBinding(
                        () -> "-fx-background-color: " + couleurPanneau.get() + ";",
                        couleurPanneau
                )
        );

        texteDuBas.textProperty().bind(message);
        texteDuBas.styleProperty().bind(
                Bindings.createStringBinding(
                        () -> "-fx-text-fill: " + couleurPanneau.get() + ";",
                        couleurPanneau
                )
        );
    }

    private static class VBoxLayout extends javafx.scene.layout.VBox {
        VBoxLayout() {
            super(5);
            setAlignment(Pos.CENTER);
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}

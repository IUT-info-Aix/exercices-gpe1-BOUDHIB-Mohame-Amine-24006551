package fr.amu.iut.exercice18;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Slider;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Line;
import javafx.beans.binding.Bindings;
import javafx.util.converter.NumberStringConverter;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Contrôleur pour la fenêtre de calcul et dessin du périmètre.
 */
public class PanneauPrincipal implements Initializable {
    @FXML private Slider sliderXA;
    @FXML private Slider sliderYA;

    @FXML private Button xBMinusButton;
    @FXML private Button xBPlusButton;
    @FXML private TextField xBField;

    @FXML private Button yBMinusButton;
    @FXML private Button yBPlusButton;
    @FXML private TextField yBField;

    @FXML private TextField perimetreField;
    @FXML private Pane dessinPane;

    private final Rectangle rectangle = new Rectangle();
    private static final int valeurMaxCoordonnees = 20;
    // facteur d'échelle pour l'affichage sur le Pane
    private static final double ratioDessin = 10.0;

    private Line horizontalSegment1;
    private Line verticalSegment1;
    private Line horizontalSegment2;
    private Line verticalSegment2;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        addLines();
        bindSommetsRectangle();
        bindPerimeterTextField();
        bindHorizontal1();
        bindVertical1();
        bindHorizontal2();   // <-- ajouté
        bindVertical2();     // <-- ajouté
        // Les autres segments pourront être liés de la même manière
    }

    /**
     * Ajoute les 4 côtés du rectangle au Pane de dessin.
     */
    private void addLines() {
        horizontalSegment1 = new Line();
        verticalSegment1   = new Line();
        horizontalSegment2 = new Line();
        verticalSegment2   = new Line();
        dessinPane.getChildren().addAll(
                horizontalSegment1,
                verticalSegment1,
                horizontalSegment2,
                verticalSegment2
        );
    }

    /**
     * Décrémente xB si possible (>=0).
     */
    @FXML
    private void decrementerBx() {
        int v = rectangle.xBProperty().get();
        if (v > 0) {
            rectangle.xBProperty().set(v - 1);
        }
    }

    /**
     * Incrémente xB si possible (<=valeurMaxCoordonnees).
     */
    @FXML
    private void incrementerBx() {
        int v = rectangle.xBProperty().get();
        if (v < valeurMaxCoordonnees) {
            rectangle.xBProperty().set(v + 1);
        }
    }

    /**
     * Décrémente yB si possible (>=0).
     */
    @FXML
    private void setByMinusAction() {
        int v = rectangle.yBProperty().get();
        if (v > 0) {
            rectangle.yBProperty().set(v - 1);
        }
    }

    /**
     * Incrémente yB si possible (<=valeurMaxCoordonnees).
     */
    @FXML
    private void setByPlusAction() {
        int v = rectangle.yBProperty().get();
        if (v < valeurMaxCoordonnees) {
            rectangle.yBProperty().set(v + 1);
        }
    }

    /**
     * Lie les sommets A (sliders) et B (text-fields) à la classe Rectangle.
     */
    private void bindSommetsRectangle() {
        // A via sliders (unidirectionnel)
        rectangle.xAProperty().bind(
                Bindings.createIntegerBinding(
                        () -> (int) sliderXA.getValue(),
                        sliderXA.valueProperty()
                )
        );
        rectangle.yAProperty().bind(
                Bindings.createIntegerBinding(
                        () -> (int) sliderYA.getValue(),
                        sliderYA.valueProperty()
                )
        );
        // B via bidirectional text fields
        Bindings.bindBidirectional(
                xBField.textProperty(),
                rectangle.xBProperty(),
                new NumberStringConverter()
        );
        Bindings.bindBidirectional(
                yBField.textProperty(),
                rectangle.yBProperty(),
                new NumberStringConverter()
        );
    }

    /**
     * Lie l'affichage du périmètre au TextField dédié.
     */
    private void bindPerimeterTextField() {
        perimetreField.textProperty().bind(
                rectangle.perimetreProperty().asString()
        );
    }

    /**
     * Lie le premier segment horizontal (A->B.x, A.y).
     */
    private void bindHorizontal1() {
        horizontalSegment1.startXProperty().bind(
                rectangle.xAProperty().multiply(ratioDessin)
        );
        horizontalSegment1.startYProperty().bind(
                rectangle.yAProperty().multiply(ratioDessin)
        );
        horizontalSegment1.endXProperty().bind(
                rectangle.xBProperty().multiply(ratioDessin)
        );
        horizontalSegment1.endYProperty().bind(
                rectangle.yAProperty().multiply(ratioDessin)
        );
    }

    /**
     * Lie le premier segment vertical (A.x, A->B.y).
     */
    private void bindVertical1() {
        verticalSegment1.startXProperty().bind(
                rectangle.xAProperty().multiply(ratioDessin)
        );
        verticalSegment1.startYProperty().bind(
                rectangle.yAProperty().multiply(ratioDessin)
        );
        verticalSegment1.endXProperty().bind(
                rectangle.xAProperty().multiply(ratioDessin)
        );
        verticalSegment1.endYProperty().bind(
                rectangle.yBProperty().multiply(ratioDessin)
        );
    }

    private void bindHorizontal2() {
        horizontalSegment2.startXProperty().bind(
                rectangle.xAProperty().multiply(ratioDessin)
        );
        horizontalSegment2.startYProperty().bind(
                rectangle.yBProperty().multiply(ratioDessin)
        );
        horizontalSegment2.endXProperty().bind(
                rectangle.xBProperty().multiply(ratioDessin)
        );
        horizontalSegment2.endYProperty().bind(
                rectangle.yBProperty().multiply(ratioDessin)
        );
    }

    /** 2ᵉ segment vertical (droite du rectangle) :
     *  de (xB, yA) à (xB, yB)
     */
    private void bindVertical2() {
        verticalSegment2.startXProperty().bind(
                rectangle.xBProperty().multiply(ratioDessin)
        );
        verticalSegment2.startYProperty().bind(
                rectangle.yAProperty().multiply(ratioDessin)
        );
        verticalSegment2.endXProperty().bind(
                rectangle.xBProperty().multiply(ratioDessin)
        );
        verticalSegment2.endYProperty().bind(
                rectangle.yBProperty().multiply(ratioDessin)
        );
    }


}

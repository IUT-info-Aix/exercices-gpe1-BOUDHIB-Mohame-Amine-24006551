// Fichier : Obstacle.java
package fr.amu.iut.exercice5;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Obstacle extends Rectangle {
    public Obstacle(double x, double y, double width, double height) {
        super(x, y, width, height);
        setFill(Color.GRAY);
    }
}
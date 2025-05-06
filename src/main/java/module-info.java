module tp.intro.javafx {
    requires javafx.base;
    requires javafx.controls;
    requires javafx.graphics;
    requires javafx.fxml;
    exports com.example.partie1;
    exports com.example.partie2;
    opens com.example.partie3 to javafx.fxml;
    exports com.example.partie3;
    opens fr.amu.iut.exercice7 to javafx.fxml;
    exports fr.amu.iut.exercice7;
    opens fr.amu.iut.exercice8 to javafx.fxml;
    exports fr.amu.iut.exercice8;
    opens fr.amu.iut.exercice9 to javafx.fxml;
    exports fr.amu.iut.exercice9;
}



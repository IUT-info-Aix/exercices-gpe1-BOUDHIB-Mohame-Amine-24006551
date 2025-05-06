module tp.intro.javafx {
    requires javafx.base;
    requires javafx.controls;
    requires javafx.graphics;
    requires javafx.fxml;
    exports com.example.partie1;
    exports com.example.partie2;
    opens com.example.partie3 to javafx.fxml;
    exports com.example.partie3;
    opens fr.amu.iut.exercice1 to javafx.fxml;
    exports fr.amu.iut.exercice1;
    opens fr.amu.iut.exercice2 to javafx.fxml;
    exports fr.amu.iut.exercice2;
    opens fr.amu.iut.exercice3 to javafx.fxml;
    exports fr.amu.iut.exercice3;
    opens fr.amu.iut.exercice4 to javafx.fxml;
    exports fr.amu.iut.exercice4;
    opens fr.amu.iut.exercice5 to javafx.fxml;
    exports fr.amu.iut.exercice5;
    opens fr.amu.iut.exercice6 to javafx.fxml;
    exports fr.amu.iut.exercice6;
    opens fr.amu.iut.exercice7 to javafx.fxml;
    exports fr.amu.iut.exercice7;
    opens fr.amu.iut.exercice8 to javafx.fxml;
    exports fr.amu.iut.exercice8;
    opens fr.amu.iut.exercice9 to javafx.fxml;
    exports fr.amu.iut.exercice9;
    opens fr.amu.iut.exercice10 to javafx.fxml;
    exports fr.amu.iut.exercice10;
}



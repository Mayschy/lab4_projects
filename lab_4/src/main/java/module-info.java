module com.mixfa.lab_ {
    requires javafx.controls;
    requires javafx.fxml;

    requires com.dlsc.formsfx;
    requires static lombok;
    requires xstream;
    requires java.base;


    opens com.mixfa.lab_4 to javafx.fxml;
    exports com.mixfa.lab_4;
    exports com.mixfa.lab_4.model;
    exports com.mixfa.lab_4.controller;
    opens com.mixfa.lab_4.controller to javafx.fxml;
    opens com.mixfa.lab_4.model to xstream;
}
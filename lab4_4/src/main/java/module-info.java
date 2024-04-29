module com.mixfa.lab4_4 {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.fasterxml.jackson.databind;
    requires static lombok;

    opens com.mixfa.lab4_4 to javafx.fxml;
    exports com.mixfa.lab4_4;
}
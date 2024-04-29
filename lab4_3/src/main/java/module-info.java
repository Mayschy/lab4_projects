module com.mixfa.lab4_3 {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires java.datatransfer;
    requires java.desktop;

    opens com.mixfa.lab4_3 to javafx.fxml;
    exports com.mixfa.lab4_3;
}
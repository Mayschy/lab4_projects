package com.mixfa.lab4_3;

import javafx.beans.value.ObservableValue;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class Utils {
    private Utils() {
    }

    public static void setTextFieldNumberOnly(TextField textField) {
        textField.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue,
                                              String newValue) -> {
            if (!newValue.matches("\\d*")) {
                textField.setText(newValue.replaceAll("\\D", ""));
            }
        });
    }

    public static void closeCurrentWindow(Node node) {
        if (node != null)
            ((Stage) node.getScene().getWindow()).close();
    }
}
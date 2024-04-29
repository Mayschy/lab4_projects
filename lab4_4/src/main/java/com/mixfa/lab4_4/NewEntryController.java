package com.mixfa.lab4_4;

import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class NewEntryController {
    public TextField urkField;
    public TextField engField;

    public void addClicked() {
        DataLayer.newEntryEng = engField.getText();
        DataLayer.newEntryUrk = urkField.getText();

        ((Stage) urkField.getScene().getWindow()).close();
    }
}

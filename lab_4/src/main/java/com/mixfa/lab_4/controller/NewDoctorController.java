package com.mixfa.lab_4.controller;

import com.mixfa.lab_4.DataLayer;
import com.mixfa.lab_4.Utils;
import com.mixfa.lab_4.model.DoctorWithArrayList;
import com.mixfa.lab_4.model.ObservableDoctor;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class NewDoctorController {
    public TextField doctorNameField;
    public TextField doctorProfessionField;
    public Button closeButton;

    public void createNewDoctor(ActionEvent actionEvent) {
        DataLayer.currentDoctor = new ObservableDoctor(
                doctorNameField.getText(),
                doctorProfessionField.getText());

        Utils.closeCurrentWindow(closeButton);
    }
}

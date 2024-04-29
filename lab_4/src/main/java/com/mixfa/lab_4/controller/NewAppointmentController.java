package com.mixfa.lab_4.controller;

import com.mixfa.lab_4.DataLayer;
import com.mixfa.lab_4.Utils;
import com.mixfa.lab_4.model.Appointment;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.ResourceBundle;

public class NewAppointmentController implements Initializable {
    public Button createButton;
    public ChoiceBox<String> shiftChoiceBox;
    public DatePicker datePicker;
    public TextField visitorsField;

    public void createNewAppointment(ActionEvent actionEvent) {

        LocalDate localDate = datePicker.getValue();
        Instant instant = Instant.from(localDate.atStartOfDay(ZoneId.systemDefault()));
        Date date = Date.from(instant);

        var appointment = new Appointment(date, shiftChoiceBox.getValue(), Integer.parseInt(visitorsField.getText()));
        DataLayer.setNewAppointment(appointment);
        Utils.closeCurrentWindow(createButton);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        shiftChoiceBox.getItems().addAll("day", "night");
        shiftChoiceBox.setValue("day");

        Utils.setTextFieldNumberOnly(visitorsField);
    }
}

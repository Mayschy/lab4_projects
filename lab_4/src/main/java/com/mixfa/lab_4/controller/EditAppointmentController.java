package com.mixfa.lab_4.controller;

import com.mixfa.lab_4.DataLayer;
import com.mixfa.lab_4.Utils;
import com.mixfa.lab_4.model.Appointment;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.ResourceBundle;

public class EditAppointmentController implements Initializable {
    public ChoiceBox<String> shiftChoiceBox;
    public DatePicker datePicker;
    public TextField visitorsField;
    public VBox box;

    private Appointment appointment = null;

    public void save(ActionEvent actionEvent) {
        Utils.closeCurrentWindow(box);
    }

    public void deleteAppointment(ActionEvent actionEvent) {
        DataLayer.currentDoctor.getAppointments().remove(appointment);

        Utils.closeCurrentWindow(box);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        appointment = DataLayer.pickEditableAppointment();
        if (appointment == null)
        {
            Utils.closeCurrentWindow(box);
            return;
        }

        shiftChoiceBox.getItems().addAll("day", "night");
        shiftChoiceBox.setValue(appointment.getShift());

        Utils.setTextFieldNumberOnly(visitorsField);

        visitorsField.setText(String.valueOf(appointment.getVisitorsCount()));

        LocalDate localDate = LocalDate.ofInstant(appointment.getDate().toInstant(), ZoneId.systemDefault());
        datePicker.setValue(localDate);
    }

    public void onSelectShift(ActionEvent actionEvent) {
        appointment.setShift(shiftChoiceBox.getValue());
    }

    public void onSelectDate() {
        LocalDate localDate = datePicker.getValue();
        Instant instant = Instant.from(localDate.atStartOfDay(ZoneId.systemDefault()));
        appointment.setDate(Date.from(instant));
    }

    public void setVisitorsCount() {
        int visitorsCount = Integer.parseInt(visitorsField.getText());
        System.out.println(visitorsCount);
        appointment.setVisitorsCount(visitorsCount);
    }
}

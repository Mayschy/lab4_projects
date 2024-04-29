package com.mixfa.lab_4.controller;

import com.mixfa.lab_4.DataLayer;
import com.mixfa.lab_4.Utils;
import javafx.fxml.Initializable;
import javafx.scene.text.Text;

import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ResourceBundle;

public class AppointmentViewController implements Initializable {
    private static final DateFormat dateFormat = new SimpleDateFormat();
    public Text shiftField;
    public Text visitorsCountField;
    public Text dateField;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        var appointment = DataLayer.pickDisplayableAppointment();
        if (appointment == null) {
            Utils.closeCurrentWindow(shiftField);
            return;
        }

        shiftField.setText(STR."Shift: \{appointment.getShift()}");
        visitorsCountField.setText(STR."Visitors count: \{appointment.getVisitorsCount()}");
        dateField.setText(STR."Date: \{dateFormat.format(appointment.getDate())}");
    }
}

package com.mixfa.lab_4.controller;

import com.mixfa.lab_4.DataLayer;
import com.mixfa.lab_4.ModalOpener;
import com.mixfa.lab_4.Utils;
import com.mixfa.lab_4.model.Appointment;
import com.mixfa.lab_4.model.ObservableDoctor;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ResourceBundle;

public class AnalysController implements Initializable {
    public VBox box;
    public ListView<Appointment> appointmentsList;
    private ObservableDoctor doctor = null;

    public void onFind() {

        var foundAppointment = doctor.appointmentWithFewestNumberOfVisitors();
        if (foundAppointment == null || foundAppointment.length == 0) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Not found", ButtonType.OK);
            alert.showAndWait();
        } else {
            DataLayer.setDisplayableAppointment(foundAppointment[0]);
            ModalOpener.openModal("appointment-view.fxml");
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        doctor = DataLayer.currentDoctor;
        if (doctor == null) {
            Utils.closeCurrentWindow(box);
            return;
        }
        appointmentsList.setItems((ObservableList<Appointment>) doctor.getAppointments());

    }

    public void sortByVisitors() {
        doctor.sortByVisitorsCount();
    }

    public void sortByDateReversed() {
        doctor.sortByDateReversed();
    }

    public void displayAppointment() {
        var selectedAppointment = appointmentsList.getSelectionModel().getSelectedItem();
        if (selectedAppointment == null) return;
        DataLayer.setDisplayableAppointment(selectedAppointment);
        ModalOpener.openModal("appointment-view.fxml");
    }
}

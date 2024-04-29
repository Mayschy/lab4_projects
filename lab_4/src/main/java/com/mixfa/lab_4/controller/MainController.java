package com.mixfa.lab_4.controller;

import com.mixfa.lab_4.DataLayer;
import com.mixfa.lab_4.ModalOpener;
import com.mixfa.lab_4.model.Appointment;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;

public class MainController {
    private static final FileChooser.ExtensionFilter XML_EXTENSION_FILTER = new FileChooser.ExtensionFilter("xml file", "*.xml");

    public TextField doctorNameField;
    public TextField doctorProfessionField;
    public ListView<Appointment> appointmentsList;
    public VBox box;

    public void onNewDoctor() {
        ModalOpener.openModal("new-doctor-stage.fxml");

        doctorNameField.setText(DataLayer.currentDoctor.getName());
        doctorProfessionField.setText(DataLayer.currentDoctor.getProfession());

        appointmentsList.setItems((ObservableList<Appointment>) DataLayer.currentDoctor.getAppointments());
    }

    public void nameChange() {
        var doctor = DataLayer.currentDoctor;
        if (doctor != null)
            doctor.setName(doctorNameField.getText());
    }

    public void professionChanged() {
        var doctor = DataLayer.currentDoctor;
        if (doctor != null)
            doctor.setProfession(doctorNameField.getText());
    }

    public void onNewAppointment() {
        ModalOpener.openModal("new-appointment-stage.fxml");

        var doctor = DataLayer.currentDoctor;
        if (doctor != null) {
            var appointment = DataLayer.pickAppointment();
            if (appointment != null)
                doctor.addAppointment(appointment);
        }
    }

    public void onEditAppointment(MouseEvent mouseEvent) {
        var appointment = appointmentsList.getSelectionModel().getSelectedItem();
        DataLayer.setEditableAppointment(appointment);

        ModalOpener.openModal("edit-appointment.fxml");

        appointmentsList.refresh();
    }



    public void onSave(ActionEvent actionEvent) {
        if (DataLayer.currentDoctor == null) return;

        var window = box.getScene().getWindow();
        var fileChooser = new FileChooser();

        fileChooser.getExtensionFilters().add(XML_EXTENSION_FILTER);
        fileChooser.setInitialFileName(DataLayer.currentDoctor.getName());

        var file = fileChooser.showSaveDialog(window);
        if (file != null)
            DataLayer.saveDoctorXml(file);
    }

    public void onLoad(ActionEvent actionEvent) {
        var window = box.getScene().getWindow();
        var fileChooser = new FileChooser();

        fileChooser.getExtensionFilters().add(XML_EXTENSION_FILTER);

        var file = fileChooser.showOpenDialog(window);
        if (file != null) {
            DataLayer.loadDoctorXml(file);

            doctorNameField.setText(DataLayer.currentDoctor.getName());
            doctorProfessionField.setText(DataLayer.currentDoctor.getProfession());

            appointmentsList.setItems((ObservableList<Appointment>) DataLayer.currentDoctor.getAppointments());
            appointmentsList.refresh();
        }
    }

    public void showAbout(ActionEvent actionEvent) {
        ModalOpener.openModal("about.fxml");
    }

    public void showAnalysView(ActionEvent actionEvent) {
        ModalOpener.openModal("analys-stage.fxml");
    }
}
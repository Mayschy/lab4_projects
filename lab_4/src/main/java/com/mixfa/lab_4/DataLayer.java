package com.mixfa.lab_4;

import com.mixfa.lab_4.crutches.ObservableListConverter;
import com.mixfa.lab_4.model.Appointment;
import com.mixfa.lab_4.model.DoctorWithArrayList;
import com.mixfa.lab_4.model.ObservableDoctor;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.StaxDriver;
import com.thoughtworks.xstream.security.AnyTypePermission;
import lombok.Setter;
import lombok.SneakyThrows;

import java.io.File;
import java.io.FileOutputStream;

public class DataLayer {
    private DataLayer() {
    }

    public static XStream newXMLXStream() {
        XStream xmlXStream = new XStream(new StaxDriver());
        xmlXStream.addPermission(AnyTypePermission.ANY);
        xmlXStream.registerConverter(new ObservableListConverter(xmlXStream.getMapper()));
        return xmlXStream;
    }

    public static ObservableDoctor currentDoctor = null;
    private static final XStream xmlStream = newXMLXStream();
    @Setter
    private static Appointment newAppointment = null;
    @Setter
    private static Appointment editableAppointment = null;
    @Setter
    private static Appointment displayableAppointment = null;

    public static Appointment pickDisplayableAppointment() {
        var appointment = displayableAppointment;
        displayableAppointment = null;
        return appointment;
    }

    public static Appointment pickEditableAppointment() {
        var appointment = editableAppointment;
        editableAppointment = null;
        return appointment;
    }


    public static Appointment pickAppointment() {
        var appointment = newAppointment;
        newAppointment = null;
        return appointment;
    }

    @SneakyThrows
    public static void saveDoctorXml(File file) {
        if (currentDoctor == null) return;
        try (var fileOutputStream = new FileOutputStream(file)) {
            xmlStream.toXML(currentDoctor, fileOutputStream);
        }
    }

    public static void loadDoctorXml(File file) {
        currentDoctor = (ObservableDoctor) xmlStream.fromXML(file);
    }
}

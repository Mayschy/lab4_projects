package com.mixfa.lab_4.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class ObservableDoctor  extends AbstractDoctor {
    @Setter
    private String name;
    @Setter
    private String profession;
    private final ObservableList<Appointment> appointments;

    public ObservableDoctor(String name, String profession) {
        this.name = name;
        this.profession = profession;
        this.appointments = FXCollections.observableArrayList();
    }

    public List<Appointment> sortedByDateReversed() {
        return appointments.stream().sorted((a1, a2) -> a2.getDate().compareTo(a1.getDate())).toList();
    }

    public List<Appointment> sortedByVisitorsCount() {
        return appointments.stream().sorted().toList();
    }

    @Override
    public Appointment[] sortByDateReversed() {
        appointments.sort((a1, a2) -> a2.getDate().compareTo(a1.getDate())); // using list method sort
        return appointments.toArray(Appointment[]::new);
    }

    @Override
    public Appointment[] sortByVisitorsCount() {
        Collections.sort(appointments); // using same method, but via Collections class
        return appointments.toArray(Appointment[]::new);
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getProfession() {
        return profession;
    }

    @Override
    public Collection<Appointment> getAppointments() {
        return appointments;
    }

    @Override
    public void addAppointment(Appointment appointment) {
        appointments.add(appointment);
    }

}
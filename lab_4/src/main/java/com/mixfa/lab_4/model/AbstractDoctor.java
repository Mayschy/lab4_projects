package com.mixfa.lab_4.model;

import java.util.Collection;
import java.util.Comparator;
import java.util.Objects;

public abstract class AbstractDoctor {

    public abstract String getName();

    public abstract String getProfession();

    public abstract Collection<Appointment> getAppointments();

    public abstract void addAppointment(Appointment appointment);

    public int getNameLength() {
        return getName().length();
    }

    /**
     * @return total number of visitors
     */
    public int getVisitorsCount() {
        return getAppointments().stream().mapToInt(Appointment::getVisitorsCount).sum();
    }

    /**
     * @return array with appointment with fewest number of visitors or null (if appointments is empty)
     */
    public Appointment[] appointmentWithFewestNumberOfVisitors() {
        var appointmentWithFewestNumberOfVisitors = getAppointments()
                .stream()
                .min(Comparator.comparingInt(Appointment::getVisitorsCount)); // required jdk 21 ;)

        return appointmentWithFewestNumberOfVisitors.map(appointment -> new Appointment[]{appointment}).orElse(null);
    }

    /**
     * Sorts appointments by date in reverse order
     *
     * @return sorted array of appointments
     * @see AbstractDoctor#sortByVisitorsCount()
     */
    public Appointment[] sortByDateReversed() {
        Appointment[] appointments = getAppointments().toArray(new Appointment[0]);
        boolean swapped;

        for (int i = 0; i < appointments.length - 1; i++) {
            swapped = false;

            for (int j = 0; j < appointments.length - i - 1; j++) {
                if (appointments[j].getDate().compareTo(appointments[j + 1].getDate()) < 0) {

                    Appointment temp = appointments[j];
                    appointments[j] = appointments[j + 1];
                    appointments[j + 1] = temp;

                    swapped = true;
                }
            }

            if (!swapped)
                break;

        }
        return appointments;
    }

    /**
     * Sorts appointments by visitors count
     *
     * @return sorted array of appointments
     * @see AbstractDoctor#sortByDateReversed()
     */
    public Appointment[] sortByVisitorsCount() {
        Appointment[] appointments = getAppointments().toArray(new Appointment[0]);

        for (int i = 1; i < appointments.length; i++) {
            Appointment key = appointments[i];
            int j = i - 1;

            while (j >= 0 && appointments[j].getVisitorsCount() < key.getVisitorsCount()) {
                appointments[j + 1] = appointments[j];
                j--;
            }

            appointments[j + 1] = key;
        }

        return appointments;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AbstractDoctor that = (AbstractDoctor) o;
        return Objects.equals(getName(), that.getName()) && Objects.equals(getProfession(), that.getProfession());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getProfession());
    }

    @Override
    public String toString() {
        return STR."AbstractDoctor{name='\{getName()}\{'\''}, profession='\{getProfession()}\{'\''}\{'}'}";
    }


}

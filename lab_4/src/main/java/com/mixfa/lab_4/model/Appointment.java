package com.mixfa.lab_4.model;

import lombok.Getter;
import lombok.Setter;

import java.util.Comparator;
import java.util.Date;
import java.util.Objects;


@Getter
@Setter
public class Appointment implements Comparable<Appointment> {
    private Date date;
    private String shift;
    private int visitorsCount;

    public static final Comparator<Appointment> revercedDateComparator = Comparator.comparing(Appointment::getDate).reversed();

    public Appointment(Date date, String shift, int visitorsCount) {
        this.date = date;
        this.shift = shift;
        this.visitorsCount = visitorsCount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Appointment that = (Appointment) o;
        return visitorsCount == that.visitorsCount && Objects.equals(date, that.date) && Objects.equals(shift, that.shift);
    }

    @Override
    public int hashCode() {
        return Objects.hash(date, shift, visitorsCount);
    }

    @Override
    public String toString() {
        return STR."date=\{date}, shift=\{shift} Visitors Count=\{visitorsCount}";
    }

    /**
     * compares appointments by visitors count
     *
     * @param o the object to be compared.
     * @return comparation result
     */
    @Override
    public int compareTo(Appointment o) {
        return Integer.compare(this.visitorsCount, o.visitorsCount);
    }
}
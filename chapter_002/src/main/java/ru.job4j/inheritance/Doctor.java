package ru.job4j.inheritance;

public class Doctor extends Profession {
    public void cure(Pacient patient) {
    }
    public Diagnose heal(Pacient pacient) {
        return new Diagnose();
    }
}

package roles;

import uni.Kurs;
import uni.Veranstaltung;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.Objects;

public class Student {
    private final String vorname;
    private final String nachname;
    private final int matrikelnummer;
    private final ArrayList<Kurs> abgeschlosseneKurse;
    private final ArrayList<Veranstaltung> veranstaltungen;
    private final LocalDate einstiegsdatum;
    private final ArrayList<Kurs> eingetrageneKurse;

    public Student(String vorname, String nachname, int matrikelnummer, LocalDate einstiegsdatum) {
        this.vorname = vorname;
        this.nachname = nachname;
        this.matrikelnummer = matrikelnummer;
        this.abgeschlosseneKurse = new ArrayList<>();
        this.einstiegsdatum = einstiegsdatum;
        this.veranstaltungen = new ArrayList<>();
        this.eingetrageneKurse = new ArrayList<>();

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return matrikelnummer == student.matrikelnummer &&
                vorname.equals(student.vorname) &&
                nachname.equals(student.nachname);
    }

    public void addAbgeschlosseneKurse(Kurs kurs){
        this.abgeschlosseneKurse.add(kurs);
    }
    public void addVeranstaltungen(Veranstaltung veranstaltung){
        this.veranstaltungen.add(veranstaltung);
    }
    public void addEingetragenKurse(Kurs kurs){
        this.eingetrageneKurse.add(kurs);
    }
    public void removeEingetrageneKurse(Kurs kurs){
        this.eingetrageneKurse.remove(kurs);
    }

    public ArrayList<Veranstaltung> getVeranstaltungen() {
        return veranstaltungen;
    }

    public ArrayList<Kurs> getAbgeschlosseneKurse() {
        return abgeschlosseneKurse;
    }

    public ArrayList<Kurs> getEingetrageneKurse() {
        return eingetrageneKurse;
    }

    public int getMatrikelnummer() {
        return matrikelnummer;
    }

    @Override
    public String toString() {
        return "Student{" +
                "vorname='" + vorname + '\'' +
                ", nachname='" + nachname + '\'' +
                ", matrikelnummer=" + matrikelnummer +
                ", abgeschlosseneKurse=" + abgeschlosseneKurse +
                ", veranstaltungen=" + veranstaltungen +
                ", einstiegsdatum=" + einstiegsdatum +
                '}';
    }
}

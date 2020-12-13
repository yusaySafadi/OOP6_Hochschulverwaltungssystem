package roles;

import uni.Kurs;
import uni.Veranstaltung;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Objects;

public final class Dozent extends Angestellter {
    private Mitarbeiter mitarbeiterVerweis;

    private ArrayList<Kurs> kurse;
    private ArrayList<Veranstaltung> veranstaltungen;

    public Dozent(String vorname, String nachname, String personalNummer, LocalDate einstiegsDatum) {
        super(vorname, nachname, personalNummer, einstiegsDatum);
        this.kurse = new ArrayList<>();
        this.veranstaltungen = new ArrayList<>();
    }
    public Dozent(String vorname, String nachname, String personalNummer, LocalDate einstiegsDatum, Mitarbeiter mitarbeiter) {
        super(vorname, nachname, personalNummer, einstiegsDatum);
        this.mitarbeiterVerweis = mitarbeiter;
        this.kurse = new ArrayList<>();
        this.veranstaltungen = new ArrayList<>();
    }

    public void addKurs(Kurs kurs){
        if(this.kurse.contains(kurs)){
            System.out.println("Dieser Dozent ist bereits in diesem Kurs eingetragen!");
        }
        this.kurse.add(kurs);
    }
    public void addVeranstaltung(Veranstaltung veranstaltung){
        Kurs veranstaltungsKurs = veranstaltung.getKurs();
        for(Kurs kurs: this.kurse){
            if(kurs == veranstaltungsKurs){
                this.veranstaltungen.add(veranstaltung);
                return;
            }
        }

        System.out.println("Diese Veranstaltung befindet sich in keinem der Kurse dieses Dozentes");

    }

    public Mitarbeiter getMitarbeiterVerweis() {
        return mitarbeiterVerweis;
    }

    public ArrayList<Kurs> getKurse() {
        return kurse;
    }

    public ArrayList<Veranstaltung> getVeranstaltungen() {
        return veranstaltungen;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Dozent dozent = (Dozent) o;
        return Objects.equals(mitarbeiterVerweis, dozent.mitarbeiterVerweis) &&
                Objects.equals(kurse, dozent.kurse) &&
                Objects.equals(veranstaltungen, dozent.veranstaltungen);
    }


    @Override
    public String toString() {
        return "Dozent{" +
                "Vorname="+ this.getVorname() +
                ", Nachname=" + this.getNachname() +
                "Personalnummer=" +this.getPersonalNummer()+
                ", mitarbeiterVerweis=" + mitarbeiterVerweis +
                ", kurse=" + kurse +
                ", veranstaltungen=" + veranstaltungen +
                ", einstiegsdatum= " + this.getEinstiegsDatum()+
                '}';
    }
}

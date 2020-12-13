package roles;

import uni.Kurs;
import uni.Veranstaltung;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Objects;

public class Tutor extends Angestellter{
    final Student studentVerweis;
    private int SWS;
    private ArrayList<Veranstaltung> veranstaltungen;

    public Tutor(String vorname, String nachname, String personalNummer, LocalDate einstiegsDatum, Student studentVerweis) {
        super(vorname, nachname, personalNummer, einstiegsDatum);
        this.veranstaltungen = new ArrayList<>();
        this.studentVerweis = studentVerweis;
        this.SWS = 0;
    }

    public Student getStudentVerweis() {
        return studentVerweis;
    }

    public int getSWS() {
        return SWS;
    }

    public ArrayList<Veranstaltung> getVeranstaltungen() {
        return veranstaltungen;
    }

    public final void  addVeranstaltung(Veranstaltung veranstaltung){
        if(this.studentVerweis.getAbgeschlosseneKurse().size() == 0){
            System.out.println("Studentverweis hat noch keine Kurse abgeschlossen!");

        } else if(this.studentVerweis.getAbgeschlosseneKurse().size()>0){
            boolean done = false;
            for(Kurs kurs : this.studentVerweis.getAbgeschlosseneKurse()){
                if (kurs.equals(veranstaltung.getKurs())) {
                    done = true;
                    break;
                }
            }
            if(done){
                if(veranstaltung.getSWS() + this.SWS >10){
                    System.out.println("Tutor hätte mehr als die maximale Anzahl von SWS (MAX = 10). Veranstaltung wird nicht eingetragen");
                    return;
                }
                this.SWS+=veranstaltung.getSWS();
                this.veranstaltungen.add(veranstaltung);
            } else{
                System.out.println("Studentverweis hat den dazugehören Kurs nicht abgeschlossen!" );
            }


        }

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tutor tutor = (Tutor) o;
        return SWS == tutor.SWS &&
                Objects.equals(studentVerweis, tutor.studentVerweis) &&
                Objects.equals(veranstaltungen, tutor.veranstaltungen);
    }

    @Override
    public String toString() {
        return "Tutor{" +
                "studentVerweis=" + studentVerweis +
                ", SWS=" + SWS +
                ", veranstaltungen=" + veranstaltungen +
                ", einstiegsdatum= " + this.getEinstiegsDatum() +
                '}';
    }
}

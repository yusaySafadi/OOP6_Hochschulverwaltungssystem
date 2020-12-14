package uni;

import java.time.LocalDate;
import java.util.ArrayList;

import Exceptions.AlreadyExistsException;
import Exceptions.MatrikelException;
import Exceptions.PersonalNummerException;
import Exceptions.VerweisException;
import roles.*;

import javax.print.attribute.standard.JobKOctets;

public class Hochschule {
    private ArrayList<Dozent> dozenten;
    private ArrayList<Tutor> tutoren;
    private ArrayList<Student> studenten;
    private ArrayList<Mitarbeiter> mitarbeiter;

    private ArrayList<Kurs> kurse;
    private ArrayList<Veranstaltung> veranstaltungen;

    ArrayList<String> personalnummern;
    final ArrayList<Integer> matrikelnummern;

    public Hochschule(ArrayList<Dozent> dozenten, ArrayList<Tutor> tutoren, ArrayList<Student> studenten,
                      ArrayList<Kurs> kurse, ArrayList<Veranstaltung> veranstaltungen) {
        this.dozenten = dozenten;
        this.tutoren = tutoren;
        this.studenten = studenten;
        this.kurse = kurse;
        this.veranstaltungen = veranstaltungen;
        this.matrikelnummern = new ArrayList<>();

        for(Student student : this.studenten){
            this.matrikelnummern.add(student.getMatrikelnummer());
        }
    }

    public Hochschule() {
        this.dozenten = new ArrayList<>();
        this.tutoren = new ArrayList<>();
        this.studenten = new ArrayList<>();
        this.kurse = new ArrayList<>();
        this.veranstaltungen = new ArrayList<>();
        this.matrikelnummern = new ArrayList<>();
        this.mitarbeiter = new ArrayList<>();
        this.personalnummern = new ArrayList<>();
    }
    //Studenten Funktionen
    public void printAllStudents(){
        for(Student student: this.studenten){
            System.out.println(student);
        }
    }
    public void addStudent(String vorname, String nachname, int matrikelnummer, LocalDate einstiegsdatum) throws MatrikelException {

        if(this.matrikelnummern.contains(matrikelnummer)){
                throw new MatrikelException("Matrikelnummer schon im System vorhanden!");
        }
        else if(String.valueOf(matrikelnummer).length() != 8){
                throw new MatrikelException("Matrikelnummer muss eine 8 stellige Nummer sein! ");

        }
        else{
            Student student = new Student(vorname, nachname, matrikelnummer, einstiegsdatum);
            this.studenten.add(student);
            this.matrikelnummern.add(student.getMatrikelnummer());

        }


    }
    public Student getStudent(int i){
        return this.studenten.get(i);
    }
    public ArrayList<Student> getStudenten() {
        return studenten;
    }
    //Tutoren Funktionen
    public void printAllTutoren(){
        for(Tutor tutor: this.tutoren){
            System.out.println(tutor);
        }
    }
    public void addTutor(String vorname, String nachname, int personalNummer, LocalDate einstiegsdatum, Student student) throws VerweisException, PersonalNummerException {
        if(String.valueOf(personalNummer).length() != 6){

            throw new PersonalNummerException("Die Zahl muss 6 Ziffern lang sein!");

        }
        String newPersonalNummer = nachname.substring(0,1) + personalNummer + "T" ;
        if(this.personalnummern.contains(newPersonalNummer)){

            throw new PersonalNummerException("Diese Personalnummer existiert bereits im System!");
        }
        if(student == null){
                throw new VerweisException("Tutor muss Studentverweis enthalten!");
        } else{
            Tutor tutor = new Tutor(vorname,nachname,newPersonalNummer,einstiegsdatum,student);
            this.personalnummern.add(newPersonalNummer);
            this.tutoren.add(tutor);
        }


    }
    //Dozent Funktionen
    public void printAllDozenten(){
        for(Dozent dozent : this.dozenten){
            System.out.println(dozent);
        }
    }
    public void addDozent(String vorname, String nachname, int personalNummer, LocalDate einstiegsdatum, Mitarbeiter mitarbeiter){
        if(String.valueOf(personalNummer).length() != 6){
            try {
                throw new PersonalNummerException("Die Zahl muss 6 Ziffern lang sein!");
            }catch (PersonalNummerException e){
                e.printStackTrace();
            }
        }
        String newPersonalNummer = nachname.substring(0,1) + personalNummer + "D" ;
        if(this.personalnummern.contains(newPersonalNummer)){
            try {
                throw new PersonalNummerException("Diese Personalnummer existiert bereits im System!");
            }catch (PersonalNummerException e){
                e.printStackTrace();
            }
        }
        Dozent dozent;
        if(mitarbeiter == null){
            dozent = new Dozent(vorname, nachname, newPersonalNummer, einstiegsdatum);
        } else {
            dozent = new Dozent(vorname, nachname, newPersonalNummer, einstiegsdatum, mitarbeiter);
        }
        this.dozenten.add(dozent);

        this.personalnummern.add(newPersonalNummer);

    }
    //Mitarbeiter Funktionen
    public void addMitarbeiter(String vorname, String nachname, int personalNummer,LocalDate einstiegDatum) throws PersonalNummerException {
        if(String.valueOf(personalNummer).length() != 6){
                throw new PersonalNummerException("Die Zahl muss 6 Ziffern lang sein!");

        }

        String newPersonalNummer = nachname.substring(0,1) + personalNummer + "M" ;

        if(this.personalnummern.contains(newPersonalNummer)){

                throw new PersonalNummerException("Diese Personalnummer existiert bereits im System!");

        }
        this.personalnummern.add(newPersonalNummer);
        Mitarbeiter mitarbeiter = new Mitarbeiter(vorname,nachname,newPersonalNummer,einstiegDatum);
        this.mitarbeiter.add(mitarbeiter);
    }

    public void printAllMitartbeiter(){

        for(Mitarbeiter m : this.mitarbeiter){
            System.out.println(m);
        }
    }
    //Kurse Funktionen
    public void printAllKurse(){
        for(Kurs kurs : this.kurse){
            System.out.println(kurs);
        }
    }
    public void addKurs(String bezeichnung, int semester, ArrayList<Kurs> vorleistungen) throws AlreadyExistsException {

        Kurs kurs = new Kurs(bezeichnung,semester,vorleistungen);
        for(Kurs k: this.kurse){
            if(kurs.equals(k)){
                throw new AlreadyExistsException("Kurs ist bereits im System");

            }
        }
        this.kurse.add(kurs);
    }

    //Veranstaltungen Funktionen
    public void printAllVeranstaltungen(){
        for(Veranstaltung veranstaltung: this.veranstaltungen){
            System.out.println(veranstaltung);
        }
    }
    public void addVeranstaltung(Kurs kurs, LocalDate beginndaten, LocalDate enddaten, int sws) throws AlreadyExistsException {
        Veranstaltung veranstaltung = new Veranstaltung(kurs,beginndaten,enddaten, sws);
        if(this.veranstaltungen.contains(veranstaltung)){
            throw new AlreadyExistsException("Veranstaltung ist bereits im System");
        } else{
            this.veranstaltungen.add(veranstaltung);
        }

    }

    public ArrayList<Dozent> getDozenten() {
        return dozenten;
    }


    public ArrayList<Tutor> getTutoren() {
        return tutoren;
    }

    public ArrayList<Kurs> getKurse() {
        return kurse;
    }
    public ArrayList<Mitarbeiter> getMitarbeiter(){
        return mitarbeiter;
    }

    public ArrayList<Veranstaltung> getVeranstaltungen() {
        return veranstaltungen;
    }

}

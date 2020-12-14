import Exceptions.*;

import roles.Dozent;
import roles.Mitarbeiter;
import roles.Student;
import roles.Tutor;
import uni.Hochschule;
import uni.Kurs;
import uni.Veranstaltung;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws AlreadyExistsException, PersonalNummerException, MatrikelException, VerweisException, EmptyException {

        Scanner sc = new Scanner(System.in);
        Hochschule bielefeld = new Hochschule();
        bielefeld.addStudent("Yusay", "Sfai", 12345678, LocalDate.of(2020, 4, 11));
        bielefeld.addStudent("Maria", "Sadi", 14672334, LocalDate.of(2020, 2, 23));
        bielefeld.addStudent("Bob", "adi", 43333333, LocalDate.of(2020, 5, 10));
        bielefeld.addStudent("YuS", "Sai", 22222222, LocalDate.of(2020, 8, 8));
        bielefeld.addStudent("Yusaya", "Safdi", 23455647, LocalDate.of(2020, 3, 31));
        bielefeld.addStudent("Yusy", "Saf", 76545432, LocalDate.of(2020, 9, 3));
        bielefeld.addStudent("Yu", "Cin", 55555555, LocalDate.of(2020, 12, 3));
        ArrayList<Kurs> kurse = new ArrayList<>();
        ArrayList<Kurs> empty = new ArrayList<>();
        Kurs oop = new Kurs("OOP", 1,empty);
        Kurs mathe = new Kurs("Mathematik 1", 1, empty);
        kurse.add(mathe);
        Kurs mathe2 = new Kurs("Mathematik 2",2, kurse);


        bielefeld.addKurs("Mathematik 2",2, kurse);
        bielefeld.addKurs("Mathematik 1", 1, empty);
        bielefeld.addKurs("OOP", 1,empty);


        bielefeld.addVeranstaltung(oop, LocalDate.of(2020, 3,12), LocalDate.of(2021, 1,2), 4);
        bielefeld.addVeranstaltung(mathe,LocalDate.of(2020, 3,12), LocalDate.of(2021, 1,2), 3);
        bielefeld.addVeranstaltung(mathe2, LocalDate.of(2021, 3,12), LocalDate.of(2022, 1,2), 4);

        bielefeld.addMitarbeiter("Mario", "Lopets", 123456, LocalDate.of(2022, 1,2));
        bielefeld.addMitarbeiter("Mario", "Lopets", 123454,LocalDate.of(2022, 1,2));
        bielefeld.addMitarbeiter("Mario", "Lopets", 123426,LocalDate.of(2022, 1,2));

        bielefeld.addDozent("Philip", "Kroos", 123456,LocalDate.of(2020,3,1),null);
        bielefeld.addDozent("Philipa", "Krsde", 123226,LocalDate.of(2020,4,1),null);
        bielefeld.addDozent("Philip", "Kroos", 123446,LocalDate.of(2020,1,1),null);
        bielefeld.addDozent("Philip", "Kroos", 123416,LocalDate.of(2020,6,1),null);
        bielefeld.addDozent("Philip", "Kroos", 123436,LocalDate.of(2020,3,1),null);

        bielefeld.addTutor("Robert","Bischhoff",222111,LocalDate.of(2020,3,1),bielefeld.getStudent(0));




        while(true){
            hauptmenue();
            String input = sc.nextLine().trim();
            switch (input){
                case "0":
                    studentenManagement(sc, bielefeld);
                    break;
                case "1":
                    dozentenManagement(sc, bielefeld);
                    break;
                case "2":
                    mitarbeiterManagement(sc,bielefeld);
                    break;
                case "3":
                    tutorenManagement(sc,bielefeld);
                    break;
                case "4":
                    kurseManagement(sc,bielefeld);
                    break;
                case "5":
                    veranstaltungenManagement(sc,bielefeld);
                    break;
                case "6":
                    return;
            }
        }
    }
    public static void hauptmenue(){
        System.out.println("Was möchtest du tun?");

        System.out.println("0 Studenten verwalten");
        System.out.println("1 Dozente verwalten");
        System.out.println("2 Mitarbeiter verwarlten");
        System.out.println("3 Tutoren verwarlten");
        System.out.println("4 Kurse verwalten");
        System.out.println("5 Veranstaltungen verwalten");
        System.out.println("6 Programm beenden");

    }

    public static void studentenManagement(Scanner sc, Hochschule uni) throws MatrikelException, EmptyException {
        System.out.println("0 Alle Studenten anzeigen");
        System.out.println("1 Student hinzufügen");
        System.out.println("2 einzelner Student verwalten");


        String input = sc.nextLine();
        switch (input){
            case "0":
                uni.printAllStudents();
                break;
            case "1":
                System.out.println("Vorname:");
                String vorname = sc.nextLine();

                System.out.println("Nachname:");
                String nachname = sc.nextLine();

                System.out.println("Matrikelnummer(8 Ziffer)");
                int matrikelnummer = Integer.parseInt(sc.nextLine());

                System.out.println("Einstiegsdatum Jahr:");
                int jahr = Integer.parseInt(sc.nextLine());
                System.out.println("Einstiegsdatum Monat(1-12):");
                Month monat = Month.of(Integer.parseInt(sc.nextLine())) ;

                System.out.println("Einstiegsdatum Tag:");
                int tag = Integer.parseInt(sc.nextLine());

                LocalDate eintiegsdatum = LocalDate.of(jahr,monat,tag);
                uni.addStudent(vorname, nachname, matrikelnummer, eintiegsdatum);
                break;
            case "2":
                if(uni.getStudenten().size() == 0){
                    throw new EmptyException("Diese Hochschule hat noch keine Studenten eingetragen");
                }
                Student selectedStudent = selectStudent(sc, uni);

                System.out.println("0 Student eine Veranstaltung Hinzufügen");
                System.out.println("1 Student Kurs hinzufügen");
                System.out.println("2 Kurse und Veranstaltungen anzeigen");
                System.out.println("3 Kurs als abgeschlossen markieren");
                String option = sc.nextLine();
                switch (option) {
                    case "0":

                        Veranstaltung veranstaltung = selectVeranstalung(sc, uni);
                        if (selectedStudent.getVeranstaltungen().contains(veranstaltung)) {
                            System.out.println("Student ist bereits in Veranstaltung eingetragen");
                        } else {

                            selectedStudent.addVeranstaltungen(veranstaltung);
                        }
                        break;
                    case "1":
                        Kurs kurs = selectKurs(sc, uni);

                        if (selectedStudent.getEingetrageneKurse().contains(kurs)) {
                            System.out.println("Student ist bereits im Kurs eingetragen");
                        } else {
                            selectedStudent.addEingetragenKurse(kurs);
                        }
                        break;
                    case "2":
                        System.out.println("ABGESCHLOSSENE KURSE");
                        System.out.println("-------------------------------------------");
                        System.out.println(selectedStudent.getAbgeschlosseneKurse());
                        System.out.println("EINGETRAGENE KURSE");
                        System.out.println("-------------------------------------------");
                        System.out.println(selectedStudent.getEingetrageneKurse());
                        System.out.println("-------------------------------------------");
                        System.out.println("VERANSTALTUNGEN");
                        System.out.println(selectedStudent.getVeranstaltungen());
                        System.out.println("-------------------------------------------");

                        break;
                    case "3":
                        Kurs selectedkurs = selectKurs(sc, uni);
                        if(selectedStudent.getAbgeschlosseneKurse().contains(selectedkurs)){
                            System.out.println("Kurs ist bereits abgeschlossen");
                        } else{

                            selectedStudent.addAbgeschlosseneKurse(selectedkurs);
                            selectedStudent.removeEingetrageneKurse(selectedkurs);
                        }
                        break;

                    default:
                        System.out.println("falsche Eingabe");
                }
        }
    }

    public static Student selectStudent(Scanner sc,Hochschule uni) throws EmptyException {
        System.out.println("Um welchen Student handelt es sich?");
        ArrayList<Student> studenten = uni.getStudenten();
        int count = 0;
        for(Student student: studenten){
            System.out.println(count+ " " + student);
            count++;
        }
        int studentIndex = Integer.parseInt(sc.nextLine());
        if(studentIndex > studenten.size()-1){
            throw new EmptyException("Student existiert nicht");
        }
        return studenten.get(studentIndex);
    }

    public static void dozentenManagement(Scanner sc, Hochschule uni) throws AlreadyExistsException, EmptyException {
        System.out.println("0 Alle Dozente anzeigen");
        System.out.println("1 Dozent hinzufügen");
        System.out.println("2 Dozent Verwalten");



        String input = sc.nextLine();
        switch (input){
            case "0":
                uni.printAllDozenten();
                break;

            case "1":
                System.out.println("Vorname:");
                String vorname = sc.nextLine();

                System.out.println("Nachname:");
                String nachname = sc.nextLine();

                System.out.println("Personalnummer(6 Ziffer)");
                int personalnummer = Integer.parseInt(sc.nextLine());

                System.out.println("Einstiegsdatum Jahr:");
                int jahr = Integer.parseInt(sc.nextLine());
                System.out.println("Einstiegsdatum Monat(1-12):");
                Month monat = Month.of(Integer.parseInt(sc.nextLine())) ;

                System.out.println("Einstiegsdatum Tag:");
                int tag = Integer.parseInt(sc.nextLine());
                LocalDate einstiegsdatum = LocalDate.of(jahr,monat,tag);

                System.out.println("Miarbeiterverweis? (ja/nein");

                String antwort = sc.nextLine().trim().toLowerCase();

                if(antwort.equals("ja")){
                    Mitarbeiter mitarbeiter = selectMitarbeiter(sc,uni);
                    uni.addDozent(vorname,nachname,personalnummer,einstiegsdatum,mitarbeiter);
                } else if(antwort.equals("nein")){
                    uni.addDozent(vorname, nachname, personalnummer, einstiegsdatum, null);
                } else{
                    System.out.println("falsche Eingabe, es wird Dozent ohne Mitarbeiterverweis angenommen ");
                    uni.addDozent(vorname, nachname, personalnummer, einstiegsdatum, null);
                }
                break;
            case "2":
                if(uni.getDozenten().size() == 0){
                    throw new EmptyException("Diese Hochschule hat noch keine Dozenten eingetragen");

                }
                Dozent selectedDozent = selectDozent(sc,uni);


                System.out.println("0 Dozent Veranstaltung zuweisen");
                System.out.println("1 Dozent Veranstaltungen anzeigen");
                System.out.println("2 Dozent Kurs zuweisen");
                System.out.println("3 Dozent Kurse anzeigen");

                String option = sc.nextLine();

                switch (option){

                    case "0":
                        if(uni.getVeranstaltungen().size() == 0){
                            throw new EmptyException("Diese Hochschule hat noch keine Veranstalung eingetragen");

                        }
                        Veranstaltung veranstaltung = selectVeranstalung(sc, uni);
                        selectedDozent.addVeranstaltung(veranstaltung);
                        break;

                    case "1":
                        if(uni.getVeranstaltungen().size() == 0){
                            throw new EmptyException("Diese Hochschule hat noch keine Veranstalung eingetragen");
                        }
                        for(Veranstaltung v: selectedDozent.getVeranstaltungen()){
                            System.out.println(v);
                        }
                        break;

                    case "2":
                        if(uni.getKurse().size() == 0){
                            throw new EmptyException("Diese Hochschule hat noch keine Kurse eingetragen");
                        }
                        Kurs kurs = selectKurs(sc, uni);
                        selectedDozent.addKurs(kurs);
                        break;

                    case "3":
                        if(uni.getKurse().size() == 0){
                            throw new EmptyException("Diese Hochschule hat noch keine Kurse eingetragen");

                        }
                        for(Kurs k : selectedDozent.getKurse()){
                            System.out.println(k);
                        }
                        break;
                }
                break;
            default:
                System.out.println("falsche Eingabe");
                break;

        }
    }

    public static Dozent selectDozent(Scanner sc, Hochschule uni) throws EmptyException {
        System.out.println("Um welchen Dozent handelt es sich?");
        ArrayList<Dozent> dozenten = uni.getDozenten();
        int count = 0;
        for(Dozent dozent: dozenten){
            System.out.println(count +" " + dozent);
            count++;
        }
        int index = Integer.parseInt(sc.nextLine());
        if(index > dozenten.size()-1){
            throw new EmptyException("Dozent existiert nicht");
        }
        return dozenten.get(index);
    }

    public static Tutor selectTutor(Scanner sc, Hochschule uni) throws EmptyException {
        System.out.println("Um welchen Tutor handelt es sich?");
        ArrayList<Tutor> tutoren = uni.getTutoren();
        int count = 0;
        for(Tutor tutor: tutoren){
            System.out.println(count +" " + tutor);
            count++;
        }
        int index = Integer.parseInt(sc.nextLine());
        if(index > tutoren.size()-1){
            throw new EmptyException("Tutor existiert nicht");
        }
        return tutoren.get(index);
    }
    public static Mitarbeiter selectMitarbeiter(Scanner sc, Hochschule uni) throws EmptyException {
        System.out.println("Um welchen Mitarbeiter handelt es sich?");
        ArrayList<Mitarbeiter> mitarbeiters = uni.getMitarbeiter();
        int count = 0;
        for(Mitarbeiter mitarbeiter: mitarbeiters){
            System.out.println(count+ " " + mitarbeiter);
            count++;
        }
        int mitarbeiterIndex = Integer.parseInt(sc.nextLine());
        if(mitarbeiterIndex> mitarbeiters.size()-1){
            throw new EmptyException("Mitarbeiter existiert nicht");
        }
        return mitarbeiters.get(mitarbeiterIndex);
    }

    public static void mitarbeiterManagement(Scanner sc, Hochschule uni) throws PersonalNummerException, EmptyException {
        System.out.println("0 Alle Mitarbeiter anzeigen");
        System.out.println("1 Mitarbeiter hinzufügen");
        System.out.println("2 einzelner Mitarbeiter Verwalten");

        String input = sc.nextLine();
        switch (input){
            case "0":
                System.out.println("sdfasd");
                uni.printAllMitartbeiter();
                break;

            case "1":
                System.out.println("Vorname:");
                String vorname = sc.nextLine();

                System.out.println("Nachname:");
                String nachname = sc.nextLine();

                System.out.println("Personalnummer(6 Ziffer)");
                int personalnummer = Integer.parseInt(sc.nextLine());

                System.out.println("Einstiegsdatum Jahr:");
                int jahr = Integer.parseInt(sc.nextLine());
                System.out.println("Einstiegsdatum Monat(1-12):");
                Month monat = Month.of(Integer.parseInt(sc.nextLine())) ;

                System.out.println("Einstiegsdatum Tag:");
                int tag = Integer.parseInt(sc.nextLine());
                LocalDate einstiegsdatum = LocalDate.of(jahr,monat,tag);
                uni.addMitarbeiter(vorname,nachname,personalnummer,einstiegsdatum);
                break;
            case "2":
                if(uni.getDozenten().size() == 0){
                    System.out.println("Diese Hochschule hat noch keine Dozenten eingetragen");
                    return;
                }
                Mitarbeiter selectedMitarbeiter = selectMitarbeiter(sc,uni);


                System.out.println("0 Information anzeigen");


                String option = sc.nextLine();
                switch (option){
                    case "0":
                        System.out.println(selectedMitarbeiter);
                        break;
                    default:
                        System.out.println("Falsche eingabe");
                        break;
                }
                break;
            default:
                System.out.println("falsche Eingabe");
                break;

        }

    }
    public static void tutorenManagement(Scanner sc, Hochschule uni) throws PersonalNummerException, VerweisException, EmptyException {
        System.out.println("0 Alle Tutoren anzeigen");
        System.out.println("1 Tutor hinzufügen");
        System.out.println("2 Tutor Verwalten");

        String input = sc.nextLine();
        switch (input){
            case "0":
                uni.printAllTutoren();
                break;

            case "1":
                System.out.println("Vorname:");
                String vorname = sc.nextLine();

                System.out.println("Nachname:");
                String nachname = sc.nextLine();

                System.out.println("Personalnummer(6 Ziffer)");
                int personalnummer = Integer.parseInt(sc.nextLine());

                System.out.println("Einstiegsdatum Jahr:");
                int jahr = Integer.parseInt(sc.nextLine());
                System.out.println("Einstiegsdatum Monat(1-12):");
                Month monat = Month.of(Integer.parseInt(sc.nextLine())) ;

                System.out.println("Einstiegsdatum Tag:");
                int tag = Integer.parseInt(sc.nextLine());
                LocalDate einstiegsdatum = LocalDate.of(jahr,monat,tag);

                System.out.println("Studentenverweis:");
                Student studentverweis = selectStudent(sc,uni);

                uni.addTutor(vorname,nachname, personalnummer,einstiegsdatum,studentverweis);
                break;
            case "2":
                if(uni.getTutoren().size() == 0){
                    System.out.println("Diese Hochschule hat noch keine Tutoren eingetragen");
                    return;
                }
                Tutor selectedTutor = selectTutor(sc, uni);

                System.out.println("0 Tutor Veranstaltung zuweisen");
                System.out.println("1 Tutor Veranstaltungen anzeigen");

                String option = sc.nextLine();

                switch (option){

                    case "0":
                        if(uni.getVeranstaltungen().size() == 0){
                            System.out.println("Diese Hochschule hat noch keine Veranstalung eingetragen");
                            return;
                        }
                        Veranstaltung veranstaltung = selectVeranstalung(sc, uni);
                        selectedTutor.addVeranstaltung(veranstaltung);
                        break;

                    case "1":
                        if(uni.getVeranstaltungen().size() == 0){
                            System.out.println("Diese Hochschule hat noch keine Veranstalung eingetragen");
                            return;
                        }
                        for(Veranstaltung v: selectedTutor.getVeranstaltungen()){
                            System.out.println(v);
                        }
                        break;
                }
                break;
            default:
                System.out.println("falsche Eingabe");
                break;

        }

    }


    public static void kurseManagement(Scanner sc, Hochschule uni) throws AlreadyExistsException, EmptyException {

        System.out.println("0 Alle Kurse anzeigen");
        System.out.println("1 Kurs hinzufügen");

        String input = sc.nextLine();
        switch (input){
            case "0":
                if(uni.getKurse().size() == 0){
                    System.out.println("Diese Hochschule hat noch keine Kurse eingetragen");
                    return;
                }
                uni.printAllKurse();
                break;
            case "1":
                System.out.println("Kursbezeichnung:");
                String bezeichung = sc.nextLine();

                System.out.println("Semester:");
                int semester = Integer.parseInt(sc.nextLine());

                ArrayList<Kurs> leistungen = new ArrayList<>();

                System.out.println("Vorleistungen (einzeln und mit 'stop' beenden)");
                boolean done = false;
                do {

                    Kurs kurs = selectKurs(sc, uni) ;
                    if(kurs == null){
                        done = true;
                    } else{
                        for(Kurs k: leistungen){
                            if(k.equals(kurs)){
                                System.out.println("Kurs ist bereits in Vorleistungen");
                                return;
                            }


                        }
                        leistungen.add(kurs);

                    }
                } while(!done);

                uni.addKurs(bezeichung, semester, leistungen);

                break;

        }
    }

    public static void veranstaltungenManagement(Scanner sc, Hochschule uni) throws AlreadyExistsException, EmptyException {
        System.out.println("0 Alle Veranstaltungen anzeigen");
        System.out.println("1 Veranstaltung hinzufügen");

        String input = sc.nextLine();
        switch (input){
            case "0":
                if(uni.getVeranstaltungen().size() == 0){
                    System.out.println("Diese Hochschule hat noch keine Veranstaltung eingetragen");
                    return;
                }
                uni.printAllVeranstaltungen();
                break;
            case "1":
                Kurs kurs = selectKurs(sc, uni);

                System.out.println("Beginndaten:");

                System.out.println("Jahr:");
                int jahr = Integer.parseInt(sc.nextLine());

                System.out.println("Monat(1-12):");
                Month monat = Month.of(Integer.parseInt(sc.nextLine())) ;

                System.out.println("Tag:");
                int tag = Integer.parseInt(sc.nextLine());
                LocalDate beginndaten = LocalDate.of(jahr,monat,tag);

                System.out.println("Enddaten");
                System.out.println("Jahr:");
                jahr = Integer.parseInt(sc.nextLine());

                System.out.println("Monat(1-12):");
                monat = Month.of(Integer.parseInt(sc.nextLine())) ;

                System.out.println("Tag:");
                tag = Integer.parseInt(sc.nextLine());
                LocalDate enddaten= LocalDate.of(jahr,monat,tag);

                System.out.println("SWS:");
                int sws = Integer.parseInt(sc.nextLine().trim());

                uni.addVeranstaltung(kurs, beginndaten, enddaten, sws);

                break;

        }


    }


    public static Kurs selectKurs(Scanner sc,Hochschule uni) throws EmptyException {
        System.out.println("Um welchen Kurs handelt es sich?");
        ArrayList<Kurs> kurse = uni.getKurse();
        int count = 0;
        for(Kurs kurs : kurse){
            System.out.println(count+ " " + kurs);
            count++;
        }
        String input = sc.nextLine().trim();
        if(input.equals("stop")){
            return null;
        } else{
            int kursIndex = Integer.parseInt(input);
            if(kursIndex> kurse.size()-1){
                throw new EmptyException("Kurs existiert nicht");
            }
            return kurse.get(kursIndex);
        }

        }


    public static Veranstaltung selectVeranstalung(Scanner sc, Hochschule uni) throws EmptyException {
        System.out.println("Um welche Veranstaltung handelt es sich?");
        ArrayList<Veranstaltung> veranstaltungen = uni.getVeranstaltungen();

        int count = 0;
        for(Veranstaltung veranstaltung : veranstaltungen){
            System.out.println(count + " " + veranstaltung);
            count++;
        }
        int veranstaltungIndex = Integer.parseInt(sc.nextLine());
        if(veranstaltungIndex> veranstaltungen.size()-1){
            throw new EmptyException("Veranstaltung existiert nicht");
        }
        return veranstaltungen.get(veranstaltungIndex);


    }

}

package roles;

import java.time.LocalDate;

public class Mitarbeiter extends Angestellter {
    public Mitarbeiter(String vorname, String nachname, String personalNummer, LocalDate einstiegsDatum) {
        super(vorname, nachname, personalNummer, einstiegsDatum);
    }


    @Override
    public boolean equals(Object obj) {
        return false;
    }

    @Override
    public String toString() {
        return "Mitarbeiter{" +
                ",vorname=" + this.getVorname() +
                ",nachmame=" +this.getNachname()+
                ",personalnummer" + this.getPersonalNummer()+
                ", einstiegsdatum= " + this.getEinstiegsDatum() +
                '}';
    }
}

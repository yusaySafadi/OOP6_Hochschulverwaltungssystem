package roles;

import java.time.LocalDate;

abstract class Angestellter {
    private  final String vorname;
    private final String nachname;
    private final String personalNummer;
    private final LocalDate einstiegsDatum;

    public Angestellter(String vorname, String nachname, String personalNummer, LocalDate einstiegsDatum) {
        this.vorname = vorname;
        this.nachname = nachname;
        this.personalNummer = personalNummer;
        this.einstiegsDatum = einstiegsDatum;
    }

    public String getVorname() {
        return vorname;
    }

    public String getNachname() {
        return nachname;
    }

    public String getPersonalNummer() {
        return personalNummer;
    }

    public LocalDate getEinstiegsDatum() {
        return einstiegsDatum;
    }

    @Override
    public abstract boolean equals(Object obj);

    @Override
    public abstract String toString();
}

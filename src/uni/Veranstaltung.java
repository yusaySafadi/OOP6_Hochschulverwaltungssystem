package uni;

import java.time.LocalDate;
import java.util.Date;
import java.util.Objects;

public class Veranstaltung {
    private final Kurs kurs;
    private final LocalDate beginndaten;
    private final LocalDate enddaten;
    final int SWS;

    public Veranstaltung(Kurs kurs, LocalDate beginndaten, LocalDate enddaten, int SWS) {
        this.kurs = kurs;
        this.beginndaten = beginndaten;
        this.enddaten = enddaten;
        this.SWS = SWS;
    }

    public Kurs getKurs(){
        return this.kurs;
    }


    public LocalDate getEnddaten() {
        return enddaten;
    }

    public int getSWS() {
        return SWS;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Veranstaltung that = (Veranstaltung) o;
        return SWS == that.SWS &&
                Objects.equals(kurs, that.kurs) &&
                Objects.equals(beginndaten, that.beginndaten) &&
                Objects.equals(enddaten, that.enddaten);
    }

    @Override
    public String toString() {
        return "Veranstaltung{" +
                "kurs=" + kurs.bezeichnung +
                ", beginndaten=" + beginndaten +
                ", enddaten=" + enddaten +
                ", SWS=" + SWS +
                '}';
    }
}

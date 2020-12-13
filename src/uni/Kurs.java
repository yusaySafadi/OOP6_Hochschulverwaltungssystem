package uni;

import java.util.ArrayList;

public class Kurs {
    String bezeichnung;
    int semester;
    ArrayList<Kurs> vorleistungen;

    public Kurs(String bezeichnung, int semester, ArrayList<Kurs> vorleistungen) {
        this.bezeichnung = bezeichnung;
        this.semester = semester;
        this.vorleistungen = vorleistungen;
    }

    public String getBezeichnung() {
        return bezeichnung;
    }

    public void setBezeichnung(String bezeichnung) {
        this.bezeichnung = bezeichnung;
    }

    public int getSemester() {
        return semester;
    }

    public void setSemester(int semester) {
        this.semester = semester;
    }

    public ArrayList<Kurs> getVorleistungen() {
        return vorleistungen;
    }

    public void setVorleistungen(ArrayList<Kurs> vorleistungen) {
        this.vorleistungen = vorleistungen;
    }

    @Override
    public String toString() {
        return "Kurs{" +
                "bezeichnung='" + bezeichnung + '\'' +
                ", semester=" + semester +
                ", vorleistungen=" + vorleistungen +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Kurs kurs = (Kurs) o;
        return semester == kurs.semester &&
                bezeichnung.equals(kurs.bezeichnung) &&
                vorleistungen.equals(kurs.vorleistungen);
    }

    @Override
    public int hashCode() {
        return 0;
    }
}

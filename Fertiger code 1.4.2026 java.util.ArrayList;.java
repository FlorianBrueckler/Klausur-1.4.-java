import java.util.ArrayList;

abstract class Medium {
    private String titel;
    private String autor;
    private boolean verfuegbar;

    public Medium(String titel, String autor) {
        this.titel = titel;
        this.autor = autor;
        this.verfuegbar = true;
    }

    public String getTitel() {
        return titel;
    }

    public String getAutor() {
        return autor;
    }

    public boolean isVerfuegbar() {
        return verfuegbar;
    }

    public void setTitel(String titel) {
        this.titel = titel;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public void setVerfuegbar(boolean verfuegbar) {
        this.verfuegbar = verfuegbar;
    }

    public void ausleihen() {
        if (verfuegbar) {
            verfuegbar = false;
            System.out.println("\"" + titel + "\" wurde erfolgreich ausgeliehen.");
        } else {
            System.out.println("\"" + titel + "\" ist aktuell nicht verfügbar.");
        }
    }

    public void zurueckgeben() {
        verfuegbar = true;
        System.out.println("\"" + titel + "\" wurde zurückgegeben.");
    }

    public abstract double berechneAusleihegebuehr(int tage);

    @Override
    public abstract String toString();
}

class Buch extends Medium {
    private int seitenanzahl;
    private String isbn;

    public Buch(String titel, String autor, int seitenanzahl, String isbn) {
        super(titel, autor);
        this.seitenanzahl = seitenanzahl;
        this.isbn = isbn;
    }

    public int getSeitenanzahl() {
        return seitenanzahl;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setSeitenanzahl(int seitenanzahl) {
        this.seitenanzahl = seitenanzahl;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    @Override
    public double berechneAusleihegebuehr(int tage) {
        double gebuehr = tage * 0.50;
        return Math.min(gebuehr, 10.0);
    }

    @Override
    public String toString() {
        return "Buch: " + getTitel() +
                ", Autor: " + getAutor() +
                ", Seiten: " + seitenanzahl +
                ", ISBN: " + isbn +
                ", Verfügbar: " + isVerfuegbar();
    }
}

class DVD extends Medium {
    private int dauer;
    private int fsk;

    public DVD(String titel, String autor, int dauer, int fsk) {
        super(titel, autor);
        this.dauer = dauer;
        this.fsk = fsk;
    }

    public int getDauer() {
        return dauer;
    }

    public int getFsk() {
        return fsk;
    }

    public void setDauer(int dauer) {
        this.dauer = dauer;
    }

    public void setFsk(int fsk) {
        this.fsk = fsk;
    }

    @Override
    public double berechneAusleihegebuehr(int tage) {
        double gebuehr = tage * 1.00 + 2.00;
        return Math.min(gebuehr, 20.0);
    }

    @Override
    public String toString() {
        return "DVD: " + getTitel() +
                ", Regisseur/Autor: " + getAutor() +
                ", Dauer: " + dauer + " Minuten" +
                ", FSK: " + fsk +
                ", Verfügbar: " + isVerfuegbar();
    }
}

public class Bibliothek {
    private ArrayList<Medium> medien;

    public Bibliothek() {
        medien = new ArrayList<>();
    }

    public void mediumHinzufuegen(Medium m) {
        medien.add(m);
    }

    public void alleVerfuegbarenAnzeigen() {
        System.out.println("\nVerfügbare Medien:");
        for (Medium m : medien) {
            if (m.isVerfuegbar()) {
                System.out.println(m);
            }
        }
    }

    public void sucheNachTitel(String titel) {
        System.out.println("\nSuchergebnisse für: " + titel);
        boolean gefunden = false;

        for (Medium m : medien) {
            if (m.getTitel().equalsIgnoreCase(titel)) {
                System.out.println(m);
                gefunden = true;
            }
        }

        if (!gefunden) {
            System.out.println("Kein Medium mit diesem Titel gefunden.");
        }
    }

    public static void main(String[] args) {
        Bibliothek bib = new Bibliothek();

        Buch b1 = new Buch("Java Grundlagen", "Max Meier", 350, "978-3-12345-111-1");
        Buch b2 = new Buch("OOP verstehen", "Laura Schulz", 420, "978-3-12345-222-2");
        Buch b3 = new Buch("Datenstrukturen", "Tobias Klein", 500, "978-3-12345-333-3");

        DVD d1 = new DVD("Inception", "Christopher Nolan", 148, 12);
        DVD d2 = new DVD("Interstellar", "Christopher Nolan", 169, 12);

        bib.mediumHinzufuegen(b1);
        bib.mediumHinzufuegen(b2);
        bib.mediumHinzufuegen(b3);
        bib.mediumHinzufuegen(d1);
        bib.mediumHinzufuegen(d2);

        bib.alleVerfuegbarenAnzeigen();

        b1.ausleihen();
        d1.ausleihen();

        System.out.println("Gebühr für 'Java Grundlagen' bei 8 Tagen: " + b1.berechneAusleihegebuehr(8) + " €");
        System.out.println("Gebühr für 'Inception' bei 5 Tagen: " + d1.berechneAusleihegebuehr(5) + " €");

        bib.alleVerfuegbarenAnzeigen();

        d1.zurueckgeben();

        bib.alleVerfuegbarenAnzeigen();
        bib.sucheNachTitel("Interstellar");
        bib.sucheNachTitel("Harry Potter");
    }
}



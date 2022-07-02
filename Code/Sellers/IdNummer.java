package Sellers;

import java.util.function.Supplier;

/**
 * Fachklasse einer ID-Nummer
 *
 * @author 1319658
 * @version 2.3
 */
public class IdNummer extends AccountBasis implements Supplier<Long> {

    private long nummer; //Speichert aktuelle IdNummer

    /**
     * Konstruktor für IdNummer mit bestimmter Nummer
     *
     * @params set, aktuell maximaler Nummern-Key
     */
    public IdNummer(Long set)
    {
        super("", "", "-2"); //Konstruktor der super-Klasse aufrufen
        this.nummer = set;
    }

    /**
     * Konstruktor für IdNummer
     *
     */
    public IdNummer() //Standard-Konstruktor
    {
        this(-1L); //Start-ID auf -1
    }

    /**
     * Gibt nächste ID-Nummer zurück
     *
     * @return Aktuell folgende ID-Nummer
     */
    public Long nextNumber()
    {
        nummer = nummer + 1L; //Nummer um 1 erhöhen
        return nummer; //Nummer zurückgeben
    }

    @Override
    public String[][] returnMenu() {
        return new String[0][];
    }

    /**
     * Erzeugt einen String, der eine fixe Ausgabe im CSV-Format für die Klasse hat
     *
     * @return CSV-formatierte Ausgabe des Inhalts der Klasse
     * @inv Instanz bleibt gleich
     */
    @Override
    public String toCsv()
    {
        StringBuilder csv = super.BasisToCsv(); //StringBuilder mit Daten aus Super-Klasse

        csv.append(delimiter) //Anfügen der IdNummer-spezifischen Nummer
                .append(nummer);

        return csv.toString(); //Zurückgeben des zusammengebauten Strings
    }

    /**
     * Erzeugt aus einem CSV-formatierten Text eine Instanz einer IdNummer
     *
     * @params CSV-formatierte Parameter
     * @return IdNummer mit Daten aus den gelesenen Parametern
     */
    @Override
    public IdNummer fromCsv(String daten)
    {
        String[] parameter = daten.split(delimiter); //Splitten des Strings am Seperator
        return new IdNummer (Long.parseLong(parameter[4])); //Erstellen und zurückgeben des erstellten Id-Objekts
    }

    /**
     * Gibt nächste ID-Nummer zurück
     *
     * @return Aktuell folgende ID-Nummer
     */
    @Override
    public Long get()
    {
        return nextNumber(); //Zurückgeben der nächsten Nummer
    }

    /**
     * Gibt Daten der IdNummer in die Konsole aus
     *
     */
    @Override
    public void printData()
    {   //Nicht verwendet
        System.out.println("Aktuelle ID-Nummer: " + nummer);
    }
}

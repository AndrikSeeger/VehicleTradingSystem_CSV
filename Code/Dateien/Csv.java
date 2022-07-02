package Dateien;

/**
 * Interface für CSV-Funktionalität
 *
 * @author 1319658
 * @version 2.3
 */
public interface Csv extends Delimiter
{
    /**
     * Abstrakte Methode
     *
     * @return CSV-String
     */
    public abstract String toCsv(); //Konvertiert Objekt in csv-String

    /**
     * Abstrakte Methode um aus CSV-Daten ein Objekt zu erstellen
     *
     * @return Objekt aus CSV-Daten
     */
    public abstract Object fromCsv(String daten); //Erstellt Objekt aus csv-String

    /**
     * Abstrakte Methode um Objekt-Daten in einen CSV-StringBuilder umzuwandeln
     *
     * @return CSV-StringBuilder
     */
    public StringBuilder BasisToCsv(); //Bei Vererbung muss der csv-String mit den Daten der Super-Klasse kombiniert werden
}

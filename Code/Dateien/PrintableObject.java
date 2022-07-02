package Dateien;

/**
 * Interface für Konsolenausgabe
 *
 * @author 1319658
 * @version 2.3
 */
public interface PrintableObject {
    //implement in child
    /**
     * Abstrakte Methode um Objekt-Daten in Konsole auszugeben
     *
     */
    public void printData(); //Gibt Daten der Klasse in Konsole aus
    //implement in super

    /**
     * Abstrakte Methode um Objekt-Daten in Konsole auszugeben
     *
     */
    public void printBasisData(); //Gibt Daten der Super-Klasse in Konsole aus
}

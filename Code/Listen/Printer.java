package Listen;

import Dateien.PrintableObject;

import java.util.ArrayList;

/**
 * Klasse um Listen in die Konsole auszugeben
 *
 * @author 1319658
 * @version 2.3
 */
public class Printer
{
    /**
     * Ausgeben einer Liste in die Konsole mit Nummerierung
     *
     * @params liste, ArrayList mit auszugebenden Objekten
     * @inv liste unverändert
     */
    public static <T extends PrintableObject> void printListWithNumbers (ArrayList<T> liste)
    {
        for(int n = 0; n<liste.size(); n++) //Für alle Elemente der Liste
        {
            liste.get(n).printData(); //Gibt Daten des Objekts in Konsole aus
            System.out.println("-----#" + n + "-----"); //Gibt Nummer des Objekts in der Liste in Konsole aus
        }
    }

    /**
     * Ausgeben einer Liste in die Konsole
     *
     * @params liste, ArrayList mit auszugebenden Objekten
     * @inv liste unverändert
     */
    public static <T extends PrintableObject> void printList(ArrayList<T> liste)
    {
        liste.forEach(PrintableObject::printData); //Für jedes Element der Liste Daten in Konsole ausgeben
    }
}

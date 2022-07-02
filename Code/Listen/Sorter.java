package Listen;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * Klasse um eine Liste zu sortieren
 *
 * @author 1319658
 * @version 2.3
 */

public class Sorter {

    /**
     * Sortieren eine Liste
     *
     * @return ArrayList mit sortierten Elementen
     * @params liste, ArrayList von welche ortiert werden soll
     * @params data, Sortierparamter
     * @pre Aufbau data korrekt
     * @post Kopie von liste sortiert
     * @inv liste bleibt gleich
     * @throws RuntimeException RuntimeException wenn data falsche Struktur hat
     */
    public static <T> ArrayList<T> sortList(ArrayList<T> liste, Object[] data)
    {
        ArrayList<T> listeKopie = new ArrayList<>((liste)); //Kopie der Liste erstellend welche sortiert werden soll

        System.out.println();

        String message; //Nachricht welche beim Sortieren ausgegeben werden soll
        Comparator<T> comp; //Comparator mit dem sortiert werden soll
        boolean reverse; //Bestimmt ob auf- oder absteigend sortiert wird
        try
        { //Daten aus Object-Array
            message = (String) data[0];
            comp = (Comparator<T>) data[1];
            reverse = (boolean) data[2];
        }
        catch(ClassCastException e) //Casten nicht möglich
        {
            e.printStackTrace();
            throw new RuntimeException("Sortierdaten haben falsche Datenstruktur");
        }

        System.out.print(message); //Nachricht ausgeben
        System.out.flush();

        listeKopie.sort(comp); //Kopie sortieren

        if(reverse) //Wenn reverse true -> Liste umkehren
        {
            System.out.print(" (absteigend)\n");
            Collections.reverse(listeKopie); //Umkehren der Liste
        }
        else
        {
            System.out.print(" (aufsteigend)\n");
        }

        System.out.println();

        return  listeKopie; //Sortierte Liste zurückgeben
    }
}

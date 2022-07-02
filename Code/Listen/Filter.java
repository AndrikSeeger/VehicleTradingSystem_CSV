package Listen;

import java.util.ArrayList;
import java.util.function.Predicate;

/**
 * Klasse um eine Liste zu filtern
 *
 * @author 1319658
 * @version 1.3
 */

public class Filter {

    /**
     * Filtern einer Liste nach gegebenem Filter
     *
     * @return ArrayList mit gefilterten Elementen
     * @params filter, Predicate zum Filtern
     * @params liste, ArrayList die gefiltert werden soll
     * @post Alle Elemente in der return-Liste entsprechen dem Predicate
     * @inv Die gegebene ArrayList bleibt unverändert
     */

    public static <T> ArrayList<T> filterList(Predicate<T> filter, ArrayList<T> liste)
    {
        ArrayList<T> filteredList = new ArrayList<>();
        liste.stream()
                .filter(filter) //Filtert Elemente nach gegebenem Filter
                .forEach(fz -> filteredList.add((T) fz)); //Fügt alle passenden Elemente zur neuen Liste hinzu

        return filteredList; //Rückgabe der passenden Elemente
    }
}

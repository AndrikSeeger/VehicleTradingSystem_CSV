package Vehicles;

import java.util.ArrayList;

/**
 * Klasse um Fahrzeuge zu suchen
 *
 * @author 1319658
 * @version 2.3
 */
public class FahrzeugSuche {

    /**
     * Fahrzeuge zu gegebenem Key finden und zurückgeben
     *
     * @return ArrayList mit Fahrzeugen zu gegebenem Key
     * @params fahrzeuge, ArrayList mit allen Fahrzeugen
     * @params key, Fahrzeug-ID
     * @post Fahrzeug-Liste vorhanden
     * @inv Fahrzeug-Liste unverändert vorhanden
     */
    public static <T extends FahrzeugBasis> ArrayList<T> fahrzeugeZuKey(ArrayList<T> fahrzeuge, long key)
    {
        ArrayList<T> fahrzeugeZuKey = new ArrayList<>(); //Liste in der die zugehörigen Fahrzeuge gespeichert werden
        fahrzeuge.stream()
                .filter(vec -> (vec.getKey() == key)) //Filtern der Fahrzeuge zu gegebenem Key
                .forEach(fahrzeugeZuKey::add); //Hinzufügen der Fahrzeuge zur Liste
        return fahrzeugeZuKey; //Zurückgeben der Lsite mit den gefundenen Fahrzeugen
    }

}

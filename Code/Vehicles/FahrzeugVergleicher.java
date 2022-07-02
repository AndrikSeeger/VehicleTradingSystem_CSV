package Vehicles;

import java.util.ArrayList;
import java.util.Comparator;

/**
 * Klasse für Sortier-Daten
 *
 * @author 1319658
 * @version 5.3
 */
public class FahrzeugVergleicher {

    /**
     * Gibt Sortierdaten zu gegebener Nummer zurück
     *
     * @return Datenpaket für Sortierung
     * @params wahl, Nummer bestimmt nach was wie sortiert werden soll
     */

    public static Object[] returnCompInfo (int wahl)
    {
        //bspw. wahl 0 = preis aufsteigend, wahl 1 = preis absteigend --> Gerade Zahlen aufsteigend, ungerade Zahlen absteigend

        //0 = Preis aufsteigend, 1 = Preis absteigend, 2 = Kilometerstand aufsteigend, 3 = Kilometerstand absteigend, 4 = Erstzulassung aufsteigend, 5 = Erstzulassung absteigend, 6 = Einstelldatum aufsteigend, 7 = Einstelldatum absteigend

        boolean reversed = false; //Gibt an ob aufsteigend (false) oder absteigend (true) sortiert werden soll

        ArrayList<Object[]> menuComparator = setMenuComparator(); //Daten für verschiedene Sortierungen setzen und zurückgeben

        if((wahl%2) == 1) //Bei ungeraden Zahlen --> Sortierung rückwärts
        {
            reversed = true;
        }

        //wahl = wahl - (wahl%2); //
        wahl = wahl/2; //Auf nächste untere gerade Zahl wenn ungerade
        menuComparator.get(wahl)[2] = reversed; //Setzen ob auf- oder absteigend sortiert werden soll
        return menuComparator.get(wahl); //Zurückgeben der Sortier-Informationen
    }

    /**
     * Speichert, setzt Sortier-Datenpakete
     *
     * @return ArrayList mit Datenpaketent
     */
    private static ArrayList<Object[]> setMenuComparator()
    {
        ArrayList<Object[]> menuComparator = new ArrayList<>(); //Liste der einzelnen Datenpakete

        Object[] preis = new Object[3]; //Informationen zu Preis-Sortierung = Datenpaket
        Object[] kilometer = new Object[3]; //Informationen zu Kilometerstand-Sortierung = Datenpaket
        Object[] erstzulassung = new Object[3]; //Informationen zu Erstzulassung-Sortierung = Datenpaket
        Object[] einstelldatum = new Object[3]; //Informationen zu Einstelldatum-Sortierung = Datenpaket

        preis[0] = "Nach Preis sortiert"; //Nachricht die ausgegeben werden soll beim Sortieren
        preis[1] = COMP_PREIS; //Comparator welcher verwendet werden soll

        //Schema fortlaufend

        kilometer[0] = "Nach Kilometerstand sortiert";
        kilometer[1] = COMP_ODO;

        erstzulassung[0] = "Nach Erstzulassung sortiert";
        erstzulassung[1] = COMP_ERSTZULASSUNG;

        einstelldatum[0] = "Nach Einstelldatum sortiert";
        einstelldatum[1] = COMP_EINSTELLDATUM;

        menuComparator.add(preis); //Hinzufügen der Datenpakete zur Liste
        menuComparator.add(kilometer);
        menuComparator.add(erstzulassung);
        menuComparator.add(einstelldatum);
        return menuComparator; //Zurückgeben der Liste mit Datenpaketen
    }

    private static final Comparator<FahrzeugBasis> COMP_PREIS = Comparator.comparing(FahrzeugBasis::getPreis); //Comparator für Preis eines Fahrzeugs
    private static final Comparator<FahrzeugBasis> COMP_ODO = Comparator.comparing(FahrzeugBasis::getOdometer); //Comparator für Kilometerstand eines Fahrzeugs
    private static final Comparator<FahrzeugBasis> COMP_ERSTZULASSUNG = Comparator.comparing(FahrzeugBasis::getErstzulassungAsDate); //Comparator für Erstzulassung eines Fahrzeugs
    private static final Comparator<FahrzeugBasis> COMP_EINSTELLDATUM = Comparator.comparing(FahrzeugBasis::getEinstelldatumAsDate); //Comparator für Einstelldatum eines Fahrzeugs
}

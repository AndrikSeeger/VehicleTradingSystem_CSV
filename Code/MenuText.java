/**
 * Klasse für Menü-Texte
 *
 * @author 1319658
 * @version 2.3
 */

public class MenuText {
    public static void menuHaupt() {
        message("Menü: ");
        message("0 = Fahrzeugesuche");
        message("1 = Fahrzeug inserieren");
        message("2 = Meine Inserate anzeigen");
        message("3 = Meine Inserate bearbeiten");
        message("4 = Anmelden");
        message("5 = Abmelden");
        message("6 = Account erstellen");
        message("7 = Account löschen");
    }

    public static void menuFilterAuto() {
        message("7 = Fahrzeugart filtern (Geländewagen, Kombi, Limousine, Kleinwagen, Cabrio, Sportwagen): ");
        message("8 = Sportpaket filtern (ja/nein): ");
    }

    public static void menuFilternBasis() {
        message("Hinweis: * zum auslassen eines Wertes");
        message("0 = Marke filtern: ");
        message("1 = Modell filtern: ");
        message("2 = Preis filtern: ");
        message("3 = Leistung filtern: ");
        message("4 = Laufleistung filtern: ");
        message("5 = Gewicht filtern: ");
        message("6 = Erstzulassung filtern: ");
    }

    public static void menuSortieren() {
        message("0 = Preis aufsteigend");
        message("1 = Preis absteigend");
        message("2 = Kilometer aufsteigend");
        message("3 = Kilometer absteigend");
        message("4 = Erstzulassung aufsteigend");
        message("5 = Erstzulassung absteigend");
        message("6 = Einstelldatum aufsteigend");
        message("7 = Einstelldatum absteigend");
    }

    public static void menuAccountErstellen() {
        message("0 = Zurück");
        message("1 = Privaten Account erstellen");
        message("2 = Professionellen Account erstellen");
    }

    public static void menuInserieren() {
        message("0 = Zurück");
        message("1 = Auto inserieren");
        message("2 = LKW inserieren");
    }

    /**
     * Gibt Nachricht in Konsole aus
     *
     * @params message, Nachricht die ausgegeben werden soll
     */
    public static void message(String message)
    {
        System.out.println(message);
    }

    /**
     * Gibt leere Zeile in Konsole aus
     *
     */
    public static void printBlanc()
    {
        System.out.println();
    }
}
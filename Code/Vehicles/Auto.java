package Vehicles;

import java.text.ParseException;

/**
 * Fachklasse eines Autos
 *
 * @author 1319658
 * @version 8.3
 */
public class Auto extends FahrzeugBasis
{
    private String[][] menu = new String[2][3]; //Menü spezifisch für die Auto-Daten

    private String fahrzeugArt = ""; //Fahrzeugart
    private boolean sportPaket; //Sportpaket vorhanden

    /**
     * Konstruktor für Auto mit allen Daten
     *
     * @pre key, leistung, gewicht, odometer, preis entspricht Ziffern, erstzulassung und einstelldatum entsprechen korrekten Patterns
     * @params key, Nummern-Key des zugehörigen Accounts
     * @params marke, Marke des Fahrzeugs
     * @params modell, Modell des Fahrzeugs
     * @params erstzulassung, Erstzulassung des Fahrzeugs
     * @params einstelldatum, Einstelldatum des Fahrzeugs
     * @params leistung, Leistung des Fahrzeugs
     * @params gewicht, Gewicht des Fahrzeugs
     * @params odometer, Kilometerstand des Fahrzeugs
     * @params preis, Preis des Fahrzeugs
     * @params fahrzeugArt, Fahrzeugart
     * @params sportPaket, Sportpaket vorhanden
     */
    public Auto (String key, String marke, String modell, String erstzulassung, String einstelldatum, String leistung, String gewicht, String odometer, String preis, String fahrzeugArt, String sportPaket) throws ParseException {
        super(marke, modell, erstzulassung, einstelldatum, leistung, gewicht, odometer, preis, key); //Konstruktor der super-Klasse aufrufen
        this.fahrzeugArt = fahrzeugArt;
        setSportPaket(sportPaket);
    }

    /**
     * Konstruktor für Auto
     *
     */
    public Auto()
    {
        super();
        setMenu(); //Setzen des Menüs
    }

    //Eigenschaften
    public String getFahrzeugArt() //Zurückgeben der Fahrzeugart
    {
        return fahrzeugArt;
    }

    public void setFahrzeugArt(String fahrzeugArt) //Setzen der Fahrzeugart
    {
        switch (fahrzeugArt){
            case "*": break;
            case "1": this.fahrzeugArt = "Geländewagen"; break;
            case "2": this.fahrzeugArt = "Kombi"; break;
            case "3": this.fahrzeugArt = "Limousine"; break;
            case "4": this.fahrzeugArt = "Kleinwagen"; break;
            case "5": this.fahrzeugArt = "Cabrio"; break;
            case "6": this.fahrzeugArt = "Sportwagen"; break;
        }
    }

    public String getSportPaket ()//Zurückgeben ob ein Sportpaket vorhanden ist als String
    {
        if(sportPaket)
        {
            return "ja";
        }
        else
        {
            return "nein";
        }

    }

    public void setSportPaket(boolean sportPaket) //Setzen ob ein Sportpaket vorhanden ist als mit einem boolean
    {
        this.sportPaket = sportPaket;
    }

    public void setSportPaket(String sportPaket) //Setzen ob ein Sportpaket vorhanden ist als mit einem String
    {
        this.sportPaket = sportPaket.equals("ja") || sportPaket.equals("true");
    }

    /**
     * Erzeugt einen String, der eine fixe Ausgabe im CSV-Format für die Klasse hat
     *
     * @return CSV-formatierte Ausgabe des Inhalts der Klasse
     * @inv Instanz bleibt gleich
     */
    @Override
    public String toCsv() {
        StringBuilder csv = super.BasisToCsv(); //StringBuilder mit Daten aus Super-Klasse

        csv.append(delimiter)  //Anfügen der Auto-spezifischen Daten
                .append(fahrzeugArt)
                .append(delimiter)
                .append(sportPaket);

        return csv.toString(); //Zurückgeben des zusammengebauten Strings
    }

    /**
     * Erzeugt aus einem CSV-formatierten Text eine Instanz eines Autos
     *
     * @params CSV-formatierte Parameter
     * @return Auto mit Daten aus den gelesenen Parametern
     */
    @Override
    public Auto fromCsv(String daten)
    {
        String[] parameter = daten.split(delimiter); //Splitten des Strings am Seperator
        try{
            return new Auto (parameter[0], parameter[2], parameter[3], parameter[4], parameter[5], parameter[6], parameter[7], parameter[8], parameter[9], parameter[10], parameter[11]); //Erstellen und zurückgeben des erstellten Autos
        }
        catch(ParseException e) //Gibt null zurück falls kein Auto erstellt werden kann
        {
            return null;
        }
    }

    /**
     * Setzt Menü für das Erstellen eines Accounts
     *
     */
    private void setMenu()
    {
        //Setzen des Auto-spezifischen Menüs
        menu[0][0] = "Fahrzeugart wählen (1 = Geländewagen, 2 = Kombi, 3 = Limousine, 4 = Kleinwagen, 5 = Cabrio, 6 = Sportwagen): ";
        menu[1][0] = "Sportpaket (ja/nein)?: ";

        menu[0][1] = "Regex" + "[1-6]"; //Zahl 1-6
        menu[1][1] = "Regex" + "(ja)|(nein)"; //ja oder nein

        menu[0][2] = "setFahrzeugArt"; //Methode zum setzen der Fahrzeugart
        menu[1][2] = "setSportPaket"; //Methode zum setzen ob ein Sportpaket vorhanden ist
    }

    /**
     * Gibt Menü für das Erstellen eines Autos zurück
     *
     * @return Menü
     */
    @Override
    public String[][] returnMenu()
    {
        return super.completeMenu(menu); //Zurückgeben des zusammengesetzen Menüs
    }

    /**
     * Gibt Daten des Autos in die Konsole aus
     *
     */
    @Override
    public void printData()
    {
        printBasisData(); //Daten der super-Klasse in Konsole ausgeben
        System.out.println("Fahrzeugart: " + fahrzeugArt); //Auto-spezifische Daten in Konsole ausgeben
        System.out.println("Sportpaket? " + getSportPaket());
    }
}

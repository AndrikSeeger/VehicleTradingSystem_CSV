package Vehicles;

import java.text.ParseException;

/**
 * Fachklasse eines Lkws
 *
 * @author 1319658
 * @version 8.3
 */
public class Lkw extends FahrzeugBasis
{
    private String[][] menu = new String[3][3]; //Menü spezifisch für die Lkw-Daten

    private int zuladung = -1; //Zuladung
    private Double zuglast = -1.0; //Zuglast
    private boolean hydraulik; //Hydraulik vorhanden

    /**
     * Konstruktor für Lkw mit allen Daten
     *
     * @pre keyleistung, gewicht, odometer, preis, zuladung, zuglast entspricht Ziffern, erstzulassung und einstelldatum entsprechen korrekten Patterns
     * @params key, Nummern-Key des zugehörigen Accounts
     * @params marke, Marke des Fahrzeugs
     * @params modell, Modell des Fahrzeugs
     * @params erstzulassung, Erstzulassung des Fahrzeugs
     * @params einstelldatum, Einstelldatum des Fahrzeugs
     * @params leistung, Leistung des Fahrzeugs
     * @params gewicht, Gewicht des Fahrzeugs
     * @params odometer, Kilometerstand des Fahrzeugs
     * @params preis, Preis des Fahrzeugs,
     * @params zuladung, Zuladung des Fahrzeugs
     * @params zuglast, Zuglast des Fahrzeugs
     * @params hydraulik, Hydraulik vorhanden
     */
    public Lkw(String key, String marke, String modell, String erstzulassung, String einstelldatum, String leistung, String gewicht, String odometer, String preis, String zuladung, String zuglast, String hydraulik) throws ParseException {
        super(marke, modell, erstzulassung, einstelldatum, leistung, gewicht, odometer, preis, key); //Konstruktor der super-Klasse aufrufen
        this.zuladung = Integer.parseInt(zuladung);
        this.zuglast = Double.parseDouble(zuglast);
        setHydraulik(hydraulik);
    }

    /**
     * Konstruktor für Lkw
     *
     */
    public Lkw() {
        super();
        setMenu(); //Setzen des Menüs
    }


    //Eigenschaften
    public int getZuladung() //Zurückgeben der Zuladung
    {
        return zuladung;
    }

    public void setZuladung(String zuladung) //Setzen der Zuladung
    {
        this.zuladung = Integer.parseInt(zuladung);;
    }

    public Double getZuglast() //Zurückgeben der Zuglast
    {
        return zuglast;
    }

    public void setZuglast(String zuglast) //Setzen der Zuglast
    {
        this.zuglast = Double.parseDouble(zuglast);
    }

    public String getHydraulik() //Zurückgeben ob Hydraulik vorhanden ist als String
    {
        if(hydraulik)
        {
            return "ja";
        }
        else
        {
            return "nein";
        }
    }

    public void setHydraulik(String hydraulik) //Setzen ob Hydraulik vorhanden ist
    {
        this.hydraulik = hydraulik.equals("ja") || hydraulik.equals("true");
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

        csv.append(delimiter) //Anfügen der Lkw-spezifischen Daten
                .append(zuladung)
                .append(delimiter)
                .append(zuglast)
                .append(delimiter)
                .append(hydraulik);

        return csv.toString(); //Zurückgeben des zusammengebauten Strings
    }

    /**
     * Erzeugt aus einem CSV-formatierten Text eine Instanz eines Lkws
     *
     * @params CSV-formatierte Parameter
     * @return Lkws mit Daten aus den gelesenen Parametern
     */
    @Override
    public Lkw fromCsv(String daten)
    {
        String[] parameter = daten.split(delimiter); //Splitten des Strings am Seperator
        try{
            return new Lkw(parameter[0], parameter[2], parameter[3], parameter[4], parameter[5], parameter[6], parameter[7], parameter[8], parameter[9], parameter[10], parameter[11], parameter[12]); //Erstellen und zurückgeben des erstellten Lkws
        }
        catch(ParseException e) //Gibt null zurück falls kein Lkw erstellt werden kann
        {
            return null;
        }
    }

    /**
     * Setzt Menü für das Erstellen eines Lkws
     *
     */
    private void setMenu()
    {
        //Setzen des Lkw-spezifischen Menüs
        menu[0][0] = "Zuladung (in kg)(ZZZZZ): ";
        menu[1][0] = "Zuglast (in t) (ZZ.Z): ";
        menu[2][0] = "Hydraulik (ja/nein)?: ";

        menu[0][1] = "Regex" + "\\d{1,5}"; //0-99999
        menu[1][1] = "Regex" + "\\d{1,2}\\.\\d"; // 00,0 - 99.9
        menu[2][1] = "Regex" + "(ja)|(nein)"; //ja oder nein

        menu[0][2] = "setZuladung"; //Methode zum setzen der Zuladung
        menu[1][2] = "setZuglast"; //Methode zum setzen der Zuglast
        menu[2][2] = "setHydraulik";  //Methode zum setzen ob Hydraulik vorhanden ist
    }

    /**
     * Gibt Menü für das Erstellen eines Lkws zurück
     *
     * @return Menü
     */
    @Override
    public String[][] returnMenu()
    {
        return super.completeMenu(menu); //Zurückgeben des zusammengesetzen Menüs
    }

    /**
     * Gibt Daten des Lkws in die Konsole aus
     *
     */
    @Override
    public void printData()
    {
        printBasisData(); //Daten der super-Klasse in Konsole ausgeben
        System.out.println("Zuladung: " + zuladung + "kg"); //Auto-spezifische Daten in Konsole ausgeben
        System.out.println("Zuglast: " + zuglast + "t");
        System.out.println("Hydraulik? " + getHydraulik());
    }

}

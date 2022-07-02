package Vehicles;

//import Dateien.CSV;
import Dateien.Csv;
import Menu.MenuInterface;
import Dateien.PrintableObject;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Abstrakte Klasse für die Basis eines jeden Fahrzeugs
 *
 * @author 1319658
 * @version 7.3
 */
public abstract class FahrzeugBasis implements Csv, PrintableObject, MenuInterface //Menu (SetMenu, returnMenu, complete Menu) --> Interface für Menü
{
    private String[][] menu = new String[7][3]; //Menü welches Basis-Daten für die Erstellung eines Fahrzeugs speichert

    private final String musterEinstelldatum = "dd.MM.yyyy"; //Muster für Syntax des Einstelldatums als String
    private final DateFormat einstelldatumFormater = new SimpleDateFormat(musterEinstelldatum); //DateFormater für Einstelldatum-Syntax
    private final String musterErstzulassung = "MM/yyyy"; //Muster für Syntax der Erstzulassung als String
    private final DateFormat erstzulassungFormater = new SimpleDateFormat(musterErstzulassung); //DateFormater für Erstzulassung-Syntax

    private String marke = ""; //Fahrzeugmarke
    private String modell = ""; //Fahrzeugmodell
    private String typ = ""; //Vollst. Klassenname
    private Date einstelldatum = null; //Einstelldatum des Inserats
    private Date erstzulassung = null; //Erstzulassung des Fahrzeugs
    private int leistung = -1; //Fahrzeugleistung
    private int gewicht = -1; //Fahrzeuggewicht
    private int odometer = -1; //Kilometerstand/Laufleistung
    private int preis = -1; //Fahrzeugpreis
    private long keyAccount = -1L; //Key des dazugehörigen Accounts welcher Fahrzeug inseriert hat


    /**
     * Konstruktor für Basis-Fahrzeug mit allen Daten
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
     */
    public FahrzeugBasis(String marke, String modell, String erstzulassung, String einstelldatum, String leistung, String gewicht, String odometer, String preis, String keyAccount) throws ParseException {
        this.typ = this.getClass().getCanonicalName(); //Klassenname speichern
        this.marke = marke;
        this.modell = modell;
        this.einstelldatum = new SimpleDateFormat(musterEinstelldatum).parse(einstelldatum); //Datum aus String
        this.erstzulassung = new SimpleDateFormat(musterErstzulassung).parse(erstzulassung); //Datum aus String
        this.leistung = Integer.parseInt(leistung);
        this.gewicht = Integer.parseInt(gewicht);
        this.odometer = Integer.parseInt(odometer);
        this.preis = Integer.parseInt(preis);
        this.preis = Integer.parseInt(preis);
        this.keyAccount = Long.parseLong(keyAccount, 10); //Key als Long speichern
    }

    /**
     * Konstruktor für Basis-Fahrzeug mit allen Daten
     *
     */
    public FahrzeugBasis()
    {
        typ = this.getClass().getCanonicalName(); //Klassenname speichern
        this.einstelldatum = new Date(); //Aktuelles Datum als Einstelldatum setzen
        setBasicMenu(); //Setzen der Menü-Daten
    }

    /**
     * Setzt Basis-Menü für das Erstellen eines Fahrzeugs
     *
     */
    private void setBasicMenu()
    {
        //Setzen der Daten des Menüs für die Basis-Daten
        menu[0][0] = "Marke eingeben (XXX, keine Sonderz.): ";
        menu[1][0] = "Modell setzen (XXX, keine Sonderz.): ";
        menu[2][0] = "Erstzulassung eingeben (MM/YYYY, nicht zukünftig): ";
        menu[3][0] = "Leistung (in PS) eingeben (ZZZZ): ";
        menu[4][0] = "Gewicht (in kg) eingebn (ZZZZZ): ";
        menu[5][0] = "Kilometerstand eingeben (ZZZZZZZ): ";
        menu[6][0] = "Preis eingeben (ZZZZZZZZZ): ";

        menu[0][1] = "Regex" + "\\w{3,16}"; //zw. 3 und 16 Zeichen, keine Sonderzeichen
        menu[1][1] = menu[0][1]; //siehe Marke
        menu[2][1] = "Regex"+ "(([0][1-9])|([1][0-2]))\\/((1([8]|[9])\\d{2})|([2-9]\\d{3}))" + "Date" + musterErstzulassung; //Form: 05/2001, nicht nach aktuellem Datum (nicht in Zukunft)
        menu[3][1] = "Regex" + "\\d{1,4}"; //0-9.999
        menu[4][1] = "Regex" + "\\d{1,5}"; //0-99.999
        menu[5][1] = "Regex" + "\\d{1,7}"; //0-9.999.999
        menu[6][1] = "Regex" + "\\d{1,9}"; //0-999.999.999

        menu[0][2] = "setMarke";
        menu[1][2] = "setModell";
        menu[2][2] = "setErstzulassung";
        menu[3][2] = "setLeistung";
        menu[4][2] = "setGewicht";
        menu[5][2] = "setOdometer";
        menu[6][2] = "setPreis";
    }

    /**
     * Erzeugt einen StringBuilder, der eine fixe Ausgabe im CSV-Format für die Klasse hat
     *
     * @return CSV-formatierte Ausgabe des Inhalts der Klasse
     * @inv Instanz bleibt gleich
     */
    public StringBuilder BasisToCsv()
    {

        StringBuilder csv = new StringBuilder(); //StringBuilder für FahrzeugBasis-Daten
       csv.append(keyAccount) //Alle Daten zusammensetzen
            .append(delimiter)
            .append(typ)
            .append(delimiter)
            .append(marke)
            .append(delimiter)
            .append(modell)
            .append(delimiter)
            .append(erstzulassungFormater.format(erstzulassung)) //Datum zu String
            .append(delimiter)
            .append(einstelldatumFormater.format(einstelldatum)) //Datum zu String
            .append(delimiter)
            .append(leistung)
            .append(delimiter)
            .append(gewicht)
            .append(delimiter)
            .append(odometer)
            .append(delimiter)
            .append(preis);

        return csv; //Zurückgeben des StringBuilders
    }

    /**
     * Abstrakte Methode
     *
     * @return Menü
     */
    public abstract String[][] returnMenu(); //returnMenu muss in Child-Klassen überschrieben werden

    /**
     * Fügt Basis-Menü mit zusätzlichem Menü zusammen
     *
     * @params menuFahrzeug, zusätzliches Menü
     * @return Gesamtmenü
     */
    public String[][] completeMenu(String[][] menuFahrzeug)//Rückgabe des Menüs
    {
        //Fügt Array des Basis-Menüs mit dem der Child-Klasse zusammen
        String[][] completeArray = new String[this.menu.length + menuFahrzeug.length][this.menu[0].length]; //Neues Array mit Länge entsprechend der Summe der beiden Array-Längen erstellen, zweite Dimension bei beiden gleich
        System.arraycopy(this.menu, 0, completeArray, 0, this.menu.length);  //Kopiert Daten des Basis-Menüs in neues Array
        System.arraycopy(menuFahrzeug, 0, completeArray, this.menu.length, menuFahrzeug.length); //Kopiert Daten des Parameter-Menüs in neues Array
        return completeArray; //Rückgabe des vervollständigten Menüs
    }

    /**
     * Gibt Basis-Fahrzeug-Daten in Konsole aus
     *
     */
    public void printBasisData()
    {
        //Konsolenausgabe der Basis-Fahrzeug Daten
        System.out.println("---------------------------------------------");
        System.out.println("Preis: " + this.preis + "€");
        System.out.println("Fahrzeug: " + this.marke + " " + this.modell);
        System.out.println("Leistung: " + this.leistung + "PS");
        System.out.println("Laufleistung: " + this.odometer + "km");
        System.out.println("Gewicht: " + this.gewicht + "kg");
        System.out.println("Erstzulassung: " + getErstzulassungString());
        System.out.println("Einstelldatum: " + getEinstelldatumString());
    }


    //Eigenschaften
    public String getMarke() //Zurückgeben der Marke
    {
        return marke;
    }

    public void setMarke(String marke) //Setzen der Marke
    {
        this.marke = marke;
    }

    public String getModell() //Zurückgeben des Modells
    {
        return modell;
    }

    public void setModell(String modell) //Setzen des Modells
    {
        this.modell = modell;
    }

    public String getEinstelldatumString() //Zurückgeben des Einstelldatums als String
    {
        return new SimpleDateFormat(musterEinstelldatum).format(einstelldatum);
    }

    public Date getEinstelldatumAsDate() //Zurückgeben des Einstelldatums als Datum
    {
        return einstelldatum;
    }

    public void setEinstelldatum(String einstelldatum) throws ParseException //Setzen des Einstelldatums
    {
        this.einstelldatum = new SimpleDateFormat(musterEinstelldatum).parse(einstelldatum);
    }

    public String getErstzulassungString() //Zurückgeben der Erstzulassung als String
    {
        return new SimpleDateFormat(musterErstzulassung).format(erstzulassung);
    }

    public Date getErstzulassungAsDate() //Zurückgeben der Erstzulassung als Datum
    {
        return erstzulassung;
    }

    public void setErstzulassung(String erstzulassung) throws ParseException //Setzen der Erstzulassung
    {
        this.erstzulassung = new SimpleDateFormat(musterErstzulassung).parse(erstzulassung);
    }

    public int getLeistung() //Zurückgeben der Leistung
    {
        return leistung;
    }

    public void setLeistung(String leistung) //Setzen der Leistung
    {
        this.leistung = Integer.parseInt(leistung);
    }

    public int getGewicht() //Zurückgeben des Gewichts
    {
        return gewicht;
    }

    public void setGewicht(String gewicht) //Setzen des Gewichts
    {
        this.gewicht = Integer.parseInt(gewicht);;
    }

    public int getOdometer() //Zurückgeben des Kilometerstands
    {
        return odometer;
    }

    public void setOdometer(String odometer) //Setzen des Kilometerstands
    {
        this.odometer = Integer.parseInt(odometer);;
    }

    public int getPreis() //Zurückgeben des Preises
    {
        return preis;
    }

    public void setPreis(String preis) //Setzen des Preises
    {
        this.preis = Integer.parseInt(preis);;
    }

    public String getTyp() //Zurückgeben des vollständigen Klassennamens
    {
        return typ;
    }

    public void setTyp(String typ) //Setzen des vollst. Klassennamens
    {
        this.typ = typ;
    }

    public long getKey() //Zurückgeben des zugehörigen Account-Keys
    {
        return keyAccount;
    }

    public void setKey(Long key) //Setzen des zugehörigen Account-Keys
    {
        this.keyAccount = key;
    }
}

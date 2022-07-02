package Sellers;

//import Dateien.CSV;
import Dateien.Csv;
import Menu.MenuInterface;
import Dateien.PrintableObject;

/**
 * Abstrakte Klasse für die Basis eines jeden Accounts
 *
 * @author 1319658
 * @version 4.3
 */
public abstract class AccountBasis implements Csv, MenuInterface, PrintableObject
{
    private String[][] menu = new String[2][3]; //Menü welches Basis-Daten für die Erstellung eines Accounts speichert

    private String typ = ""; //Vollst. Klassenname
    private String mail = ""; //E-Mail-Adresse
    private String passwort = ""; //Passwort
    private long key = -1L; //Account-key zur eindeutigen Identifizierung

    /**
     * Konstruktor für AccountBasis mit allen Daten
     *
     * @pre key entspricht Ziffern
     * @params mail, E-Mail-Adresse des Accounts
     * @params passwort, Passwort des Accounts
     * @params key, Nummern-Key des Accounts
     */
    public AccountBasis(String mail, String passwort, String key)
    {
        this.typ = this.getClass().getCanonicalName(); //Klassenname speichern
        this.mail = mail;
        this.passwort = passwort;
        this.key = Long.parseLong(key, 10); //Key als Long speichern

    }

    /**
     * Konstruktor für AccountBasis
     *
     */
    public AccountBasis()
    {
        typ = this.getClass().getCanonicalName(); //Klassenname speichern
        setBasicMenu(); //Setzen der Menü-Daten
    }

    /**
     * Erzeugt einen StringBuilder, der eine fixe Ausgabe im CSV-Format für die Klasse hat
     *
     * @return CSV-formatierte Ausgabe des Inhalts der Klasse
     * @inv Instanz bleibt gleich
     */
    public StringBuilder BasisToCsv()
    {
        StringBuilder csv = new StringBuilder(); //StringBuilder für AccountBasis-Daten

        csv.append(key) //Alle Daten zusammensetzen
                .append(delimiter)
                .append(typ)
                .append(delimiter)
                .append(mail)
                .append(delimiter)
                .append(passwort);

        return csv; //Zurückgeben des StringBuilders
    }


    //Eigenschaften
    public String getMail() //Zurückgeben der Mail
    {
        return mail;
    }

    public void setMail(String mail) //Setzen der Mail
    {
        this.mail = mail.toLowerCase(); //wird in Kleinbuchstaben gespeichert -> Bei E-Mail Groß-/Kleinschreibung irrelevant
    }

    public String getPasswort() //Zurückgeben des Passworts
    {
        return passwort;
    }

    public void setPasswort(String passwort)  //Setzen des Passworts
    {
        this.passwort = passwort;
    }

    public long getKey() //Zurückgeben des Keys
    {
        return key;
    }

    public void setKey(Long key) //Setzen des Keys als Long-Objekt
    {
        this.key = key;
    }

    public String getTyp()  //Zurückgeben des Keys
    {
        return typ;
    }

    public void setTyp(String typ) //Setzen des Klassennamens
    {
        this.typ = typ;
    }

    /**
     * Setzt Basis-Menü für das Erstellen eines Accounts
     *
     */
    private void setBasicMenu()
    {
        //Setzen der Daten des Menüs für die Basis-Daten
        menu[0][0] = "E-Mail eingeben (X.X@X.de / XX@X.com): ";
        menu[1][0] = "Passwort setzen (Mindestens 1 Kleinbuchst., 1 Großbuchst., 2 Ziffern, 2 Sonderzeichen): ";

        menu[0][1] = "Regex" + "\\w+" + "\\.?" + "\\w+" + "@" + "\\w+" + "\\." + "(([dD][eE])|([cC][oO][mM]))"; //E-Mail Regex: aa@a.de oder a.a@a.de oder aa@a.com oder a.a@a.com
        menu[1][1] = "Regex" + "^(?=(.*\\d){2,})(?=.*[a-z])(?=.*[A-Z])(?=(.*[\\W]){2,}).{8,16}$"; //Mindestens ein Kleinbuchstabe, ein Großbuchstabe, zwei Ziffern, zwei Sonderzeichen. Insgesamt zwischen 8 und 16 Zeichen.

        menu[0][2] = "setMail"; //Methode zum setzen der Mail
        menu[1][2] = "setPasswort"; //Methode zum setzen des Passworts

    }

    /**
     * Fügt Basis-Menü mit zusätzlichem Menü zusammen
     *
     * @params menuAccount, zusätzliches Menü
     * @return Gesamtmenü
     */
    public String[][] completeMenu(String[][] menuAccount)
    {
        //Fügt Array des Basis-Menüs mit dem der Child-Klasse zusammen
        String[][] completeArray = new String[this.menu.length + menuAccount.length][this.menu[0].length]; //Neues Array mit Länge entsprechend der Summe der beiden Array-Längen erstellen, zweite Dimension bei beiden gleich
        System.arraycopy(this.menu, 0, completeArray, 0, this.menu.length); //Kopiert Daten des Basis-Menüs in neues Array
        System.arraycopy(menuAccount, 0, completeArray, this.menu.length, menuAccount.length); //Kopiert Daten des Parameter-Menüs in neues Array
        return completeArray; //Rückgabe des vervollständigten Menüs
    }


    /**
     * Abstrakte Methode
     *
     * @return Menü
     */
    public abstract String[][] returnMenu(); //returnMenu muss in Child-Klassen überschrieben werden

    /**
     * Gibt E-Mail des Accounts in die Konsole aus
     *
     */
    @Override
    public void printBasisData()
    {
        //Konsolenausgabe der E-Mail-Adresse
        System.out.print("Verkäufer: " + mail);
    }
}

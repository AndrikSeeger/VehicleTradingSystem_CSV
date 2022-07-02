package Sellers;

/**
 * Fachklasse eines Accounts
 *
 * @author 1319658
 * @version 7.3
 */

public class Account extends AccountBasis
{
    private String[][] menu = new String[2][3]; //Menü spezifisch für die Account-Daten

    private String vorname = ""; //Vorname
    private String nachname = ""; //Nachname

    /**
     * Konstruktor für Account mit allen Daten
     *
     * @pre key entspricht Ziffern
     * @params key, Nummern-Key des Accounts
     * @params mail, E-Mail-Adresse des Accounts
     * @params passwort, Passwort des Accounts
     * @params vorname, Vorname
     * @params nachname, Nachname
     */
    public Account(String key, String mail, String passwort, String vorname, String nachname)
    {
        super(mail, passwort, key); //Konstruktor der super-Klasse aufrufen
        this.vorname = vorname;
        this.nachname = nachname;
    }

    /**
     * Konstruktor für Account
     *
     */
    public Account()
    {
        super();
        setMenu(); //Setzen des Menüs
    }

    //Eigenschaften
    public String getVorname() //Zurückgeben des Vornamens
    {
        return vorname;
    }

    public void setVorname(String vorname) //Setzen des Vornamens
    {
        this.vorname = vorname;
    }

    public String getNachname() //Zurückgeben des Nachnamens
    {
        return nachname;
    }

    public void setNachname(String nachname) //Setzen des Nachnamens
    {
        this.nachname = nachname;
    }

    /**
     * Erzeugt einen String, der eine fixe Ausgabe im CSV-Format für die Klasse hat
     *
     * @return CSV-formatierte Ausgabe des Inhalts der Klasse
     * @inv Instanz bleibt gleich
     */
    @Override
    public String toCsv()
    {
        StringBuilder csv = super.BasisToCsv(); //StringBuilder mit Daten aus Super-Klasse

        csv.append(delimiter) //Anfügen der Account-spezifischen Daten
            .append(vorname)
            .append(delimiter)
            .append(nachname);

        return csv.toString(); //Zurückgeben des zusammengebauten Strings
    }

    /**
     * Erzeugt aus einem CSV-formatierten Text eine Instanz eines Accounts
     *
     * @params CSV-formatierte Parameter
     * @return Account mit Daten aus den gelesenen Parametern
     */
    @Override
    public Account fromCsv(String daten) //Instanz-Methode um mit Interface beschreiben zu können ohne Inhalt zu definieren
    {
        String[] parameter = daten.split(delimiter); //Splitten des Strings am Seperator
        return new Account (parameter[0], parameter[2], parameter[3], parameter[4], parameter[5]); //Erstellen und zurückgeben des aus den Daten erstellten Accounts
    }

    /**
     * Setzt Menü für das Erstellen eines Accounts
     *
     */
    private void setMenu()
    {
        //Setzen des Account-spezifischen Menüs
        menu[0][0] = "Vornamen eingeben (Xx): ";
        menu[1][0] = "Nachnamen eingeben (Xx): ";

        menu[0][1] = "Regex" + "[A-Z][a-z]{1,9}([\\s,-][A-Z][a-z]{1,9})?"; //Ein Großbuchtsabe und dann zwischen 1 und 9 Kleinbuchstaben, anschließend nach Space oder Bindestrich nochmals Namen mit Anforderungen vom Anfang falls Doppelnamen
        menu[1][1] = menu[0][1]; //Selbe wie bei Vornamen

        menu[0][2] = "setVorname"; //Methode zum setzen des Vornamens
        menu[1][2] = "setNachname"; //Methode zum setzen des Nachnamens
    }

    /**
     * Gibt Menü für das Erstellen eines Accounts zurück
     *
     * @return Menü
     */
    @Override
    public String[][] returnMenu()
    {
        return super.completeMenu(menu); //Zurückgeben des zusammengesetzen Menüs
    }

    /**
     * Gibt Daten des Accounts in die Konsole aus
     *
     */
    @Override
    public void printData()
    {
        printBasisData(); //Daten der super-Klasse in Konsole ausgeben
        System.out.println(" - " + nachname + ", " + vorname); //Account-spezifische Daten in Konsole ausgeben
    }
}

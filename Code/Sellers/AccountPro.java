package Sellers;

/**
 * Fachklasse eines professionellen Accounts
 *
 * @author 1319658
 * @version 7.3
 */
public class AccountPro extends AccountBasis
{
    private String[][] menu = new String[2][3]; //Menü spezifisch für die AccountPro-Daten

    private String zeiten = ""; //Öffnungszeiten
    private String firmenname = ""; //Firmenname
    private String typ = "Seller.AccountPro";

    /**
     * Konstruktor für professionellen Account mit allen Daten
     *
     * @pre key entspricht Ziffern
     * @params key, Nummern-Key des Accounts
     * @params mail, E-Mail-Adresse des Accounts
     * @params passwort, Passwort des Accounts
     * @params zeiten, Öffnungszeiten
     * @params firmenname, Name der Firma
     */
    public AccountPro(String key, String mail, String passwort, String zeiten, String firmenname)  {
        super(mail, passwort, key); //Konstruktor der super-Klasse aufrufen
        this.zeiten = zeiten;
        this.firmenname = firmenname;
    }

    /**
     * Konstruktor für professionellen Account
     *
     */
    public AccountPro()
    {
        super();
        setMenu(); //Setzen des Menüs
    }

    //Eigenschaften
    public String getZeiten() //Zurückgeben der Öffnungszeiten
    {
        return zeiten;
    }

    public void setZeiten(String zeiten) //Setzen der Öffnungszeiten
    {
        this.zeiten = zeiten;
    }

    public String getFirmenname() //Zurückgeben des Firmennamens
    {
        return firmenname;
    }

    public void setFirmenname(String firmenname) //Setzen des Firmennamens
    {
        this.firmenname = firmenname;
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

        csv.append(delimiter) //Anfügen der AccountPro-spezifischen Daten
                .append(zeiten)
                .append(delimiter)
                .append(firmenname);

        return csv.toString(); //Zurückgeben des zusammengebauten Strings
    }

    /**
     * Erzeugt aus einem CSV-formatierten Text eine Instanz eines professionellen Accounts
     *
     * @params CSV-formatierte Parameter
     * @return Account mit Daten aus den gelesenen Parametern
     */
    @Override
    public AccountPro fromCsv(String daten)
    {
        String[] parameter = daten.split(delimiter); //Splitten des Strings am Seperator
        return new AccountPro (parameter[0], parameter[2], parameter[3], parameter[4], parameter[5]); //Erstellen und zurückgeben des aus den Daten erstellten AccountPros
    }

    /**
     * Setzt Menü für das Erstellen eines professionellen Accounts
     *
     */
    private void setMenu()
    {
        //Setzen des AccountPro-spezifischen Menüs
        menu[0][0] = "Firmenname eingeben: ";
        menu[1][0] = "Öffnungszeiten eingeben (ZZ.ZZ - ZZ.ZZ): ";

        menu[0][1] = "Regex" + ".{2,32}"; //Zwischen 2 und 32 beliebige Zeichen
        menu[1][1] = "Regex" + "(([01]\\d)|([2][0-4]))(\\.[0-5]\\d)\\s\\-\\s(([01]\\d)|([2][0-4]))(\\.[0-5]\\d)"; //Form: 08.45 - 19.10

        menu[0][2] = "setFirmenname"; //Methode zum setzen des Firmennamens
        menu[1][2] = "setZeiten"; //Methode zum setzen der Öfnnungszeiten
    }

    /**
     * Gibt Menü für das Erstellen eines professionellen Accounts zurück
     *
     * @return Menü
     */
    public String[][] returnMenu()
    {
        return super.completeMenu(menu); //Zurückgeben des zusammengesetzen Menüs
    }

    /**
     * Gibt Daten des Accounts in die Konsole aus
     *
     */
    @Override
    public void printData() {
        printBasisData(); //Daten der super-Klasse in Konsole ausgeben
        System.out.println(" - " + "Firma: " + firmenname + " || Öffnungszeiten: "+ zeiten); //AccountPro-spezifische Daten in Konsole ausgeben
    }
}

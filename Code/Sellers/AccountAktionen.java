package Sellers;

import Exceptions.*;
import Sellers.Exceptions.NoUserLoggedIn;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Klasse für den Umgang mit Accounts
 *
 * @author 1319658
 * @version 3.3
 */
public class AccountAktionen
{
    /**
     * Überprüft die Existenz eines Accounts zur gegebenen E-Mail-Adresse
     *
     * @return -1 wenn kein passender Account gefunden wurde, ansonsten Key zur gegebenen Adresse
     * @params accList, ArrayList mit allen Accounts
     * @params mail, E-Mail-Adresse des gesuchten Accounts
     * @pre Nicht mehrere Accounts mit gleicher E-Mail
     * @post Account-Liste vorhanden
     * @inv Account-Liste unverändert vorhanden
     * @throws RuntimeException RuntimeException Wenn mehr als ein Account zur gegebenen E-Mail-Adresse gefunden wird
     */

    public static <T extends AccountBasis> long checkExist(ArrayList<T> accList, String mail)
    {
        long key = -1; //Wenn nicht angemeldet key = -1
        long[] keys = accList.stream()
                .filter(acc -> acc.getMail().equalsIgnoreCase(mail)) //Filtern nach gegebener E-Mail
                .mapToLong(AccountBasis::getKey) //zu Keys mappen
                .toArray(); //in Array speichern

        if(keys.length==1) //Nur ein Schlüssel wenn korrekt
        {
            key = keys[0];
        }
        else if (keys.length>1) //Fehler
        {
            //Manipulierte Daten, sollte nicht möglich sein, keine eindeutige Zuordnung möglich
            throw new RuntimeException("Fehler in Liste. Gleiche E-Mail mehrmals.");
        }
        return key; //Zurückgeben des gefundenen Keys (falls nur einer)
    }

    /**
     * Bestimmt den Key, die ID, zu einem gegebenem Account
     *
     * @return -1 wenn kein passender Account gefunden wurde, ansonsten Key zum gegebenen Account
     * @params accList, ArrayList mit allen Accounts
     * @params account, Account zum gesuchten Key
     * @pre Nicht mehrere Accounts mit gleicher E-Mail
     * @post Account-Liste vorhanden
     * @inv Account-Liste unverändert vorhanden
     * @throws RuntimeException RuntimeException Wenn mehr als ein Account zur gegebenen E-Mail-Adresse gefunden wird
     */
    public static <T extends AccountBasis> long checkExist(ArrayList<T> accList, T account)
    {
        //checkExist mit E-Mail der gegebenen Account aufrufen
        return checkExist(accList, account.getMail()); //getKey könnte auch direkt aufgerufen werden, dann erfolgt aber keine Kontrolle der Liste
    }

    /**
     * Überprüft Passwort zu gegebener ID
     *
     * @return boolean ob Passwort korrekt
     * @params accList, ArrayList mit allen Accounts
     * @params key, Key für Account bei dem Passwort kontrolliert werden soll
     * @params password, password welches kontrolliert werden soll
     * @pre Nicht mehrere Accounts mit gleicher ID-nummer(!) und gleichem Passwort
     * @post Account-Liste vorhanden
     * @inv Account-Liste unverändert vorhanden
     * @throws RuntimeException RuntimeException Wenn mehr als ein Account zur gegebenem Key und Passwort gefunden wird
     */
    public static <T extends AccountBasis> boolean checkPass(ArrayList<T> accList, long key, String password)
    {
        long numAccounts = accList.stream()
                            .filter(acc -> (acc.getKey() == key)) //Filtern nach gegebenem Key
                            .filter(acc -> (acc.getPasswort().equals(password))) //Filtern ob Passwort übereinstimmt
                            .count();

        if(numAccounts == 1) //Genau ein Account mit passendem Key und Passwort --> Korrekte Anmeldedaten
        {
            return true;
        }
        else if(numAccounts > 1) //Mehrere Accounts mit passendem Key und Passwort
        {
            //Manipulierte Daten, sollte nicht möglich sein, keine eindeutige Zuordnung möglich
            throw new RuntimeException("Fehler in Liste. Gleicher Key mit gleichem Passwort mehrmals.");
        }
        else //Kein passender Account mit Key und Passwort --> Falsche Anmeldedaten
        {
            return false;
        }
    }


    /**
     * Gibt Account zu gegebener ID zurück
     *
     * @return Gefundener Account oder null
     * @params accList, ArrayList mit allen Accounts
     * @params key, ID des gesuchten Accounts
     * @pre Nicht mehrere Accounts mit gleicher ID-Nummer
     * @post Account-Liste vorhanden
     * @inv Account-Liste unverändert vorhanden
     * @throws RuntimeException RuntimeException Wenn mehr als ein Account zur gegebenen ID-Nummer gefunden wird
     */
    public static <T extends AccountBasis> T getAcc(ArrayList<T> accList, long key)
    {
        ArrayList<T> list = new ArrayList<>(); //Liste mit Treffern
        accList.stream()
                .filter(acc -> (acc.getKey() == key)) //Filtern nach gegebenem Key
                .forEach(list::add); //Gefilterte Elemente zur Liste hinzufügen
            if(list.size()==1) //Nur ein Account wenn korrekt
            {
                return list.get(0); //Zurückgeben des gefundenen Accounts
            }
            else if (list.size()>1) //Mehrere Accounts mit passendem Key
            {
                //Manipulierte Daten, sollte nicht möglich sein, keine eindeutige Zuordnung möglich
                throw new RuntimeException("Fehler in Liste. Gleicher Key mehrmals");
            }
            return null; //Falls kein Account mit gegebenem Key gefundenen wurde
    }


    /**
     * Anmeldevorgang
     *
     * @return key des eingeloggten Accounts wenn anmeldung erfolgreich
     * @params accList, ArrayList mit allen Accounts
     * @pre Nicht mehrere Accounts mit gleicher ID-nummer oder E-Mail
     * @post Account-Liste vorhanden
     * @inv Account-Liste unverändert vorhanden
     * @throws RuntimeException RuntimeException Wenn mehr als ein Account zur gegebenen E-Mail-Adresse oder ID-Nummer gefunden wird
     * @throw AbortActionException Wenn User Eingabe abbrechen will
     */
    //Returns Key if Login successfull, throws Exception when user wants to end input
    public static <T extends AccountBasis> long login(ArrayList<T> accList) throws AbortActionException {
        Scanner scanner = new Scanner(System.in);
        String input = " ";
        long key; //Key des Accounts bei erfolgreicher Anmeldung

        System.out.println("--Anmeldevorgang--");


        while(!input.equals("")) //Abbruch wenn Enter ohne Eingabe
        {
            System.out.print("E-Mail eingeben: ");

            input = scanner.nextLine(); //Eingabe
            if((key = checkExist(accList, input)) != -1L) //Key zur eingegebenen E-Mail
            { //mail gefunden -> key != -1
                while(!input.equals("")) //Abbruch wenn Enter ohne Eingabe
                {
                    System.out.print("Passwort eingeben: ");
                    input = scanner.nextLine(); //Eingabe

                    if(checkPass(accList, key, input)) //Kontrollieren des Passworts zu gegebenem Key
                    {
                        return key; //Mail und passwort korrekt
                    }
                    else
                    {
                        System.out.println("Passwort inkorrekt!");
                    }
                }
            }
            else
            {
                System.out.println("E-Mail inkorrekt!");
            }
        }
        throw new AbortActionException(); //Nutzer will eingabe abbrechen
    }

    /**
     * Abmeldevorgang
     *
     * @return -1 als ID
     * @params keyAcc, ID die ausgeloggt werden soll
     * @pre User eingeloggt
     * @throws NoUserLoggedIn NoUserLoggedIn Wenn kein Nutzer eingeloggt
     */
    public static long logout(long keyAcc) throws NoUserLoggedIn {
        if(keyAcc != -1)
        { //Nutzer angemeldet
            System.out.println("Sie wurden ausgeloggt!");
            keyAcc = -1; //-1 entspricht abgemeldetem Nutzer
        }
        else
        { //Kein Nutzer angemeldet der ausgeloggt werden kann
            throw new NoUserLoggedIn();
        }
        return keyAcc; //gibt -1 zurück
    }

    /**
     * Prüfung ob Nutzer angemeldet
     *
     * @params keyAcc, ID kontrolliert werden soll
     * @pre User eingeloggt
     * @throws NoUserLoggedIn NoUserLoggedIn Wenn kein Nutzer eingeloggt
     */
    public static void angemeldet(long keyAcc) throws NoUserLoggedIn {
        if(keyAcc == -1L)
        { //Kein Nutzer angemeldet
            throw new NoUserLoggedIn();
        }
    }
}

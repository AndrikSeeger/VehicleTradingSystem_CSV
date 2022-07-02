package Menu;

import Exceptions.*;

import java.lang.reflect.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import java.util.function.Supplier;

/**
 * Klasse für Darstellung und Ausführung von Eingabemenüs
 *
 * @author 1319658
 * @version 5.3
 */
public class Menu
{
    /**
     * Erstellen eines neuen Objekts nach Konsoleneingaben
     *
     * @return Erstelltes Objekt
     * @params constructor, Vollständiger Name der Klasse des zu erstellenden Objekts
     * @params idSupplier, Supplier welcher ID bereitstellt
     * @params delimiter, Zeichen das bei Eingabe nicht erscheinen darf
     * @pre Klasse existiert und implementiert setKey und returnMenu
     * @post Objekt erstellt
     * @throw AbortActionException AbortActionException wenn keine Eingabe mehr erfolgt
     * @throws RuntimeException RuntimeException wenn Klasse nicht setKey oder returnMenu korrekt implementiert
     */
    public static Object createNewObject(String constructor, Supplier<Long> idSupplier, String delimiter) throws AbortActionException
    {
        //Aufruf mit nächster Zahl der Suppliers
        return createNewObject(constructor, idSupplier.get(), delimiter);
    }

    /**
     * Erstellen eines neuen Objekts nach Konsoleneingaben
     *
     * @return Erstelltes Objekt
     * @params constructor, Vollständiger Name der Klasse des zu erstellenden Objekts
     * @params idAccount, ID für das zu erstellende Objekt
     * @params delimiter, Zeichen das bei Eingabe nicht erscheinen darf
     * @pre Klasse existiert und implementiert setKey und returnMenu
     * @post Objekt erstellt
     * @throw AbortActionException wenn keine Eingabe mehr erfolgt
     * @throws RuntimeException RuntimeException wenn Klasse nicht setKey oder returnMenu korrekt implementiert
     */
    public static <T extends MenuInterface> T createNewObject(String constructor, long idAccount, String delimiter) throws AbortActionException {
        T objToCreate = null; //Objekt welches erstellt wird
        Class<T> classOfObject; //Klasse dieses Objekts
        Method methode; //Methode zum setzen der Daten

        try {classOfObject = (Class<T>) Class.forName(constructor);} //Klasse des zu erstellendes Objekts aus String
        catch(ClassNotFoundException e) //klasse nicht vorhanden
        {
            e.printStackTrace();
            throw new RuntimeException("Falscher Konstruktor-Name");
        }
        catch(ClassCastException e) //Klasse implementiert nicht das MenuInterface
        {
            e.printStackTrace();
            throw new RuntimeException("Erstelltes Objekt hat keine Menü-Funktionalität");
        }

        try {
            objToCreate = classOfObject.getDeclaredConstructor().newInstance(); //Neue Instanz der Klasse
        } catch (NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }

        try
        {
            methode = objToCreate.getClass().getMethod("setKey", Long.class); //Methode zum setzen Keys aus String
            methode.invoke(objToCreate, idAccount); //Key setzen
        }
        catch(NoSuchMethodException | IllegalAccessException | InvocationTargetException e)
        {
            e.printStackTrace();
            throw new RuntimeException("Erstelltes Objekt hat keine Methode 'setKey'");
        }


        String[][] eingabeMaske = objToCreate.returnMenu(); //Menü der instanzierten Klasse

        Scanner scanner = new Scanner(System.in);
        int n = 0;

        while(n <eingabeMaske.length) //Über gesamtes Array iterieren
        {
            System.out.println(eingabeMaske[n][0]); //Erstes Element des n-ten Elements ist immer Text der Maske
            String input = scanner.nextLine(); //Eingabe
            if(!input.equals("")) //Falls Eingabe leer bleibt --> Nutzer wünscht Eingabe abzubrechen
            {
                try
                {
                    if(checkInput(input, eingabeMaske[n][1]) && (delimiter == null || !input.contains(delimiter))) //Kontrollieren ob Eingabe den Vorgaben zur Form entpricht und nicht den Delimiter für CSV enthält oder dieser null ist
                    {
                        try
                        {
                            methode = objToCreate.getClass().getMethod(eingabeMaske[n][2], String.class); //Methode aus String des Menüs
                            methode.invoke(objToCreate, input); //Methode zum speichern der Eingabe aufrufen
                        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
                            e.printStackTrace();
                        }
                        n++; //Nächste Eingabemaske
                    }
                    else
                    {
                        System.out.println("Falsche Eingabe! Für Abbruch Enter drücken!"); //n wird nicht inkrementiert --> Gleiche Eingabemaske nochmals
                    }
                }
                catch (ParseException e) //Fehler im Menü-Aufbau
                {
                    //RuntimeException, da wenn Input nicht kontrolliert werden kann und falsch ist, getter und setter und somit der restliche Programmablauf zu Fehlern führen kann
                    e.printStackTrace();
                    throw new RuntimeException("Falscher String um Input zu kontrollieren");
                }
            }
            else //Nutzer will Eingabe abbrechen
            {
                throw new AbortActionException();
            }
        }

        //Wenn hier alle Daten korrekt eingegeben

        return objToCreate; //Zurückgeben des erstellten Objekts
    }

    /**
     * Kontrolliert String auf korrekte Form
     *
     * @return boolean ob String korrekte Form hat
     * @params input, zu kontrollierter Text
     * @params checker, Regex (und Datums-Pattern)
     * @pre checker hat korrekten Aufbau
     * @post input unverändert
     * @inv input unverändert
     * @throws ParseException ParseException falls Datums-Pattern inkorrekt
     */


    public static boolean checkInput(String input, String checker) throws ParseException {
        String kennwortRegex = "Regex"; //String an dem folgender Regex-Ausdruck erkannt wird
        String kennwortDate = "Date"; //String an dem folgendes Datum-Pattern erkannt wird
        boolean regexCorrect = true;
        boolean dateCorrect = true;

        if(checker.startsWith(kennwortRegex)) //Wenn Regex enthalten dann am Anfang
        {
            String regex;
            if(checker.contains(kennwortDate)) //Kennwort für Datum enthalten
            {
                regex = checker.substring(kennwortRegex.length(), checker.indexOf(kennwortDate)); //Wenn Date-Kennwort enthalten dann bis zum Beginn davon Regex, Start der Regex ab Ende Kennwort Regex
            }
            else
            {
                regex = checker.substring(kennwortRegex.length()); //Wenn kein Date enthalten bis zum Ende
            }

            //Prüfen ob Eingabe der Regex entspricht
            regexCorrect = input.matches(regex);
        }
        if(checker.contains(kennwortDate) && regexCorrect) //Wenn Regex falsch, Ergebnis schon Falsch
        {
            String pattern = checker.substring(checker.indexOf(kennwortDate) + kennwortDate.length()); //String nach Date-Kennwort extrahieren
            Date inputDate = new SimpleDateFormat(pattern).parse(input); //Eingabe in Datum nach gegebenem Pattern umwandeln
            dateCorrect = inputDate.before(new Date()); //Überprüfen ob eingegebenes Datum vor aktuellem Datum ist
        }

        return (regexCorrect && dateCorrect); //Nur wenn Regex und Datum korrekt Eingabe korrekt
    }
}

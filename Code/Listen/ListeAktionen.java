package Listen;

import Dateien.Csv;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.rmi.NoSuchObjectException;
import java.util.ArrayList;
import java.util.function.Consumer;
import java.util.function.Supplier;

/**
 * Klasse um Listen zu bearbeiten
 *
 * @author 1319658
 * @version 3.3
 */

public class ListeAktionen {

    /**
     * Ersetzen eines Objekts in einer Liste mit einem anderen
     *
     * @params fileWriter, Consumer welcher Änderung speichert
     * @params liste, ArrayList in der das Objekt ersetzt werden soll
     * @params oldObject, Objekt welches ersetzt werden soll
     * @params newObject, Objekt welches ersetzt
     * @pre oldObject in liste enthalten
     * @post liste existiert
     * @inv liste existiert mit gleicher Anzahl an Objekten
     * @throws NoSuchObjectException NoSuchObjectException wenn zu ersetzendes Objekt nicht in Liste vorhanden ist
     */

    public static <T extends Csv> void replaceObject(Consumer<ArrayList<String>> fileWriter, ArrayList<T> liste, T oldObject, T newObject) throws NoSuchObjectException {
        if(liste.contains(oldObject)) //Prüft ob zu entfernendes Objekt in Liste enthalten ist
        { //Objekt enthalten
            liste.set(liste.indexOf(oldObject), newObject); //Altes Object durch Neues ersetzen
            safeList(fileWriter, liste); //Speichern der geänderten Liste
        }
        else
        { //Objekt nicht enthalten
            throw new NoSuchObjectException("Zu ersetzendes Objekt nicht in Liste enthalten");
        }
    }

    /**
     * Löschen eines Objekts in einer Liste
     *
     * @params fileWriter, Consumer welcher Änderung speichert
     * @params liste, ArrayList in der das Objekt gelöscht werden soll
     * @params objToDelete, Objekt welches gelöscht werden soll
     * @pre objToDelete in liste enthalten
     * @post liste existiert mit einem Objekt weniger
     * @inv liste existiert
     * @throws NoSuchObjectException NoSuchObjectException wenn zu löschendes Objekt nicht in Liste vorhanden ist
     */

    public static <T extends Csv> void deleteObject(Consumer<ArrayList<String>> fileWriter, ArrayList<T> liste, T objectToDelete) throws NoSuchObjectException {
        if(liste.contains(objectToDelete)) //Prüft ob zu entfernendes Objekt in Liste enthalten ist
        { //Objekt enthalten
            liste.remove(objectToDelete); //Objekt aus Liste entfernen
            safeList(fileWriter, liste); //Speichern der geänderten Liste
        }
        else
        { //Objekt nicht enthalte
            throw new NoSuchObjectException("Zu löschendes Objekt nicht in Liste enthalten");
        }
    }

    /**
     * Löschen von Objekten in einer Liste
     *
     * @params fileWriter, Consumer welcher Änderung speichert
     * @params liste, ArrayList in der das Objekt gelöscht werden soll
     * @params elementsToDelete, ArrayList mit Objekten welche gelöscht werden sollen
     * @post liste existiert mit maximal der gleichen Anzahl an Objekten
     * @inv liste existiert
     */

    public static <T extends Csv> void deleteObjects(Consumer<ArrayList<String>> fileWriter, ArrayList<T> liste, ArrayList<T> elementsToDelete)
    {
        if(liste.removeAll(elementsToDelete)) //Prüfen ob Elemente gelöscht wurden
        { //Elemente wurden gelöscht
            safeList(fileWriter, liste); //Speichern der geänderten Liste
        }
    }


    /**
     * Hinzufügen eines Objekts zu einer Liste
     *
     * @params fileWriter, Consumer welcher Änderung speichert
     * @params liste, ArrayList zu der das Objekt hinzugefügt werden soll
     * @params newObject, Objekt welches hinzugefügt werden soll
     * @post liste existiert mit einem zusätzlichen Objekt
     * @inv liste existiert
     */
    public static <T extends Csv> void addObject (Consumer<ArrayList<String>> fileWriter, ArrayList<T> liste, T newObject) //Account-Object hinzufügen zur Liste und speichern
    {
        liste.add(newObject); //Neues Objekt zur Liste hinzufügen
        safeList(fileWriter, liste); //Speichern der geänderten Liste
    }


    /**
     * Sichern in einer Liste als CSV
     *
     * @params fileWriter, Consumer welcher Inhalt speichert
     * @params liste, ArrayList welche gespeichert werden soll
     * @pre Kein null-Element in Liste
     * @post Objekt-Daten als CSV an Consumer übergeben
     * @inv liste existiert
     * @throws NullPointerException NullPointerException falls Element null ist in Liste
     */

    public static void safeList(Consumer<ArrayList<String>> fileWriter, ArrayList<? extends Csv> liste)
    {
        if(!liste.contains(null)) //Prüfen ob null-Element enthalten ist
        { //Kein null-Element
            ArrayList<String> zeilen = new ArrayList<>();

            for (Csv account : liste) //Daten jedes Objekts in csv-String umwandeln und in neue Liste speichern
            {
                zeilen.add(account.toCsv());
            }
            fileWriter.accept(zeilen); //Alle Zeilen an Consumer übergeben
        }
        else
        { //Liste enthält null-Element
            throw new NullPointerException("Element in Liste ist null");
        }

    }

    /**
     * Liste mit Objekten aus CSV-Strings erstellen
     *
     * @return ArrayList mit erstellten Objekten aus CSV-Daten
     * @params fileReader, Supplier welcher Strings liefert
     * @params delimiter, Character welche Werte trennt
     * @pre Strings entsprechen vorgegebenem Aufbau
     * @post Liste erstellt
     * @throws RuntimeException RuntimeException Bei falschem Aufbau der Strings
     */

    public static <T extends Csv> ArrayList<T> createList(Supplier<ArrayList<String>> fileReader, String delimiter){
        //Durch die Java Reflection Funktionalität muss das Erstellen einer Liste aus einer csv-Datei nicht für mehrere Daten-Typen implementiert werden sondern bleibt allgemein und SOLID-entsprechend

        T objToCreate = null; //Objekt-Puffer
        Class<T> classOfObject; //Klasse des zu erstellenden Objekts
        Constructor<T> constructor; //Konstruktor für dieses Objekt
        ArrayList<T> allObjects = new ArrayList<>(); //Liste welche mit Objekten gefüllt wird
        Method methode; //Methode zum sichern der Daten ins Objekt

        ArrayList<String> zeilen = fileReader.get(); //Zeilen aus Supplier erhalten
        for (String s : zeilen)  //Für jede Zeile
        {
            String constructorName = s.split(delimiter)[1]; //Vollst. Name des Objekts ist zweites Daten-Element in csv-String

            try {classOfObject = (Class<T>) Class.forName(constructorName);} //Klasse aus String
            catch(ClassNotFoundException e)
            {
                e.printStackTrace();
                throw new RuntimeException("Falscher Konstruktor-Name");
            }
            catch(ClassCastException e)
            {
                e.printStackTrace();
                throw new RuntimeException("Erstelltes Objekt hat keine csv-Funktionalität");
            }


            try{constructor = classOfObject.getConstructor();} //Konstruktor zur erstellten Klasse
            catch(NoSuchMethodException e)
            {
                e.printStackTrace();
                throw new RuntimeException("Kein Konstruktor in der Klasse");
            }


            try{objToCreate = constructor.newInstance();} //Instanz der Klasse erstellen
            catch (IllegalAccessException | InvocationTargetException | InstantiationException e) {
                e.printStackTrace();
                throw new RuntimeException(e);
            }


            try
            {
                methode = classOfObject.getMethod("fromCsv", String.class); //Methode fromCsv definieren, welche Daten speichern soll
                objToCreate = (T) methode.invoke(objToCreate, s); //Aufrufen der fromCsv-Methode welche ein Objekt mit den gegebenen Daten zurückliefert
            }
            catch(NoSuchMethodException | IllegalAccessException | InvocationTargetException e)
            {
                e.printStackTrace();
                throw new RuntimeException("Erstelltes Objekt hat keine Methode 'fromCsv'");
            }
            catch(ClassCastException e)
            {
                e.printStackTrace();
                throw new RuntimeException("Falscher Return-Typ");
            }
                allObjects.add(objToCreate); //Erstelltes Objekt zur Liste hinzufügen

        }

        return allObjects; //Liste mit erstellten Objekten zurückgeben
    }
}

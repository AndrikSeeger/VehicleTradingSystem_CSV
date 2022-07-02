package Vehicles;

import java.lang.reflect.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import java.util.function.Predicate;

/**
 * Klasse um Filter zu erstellen
 *
 * @author 1319658
 * @version 6.3
 */
public class FahrzeugFilter {

    /**
     * Filter nach Anforderung erstellen
     *
     * @return Predicate mit gesetzten Filter-Parametern
     * @params getDataToCompare, Methode welche Daten nach denen gefiltert werden soll zurückgibt
     * @params vehicleClass, Klasse der Fahrzeuge die gefiltert werden sollen
     * @pre getDataToCompare ist Methode der gegebenen vehicleClass und gibt Integer, Double, Date oder String zurück
     * @post Predicate erstellt
     * @throws RuntimeException RuntimeException Wenn Return-Typ der gegebenen Methode nicht Integer, Double, Date oder String entspricht oder Methode nicht korrekt aufgerufen werden kann
     */


    public static Predicate<FahrzeugBasis> getFilter(String getDataToCompare, Class<? extends FahrzeugBasis> vehicleClass) {

        String input = "";
        Date minDate = null; //Minimales Datum
        Date maxDate = null; //Maximales Datum
        int min = -1; //Minimum Zahl
        int max = -1; //Maximum Zahl
        Double minDouble = -1.0; //Minimum Kommazahl
        Double maxDouble = -1.0; //Maximum Kommazahl
        Method methode = null; //Methode welche Wert zum Filtern liefert

        Predicate<FahrzeugBasis> specificPredicate = fz -> true; //Initialisierung falls kein Filter-Wert gesetzt wird

        Scanner sc = new Scanner(System.in);

        try {
            methode = vehicleClass.getMethod(getDataToCompare); //Methode aus String für gegebene Fahrzeugklasse

            Class retType = methode.getReturnType(); //Rückgabe-Typ der Methode (Double, Integer, String, Date,...)

            Predicate<FahrzeugBasis> classCompare = fz -> (fz.getClass() == vehicleClass); //Filter für Fahrzeugklasse wird nach gegebener Klasse gesetzt (Entweder nur Lkw, nur Autos,...)

            switch (retType.getSimpleName()) {
                case "Double": //Double muss gefiltert werden
                    try {
                        System.out.print("Minimum (Dezimalzahl) eingeben: ");
                        minDouble = Double.parseDouble(input = sc.nextLine()); //Eingabe
                    } catch (java.lang.NumberFormatException e) {
                        if (!input.equals("*")) { //Wenn Stern -> Auslassen eines Wertes -> Kein Limit
                            System.out.println("Fehlerhafte Eingabe!");
                        }
                    }
                    System.out.print("Maximum (Dezimalzahl) eingeben: ");
                    try {
                        maxDouble = Double.parseDouble(input = sc.nextLine()); //Eingabe
                    } catch (java.lang.NumberFormatException e) {
                        if (!input.equals("*")) { //Wenn Stern -> Auslassen eines Wertes -> Kein Limit
                            System.out.println("Fehlerhafte Eingabe!");
                        }
                    }

                    Method finalMethode3 = methode; //Methode welche Doubles zum Filtern liefert
                    Double finalMinDouble = minDouble; //Eingegebner Minimalwert
                    Double finalMaxDouble = maxDouble; //Eingegebener Maximalwert

                    Predicate<FahrzeugBasis> biggerEqualsDouble = fz -> {
                        try {
                            return ((Double) finalMethode3.invoke(fz) >= finalMinDouble); //Ruft Methode auf und Vergleicht Rückgabe-Wert mit gesetztem Minimum
                        } catch (IllegalAccessException | InvocationTargetException e) {
                            throw new RuntimeException("Fehler");
                        }
                    };

                    Predicate<FahrzeugBasis> smallerEqualsDouble = fz -> {
                        try {
                            return ((Double) finalMethode3.invoke(fz) <= finalMaxDouble); //Ruft Methode auf und Vergleicht Rückgabe-Wert mit gesetztem Maximum
                        } catch (IllegalAccessException | InvocationTargetException e) {
                            throw new RuntimeException("Fehler");
                        }
                    };
                    specificPredicate = combinePredicates(minDouble, maxDouble, biggerEqualsDouble, smallerEqualsDouble); //Kombiniert Filter Minimum und Maximum je nachdem ob diese gesetzt wurden
                    break;
                case "int":
                    System.out.print("Minimum (Ganzzahl) eingeben: ");
                    try {
                        min = Integer.parseInt(input = sc.nextLine()); //Eingabe
                    } catch (java.lang.NumberFormatException e) {
                        if (!input.equals("*")) { //Wenn Stern -> Auslassen eines Wertes -> Kein Limit
                            System.out.println("Fehlerhafte Eingabe!");
                        }
                    }

                    System.out.print("Maximum (Ganzzahl) eingeben: ");
                    try {
                        max = Integer.parseInt(input = sc.nextLine()); //Eingabe
                    } catch (java.lang.NumberFormatException e) {
                        if (!input.equals("*")) { //Wenn Stern -> Auslassen eines Wertes -> Kein Limit
                            System.out.println("Fehlerhafte Eingabe!");
                        }
                    }

                    Method finalMethode = methode; //Methode welche Integer zum Filtern liefert
                    int finalMin = min; //Eingegebner Minimalwert
                    int finalMax = max; //Eingegebener Maximalwert

                    Predicate<FahrzeugBasis> biggerEquals = fz -> {
                        try {
                            return ((Integer) finalMethode.invoke(fz) >= finalMin); //Ruft Methode auf und Vergleicht Rückgabe-Wert mit gesetztem Minimum
                        } catch (IllegalAccessException | InvocationTargetException e) {
                            throw new RuntimeException("Fehler");
                        }
                    };
                    Predicate<FahrzeugBasis> smallerEquals = fz -> {
                        try {
                            return ((Integer) finalMethode.invoke(fz) <= finalMax); //Ruft Methode auf und Vergleicht Rückgabe-Wert mit gesetztem Maximum
                        } catch (IllegalAccessException | InvocationTargetException e) {
                            throw new RuntimeException("Fehler");
                        }
                    };
                    specificPredicate = combinePredicates(min, max, biggerEquals, smallerEquals); //Kombiniert Filter Minimum und Maximum je nachdem ob diese gesetzt wurden
                    break;
                case "Date":
                    System.out.print("Jahr ab: ");
                    try {
                        min = Integer.parseInt(input = sc.nextLine()); //Eingabe
                    } catch (java.lang.NumberFormatException e) {
                        if (!input.equals("*")) { //Wenn Stern -> Auslassen eines Wertes -> Kein Limit
                            System.out.println("Fehlerhafte Eingabe!");
                        }
                    }
                    System.out.print("Jahr bis: ");
                    try {
                        max = Integer.parseInt(input = sc.nextLine()); //Eingabe
                    } catch (java.lang.NumberFormatException e) {
                        if (!input.equals("*")) { //Wenn Stern -> Auslassen eines Wertes -> Kein Limit
                            System.out.println("Fehlerhafte Eingabe!");
                        }
                    }

                    Method finalMethode1 = methode; //Methode welche Datum zum Filtern liefert
                    int finalMin1 = min;//Eingegebner Minimalwert
                    int finalMax1 = max;//Eingegebener Maximalwert
                    Predicate<FahrzeugBasis> after = fz -> {
                        try {


                            //Date dateVehicle = (Date) finalMethode1.invoke(fz);
                            //return dateVehicle.before(finalMinDate);
                            int year = Integer.parseInt(new SimpleDateFormat("yyyy").format(finalMethode1.invoke(fz))); //Ruft Methode auf und Vergleicht Jahreszahl des Rückgabe-Datums mit gesetztem Minimum
                            return (year >= finalMin1);
                        } catch (IllegalAccessException | InvocationTargetException e) {
                            throw new RuntimeException("Fehler");
                        }
                    };

                    Predicate<FahrzeugBasis> before = fz -> {
                        try {
                            //Date dateVehicle = (Date) finalMethode1.invoke(fz);
                            //return dateVehicle.before(finalMaxDate);
                            int year = Integer.parseInt(new SimpleDateFormat("yyyy").format(finalMethode1.invoke(fz))); //Ruft Methode auf und Vergleicht Jahreszahl des Rückgabe-Datums mit gesetztem Maximum
                            return (year <= finalMax1);
                        } catch (IllegalAccessException | InvocationTargetException e) {
                            throw new RuntimeException("Fehler");
                        }
                    };

                    specificPredicate = combinePredicates(min, max, after, before); //Kombiniert Filter Minimum und Maximum je nachdem ob diese gesetzt wurden
                    break;
                case "String":
                    System.out.print("Begriff eingeben: ");
                    input = sc.next(); //Eingabe

                    if (!input.equals("*")) { //Wenn Stern -> Auslassen eines Wertes -> Kein Limit
                        Method finalMethode2 = methode;
                        String finalInput = input;

                        Predicate<FahrzeugBasis> stringCompare = fz -> {
                            try {
                                String dataVehicle = (String) finalMethode2.invoke(fz);
                                return dataVehicle.toLowerCase().contains(finalInput.toLowerCase()); //Ruft Methode auf und Vergleicht ob Begriff enthalten ist
                            } catch (IllegalAccessException | InvocationTargetException e) {
                                throw new RuntimeException("Fehler");
                            }
                        };
                        specificPredicate = stringCompare;
                    } else {
                        specificPredicate = fz -> true; //Kein Wert gesetzt
                    }

                    break;
                default: //Anderer Typ als Integer, Double, Date oder String
                    throw new RuntimeException("Falscher Return Typ. Kann nicht verglichen werden.");
            }


            return classCompare.and(specificPredicate); //Klasse der Objekte wird immer gefiltert + zusätzlicher Filter (Min,Max oder Begriff)

        }
        catch(NoSuchMethodException e) //Kein Filter, da falsche Methode
        {
            e.printStackTrace();
            return fz -> true; //Keine zusätzliche Filterung
        }
    }


    /**
     * Verknüpfen von Filtern je nach gesetzten Werten
     *
     * @return Predicate mit gesetzten Filter-Parametern
     * @params min, Minimalwert
     * @params max, Maximalwert
     * @params greater, Predicate welches auf größer vergleicht
     * @params smaller, Predicate welches auf kleiner vergleicht
     * @post Predicate erstellt
     */
    private static Predicate<FahrzeugBasis> combinePredicates(int min, int max, Predicate<FahrzeugBasis> greater, Predicate<FahrzeugBasis> smaller) {
        //Wenn Werte -1 -> Initialwert -> Nicht gesetzt

        if(min != -1)
        { //Min gesetzt
            if(max != -1)
            { //Max gesetzt
                return greater.and(smaller); //Kombination von Minimalfilter und Maximalfilter
            }
            else
            { //Max nicht gesetzt
                return greater; //Minimalfilter
            }
        }
        else
        { //Min nicht gesetzt
            if(max != -1)
            { //Max gesetzt
                return smaller; //Maximalfilter
            }
            else
            { //Max nicht gesetzt
                return fz -> true; //Kein Filter
            }
        }
    }

    /**
     * Verknüpfen von Filtern je nach gesetzten Werten
     *
     * @return Predicate mit gesetzten Filter-Parametern
     * @params min, Minimalwert
     * @params max, Maximalwert
     * @params greater, Predicate welches auf größer vergleicht
     * @params smaller, Predicate welches auf kleiner vergleicht
     * @post Predicate erstellt
     */
    private static Predicate<FahrzeugBasis> combinePredicates(Double min, Double max, Predicate<FahrzeugBasis> greater, Predicate<FahrzeugBasis> smaller)
    { //Umwandlung der Double-Werte in Integer-Werte
        return combinePredicates(min.intValue(), max.intValue(), greater, smaller);
    }
}


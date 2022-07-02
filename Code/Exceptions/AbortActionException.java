package Exceptions;

/**
 * Eingabe abbrechen
 *
 */

public class AbortActionException extends Exception
{

    public AbortActionException() {
            super("Abbruch der aktuellen Aktion");
        } //Wird geworfen um aktuelle Eingabe-Aktion abzubrechen

}


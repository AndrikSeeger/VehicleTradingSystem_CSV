package Menu;

/**
 * Interface für Eingabe-Menü
 *
 * @author 1319658
 * @version 2.3
 */
public interface MenuInterface
{
    //Aufbau Menu: String[][0]: Textausgabe, String[][1] Regex und/oder Datumspattern, String[][2] Methode zum speichern des Inputs

    /**
     * Abstrakte Methode um Menu-Daten zurückzugeben
     *
     * @return Menu
     */
    public String[][] returnMenu(); //Menü welches Textausgabe, Eingabe-Bedingungen und Methode zum speichern der Daten beinhaltet zurückgeben

    /**
     * Abstrakte Methode um Menu-Daten zu setzen
     *
     */
    private void setMenu() {} //Menü setzen
}

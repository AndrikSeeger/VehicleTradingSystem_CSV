package Dateien;
import java.io.*;
import java.nio.file.NoSuchFileException;
import java.util.ArrayList;
import java.util.function.Consumer;
import java.util.function.Supplier;

/**
 * Klasse für einen DataFile-Reader/Writer
 *
 * @author 1319658
 * @version 2.3
 */

public class DataFile implements Consumer<ArrayList<String>>, Supplier<ArrayList<String>>
{

    private File csvFile; //Datei in die geschrieben / von der gelesen werden soll

    /**
     * Konstruktor für DataFile
     *
     * @pre Datei existiert
     * @post Datei exsitiert
     * @inv Datei existiert
     * @params csvFile Datei welche bearbeitet werden soll
     * @throws NoSuchFileException NoSuchFileException NoSuchFileException wenn keine Datei an gegebenem Pfad
     */
    public DataFile(File csvFile) throws NoSuchFileException
    {
        this.csvFile = csvFile; //Speichert beim Instanzieren zu verwendendes File
        if(!csvFile.exists()) //Falls Datei nicht existiert -> Exception
        {
            System.out.println("Keine Datei an gegebenem Pfad");
            throw new NoSuchFileException("Falscher oder kein Pfad");
        }
    }

    /**
     * Erstellen einer neuen Datei wenn diese nicht bereits vorhanden ist
     *
     * @return boolean ob neue Datei erstellt wurde
     * @params dataFile, Datei welche erstellt werden soll
     * @params message, Nachricht die beim erstellend ausgegeben werden soll
     */

    public static boolean createFile(File dataFile, String message)
    {
        try{
            if (dataFile.createNewFile()) //Neue Datei versuchen zu erstellen
            { //Neue Datei erstellt
                System.out.println(message); //Nachricht wenn neue Datei erstellt
                return true;
            }
        }
        catch(IOException e) {
            e.printStackTrace();
        }
        return false; //Keine neue Datei erstellt
    }

    /**
     * Schreiben von Strings aus einer ArrayList in Datei
     *
     * @params ArrayList, mit Zeilen welche geschrieben werden sollen
     * @pre Datei existiert
     * @post Datei existiert
     * @inv Datei existiert
     * @throws RuntimeException RuntimeException wenn Datei nicht vorhanden
     */

    @Override
    public void accept(ArrayList<String> lines) //Consumer
    {
        try(BufferedWriter writer = new BufferedWriter(new FileWriter(csvFile))) //Neuen Schreiber erstellen für Datei
        {
            for (String line : lines) //Jede Zeile der Liste in Datei schreiben
            {
                writer.write(line);
                writer.newLine();
            }
        }
        catch(IOException e) //Falls Datei zwischen Instanzierung und Methodenaufruf gelöscht wird
        {
            e.printStackTrace();
            throw new RuntimeException("Datei konnte nicht gefunden werden");
        }
    }

    /**
     * Gibt Inhalt der Datei zeilenweise in einer ArrayList zurück
     *
     * @return Zeilen der Datei als ArrayList
     * @pre Datei existiert
     * @post Datei existiert mit unverändertem Inhalt
     * @inv Datei existiert
     */

    @Override
    public ArrayList<String> get() //Supplier
    {
        String zeile;
        ArrayList<String> zeilen = new ArrayList<>(); //Liste in die gelesene Zeilen gespeichert werden

        try(BufferedReader reader = new BufferedReader(new FileReader(csvFile))) //Neuen Leser erstellen für Datei
        {
            while((zeile = reader.readLine()) != null) //Wenn null -> Ende der Datei
            {
                zeilen.add(zeile); //Jede gelesene Zeile zu Liste hinzufügen
            }
        }
        catch(IOException e) //Falls Datei zwischen Instanzierung und Methodenaufruf gelöscht wird
        {
            e.printStackTrace();
            throw new RuntimeException("Datei konnte nicht gefunden werden");
        }

        return zeilen;
    }
}

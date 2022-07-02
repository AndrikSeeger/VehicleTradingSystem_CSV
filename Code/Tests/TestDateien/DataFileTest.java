package TestDateien;

import Dateien.DataFile;
import org.junit.After;
import org.junit.Test;

import java.io.File;
import java.nio.file.NoSuchFileException;

import static org.junit.Assert.*;

/**
 * Überprüfen der DataFile-Klasse
 *
 * @author 1319658
 * @version 1.1
 */
public class DataFileTest {

    File testFile1 = new File("test");
    File testFile2 = new File("./test1.csv");
    File testFile3 = new File("");

    /**
     * Löschen der erstellten Dateien
     *
     */
    @After
    public void delete()
    {
        testFile1.delete();
        testFile2.delete();
        testFile3.delete();
    }

    /**
     * Überprüft Erstellen einer Datei
     *
     */
    @Test
    public void createFile()
    {
        boolean correct = false;
        try
        {
            DataFile df = new DataFile(testFile1);
        }
        catch(NoSuchFileException e)
        {
            correct = true;
        }

        assertTrue(correct);
        assertTrue(DataFile.createFile(testFile2, ""));
        assertFalse(DataFile.createFile(testFile3, ""));

    }


}
package TestListen;

import Listen.*;
import Dateien.*;

import Sellers.Account;
import Sellers.AccountBasis;
import Sellers.IdNummer;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.nio.file.NoSuchFileException;
import java.util.ArrayList;

import static Dateien.Delimiter.delimiter;
import static org.junit.Assert.*;

/**
 * Überprüfen der ListeAktionen-Klasse
 *
 * @author 1319658
 * @version 1.1
 */
public class ListeAktionenTest {

    private DataFile testWriter;

    /**
     * Erstellt Test-Dateien
     *
     */
    @Before
    public void fileInit() throws IOException {
        File testFile = new File("." + File.separator + "testFile.csv");
        testFile.createNewFile();
        testWriter = new DataFile(testFile);
    }

    /**
     * Überprüft Ersetzen eines Objekts in einer Liste
     *
     */
    @Test
    public void replaceObject() throws IOException {
        ArrayList<AccountBasis> testList = new ArrayList<>();

        IdNummer testObj1 = new IdNummer();
        testList.add(testObj1);
        ListeAktionen.replaceObject(testWriter, testList, testObj1, new Account());
        assertNotSame(testObj1, testList.get(0));

    }

    /**
     * Überprüft Löschen eines Objekts in einer Liste
     *
     */
    @Test
    public void deleteObject() throws IOException {
        ArrayList<AccountBasis> testList = new ArrayList<>();

        IdNummer testObj1 = new IdNummer();
        testList.add(testObj1);
        ListeAktionen.deleteObject(testWriter, testList, testObj1);
        assert(!testList.contains(testObj1));
    }

    /**
     * Überprüft Löschen von Objekten in einer Liste
     *
     */
    @Test
    public void deleteObjects() {
        ArrayList<AccountBasis> testList = new ArrayList<>();
        ArrayList<AccountBasis> testList2 = new ArrayList<>();

        IdNummer testObj1 = new IdNummer();
        testList.add(testObj1);
        testList2.add(testObj1);
        ListeAktionen.deleteObjects(testWriter, testList, testList2);
        assert(!testList.contains(testObj1));
    }

    /**
     * Überprüft hinzufügen eines Objekts zu einer Liste
     *
     */
    @Test
    public void addObject() {
        ArrayList<AccountBasis> testList = new ArrayList<>();

        IdNummer testObj1 = new IdNummer();
        ListeAktionen.addObject(testWriter, testList, testObj1);
        assert(testList.contains(testObj1));
    }


    /**
     * Überprüft Erstellen einer Liste
     *
     */
    @Test
    public void createList() throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        addObject();
        ListeAktionen.createList(testWriter, delimiter);
        assertEquals("IdNummer", ListeAktionen.createList(testWriter, delimiter).get(0).getClass().getSimpleName());
    }

}
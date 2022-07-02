package TestSellers;

import Sellers.IdNummer;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Überprüfen der IdNummer-Klasse
 *
 * @author 1319658
 * @version 1.1
 */
public class IdNummerTest {

    private IdNummer idTest;

    /**
     * Erstellen einer Test-IdNummer
     *
     */
    @Before
    public void init() {
        idTest = new IdNummer(5L);
    }

    @Test
    public void nextNumber() {
        assert(6 == idTest.nextNumber());
    }

    @Test
    public void returnMenu() {
        assertArrayEquals(new String[0][], idTest.returnMenu());
    }

    @Test
    public void get() {
        assert(6 == idTest.get());
    }
}
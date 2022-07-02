package TestMenu;

import Exceptions.*;
import Menu.Menu;
import Sellers.Account;
import Sellers.IdNummer;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.nio.charset.StandardCharsets;
import java.text.ParseException;

import static Dateien.Delimiter.delimiter;
import static org.junit.Assert.*;

/**
 * Überprüfen der Menu-Klasse
 *
 * @author 1319658
 * @version 1.1
 */
public class MenuTest {

    /**
     * System.in simulieren
     *
     */
    @Before
    public void init()
    {
        String inputData = "test.test@gmx.de\n!?Test12\nVorname\nNachname";
        InputStream testInput = new ByteArrayInputStream(inputData.getBytes(StandardCharsets.UTF_8));//sb.toString().getBytes(StandardCharsets.UTF_8)
        System.setIn(testInput);
    }

    /**
     * System.in zurücksetzen
     *
     */
    @After
    public void after()
    {
        System.setIn(System.in);
    }

    /**
     * Überprüft erstellen eines Objekts
     *
     */
    @Test
    public void createNewObjectSupplier() throws AbortActionException {
        IdNummer idSupp = new IdNummer(4L);
        Account testAcc = (Account) Menu.createNewObject("Sellers.Account", idSupp, delimiter);
        assertEquals(5L, testAcc.getKey());
        assertEquals("test.test@gmx.de", testAcc.getMail());
        assertEquals("!?Test12", testAcc.getPasswort());
        assertEquals("Vorname", testAcc.getVorname());
        assertEquals("Nachname", testAcc.getNachname());
    }

    /**
     * Überprüft erstellen eines Objekts
     *
     */
    @Test
    public void createNewObjectId() throws AbortActionException{

        Account testAcc = Menu.createNewObject("Sellers.Account", 5L, delimiter);
        assertEquals(5L, testAcc.getKey());
        assertEquals("test.test@gmx.de", testAcc.getMail());
        assertEquals("!?Test12", testAcc.getPasswort());
        assertEquals("Vorname", testAcc.getVorname());
        assertEquals("Nachname", testAcc.getNachname());
    }

    /**
     * Überprüft Exception
     *
     */
    @Test
    public void abortException() throws IllegalAccessException, ParseException, InstantiationException, AbortActionException, NoSuchMethodException, InvocationTargetException, ClassNotFoundException {
        boolean correct = false;

        String inputData = "\n";
        InputStream testInput = new ByteArrayInputStream(inputData.getBytes(StandardCharsets.UTF_8));//sb.toString().getBytes(StandardCharsets.UTF_8)
        System.setIn(testInput);
        try
        {
            Account testAcc = Menu.createNewObject("Sellers.Account", 5L, delimiter);
        }
        catch(AbortActionException e)
        {
            correct = true;
        }
        assertTrue(correct);
    }

    /**
     * Überprüft Input-Kontrolle
     *
     */
    @Test
    public void checkInput() throws ParseException {
        assertTrue(Menu.checkInput("05/2016", "Regex"+ "(([0][1-9])|([1][0-2]))\\/((1([8]|[9])\\d{2})|([2-9]\\d{3}))" + "Date" + "MM/yyyy"));
        assertTrue(Menu.checkInput("05/2016", "Regex"+ "(([0][1-9])|([1][0-2]))\\/((1([8]|[9])\\d{2})|([2-9]\\d{3}))"));
    }
}
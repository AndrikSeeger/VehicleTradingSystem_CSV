package TestSellers;

import Sellers.Account;
import Sellers.AccountPro;
import org.junit.Before;
import org.junit.Test;

import Sellers.*;
import static org.junit.Assert.*;

/**
 * Überprüfen der Account-Klasse
 *
 * @author 1319658
 * @version 1.1
 */
public class AccountTest {

    private Account testAcc;

    /**
     * Erstellen eines Test-Accounts
     *
     */
    @Before
    public void init()
    {
        testAcc = new Account();
    }


    @Test
    public void getVorname() {
        testAcc.setVorname("test123");
        assertEquals("test123", testAcc.getVorname());
    }

    @Test
    public void setVorname() {
        getVorname();
    }

    @Test
    public void getNachname() {
        testAcc.setNachname("test123");
        assertEquals("test123", testAcc.getNachname());
    }

    @Test
    public void setNachname() {
        getNachname();
    }

    @Test
    public void toCsv() {
        String daten = "1;Sellers.Account;email;passwort;vorname;nachname";
        testAcc = testAcc.fromCsv(daten);
        assertEquals(daten, testAcc.toCsv());
    }

    @Test
    public void fromCsv() {
        toCsv();
    }

    @Test
    public void returnMenu()
    {
        String[][] menu = new String[4][3];
        menu[0][0] = "E-Mail eingeben (X.X@X.de / XX@X.com): ";
        menu[1][0] = "Passwort setzen (Mindestens 1 Kleinbuchst., 1 Großbuchst., 2 Ziffern, 2 Sonderzeichen): ";

        menu[0][1] = "Regex" + "\\w+" + "\\.?" + "\\w+" + "@" + "\\w+" + "\\." + "(([dD][eE])|([cC][oO][mM]))";
        menu[1][1] = "Regex" + "^(?=(.*\\d){2,})(?=.*[a-z])(?=.*[A-Z])(?=(.*[\\W]){2,}).{8,16}$";

        menu[0][2] = "setMail";
        menu[1][2] = "setPasswort";

        menu[2][0] = "Vornamen eingeben (Xx): ";
        menu[3][0] = "Nachnamen eingeben (Xx): ";

        menu[2][1] = "Regex" + "[A-Z][a-z]{1,9}([\\s,-][A-Z][a-z]{1,9})?";
        menu[3][1] = menu[2][1];

        menu[2][2] = "setVorname";
        menu[3][2] = "setNachname";
        assertArrayEquals(menu, testAcc.returnMenu());
    }
}
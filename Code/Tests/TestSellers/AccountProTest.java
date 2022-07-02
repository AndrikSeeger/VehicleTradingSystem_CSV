package TestSellers;

import Sellers.Account;
import Sellers.AccountPro;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Überprüfen der AccountPro-Klasse
 *
 * @author 1319658
 * @version 1.1
 */
public class AccountProTest {

    private AccountPro testAcc;

    /**
     * Erstellen eines Test-Accounts
     *
     */
    @Before
    public void init()
    {
        testAcc = new AccountPro();
    }


    @Test
    public void getZeiten() {
        testAcc.setZeiten("test123");
        assertEquals("test123", testAcc.getZeiten());
    }

    @Test
    public void setZeiten() {
        getZeiten();
    }

    @Test
    public void getFirmenname() {
        testAcc.setFirmenname("test123");
        assertEquals("test123", testAcc.getFirmenname());
    }

    @Test
    public void setFirmenname() {
        getFirmenname();
    }

    @Test
    public void toCsv() {
        String daten = "1;Sellers.AccountPro;email;passwort;zeiten;firmenname";
        testAcc = testAcc.fromCsv(daten);
        assertEquals(daten, testAcc.toCsv());
    }

    @Test
    public void fromCsv() {
        toCsv();
    }

    @Test
    public void returnMenu() {
        String[][] menu = new String[4][3];
        menu[0][0] = "E-Mail eingeben (X.X@X.de / XX@X.com): ";
        menu[1][0] = "Passwort setzen (Mindestens 1 Kleinbuchst., 1 Großbuchst., 2 Ziffern, 2 Sonderzeichen): ";

        menu[0][1] = "Regex" + "\\w+" + "\\.?" + "\\w+" + "@" + "\\w+" + "\\." + "(([dD][eE])|([cC][oO][mM]))";
        menu[1][1] = "Regex" + "^(?=(.*\\d){2,})(?=.*[a-z])(?=.*[A-Z])(?=(.*[\\W]){2,}).{8,16}$";

        menu[0][2] = "setMail";
        menu[1][2] = "setPasswort";

        menu[2][0] = "Firmenname eingeben: ";
        menu[3][0] = "Öffnungszeiten eingeben (ZZ.ZZ - ZZ.ZZ): ";

        menu[2][1] = "Regex" + ".{2,32}"; //Zwischen 2 und 32 beliebige Zeichen
        menu[3][1] = "Regex" + "(([01]\\d)|([2][0-4]))(\\.[0-5]\\d)\\s\\-\\s(([01]\\d)|([2][0-4]))(\\.[0-5]\\d)"; //Form: 03.29 - 19.10

        menu[2][2] = "setFirmenname";
        menu[3][2] = "setZeiten";
        assertArrayEquals(menu, testAcc.returnMenu());
    }

}
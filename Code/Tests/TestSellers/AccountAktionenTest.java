package TestSellers;

import Listen.ListeAktionen;
import Sellers.*;
import Sellers.Exceptions.NoUserLoggedIn;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

/**
 * Überprüfen der AccountAktionen-Klasse
 *
 * @author 1319658
 * @version 1.1
 */
public class AccountAktionenTest {

    @Test
    public void checkExistObj() {
        ArrayList<AccountBasis> testList = new ArrayList<>();

        Account testAcc1 = new Account("15", "test1", "", "", "");
        AccountPro testAcc2 = new AccountPro("13", "test2", "", "", "");
        testList.add(testAcc1);
        testList.add(testAcc2);
        assertEquals(13L, AccountAktionen.checkExist(testList, testAcc2));

        testList.add(testAcc2);
        boolean throwsException = false;
        try
        {
            assertEquals(testAcc2, AccountAktionen.getAcc(testList, 13L));
        }
        catch(RuntimeException e)
        {
            throwsException = true;
        }

        assertTrue(throwsException);
    }

    @Test
    public void checkExistMail() {
        ArrayList<AccountBasis> testList = new ArrayList<>();

        Account testAcc1 = new Account("15", "test1", "", "", "");
        AccountPro testAcc2 = new AccountPro("13", "test2", "", "", "");
        testList.add(testAcc1);
        testList.add(testAcc2);
        assertEquals(13L, AccountAktionen.checkExist(testList, "test2"));
    }

    @Test
    public void checkPass()
    {
        ArrayList<AccountBasis> testList = new ArrayList<>();

        Account testAcc1 = new Account("15", "test1", "123", "", "");
        AccountPro testAcc2 = new AccountPro("13", "test2", "1234", "", "");
        testList.add(testAcc1);
        testList.add(testAcc2);
        assertFalse(AccountAktionen.checkPass(testList, 15L, "1234"));
        assertTrue(AccountAktionen.checkPass(testList, 13L, "1234"));

        testList.add(testAcc2);
        boolean throwsException = false;
        try
        {
            assertEquals(testAcc2, AccountAktionen.getAcc(testList, 13L));
        }
        catch(RuntimeException e)
        {
            throwsException = true;
        }

        assertTrue(throwsException);

    }

    @Test
    public void getAcc() {
        ArrayList<AccountBasis> testList = new ArrayList<>();

        Account testAcc1 = new Account("15", "test1", "", "", "");
        AccountPro testAcc2 = new AccountPro("13", "test2", "", "", "");
        testList.add(testAcc1);
        testList.add(testAcc2);
        assertEquals(testAcc2, AccountAktionen.getAcc(testList, 13L));

        testList.add(testAcc2);
        boolean throwsException = false;
        try
        {
            assertEquals(testAcc2, AccountAktionen.getAcc(testList, 13L));
        }
        catch(RuntimeException e)
        {
            throwsException = true;
        }

        assertTrue(throwsException);
    }

    @Test
    public void logout() throws NoUserLoggedIn {
        assertEquals(-1L, AccountAktionen.logout(5L));
    }

}
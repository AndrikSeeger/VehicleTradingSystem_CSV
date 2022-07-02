package TestVehicles;

import Sellers.Account;
import Sellers.AccountAktionen;
import Sellers.AccountPro;
import Vehicles.Auto;
import Vehicles.FahrzeugBasis;
import Vehicles.FahrzeugSuche;
import Vehicles.Lkw;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

/**
 * Überprüfen der FahrzeugSuchen-Klasse
 *
 * @author 1319658
 * @version 1.1
 */
public class FahrzeugSucheTest {

    /**
     * Überprüfen der Fahrzeug-Suche
     *
     */
    @Test
    public void fahrzeugeZuKey() {
        ArrayList<FahrzeugBasis> testList = new ArrayList<>();
        Auto testFzg1 = new Auto();
        Lkw testFzg2 = new Lkw();
        Lkw testFzg3 = new Lkw();
        testFzg1.setKey(6L);
        testFzg2.setKey(3L);
        testFzg3.setKey(6L);

        testList.add(testFzg1);
        testList.add(testFzg2);
        testList.add(testFzg3);
        assertEquals(testFzg1, FahrzeugSuche.fahrzeugeZuKey(testList, 6L).get(0));
        assertEquals(testFzg3, FahrzeugSuche.fahrzeugeZuKey(testList, 6L).get(1));
    }
}
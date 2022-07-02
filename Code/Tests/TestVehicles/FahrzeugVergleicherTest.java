package TestVehicles;

import Listen.Sorter;
import Vehicles.FahrzeugBasis;
import Vehicles.FahrzeugVergleicher;
import Vehicles.Lkw;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Comparator;

import static org.junit.Assert.*;

/**
 * Überprüfen der FahrzeugVergleicher-Klasse
 *
 * @author 1319658
 * @version 1.1
 */
public class FahrzeugVergleicherTest {

    private Lkw testFzg;

    /**
     * Erstellen eines Test-Lkws
     *
     */
    @Before
    public void init(){
        testFzg = new Lkw();
    }

    @Test
    public void returnCompInfo() throws NoSuchMethodException, ClassNotFoundException {

        String daten1 = "1;Vehicles.Lkw;man;330xl;05/2020;05.09.2020;231;1560;156000;0;12000;0.0;false";
        String daten2 = "1;Vehicles.Lkw;man;330xl;05/2020;05.09.2020;231;1560;156000;2;12000;0.0;false";

        Lkw testFzg1 = testFzg.fromCsv(daten1);
        Lkw testFzg2 = testFzg.fromCsv(daten2);

        ArrayList<FahrzeugBasis> testList = new ArrayList<>();
        testList.add(testFzg1);
        testList.add(testFzg2);

        assertEquals(testFzg2, Sorter.sortList(testList, FahrzeugVergleicher.returnCompInfo(1)).get(0));
        assertEquals(testFzg1, Sorter.sortList(testList, FahrzeugVergleicher.returnCompInfo(1)).get(1));

    }

    private static final Comparator<FahrzeugBasis> compPreis = Comparator.comparing(FahrzeugBasis::getPreis);
}
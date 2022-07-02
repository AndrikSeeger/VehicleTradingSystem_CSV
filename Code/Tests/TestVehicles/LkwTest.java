package TestVehicles;

import Vehicles.*;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Überprüfen der Lkw-Klasse
 *
 * @author 1319658
 * @version 1.1
 */
public class LkwTest {

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
    public void getZuladung() {
        testFzg.setZuladung("1234");
        assertEquals(1234, testFzg.getZuladung());
    }

    @Test
    public void setZuladung() {
        getZuladung();
    }

    @Test
    public void getZuglast() {
        testFzg.setZuglast("12.34");
        assert(12.34 == testFzg.getZuglast());
    }

    @Test
    public void setZuglast() {
        getZuglast();
    }

    @Test
    public void getHydraulik() {
        testFzg.setHydraulik("true");
        assertEquals("ja", testFzg.getHydraulik());
        testFzg.setHydraulik("false");
        assertEquals("nein", testFzg.getHydraulik());
    }

    @Test
    public void setHydraulik() {
        testFzg.setHydraulik("ja");
        assertEquals("ja", testFzg.getHydraulik());
        getHydraulik();
    }

    @Test
    public void toCsv() {
        String daten = "1;Vehicles.Lkw;man;330xl;05/2020;05.09.2020;231;1560;156000;56000;12000;15.65;false";
        testFzg = testFzg.fromCsv(daten);
        assert testFzg != null;
        assertEquals(daten, testFzg.toCsv());
    }

    @Test
    public void fromCsv() {
        toCsv();
    }

    @Test
    public void returnMenu() {
        String[][] menu = new String[10][3];

        menu[0][0] = "Marke eingeben (XXX, keine Sonderz.): ";
        menu[1][0] = "Modell setzen (XXX, keine Sonderz.): ";
        menu[2][0] = "Erstzulassung eingeben (MM/YYYY, nicht zukünftig): ";
        menu[3][0] = "Leistung (in PS) eingeben (ZZZZ): ";
        menu[4][0] = "Gewicht (in kg) eingebn (ZZZZZ): ";
        menu[5][0] = "Kilometerstand eingeben (ZZZZZZZ): ";
        menu[6][0] = "Preis eingeben (ZZZZZZZZZ): ";

        menu[0][1] = "Regex" + "\\w{3,16}";
        menu[1][1] = menu[0][1];
        menu[2][1] = "Regex"+ "(([0][1-9])|([1][0-2]))\\/((1([8]|[9])\\d{2})|([2-9]\\d{3}))" + "Date" + "MM/yyyy";
        menu[3][1] = "Regex" + "\\d{1,4}";
        menu[4][1] = "Regex" + "\\d{1,5}";
        menu[5][1] = "Regex" + "\\d{1,7}";
        menu[6][1] = "Regex" + "\\d{1,9}";

        menu[0][2] = "setMarke";
        menu[1][2] = "setModell";
        menu[2][2] = "setErstzulassung";
        menu[3][2] = "setLeistung";
        menu[4][2] = "setGewicht";
        menu[5][2] = "setOdometer";
        menu[6][2] = "setPreis";

        menu[7][0] = "Zuladung (in kg)(ZZZZZ): ";
        menu[8][0] = "Zuglast (in t) (ZZ.Z): ";
        menu[9][0] = "Hydraulik (ja/nein)?: ";

        menu[7][1] = "Regex" + "\\d{1,5}"; //0-99999
        menu[8][1] = "Regex" + "\\d{1,2}\\.\\d"; // 00,0 - 99,9 //evtl auf Punkt ändern
        menu[9][1] = "Regex" + "(ja)|(nein)";

        menu[7][2] = "setZuladung";
        menu[8][2] = "setZuglast";
        menu[9][2] = "setHydraulik";
        assertArrayEquals(menu, testFzg.returnMenu());
    }
}
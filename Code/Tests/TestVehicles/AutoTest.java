package TestVehicles;

import Vehicles.Auto;
import Vehicles.FahrzeugBasis;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Überprüfen der Auto-Klasse
 *
 * @author 1319658
 * @version 1.1
 */
public class AutoTest {

    private Auto testFzg;

    /**
     * Erstellen eines Test-Autos
     *
     */
    @Before
    public void init(){
        testFzg = new Auto();
    }

    @Test
    public void getFahrzeugArt() {
        testFzg.setFahrzeugArt("6");
        assertEquals("Sportwagen", testFzg.getFahrzeugArt());
    }

    @Test
    public void setFahrzeugArt() {
        getFahrzeugArt();
    }

    @Test
    public void getSportPaketString() {
        testFzg.setSportPaket(true);
        assertEquals("ja", testFzg.getSportPaket());
        testFzg.setSportPaket(false);
        assertEquals("nein", testFzg.getSportPaket());
    }

    @Test
    public void setSportPaket() {
        testFzg.setSportPaket("ja");
        assertEquals("ja", testFzg.getSportPaket());
    }

    @Test
    public void setSportPaketBoolean() {
        testFzg.setSportPaket(true);
        assertEquals("ja", testFzg.getSportPaket());
    }

    @Test
    public void toCsv() {
        String daten = "1;Vehicles.Auto;bmw;330Ci;05/2020;05.09.2020;231;1560;156000;14999;Cabrio;true";
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

        String[][] menu = new String[9][3];

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

        menu[7][0] = "Fahrzeugart wählen (1 = Geländewagen, 2 = Kombi, 3 = Limousine, 4 = Kleinwagen, 5 = Cabrio, 6 = Sportwagen): ";
        menu[8][0] = "Sportpaket (ja/nein)?: ";

        menu[7][1] = "Regex" + "[1-6]";
        menu[8][1] = "Regex" + "(ja)|(nein)";

        menu[7][2] = "setFahrzeugArt";
        menu[8][2] = "setSportPaket";
        assertArrayEquals(menu, testFzg.returnMenu());
    }
}
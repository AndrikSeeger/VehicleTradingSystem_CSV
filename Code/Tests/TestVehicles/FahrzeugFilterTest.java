package TestVehicles;

import Listen.Filter;
import Listen.ListeAktionen;
import Vehicles.Auto;
import Vehicles.FahrzeugBasis;
import Vehicles.FahrzeugFilter;
import Vehicles.Lkw;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.nio.charset.StandardCharsets;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Scanner;

import static org.junit.Assert.*;

/**
 * Überprüfen der FahrzeugFilter-Klasse
 *
 * @author 1319658
 * @version 1.1
 */
public class FahrzeugFilterTest {

    private Lkw testFzg;

    /**
     * Erstellen eines Test-Lkws
     *
     */
    @Before
    public void init(){
        testFzg = new Lkw();
    }

    @After
    public void after()
    {
        System.setIn(System.in);
    }

    @Test
    public void getFilter() throws UnsupportedEncodingException, InvocationTargetException, NoSuchMethodException, ParseException, IllegalAccessException {

        String daten1 = "1;Vehicles.Lkw;man;330xl;05/2020;05.09.2020;231;1560;156000;2;12000;3.5;false"; //p
        String daten2 = "1;Vehicles.Lkw;man;330xl;05/2020;05.09.2020;231;1560;156000;0;12000;1.5;false"; //l
        String daten3 = "1;Vehicles.Lkw;testmarke;330xl;05/2020;05.09.2020;231;1560;0;56000;12000;40.5;false"; //m
        String daten4 = "1;Vehicles.Lkw;man;330xl;05/2001;05.09.2020;231;1560;156000;3;12000;0.0;false"; //e
        Lkw testFzg1 = testFzg.fromCsv(daten1);
        Lkw testFzg2 = testFzg.fromCsv(daten2);
        Lkw testFzg3 = testFzg.fromCsv(daten3);
        Lkw testFzg4 = testFzg.fromCsv(daten4);

        ArrayList<FahrzeugBasis> testList = new ArrayList<>();
        testList.add(testFzg1);
        testList.add(testFzg2);
        testList.add(testFzg3);
        testList.add(testFzg4);

        String inputData1 = "1\n3\n";
        String inputData2 = "1.0\n3.0\n";
        String inputData3 = "2000\n2002\n";
        String inputData4 = "testmarke\n";


        InputStream testInput1 = new ByteArrayInputStream(inputData1.getBytes(StandardCharsets.UTF_8));//sb.toString().getBytes(StandardCharsets.UTF_8)
        System.setIn(testInput1);


        ArrayList<FahrzeugBasis> preisListe = Filter.filterList(FahrzeugFilter.getFilter("getPreis", Lkw.class), testList);

        InputStream testInput2 = new ByteArrayInputStream(inputData2.getBytes(StandardCharsets.UTF_8));
        System.setIn(testInput2);

        ArrayList<FahrzeugBasis> zuglastListe = Filter.filterList(FahrzeugFilter.getFilter("getZuglast", Lkw.class), testList);

        InputStream testInput3 = new ByteArrayInputStream(inputData3.getBytes(StandardCharsets.UTF_8));
        System.setIn(testInput3);

        ArrayList<FahrzeugBasis> erstzulassungListe = Filter.filterList(FahrzeugFilter.getFilter("getErstzulassungAsDate", Lkw.class), testList);

        InputStream testInput4 = new ByteArrayInputStream(inputData4.getBytes(StandardCharsets.UTF_8));
        System.setIn(testInput4);

        ArrayList<FahrzeugBasis> markeListe = Filter.filterList(FahrzeugFilter.getFilter("getMarke", Lkw.class), testList);

        assertEquals(testFzg1, preisListe.get(0));
        assertEquals(testFzg2, zuglastListe.get(0));
        assertEquals(testFzg3, markeListe.get(0));
        assertEquals(testFzg4, erstzulassungListe.get(0));
    }
}
package TestListen;

import Listen.*;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Comparator;

import static org.junit.Assert.*;

/**
 * Überprüfen der Sorter-Klasse
 *
 * @author 1319658
 * @version 1.1
 */
public class SorterTest{

    /**
     * Überprüft Sortieren einer Liste
     *
     */
    @Test
    public void sortTestAufsteigend() throws NoSuchMethodException, ClassNotFoundException {

        Comparator<Integer> comp = new Comparator<Integer>() {
                public int compare(Integer i1, Integer i2) {
                    return i1-i2; //Vergleicht Wohnorte
                }};

       Object[] data = {"Test", comp, false};

        ArrayList<Integer> list = new ArrayList<>();
        list.add(5);
        list.add(7);
        list.add(6);
        list.add(1);

        list = Sorter.sortList(list, data);
        assert(1 == list.get(0));
        assert(5 == list.get(1));
        assert(6 == list.get(2));
        assert(7 == list.get(3));
    }

    /**
     * Überprüft Sortieren einer Liste
     *
     */
    @Test
    public void sortTestAbsteigend() throws NoSuchMethodException, ClassNotFoundException {

        Comparator<Integer> comp = new Comparator<Integer>() {
            public int compare(Integer i1, Integer i2) {
                return i1-i2;
            }};

        Object[] data = {"Test", comp, true};

        ArrayList<Integer> list = new ArrayList<>();
        list.add(5);
        list.add(7);
        list.add(6);
        list.add(1);

        list = Sorter.sortList(list, data);
        assert(1 == list.get(3));
        assert(5 == list.get(2));
        assert(6 == list.get(1));
        assert(7 == list.get(0));
    }

}
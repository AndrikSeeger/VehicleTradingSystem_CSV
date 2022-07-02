package TestListen;

import Listen.Filter;
import org.junit.Test;

import java.util.ArrayList;
import java.util.function.Predicate;

import static org.junit.Assert.*;

/**
 * Überprüfen der Filter-Klasse
 *
 * @author 1319658
 * @version 1.1
 */
public class FilterTest {

    /**
     * Überprüft Filtern einer Liste
     *
     */
    @Test
    public void filterList()
    {
        ArrayList<Integer> list = new ArrayList();
        list.add(5);
        list.add(7);
        list.add(6);
        list.add(1);

        Predicate<Integer> filter = i -> i>6;
        list = Filter.filterList(filter, list);
        assert(list.size() == 1);
        assert(list.get(0)==7);
    }
}
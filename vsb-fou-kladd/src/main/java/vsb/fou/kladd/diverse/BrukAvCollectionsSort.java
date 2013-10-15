package vsb.fou.kladd.diverse;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author Vegard S. Bye
 */
public class BrukAvCollectionsSort {

    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        list.add("BookOfDoctorsWS");
        list.add("CONTEMPUS");
        list.add("AUW");
        list.add("SOLR");
        list.add("CalculationWS");
        System.out.println("list = " + list);
        Collections.sort(list);
        System.out.println("list = " + list);
    }
}

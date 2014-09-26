package vsb.fou.kladd.diverse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * @author Vegard S. Bye
 */
public class BrukAvCollectionsSort {

    private static final Logger LOGGER = LoggerFactory.getLogger(BrukAvCollectionsSort.class);

    public static void main(String[] args) {

        List<String> list = new ArrayList<>();
        list.add("BookOfDoctorsWS");
        list.add("CONTEMPUS");
        list.add("AUW");
        list.add("SOLR");
        list.add("CalculationWS");
        LOGGER.info("list = " + list);
        Collections.sort(list, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                if (o1.equals(o1.toUpperCase()) && !o2.toUpperCase().equals(o2)) {
                    // den første er bare uppercase => sorter den først
                    return -1;
                }
                if (o2.equals(o2.toUpperCase()) && !o1.toUpperCase().equals(o1)) {
                    // den andre er bare uppercase => sorter den først
                    return 1;
                }
                return o1.compareTo(o2);
            }
        });
        LOGGER.info("list = " + list);
    }
}

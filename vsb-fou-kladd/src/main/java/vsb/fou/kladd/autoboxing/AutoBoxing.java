package vsb.fou.kladd.autoboxing;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class AutoBoxing {

    private static final Logger LOGGER = LoggerFactory.getLogger(AutoBoxing.class);

    public AutoBoxing() {

        Integer j = 4;
        LOGGER.info("4 == " + j + " er " + (4 == j));
        Integer k = 4;
        j = 4;
        LOGGER.info(k + " == " + j + " er " + (k == j));

        for (int i = 1; i < 100000; i <<= 1) {
            j = i;
            k = i;
            LOGGER.info(k + " == " + j + " er " + (k == j)); // oppf�rer seg normalt til 128
            if (k != j) {
                break;
            }
        }

        Integer p = 0;
        Integer q = p;
        for (int i = 0; i < 3; ++i) {
            LOGGER.info("i p q = " + i + " " + p + " " + q);
            p++; // peker p p� noe annet eller ??? er dette samme som p = new Integer(p.intValue() + 1)
        }

        // List<int>

        List<Integer> li = new ArrayList<Integer>();

        for (int i : Counter.upTo(5)) {
            li.add(i);
        }

        li.add(0);
        li.add(null);
        try {
            for (Integer i : li) {
                LOGGER.info("i = " + i);
            }

            for (int i : li) {
                LOGGER.info("i = " + i);
            }
        } catch (NullPointerException e) {
            LOGGER.info("NPE!", e);
        }
    }

    public static void main(String[] args) {
        new AutoBoxing();
    }
}

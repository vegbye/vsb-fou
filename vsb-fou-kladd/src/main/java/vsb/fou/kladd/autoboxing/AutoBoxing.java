package vsb.fou.kladd.autoboxing;

import java.util.ArrayList;
import java.util.List;

public class AutoBoxing {

    public AutoBoxing() {

        Integer j = 4;
        System.out.println("4 == " + j + " er " + (4 == j));
        Integer k = 4;
        j = 4;
        System.out.println(k + " == " + j + " er " + (k == j));

        for (int i = 1; i < 100000; i <<= 1) {
            j = i;
            k = i;
            System.out.println(k + " == " + j + " er " + (k == j)); // oppf�rer seg normalt til 128
            if (k != j) {
                break;
            }
        }

        Integer p = 0;
        Integer q = p;
        for (int i = 0; i < 3; ++i) {
            System.out.println("i p q = " + i + " " + p + " " + q);
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
                System.out.println("i = " + i);
            }

            for (int i : li) {
                System.out.println("i = " + i);
            }
        } catch (NullPointerException e) {
            System.out.println(e);
        }
    }

    public static void main(String[] args) {
        new AutoBoxing();
    }
}

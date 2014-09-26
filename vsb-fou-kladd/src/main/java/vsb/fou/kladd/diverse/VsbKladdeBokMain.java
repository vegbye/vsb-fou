package vsb.fou.kladd.diverse;

import org.apache.commons.collections.comparators.NullComparator;
import org.apache.commons.lang.ObjectUtils;
import org.apache.commons.lang.time.DateFormatUtils;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.Collections;
import java.util.GregorianCalendar;
import java.util.List;

@SuppressWarnings({"unchecked", "unused", "null"})
public class VsbKladdeBokMain {

    private static final Logger LOGGER = Logger.getLogger(VsbKladdeBokMain.class);

    public static void main(String[] args) throws Exception {

        LOGGER.info("start...");

        // old();
        // old2();
        // old3();

        // Integer nummer = (Integer) finnNummer();
        // LOGGER.info("nummer ? " + nummer);
        // printNummer(nummer);

        LOGGER.info("end");
    }

    private static void printNummer(int nummer) {
        LOGGER.info("nummeret er ? " + nummer);
    }

    private static Object finnNummer() {
        return null;
    }

    private static void old3() {
        GregorianCalendar cal = new GregorianCalendar();

        DateFormatUtils.format(cal, "yyyy-MM-dd");
        LOGGER.info(DateFormatUtils.format(cal, "yyyy-MM-dd"));
        cal = null;
        LOGGER.info(DateFormatUtils.format(cal, "yyyy-MM-dd"));
    }

    private static void old2() {
        List<String> liste = new ArrayList<String>();
        liste.add("vegard");
        liste.add(null);

        Collections.sort(liste, new NullComparator());
        LOGGER.info(liste);

        Integer fem = 5;
        Long femLong = 5L;
        boolean like = fem.equals(femLong.intValue());
        LOGGER.info("Like ? " + like);
        boolean like2 = femLong.equals(fem.longValue());
        LOGGER.info("Like2 ? " + like2);

        VsbEnum a = null;
        VsbEnum b = null;
        boolean likeNullEnums = (a == b);
        LOGGER.info("Like enums ? " + likeNullEnums);

        VsbDataB bb = new VsbDataB();
        bb.setSS("ss");
        bb.setKK(1);
        VsbDataB clone = bb.clone();
        LOGGER.info("clone ? " + clone);
    }

    private static void old() {
        boolean equals = VsbEnum.START.equals(null);
        LOGGER.info("enum equals ? " + equals);

        VsbMyClass vsbMyClass = new VsbMyClass();
        VsbMyClass.Indre indre = vsbMyClass.new Indre();
        LOGGER.info(indre);

        VsbMyClass.IndreStatisk indreStatisk = new VsbMyClass.IndreStatisk();
        LOGGER.info(indreStatisk);

        kanCasteFraNull();

        for (int i = 0; i < 100; i++) {
            LOGGER.info("5 digit ? " + make5digitNumbers());
        }

        boolean like = "null".equals(null);
        LOGGER.info("like? " + like);
        like = ObjectUtils.equals("null", null);
        LOGGER.info("like? " + like);
        like = ObjectUtils.equals(null, null);
        LOGGER.info("like? " + like);
    }

    private static int make5digitNumbers() {
        return new Double(Math.random() * 100000).intValue();
    }

    private static void kanCasteFraNull() {
        Integer i = (Integer) m();
        LOGGER.info("int: " + i);
    }

    private static Number m() {
        return null;
    }
}

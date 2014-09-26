package vsb.fou.kladd.autoboxing;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AutoBoxingAvTallOver128 {

    private static final Logger LOGGER = LoggerFactory.getLogger(AutoBoxingAvTallOver128.class);

    public static void main(String[] args) {

        for (int i = 0; i < 10; i++) {
            Integer gg = new Integer(i);
            Integer kk = new Integer(i);
            if (gg != kk) {
                LOGGER.info(gg + "!=" + kk);
            }

            if ((gg + 1) == (kk + 1)) {
                LOGGER.info(gg + "+1==1+" + kk);
            }

        }

        Integer g = 1;
        Integer k = 1;
        for (int i = 0; i < 10000; i++) {
            g++;
            k++;
            if (g != k) {
                throw new IllegalStateException("H��� !?�#%�%&: g: " + g + " k: " + k);
            }
        }
        LOGGER.info("ok");
    }
}

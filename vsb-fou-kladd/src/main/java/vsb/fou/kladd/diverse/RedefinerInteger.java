package vsb.fou.kladd.diverse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;

public class RedefinerInteger {

    private static final Logger LOGGER = LoggerFactory.getLogger(RedefinerInteger.class);

    public static void main(String[] args) {
        new RedefinerInteger().munge();

        Integer a = 2;
        Integer b = 3;
        LOGGER.info("a + b = " + a + b);
    }

    public void munge() {
        try {
            Field field = Integer.class.getDeclaredField("value");
            field.setAccessible(true);
            for (int i = -127; i <= 128; i++) {
                int nyDefAvInt;
                if (Math.random() < 0.6) {
                    nyDefAvInt = i;
                } else if (Math.random() < 0.3) {
                    nyDefAvInt = 42;
                } else {
                    nyDefAvInt = i + 1;
                }
                field.setInt(Integer.valueOf(i), nyDefAvInt);
            }
            // field.setInt(Integer.valueOf(2), 8);
            // field.setInt(Integer.valueOf(3), 4);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

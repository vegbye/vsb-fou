package vsb.fou.kladd.diverse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Method;

/**
 * @author Vegard S. Bye
 */
public class BrukAvReflections {

    private static final Logger LOGGER = LoggerFactory.getLogger(BrukAvReflections.class);

    public static void main(String[] args) {
        Method enclosingMethod = BrukAvReflections.class.getEnclosingMethod();
        LOGGER.info("enclosingMethod = " + enclosingMethod);
        new BrukAvReflections().doIt();
    }

    public void doIt() {
        class Local {
        }
        Method enclosingMethod = Local.class.getEnclosingMethod();
        LOGGER.info("enclosingMethod = " + enclosingMethod);
        LOGGER.info("enclosingMethod = " + enclosingMethod.getDeclaringClass());

        LOGGER.info("assignableFrom = " + CharSequence.class.isAssignableFrom("hei".getClass()));
        Object entity = new Object();
        LOGGER.info("assignableFrom = " + CharSequence.class.isAssignableFrom(entity.getClass()));
    }
}

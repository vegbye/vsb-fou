package vsb.kladd;

import org.apache.commons.lang.SerializationUtils;
import org.apache.log4j.Logger;

public class VsbSerialiseringAvEnumsMain {

    private static final Logger log = Logger.getLogger(VsbSerialiseringAvEnumsMain.class);

    public static void main(String[] args) throws Exception {

        log.info("start...");
        log.info("java.version ? " + System.getProperty("java.version"));

        log.info("Thread.State.BLOCKED ? " + Thread.State.BLOCKED);
        Thread.State clone = (Thread.State) SerializationUtils.clone(Thread.State.BLOCKED);
        log.info("clone ? " + clone);
        boolean like = Thread.State.BLOCKED == clone;
        log.info("like ? " + like);

        log.info("end");
    }
}

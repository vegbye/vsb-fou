package vsb.kladd.memory;

import org.apache.log4j.Logger;
import org.junit.Test;

public class VsbMemoryUsageTest {

    private static final Logger log = Logger.getLogger(VsbMemoryUsageTest.class);

    private final long ONE_KB = 1024L;
    private final long ONE_MB = ONE_KB * ONE_KB;

    @Test
    public void printMinneBruk() {
        long maxMemory = Runtime.getRuntime().maxMemory() / ONE_MB;
        long totalMemory = Runtime.getRuntime().totalMemory() / ONE_MB;
        long freeMemory = Runtime.getRuntime().freeMemory() / ONE_MB;
        log.info("MB, [max/total/free] [" + maxMemory + "/" + totalMemory + "/" + freeMemory + "]");
    }
}

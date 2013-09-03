package vsb.kladd.perf4j;

import org.apache.log4j.Logger;
import org.perf4j.TimingStatistics;
import org.perf4j.log4j.Log4JStopWatch;

public class VsbPerf4jMain {

    private static final Logger log = Logger.getLogger(VsbPerf4jMain.class);

    public static void main(String[] args) throws Exception {
        log.info("start...");

        f();

        log.info("end");
    }

    private static void f() {
        TimingStatistics statisticsSuccess = new TimingStatistics();
        TimingStatistics statisticsError = new TimingStatistics();

        for (int i = 0; i < 10; i++) {
            Log4JStopWatch stopWatch = new Log4JStopWatch();
            try {
                // the code block being timed - this is just a dummy example
                long sleepTime = (long) (Math.random() * 200L);
                Thread.sleep(sleepTime);
                if (sleepTime > 100) {
                    throw new Exception("Throwing exception");
                }
                stopWatch.stop("codeBlock2.success", "Sleep time was < 500 ms");
                statisticsSuccess.addSampleTime(stopWatch.getElapsedTime());
            } catch (Exception e) {
                stopWatch.stop("codeBlock2.failure", "Exception was: " + e, e);
                statisticsError.addSampleTime(stopWatch.getElapsedTime());
            }
        }
        log.info("Suksess statistikk: " + statisticsSuccess);
        log.info("Error statistikk: " + statisticsError);
    }
}

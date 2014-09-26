package vsb.fou.kladd.executor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class BrukAvSpringTaskExecutor {

    private static final Logger LOGGER = LoggerFactory.getLogger(BrukAvSpringTaskExecutor.class);

    public static void main(String[] args) throws InterruptedException, ExecutionException, TimeoutException {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(5);
        executor.setMaxPoolSize(20);
        executor.setDaemon(true);
        Future<String> future = executor.submit(new Callable<String>() {
            @Override
            public String call() throws Exception {
                return "hei";
            }
        });

        String s = future.get(100, TimeUnit.MILLISECONDS);
        LOGGER.info("s = " + s);
    }
}

package vsb.fou.quartz;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Service;

@Service
public class MyQuartzJobBean extends QuartzJobBean {

    private static final Logger LOGGER = LoggerFactory.getLogger(MyQuartzJobBean.class);
    private RunMeTask runMeTask;

    @Override
    protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
        LOGGER.info("context:" + context);
        runMeTask.printMe();
    }

    @SuppressWarnings("UnusedDeclaration")
    public void setRunMeTask(RunMeTask runMeTask) {
        this.runMeTask = runMeTask;
    }
}

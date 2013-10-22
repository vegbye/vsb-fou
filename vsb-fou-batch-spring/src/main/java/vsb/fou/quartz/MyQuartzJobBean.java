package vsb.fou.quartz;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Service;

@Service
public class MyQuartzJobBean extends QuartzJobBean {

    private RunMeTask runMeTask;

    @Override
    protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
        System.out.println("ok:" + context);
        runMeTask.printMe();
    }

    @SuppressWarnings("UnusedDeclaration")
    public void setRunMeTask(RunMeTask runMeTask) {
        this.runMeTask = runMeTask;
    }
}

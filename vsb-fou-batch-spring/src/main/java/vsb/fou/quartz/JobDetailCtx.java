package vsb.fou.quartz;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.JobDetailFactoryBean;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Vegard S. Bye
 */
@Configuration
public class JobDetailCtx {

    @Resource
    private RunMeTask runMeTask;

    @Bean
    public JobDetailFactoryBean runMeJob() {
        JobDetailFactoryBean bean = new JobDetailFactoryBean();
        bean.setJobClass(MyQuartzJobBean.class);
        Map<String, Object> map = new HashMap<>();
        map.put("runMeTask", runMeTask);
        bean.setJobDataAsMap(map);
        bean.setDurability(true);
        return bean;
    }
}

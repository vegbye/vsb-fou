package vsb.fou.batch.spring.quartz;

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
    private VsbQuartzJob vsbQuartzJob;

    @Bean
    public JobDetailFactoryBean vsbQuartzJob() {
        JobDetailFactoryBean bean = new JobDetailFactoryBean();
        bean.setJobClass(VsbQuartzJob.class);
        Map<String, Object> map = new HashMap<>();
        map.put("vsbQuartzJob", vsbQuartzJob);
        bean.setJobDataAsMap(map);
        bean.setDurability(true);
        return bean;
    }
}

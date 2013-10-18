package vsb.fou.batch.spring;

import org.springframework.batch.admin.service.SimpleJobServiceFactoryBean;
import org.springframework.batch.admin.web.resources.DefaultResourceService;
import org.springframework.batch.admin.web.resources.ResourceService;
import org.springframework.batch.core.configuration.JobRegistry;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import vsb.fou.common.InfraConfig;

import javax.sql.DataSource;

/**
 * @author Vegard S. Bye
 */
@Configuration
@ImportResource({
        "classpath*:/META-INF/spring/batch/bootstrap/resources/*.xml",
        "classpath*:/META-INF/spring/batch/bootstrap/manager/jmx-context.xml",
        "classpath*:/META-INF/spring/batch/bootstrap/integration/*.xml"
})
@InfraConfig
public class SpringBatchAdminCtx {

    @Autowired
    private JobLauncher jobLauncher;
    @Autowired
    private JobRepository jobRepository;
    @Autowired
    private DataSource dataSource;
    @Autowired
    private JobRegistry jobRegistry;

    @Bean
    public ResourceService resourceService() {
        DefaultResourceService bean = new DefaultResourceService();
        // denne setting funker ikke
//        bean.setServletPath("/springbatchadmin");
        return bean;
    }

    @Bean
    public SimpleJobServiceFactoryBean jobService() {
        SimpleJobServiceFactoryBean bean = new SimpleJobServiceFactoryBean();
        bean.setJobLauncher(jobLauncher);
        bean.setJobRepository(jobRepository);
        bean.setJobLocator(jobRegistry);
        bean.setDataSource(dataSource);
        return bean;
    }
}

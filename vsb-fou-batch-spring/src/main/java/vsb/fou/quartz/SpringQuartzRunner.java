package vsb.fou.quartz;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class SpringQuartzRunner {

    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(MainCtxQuartz.class);

    }

}

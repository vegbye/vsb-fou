package vsb.fou.batch.spring.poc;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class MittAspekt {

    @Pointcut
    public void jepp() {
    }
}

package vsb.fou.rest.jersey.server;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import javax.ws.rs.core.Response;
import java.util.Arrays;

@Aspect
@Component
public class VsbRestAspect {

    @Pointcut("within(@javax.ws.rs.Path *)")
    public void pathAnnotatedClass() {
    }

    @Pointcut("execution(public * *(..))")
    public void publicMethod() {
    }

    @Around("publicMethod() && pathAnnotatedClass()")
    public Object aroundExeute(ProceedingJoinPoint jp) throws Throwable {
        Object[] args = jp.getArgs();
        System.out.println("Arrays.toString(args) = " + Arrays.toString(args));
        MethodSignature signature = (MethodSignature) jp.getSignature();
        System.out.println("signature.getMethod().getName() = " + signature.getMethod().getName());
        System.out.println("signature.getReturnType().getName() = " + signature.getReturnType().getName());
        String[] parameterNames = signature.getParameterNames();
        System.out.println("Arrays.toString(parameterNames) = " + Arrays.toString(parameterNames));
        Class<?> restClass = jp.getTarget().getClass();

        Object response = null;
        try {
            response = jp.proceed();
            if (response instanceof Response) {
                Response r = (Response) response;
                Object entity = r.getEntity();
                System.out.println("entity = " + entity);
            }
            return response;
        } catch (Exception e) {
            // log it
            throw e;
        } finally {
            // clean up
        }
    }

}

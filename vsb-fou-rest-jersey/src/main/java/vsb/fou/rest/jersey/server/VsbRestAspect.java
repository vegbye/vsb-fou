package vsb.fou.rest.jersey.server;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.ws.rs.core.Response;

@Aspect
@Component
public class VsbRestAspect {

    private static final Logger ERROR_LOGGER = LoggerFactory.getLogger("ERROR." + VsbRestAspect.class.getSimpleName());
    private static final Logger REQUEST_LOGGER = LoggerFactory.getLogger("TRANSACTION.REQUEST." + VsbRestAspect.class.getSimpleName());
    private static final Logger RESPONSE_LOGGER = LoggerFactory.getLogger("TRANSACTION.RESPONSE." + VsbRestAspect.class.getSimpleName());

    @Pointcut("within(@javax.ws.rs.Path *)")
    public void pathAnnotatedClass() {
    }

    @Pointcut("execution(public * *(..))")
    public void publicMethod() {
    }

    @Around("publicMethod() && pathAnnotatedClass()")
    public Object aroundExeute(ProceedingJoinPoint jp) throws Throwable {
        Object[] args = jp.getArgs();
        MethodSignature signature = (MethodSignature) jp.getSignature();
        String[] parameterNames = signature.getParameterNames();
        Class<?> restClass = jp.getTarget().getClass();

        String requestParams = getRequestParams(parameterNames, args);

        REQUEST_LOGGER.info(restClass.getSimpleName() + "." + signature.getMethod().getName()
                + "[" + requestParams + "]");
        try {
            Object response = jp.proceed();
            if (response instanceof Response) {
                Response r = (Response) response;
                Object entity = r.getEntity();
                RESPONSE_LOGGER.info(restClass.getSimpleName() + "." + signature.getMethod().getName()
                        + "[" + entity + "]");
            } else {
                RESPONSE_LOGGER.info(restClass.getSimpleName() + "." + signature.getMethod().getName()
                        + "[" + response + "]");
            }
            return response;
        } catch (Exception e) {
            RESPONSE_LOGGER.error(restClass.getSimpleName() + "." + signature.getMethod().getName()
                    + "[" + e + "]");
            ERROR_LOGGER.error("", e);
            throw e;
        } finally {
            // clean up
        }
    }

    private String getRequestParams(String[] parameterNames, Object[] args) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < parameterNames.length; i++) {
            String paramName = parameterNames[i];
            Object arg = args[i];
            sb.append(paramName)
                    .append("=")
                    .append(arg)
                    .append(" ");
        }
        return sb.toString().trim();
    }

}

package vsb.fou.rest.jersey.server;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Aspect
@Service
public class VsbRestAspect {

    private static final Logger ERROR_LOGGER = LoggerFactory.getLogger("ERROR." + VsbRestAspect.class.getSimpleName());
    private static final Logger REQUEST_LOGGER = LoggerFactory.getLogger("TRANSACTION.REQUEST." + VsbRestAspect.class.getSimpleName());
    private static final Logger RESPONSE_LOGGER = LoggerFactory.getLogger("TRANSACTION.RESPONSE." + VsbRestAspect.class.getSimpleName());
    private static final Map<String, List<String>> TRUNCATED_REQUEST_PARAMS = new HashMap<String, List<String>>();

    private static VsbSessionCache vsbSessionCache;

    public static void addTruncatedRequestParam(String serviceName, String paramName) {
        List<String> paramNames = TRUNCATED_REQUEST_PARAMS.get(serviceName);
        if (paramNames == null) {
            TRUNCATED_REQUEST_PARAMS.put(serviceName, new ArrayList<String>());
        }
        TRUNCATED_REQUEST_PARAMS.get(serviceName).add(paramName);
    }

    @Autowired
    public void setVsbSessionCache(VsbSessionCache vsbSessionCache) {
        VsbRestAspect.vsbSessionCache = vsbSessionCache;
    }

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

        String serviceName = restClass.getSimpleName() + "." + signature.getMethod().getName();
        String requestParams = getRequestParams(serviceName, parameterNames, args);

        MDC.put("THREADID", Long.toString(Thread.currentThread().getId()));
        REQUEST_LOGGER.info(serviceName + "[" + requestParams + "]");
        try {
            vsbSessionCache.sayHello();
            Object response = jp.proceed();
            if (response instanceof Response) {
                Response r = (Response) response;
                Object entity = r.getEntity();
                RESPONSE_LOGGER.info(serviceName + "[" + entity + "]");
            } else {
                RESPONSE_LOGGER.info(serviceName + "[" + response + "]");
            }
            return response;
        } catch (Exception e) {
            RESPONSE_LOGGER.error(serviceName + "[" + e + "]");
            ERROR_LOGGER.error("", e);
            throw e;
        } finally {
            MDC.clear();
        }
    }

    private String getRequestParams(String serviceName, String[] parameterNames, Object[] args) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < parameterNames.length; i++) {
            String paramName = parameterNames[i];
            Object arg = args[i];
            sb.append(paramName)
                    .append("=");

            List<String> truncatedElements = TRUNCATED_REQUEST_PARAMS.get(serviceName);
            if (truncatedElements != null && truncatedElements.contains(paramName)) {
                sb.append("---truncated---").append(" ");
            } else {
                sb.append(arg).append(" ");
            }
        }
        return sb.toString().trim();
    }

}

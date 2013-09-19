package vsb.fou.rest.jersey.server;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

@Aspect
@Component
public class VsbRestAspect {

    @Pointcut("within(@javax.jws.WebService *)")
    public void webServiceAnnotatedClass() {
    }

    @Pointcut("within(@sb1.lip.infra.ws.WebServiceWithoutMetadata *)")
    public void webServiceWithoutMetadataAnnotatedClass() {
    }

    @Pointcut("execution(public * *(..))")
    public void publicMethod() {
    }

    @Pointcut("execution(@javax.annotation.Resource * *(..))")
    public void publicSpringSetter() {
    }

    /**
     * Dette gjøres før og etter (rundt eller around) Prccess.execute, men etter beforeExecute
     */
    @Around("publicMethod() && webServiceAnnotatedClass() && !webServiceWithoutMetadataAnnotatedClass() && !publicSpringSetter()")
    public Object aroundExeute(ProceedingJoinPoint jp) throws Throwable {
        Object request = getRequest(jp);
        MethodSignature signature = (MethodSignature) jp.getSignature();
        Class<?> webServiceClass = jp.getTarget().getClass();
        Method method = findMethod(webServiceClass, signature.getName());

        lipServiceHandler.before(method, request);
        lipServiceAudit.startPerformance("WS");
        lipServiceAudit.logTransaction(TransactionType.REQUEST, request);
        Object jaxbResponse = null;
        try {
            jaxbResponse = lipExecutorService.submit(createLipCallable(jp));
        } catch (ErrorCodeException e) {
            jaxbResponse = lipServiceErrorAudit.afterThrowing(e, e.getErrorCode());
        } catch (Exception e) {
            jaxbResponse = lipServiceErrorAudit.afterThrowing(e, InfraErrorCode.UNEXPECTED_ERROR_3);
        } finally {
            lipServiceAudit.logTransaction(TransactionType.RESPONSE, jaxbResponse);
            lipServiceAudit.stopPerformance();
            lipServiceHandler.after();
        }
        return jaxbResponse;
    }

}

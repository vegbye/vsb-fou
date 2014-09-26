package vsb.fou.kladd.diverse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Method;

public class BrukAvDynamicProxy {

    private static final Logger LOGGER = LoggerFactory.getLogger(BrukAvDynamicProxy.class);

    public static void main(String[] args) throws Throwable {
        MyService myService = new MyService();
        MyDynamicProxy myDynamicProxy = new MyDynamicProxy(myService);
        Method method = MyService.class.getMethod("doIt");
        Object invoke = myDynamicProxy.invoke(myService, method, null);
        LOGGER.info("invoke = " + invoke);

        MyDynamicProxy obj = (MyDynamicProxy) new MyFactory().create();
        LOGGER.info("obj = " + obj);
//        obj.doIt();
    }

    static class MyFactory {
        public synchronized Object create() {
            Object obj = new MyService();
            return new MyDynamicProxy(obj);
        }
    }

    static class MyDynamicProxy implements java.lang.reflect.InvocationHandler {

        private final Object proxy;

        public MyDynamicProxy(Object proxy) {
            this.proxy = proxy;
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            LOGGER.info("MyDynamicProxy.invoke() - start");
            try {
                return method.invoke(this.proxy, args);
            } finally {
                LOGGER.info("MyDynamicProxy.invoke() - slutt");
            }
        }
    }

    static class MyService {
        public void doIt() {
            LOGGER.info("MyService.doIt()");
        }
    }
}

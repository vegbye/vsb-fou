package vsb.fou.kladd.diverse;

import java.lang.reflect.Method;

public class BrukAvDynamicProxy {

    public static void main(String[] args) throws Throwable {
        MyService myService = new MyService();
        MyDynamicProxy myDynamicProxy = new MyDynamicProxy(myService);
        Method method = MyService.class.getMethod("doIt");
        Object invoke = myDynamicProxy.invoke(myService, method, null);
        System.out.println("invoke = " + invoke);

        MyDynamicProxy obj = (MyDynamicProxy) new MyFactory().create();
        System.out.println("obj = " + obj);
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
            System.out.println("MyDynamicProxy.invoke() - start");
            try {
                return method.invoke(this.proxy, args);
            } finally {
                System.out.println("MyDynamicProxy.invoke() - slutt");
            }
        }
    }

    static class MyService {
        public void doIt() {
            System.out.println("MyService.doIt()");
        }
    }
}

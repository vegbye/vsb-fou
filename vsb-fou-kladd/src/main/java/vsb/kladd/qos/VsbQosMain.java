package vsb.kladd.qos;

import java.util.Arrays;

import org.apache.log4j.Logger;

public class VsbQosMain {

    private static final Logger log = Logger.getLogger(VsbQosMain.class);

    public static void main(String[] args) throws Exception {
        VsbMyManager myManager1 = new VsbMyManagerImpl();
        VsbQos annotation1 = ((VsbMyManagerImpl) myManager1).getClass().getMethod("f").getAnnotation(VsbQos.class);
        log.info("1. annotation ? " + annotation1);
        log.info("1. annotation ? " + Arrays.toString(myManager1.getClass().getMethod("f").getDeclaredAnnotations()));
        log.info("1. annotation ? " + Arrays.toString(myManager1.getClass().getMethod("f").getAnnotations()));

        VsbMyManagerImpl myManager2 = new VsbMyManagerImpl();
        VsbQos annotation2 = myManager2.getClass().getMethod("f").getAnnotation(VsbQos.class);
        log.info("2. annotation ? " + annotation2);
        log.info("2. annotation ? " + Arrays.toString(myManager2.getClass().getMethod("f").getDeclaredAnnotations()));
        log.info("2. annotation ? " + Arrays.toString(myManager2.getClass().getMethod("f").getAnnotations()));

        VsbMyManager myManager3 = new VsbMyManagerImpl();
        VsbQos annotation3 = ((VsbMyManagerImpl) myManager3).getClass().getMethod("f").getAnnotation(VsbQos.class);
        log.info("3. annotation ? " + annotation3);
        log.info("3. annotation ? " + Arrays.toString(myManager3.getClass().getMethod("f").getDeclaredAnnotations()));
        log.info("3. annotation ? " + Arrays.toString(myManager3.getClass().getMethod("f").getAnnotations()));
    }
}

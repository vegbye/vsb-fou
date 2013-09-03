package vsb.kladd.spring;

import org.apache.log4j.Logger;

public class VsbSpringBeanAImpl implements VsbSpringBeanA {

    private static final Logger log = Logger.getLogger(VsbSpringBeanA.class);

    private String kaffe;

    public void perform() {
        log.info("Dagens kaffe: " + kaffe);
        log.info("Dagens kaffe: " + System.getProperty("vsb.kaffe"));
    }

    public void setKaffe(String kaffe) {
        this.kaffe = kaffe;
    }

}

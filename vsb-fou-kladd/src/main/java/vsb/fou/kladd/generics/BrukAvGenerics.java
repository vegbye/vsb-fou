package vsb.fou.kladd.generics;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BrukAvGenerics {

    private static final Logger LOGGER = LoggerFactory.getLogger(BrukAvGenerics.class);

    public static void main(String[] args) {
        MyImplementingClass clazz = new MyImplementingClass();

        MyImplementingClass kopi = clazz.lagKopi();
        LOGGER.info("kopi:" + kopi);

        MyGenericsInterface<MyImplementingClass> kopi2 = clazz.lagKopi();
        LOGGER.info("kopi2:" + kopi2);
    }

}

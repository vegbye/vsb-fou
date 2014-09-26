package vsb.fou.kladd.diverse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("unchecked")
public class BrukAvList {

    private static final Logger LOGGER = LoggerFactory.getLogger(BrukAvList.class);

    public static void main(String[] args) {
        List list = new ArrayList();
        list.add("hei");
        list.add("deg");
        list.add(list);
        list.add("meg");
        list.add(list);

        for (Object object : list) {
            LOGGER.info("object ? " + object);
            if (object instanceof List) {
                List l = (List) object;
                for (Object object2 : l) {
                    LOGGER.info("object2 ? " + object2);
                    if (object2 instanceof List) {
                        for (Object object3 : l) {
                            LOGGER.info("object3 ? " + object3);
                        }
                    }
                }
            }
        }
        List list2 = new ArrayList(list);
        LOGGER.info("like ? " + list.equals(list2));
    }
}

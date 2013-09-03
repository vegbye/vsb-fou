package vsb.kladd;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("unchecked")
public class BrukAvList {

    public static void main(String[] args) {
        List list = new ArrayList();
        list.add("hei");
        list.add("deg");
        list.add(list);
        list.add("meg");
        list.add(list);

        for (Object object : list) {
            System.out.println("object ? " + object);
            if (object instanceof List) {
                List l = (List) object;
                for (Object object2 : l) {
                    System.out.println("object2 ? " + object2);
                    if (object2 instanceof List) {
                        for (Object object3 : l) {
                            System.out.println("object3 ? " + object3);
                        }
                    }
                }
            }
        }
        List list2 = new ArrayList(list);
        System.out.println("like ? " + list.equals(list2));
    }
}

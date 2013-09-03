package vsb.generics;

import java.util.ArrayList;
import java.util.List;

public class GenericsGotchas {

    public static void main(String[] args) {
        List<A> listeAvA = new ArrayList<A>();

        // doIt(listeAvA); // kompileringsfeil

        A erDetteVirkeligEnA = listeAvA.get(0);
    }

    public static void doIt(List<B> liste) {
        B c = new C();
        liste.add(c);
        liste.add(new A());
    }
}

interface B {
}

class A implements B {
}

class C implements B {
}

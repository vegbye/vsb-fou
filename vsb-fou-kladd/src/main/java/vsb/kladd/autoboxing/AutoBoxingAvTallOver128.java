package vsb.kladd.autoboxing;

public class AutoBoxingAvTallOver128 {

    public static void main(String[] args) {

        for (int i = 0; i < 10; i++) {
            Integer gg = new Integer(i);
            Integer kk = new Integer(i);
            if (gg != kk) {
                System.out.println(gg + "!=" + kk);
            }

            if ((gg + 1) == (kk + 1)) {
                System.out.println(gg + "+1==1+" + kk);
            }

        }

        Integer g = 1;
        Integer k = 1;
        for (int i = 0; i < 10000; i++) {
            g++;
            k++;
            if (g != k) {
                throw new IllegalStateException("Hæææ !?¤#%¤%&: g: " + g + " k: " + k);
            }
        }
        System.out.println("ok");
    }
}

package vsb.generics.merkelig;

import java.util.Random;

public class KonkretKlasse implements MerkeligInterface, Cloneable {

    private static final Random rand = new Random();

    private final int data;

    public KonkretKlasse() {
        data = rand.nextInt(100);
    }

    @Override
    protected KonkretKlasse clone() {
        try {
            return (KonkretKlasse) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException("Dette skal aldri ikke skje!?");
        }
    }

    public <T extends MerkeligInterface> T lagKopi() {
        KonkretKlasse kopi = clone();

        @SuppressWarnings("unchecked")
        T t = (T) kopi;
        return t;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append(this.getClass().getSimpleName() + "[");
        builder.append("data: " + data);
        builder.append("]");
        return builder.toString();
    }
}

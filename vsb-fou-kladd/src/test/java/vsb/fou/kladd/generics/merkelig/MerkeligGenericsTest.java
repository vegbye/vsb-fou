package vsb.fou.kladd.generics.merkelig;

import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class MerkeligGenericsTest {

    @Test
    public void test_merkelig_generics_oppfoersel() {
        MerkeligInterface konkretKlasse = new KonkretKlasse();
        System.out.println(konkretKlasse);
        MerkeligInterface kopi = konkretKlasse.lagKopi();
        System.out.println(kopi);
        MerkeligInterface kopi2 = kopier(konkretKlasse);
        System.out.println(kopi2);
        MerkeligInterface kopi3 = kopier2(konkretKlasse);
        System.out.println(kopi3);

        assertTrue(kopi != konkretKlasse);
        assertTrue(kopi2 != konkretKlasse);
    }

    private <T extends MerkeligInterface> T kopier(T t) {
        T kopi = t.lagKopi();
        return kopi;
    }

    private MerkeligInterface kopier2(MerkeligInterface t) {
        MerkeligInterface kopi = t.lagKopi();
        return kopi;
    }
}

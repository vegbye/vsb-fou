package vsb.fou.kladd.generics.merkelig;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.Assert.assertTrue;

public class MerkeligGenericsTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(MerkeligGenericsTest.class);

    @Test
    public void test_merkelig_generics_oppfoersel() {
        MerkeligInterface konkretKlasse = new KonkretKlasse();
        LOGGER.info("Klasse:" + konkretKlasse);
        MerkeligInterface kopi = konkretKlasse.lagKopi();
        LOGGER.info("Kopi:" + kopi);
        MerkeligInterface kopi2 = kopier(konkretKlasse);
        LOGGER.info("Kopi2:" + kopi2);
        MerkeligInterface kopi3 = kopier2(konkretKlasse);
        LOGGER.info("Kopi3:" + kopi3);

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

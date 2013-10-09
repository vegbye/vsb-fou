package vsb.fou.kladd.serialization;

import org.apache.commons.lang.SerializationUtils;
import org.apache.log4j.Logger;
import org.junit.Test;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class VsbSerializableTest {

    private static final Logger log = Logger.getLogger(VsbSerializableTest.class);

    @Test
    public void demonstrerSerialisering() {
        VsbSubSerializable sub = new VsbSubSerializable();
        sub.setA("A");
        sub.setB("B");

        log.info("sub: " + sub);

        Object clone = SerializationUtils.clone(sub);
        log.info("clone: " + clone);

        assertEquals(sub, clone);

        List<VsbSuperSerializable> liste = new ArrayList<VsbSuperSerializable>();
        liste.add(sub);
        log.info("liste: " + liste);
        Object cloneListe = SerializationUtils.clone((Serializable) liste);
        log.info("cloneListe: " + cloneListe);
        assertEquals(liste, cloneListe);
    }
}

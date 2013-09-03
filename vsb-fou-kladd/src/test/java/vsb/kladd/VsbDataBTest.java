package vsb.kladd;

import static org.junit.Assert.assertEquals;

import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;

public class VsbDataBTest {

    private static final Logger log = Logger.getLogger(VsbDataBTest.class);

    private VsbDataB b;
    private VsbDataB bb;

    @Before
    public void createData() {
        String s = "BBB";
        int k = 987;

        b = new VsbDataB();
        b.setSS(s);
        b.setKK(k);

        bb = new VsbDataB();
        bb.setSS(s);
        bb.setKK(k);
    }

    @Test
    public void testToString() {
        log.info(b);
        log.info(bb);
    }

    @Test
    public void testEquals() {
        assertEquals(b, bb);
    }

    @Test
    public void testHasCode() {
        assertEquals(b.hashCode(), bb.hashCode());
    }

    @Test
    public void testCompareTo() {
        assertEquals(0, b.compareTo(bb));
        assertEquals(0, bb.compareTo(b));
    }

    @Test
    public void testClone() {
        assertEquals(b, b.clone());
        assertEquals(bb, bb.clone());
        assertEquals(b, bb.clone());
        assertEquals(bb, b.clone());
    }
}

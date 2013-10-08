package vsb.fou.kladd;

import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;
import vsb.fou.kladd.diverse.VsbDataA;

import static org.junit.Assert.assertEquals;

public class VsbDataATest {

    private static final Logger log = Logger.getLogger(VsbDataATest.class);

    private VsbDataA a;
    private VsbDataA aa;

    @Before
    public void createData() {
        String s = "AAA";
        int k = 123;

        a = new VsbDataA();
        a.setS(s);
        a.setK(k);

        aa = new VsbDataA();
        aa.setS(s);
        aa.setK(123);

    }

    @Test
    public void testToString() {
        log.info(a);
        log.info(aa);
    }

    @Test
    public void testEquals() {
        assertEquals(a, aa);
        assertEquals(aa, a);
    }

    @Test
    public void testHasCode() {
        assertEquals(a.hashCode(), aa.hashCode());
    }

    @Test
    public void testCompareTo() {
        assertEquals(0, a.compareTo(aa));
        assertEquals(0, aa.compareTo(a));
    }

    @Test
    public void testClone() {
        assertEquals(a, a.clone());
        assertEquals(aa, aa.clone());
        assertEquals(a, aa.clone());
        assertEquals(aa, a.clone());
    }
}

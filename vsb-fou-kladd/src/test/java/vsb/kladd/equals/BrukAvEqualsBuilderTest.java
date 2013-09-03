package vsb.kladd.equals;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.junit.Test;

public class BrukAvEqualsBuilderTest {

    @Test
    public void testIt() {
        VsbDataEqualsB b = new VsbDataEqualsB("Billingstadlia");
        VsbDataEqualsA a = new VsbDataEqualsA("Vegard", b);

        VsbDataEqualsB b2 = new VsbDataEqualsB("Billingstadlia");
        VsbDataEqualsA a2 = new VsbDataEqualsA("Vegard", b2);

        assertFalse(a.equals(a2));
        assertFalse(EqualsBuilder.reflectionEquals(a, a2));
        assertTrue(EqualsBuilder.reflectionEquals(a, a2, Arrays.asList("b")));

    }
}

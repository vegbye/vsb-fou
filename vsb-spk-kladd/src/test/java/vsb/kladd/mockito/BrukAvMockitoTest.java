package vsb.kladd.mockito;

import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Matchers;
import org.mockito.Mockito;

public class BrukAvMockitoTest {

    private MinTjeneste minTjeneste;

    @Before
    public void settMegOpp() {
        minTjeneste = new MinTjeneste();
        MittGrensesnitt mittGrensesnittMock = Mockito.mock(MittGrensesnitt.class);
        Mockito.when(mittGrensesnittMock.hentMineData(Matchers.any(String.class))).thenReturn(new MineData());
        minTjeneste.setMittGrensesnitt(mittGrensesnittMock);
    }

    @Test
    public void testIt() {
        Assert.assertThat(minTjeneste.kjoer("hei på deg"), CoreMatchers.is(true));
    }
}

class MinTjeneste {

    private MittGrensesnitt mittGrensesnitt;

    boolean kjoer(String id) {
        mittGrensesnitt.hentMineData(id);
        return true;
    }

    public void setMittGrensesnitt(MittGrensesnitt mittGrensesnitt) {
        this.mittGrensesnitt = mittGrensesnitt;
    }
}

interface MittGrensesnitt {

    public MineData hentMineData(String id);
}

class MineData {
}

package id.web.gama.waterpoints.control;

import id.web.gama.waterpoints.entity.WaterPoint;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.hamcrest.Matchers.equalToIgnoringCase;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * Created by gama on 10/21/17.
 */
public class WaterPointGatewayTest {
    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void initWaterPoints() throws Exception {
        WaterPointGateway cut = new WaterPointGateway();
        List<WaterPoint> actual = cut.initWaterPoints();

        assertThat(actual.size(), is(712));
        assertThat(actual.get(0).getVillageName(), is("Gumaryili"));
        assertThat(actual.get(0).getWaterFunctioning(), equalToIgnoringCase("yes"));
    }

}
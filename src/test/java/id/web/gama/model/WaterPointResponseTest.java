package id.web.gama.model;

import com.google.common.collect.Lists;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by gama on 10/19/17.
 */
public class WaterPointResponseTest {
    private WaterPointResponse waterPointResponse = new WaterPointResponse();
    @Before
    public void setUp() throws Exception {
        waterPointResponse.setWaterPoints(Lists.newArrayList(
                new WaterPoint("village1", "yes"),
                new WaterPoint("village2", "no"),
                new WaterPoint("village3", "na_dn"),
                new WaterPoint("village4", "yes"),
                new WaterPoint("village4", "yes"),
                new WaterPoint("village4", "na_dn")
        ));
    }

    @Test
    public void shouldReturn3ForFunctioningWaterPoint() throws Exception {
        assertEquals(3, waterPointResponse.getFunctionalWaterPoint().size());
    }

    @Test
    public void shouldReturn1ForNonFunctioningWaterPoint() throws Exception {
        assertEquals(1, waterPointResponse.getNonFunctionalWaterPoint().size());
    }

    @Test
    public void shouldReturn2ForUnknownFunctioningWaterPoint() throws Exception {
        assertEquals(2, waterPointResponse.getUnknownFunctionalWaterPoint().size());
    }

    @Test
    public void shouldReturn4UniqueVillage() {
        assertEquals(4, waterPointResponse.getVillagesName(waterPointResponse.getWaterPoints()).size());
    }

}
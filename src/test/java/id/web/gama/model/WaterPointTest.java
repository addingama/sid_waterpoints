package id.web.gama.model;

import static org.junit.Assert.*;

/**
 * Created by gama on 10/19/17.
 */
public class WaterPointTest {
    private WaterPoint waterPoint;
    private String functioning = "Yes";
    private String villageName = "Mataram";

    @org.junit.Before
    public void setUp() throws Exception {
        waterPoint = new WaterPoint(villageName, functioning);
    }

    @org.junit.Test
    public void getCommunityVillages() throws Exception {
        assertEquals("Mataram", waterPoint.getCommunityVillages());
        assertNotEquals("mataram", waterPoint.getCommunityVillages());
    }

    @org.junit.Test
    public void setCommunityVillages() throws Exception {
        assertEquals(waterPoint.getCommunityVillages(), "Mataram");
        waterPoint.setCommunityVillages("Cakranegara");
        assertEquals(waterPoint.getCommunityVillages(), "Cakranegara");
    }

    @org.junit.Test
    public void getWaterFunctioning() throws Exception {
        assertEquals("Yes", waterPoint.getWaterFunctioning());
        assertNotEquals("yes", waterPoint.getWaterFunctioning());
    }

    @org.junit.Test
    public void setWaterFunctioning() throws Exception {
        assertEquals("Yes", waterPoint.getWaterFunctioning());
        waterPoint.setWaterFunctioning("No");
        assertEquals("No", waterPoint.getWaterFunctioning());
    }

}
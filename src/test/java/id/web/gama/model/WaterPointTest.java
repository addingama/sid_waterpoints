package id.web.gama.model;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by gama on 10/19/17.
 */
public class WaterPointTest {
    private Gson gson = new Gson();
    private WaterPoint waterPoint;
    private String functioning = "Yes";
    private String villageName = "Mataram";

    @Before
    public void setUp() throws Exception {
        waterPoint = new WaterPoint(villageName, functioning);
    }

    @Test
    public void getCommunityVillages() throws Exception {
        assertEquals("Mataram", waterPoint.getCommunityVillages());
        assertNotEquals("mataram", waterPoint.getCommunityVillages());
    }

    @Test
    public void setCommunityVillages() throws Exception {
        assertEquals(waterPoint.getCommunityVillages(), "Mataram");
        waterPoint.setCommunityVillages("Cakranegara");
        assertEquals(waterPoint.getCommunityVillages(), "Cakranegara");
    }

    @Test
    public void getWaterFunctioning() throws Exception {
        assertEquals("Yes", waterPoint.getWaterFunctioning());
        assertNotEquals("yes", waterPoint.getWaterFunctioning());
    }

    @Test
    public void setWaterFunctioning() throws Exception {
        assertEquals("Yes", waterPoint.getWaterFunctioning());
        waterPoint.setWaterFunctioning("No");
        assertEquals("No", waterPoint.getWaterFunctioning());
    }

    @Test
    public void shouldReturnNullAttributeBecauseofInvalidName() {
        JsonObject wpJson = new JsonObject();
        wpJson.addProperty("communityVillages", villageName);
        wpJson.addProperty("waterFunctioning", functioning);
        WaterPoint wp = gson.fromJson(wpJson, WaterPoint.class);
        assertEquals(null, wp.getCommunityVillages());
        assertEquals(null, wp.getWaterFunctioning());
    }

    @Test
    public void shouldReturnValueAttributeBecauseofValidName() {
        JsonObject wpJson = new JsonObject();
        wpJson.addProperty("communities_villages", villageName);
        wpJson.addProperty("water_functioning", functioning);
        WaterPoint wp = gson.fromJson(wpJson, WaterPoint.class);
        assertEquals(villageName, wp.getCommunityVillages());
        assertEquals(functioning, wp.getWaterFunctioning());
    }

}
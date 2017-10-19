package id.web.gama.service;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

/**
 * Created by gama on 10/19/17.
 */
public interface WaterPointService {
    @GET
    @Path("onaio/ona-tech/master/data/water_points.json")
    @Produces("application/json")
    String getWaterPoints();
}

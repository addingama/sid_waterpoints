package id.web.gama.waterpoints.control;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import id.web.gama.waterpoints.entity.WaterPoint;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;
import java.lang.reflect.Type;
import java.util.List;

/**
 * Created by gama on 10/20/17.
 */
public class WaterPointGateway {
    public static final String WATER_SERVICE_URL = "https://raw.githubusercontent.com/onaio/ona-tech/master/data/water_points.json";

    public List<WaterPoint> initWaterPoints(){
        Gson gson = new Gson();

        Client client = ClientBuilder.newBuilder().build();
        WebTarget target = client.target(WATER_SERVICE_URL);
        Response response = target.request().get();
        String value = response.readEntity(String.class);

        Type listType = new TypeToken<List<WaterPoint>>(){}.getType();
        List<WaterPoint> waterPoints = gson.fromJson(value, listType);

        response.close();

        return waterPoints;
    }
}

package id.web.gama;

import com.google.gson.Gson;
import id.web.gama.model.WaterPoint;
import id.web.gama.model.WaterPointResponse;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by gama on 10/19/17.
 */
public class Main {
    private static final String URL = "https://raw.githubusercontent.com/onaio/ona-tech/master/data/water_points.json";
    private List<WaterPoint> waterPoints = new ArrayList<WaterPoint>();

    public static void main(String [] args) {
        System.out.println("SID advance IT Test");
        Main main = new Main();
        main.waterPoints = main.getWaterPoints(URL).getWaterPoints();
        System.out.printf("Jumlah Water Point : %d\n\n", main.waterPoints.size());
        System.out.println(main.waterPoints.get(0));
    }

    private WaterPointResponse getWaterPoints(String url) {
        Gson gson = new Gson();
        Client client = ClientBuilder.newBuilder().build();
        WebTarget target = client.target(url);
        Response response = target.request().get();
        String value = "{\"waterPoints\":" + response.readEntity(String.class) + "}";
        response.close();  // You should close connections!
        System.out.println(value);
        return gson.fromJson(value, WaterPointResponse.class);
    }
}

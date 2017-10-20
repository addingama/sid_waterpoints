package id.web.gama;

import com.google.gson.Gson;
import id.web.gama.model.WaterPoint;
import id.web.gama.model.WaterPointResponse;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by gama on 10/19/17.
 */
public class Main {
    private static final String URL = "https://raw.githubusercontent.com/onaio/ona-tech/master/data/water_points.json";
    private WaterPointResponse response;

    public static void main(String [] args) {
        System.out.println("SID advance IT Test");
        Main main = new Main();
        main.response = main.getWaterPoints(URL);
        System.out.printf("Jumlah Water Point : %d\n\n", main.response.getWaterPoints().size());
        System.out.printf("Jumlah Water Point yang berfungsi : %d\n", main.response.getFunctionalWaterPoint().size());
        System.out.printf("Jumlah Water Point yang tidak berfungsi : %d\n", main.response.getNonFunctionalWaterPoint().size());
        List<WaterPoint> wpUnknownFunction = main.response.getUnknownFunctionalWaterPoint();
        System.out.printf("Jumlah Water Point yang tidak diketahui fungsinya : %d\n", wpUnknownFunction.size());
        main.response.printWaterPoints(wpUnknownFunction);
        System.out.printf("\n\nJumlah village : %s", main.response.getVillagesName(main.response.getWaterPoints()).size());
    }

    /**
     * Get water point data from url and return as a list of water point encapsulated on WaterPointResponse class
     * @param url
     * @return WaterPointResponse
     */
    private WaterPointResponse getWaterPoints(String url) {
        Gson gson = new Gson();
        Client client = ClientBuilder.newBuilder().build();
        WebTarget target = client.target(url);
        Response response = target.request().get();
        String value = String.format("{\"waterPoints\": %s}", response.readEntity(String.class));
        response.close();
        System.out.println(value);
        return gson.fromJson(value, WaterPointResponse.class);

    }
}

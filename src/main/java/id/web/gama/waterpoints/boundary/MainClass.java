package id.web.gama.waterpoints.boundary;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import id.web.gama.waterpoints.control.WaterPointGateway;
import id.web.gama.waterpoints.control.WaterPointProcessor;
import id.web.gama.waterpoints.entity.SortType;
import id.web.gama.waterpoints.entity.VillageStatistic;
import id.web.gama.waterpoints.entity.WaterPoint;

import java.util.List;

/**
 * Created by gama on 10/21/17.
 */
public class MainClass {
    public static void main(String[] args) {
        WaterPointGateway gateway = new WaterPointGateway();
        List<WaterPoint> waterPoints = gateway.initWaterPoints();

        WaterPointProcessor processor = new WaterPointProcessor(waterPoints);

        Gson gson = new Gson();
        List<VillageStatistic> stats = processor.stats();
        List<VillageStatistic> statsOrderedByNonFunctioningRank = processor.sortStats(stats, SortType.NON_FUNCTION_DESC);
        JsonElement villageStatistic = gson.toJsonTree(statsOrderedByNonFunctioningRank);
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("total_water_points", processor.countAllWaterPoint());
        jsonObject.addProperty("total_functioning", processor.countAllFunctioningWaterPoint());
        jsonObject.addProperty("total_non_functioning", processor.countAllNonFunctioningWaterPoint());
        jsonObject.addProperty("total_unknown_functioning", processor.countAllUnknowFunctioningWaterPoint());
        jsonObject.add("village_statistic", villageStatistic);

        System.out.println(jsonObject);
    }
}

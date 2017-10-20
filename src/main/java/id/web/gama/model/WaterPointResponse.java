package id.web.gama.model;

import com.google.common.collect.FluentIterable;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * Created by gama on 10/19/17.
 */
public class WaterPointResponse {

    private List<WaterPoint> waterPoints;

    public List<WaterPoint> getWaterPoints() {
        return waterPoints;
    }

    public void setWaterPoints(List<WaterPoint> waterPoints) {
        this.waterPoints = waterPoints;
    }

    public List<WaterPoint> getFunctionalWaterPoint() {
        Predicate<WaterPoint> byFunctioning = waterPoint -> waterPoint.getWaterFunctioning().equalsIgnoreCase("yes");

        return this.waterPoints.stream().filter(byFunctioning).collect(Collectors.<WaterPoint> toList());
    }

    public List<WaterPoint> getNonFunctionalWaterPoint() {
        Predicate<WaterPoint> byFunctioning = waterPoint -> waterPoint.getWaterFunctioning().equalsIgnoreCase("no");

        return this.waterPoints.stream().filter(byFunctioning).collect(Collectors.<WaterPoint> toList());
    }

    public  List<WaterPoint> getUnknownFunctionalWaterPoint() {
        Predicate<WaterPoint> byFunctioning = waterPoint -> (!waterPoint.getWaterFunctioning().equalsIgnoreCase("no")
                && !waterPoint.getWaterFunctioning().equalsIgnoreCase("yes"));

        return this.waterPoints.stream().filter(byFunctioning).collect(Collectors.<WaterPoint> toList());
    }

    public void printWaterPoints(List<WaterPoint> waterPoints) {
        for (WaterPoint wp: waterPoints) {
            System.out.println(wp);
        }
    }
}

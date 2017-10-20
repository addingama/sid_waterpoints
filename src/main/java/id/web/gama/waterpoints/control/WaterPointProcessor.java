package id.web.gama.waterpoints.control;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Ordering;
import id.web.gama.waterpoints.entity.SortType;
import id.web.gama.waterpoints.entity.VillageStatistic;
import id.web.gama.waterpoints.entity.WaterPoint;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

/**
 * Created by gama on 10/21/17.
 */
public class WaterPointProcessor {
    List<WaterPoint> waterPoints = Lists.newArrayList();
    Predicate<WaterPoint> byFunctioning = waterPoint -> waterPoint.getWaterFunctioning().equalsIgnoreCase("yes");
    Predicate<WaterPoint> byNonFunctioning = waterPoint -> waterPoint.getWaterFunctioning().equalsIgnoreCase("no");
    Predicate<WaterPoint> byUnknownFunctioning = waterPoint -> (!waterPoint.getWaterFunctioning().equalsIgnoreCase("no") &&
            !waterPoint.getWaterFunctioning().equalsIgnoreCase("yes"));

    public WaterPointProcessor(List<WaterPoint> waterPoints) {
        this.waterPoints = waterPoints;
    }

    public Integer countAllWaterPoint() {
        return waterPoints.size();
    }

    public Integer countAllFunctioningWaterPoint() {
        return waterPoints.stream().filter(byFunctioning).collect(Collectors.toList()).size();
    }
    public Integer countAllNonFunctioningWaterPoint() {
        return waterPoints.stream().filter(byNonFunctioning).collect(Collectors.toList()).size();
    }
    public Integer countAllUnknowFunctioningWaterPoint() {
        return waterPoints.stream().filter(byUnknownFunctioning).collect(Collectors.toList()).size();
    }

    public List<VillageStatistic> stats() {
        Map<String, List<WaterPoint>> waterPointsGroup = waterPoints.stream()
                .collect(Collectors.groupingBy(WaterPoint::getVillageName, toList()));

        Map<String, VillageStatistic> mapResult = Maps.transformEntries(waterPointsGroup, (villageName, waterPoints1) -> {
            Integer totalWaterPoints = waterPoints1.size();
            Integer functionalWaterPoints = waterPoints1.stream().filter(byFunctioning).collect(Collectors.toList()).size();
            Integer nonFunctionalWaterPoints = waterPoints1.stream().filter(byNonFunctioning).collect(Collectors.toList()).size();
            Float functioningPercentage = countWaterPointPercentage(totalWaterPoints, functionalWaterPoints);
            Float nonFunctioningPercentage = countWaterPointPercentage(totalWaterPoints, nonFunctionalWaterPoints);

            return new VillageStatistic(
                    villageName,
                    totalWaterPoints,
                    functionalWaterPoints,
                    nonFunctionalWaterPoints,
                    functioningPercentage,
                    nonFunctioningPercentage
            );
        });

        return Lists.newArrayList(mapResult.values());
    }

    public List<VillageStatistic> sortStats(List<VillageStatistic> statistics, SortType sortType) {
        switch (sortType) {
            case FUNCTION_ASC:
                Collections.sort(statistics, new Ordering<VillageStatistic>() {
                    @Override
                    public int compare(VillageStatistic v1, VillageStatistic v2) {
                        return v1.getFunctioningPercentage().compareTo(v2.getFunctioningPercentage());
                    }
                });
                break;
            case FUNCTION_DESC:
                Collections.sort(statistics, new Ordering<VillageStatistic>() {
                    @Override
                    public int compare(VillageStatistic v1, VillageStatistic v2) {
                        return v2.getFunctioningPercentage().compareTo(v1.getFunctioningPercentage());
                    }
                });
                break;
            case NON_FUNCTION_ASC:
                Collections.sort(statistics, new Ordering<VillageStatistic>() {
                    @Override
                    public int compare(VillageStatistic v1, VillageStatistic v2) {
                        return v1.getNonFunctioningPercentage().compareTo(v2.getNonFunctioningPercentage());
                    }
                });
                break;
            case NON_FUNCTION_DESC:
                Collections.sort(statistics, new Ordering<VillageStatistic>() {
                    @Override
                    public int compare(VillageStatistic v1, VillageStatistic v2) {
                        return v2.getNonFunctioningPercentage().compareTo(v1.getNonFunctioningPercentage());
                    }
                });
                break;
        }

        return statistics;
    }

    float countWaterPointPercentage(Integer totalWaterPoints, Integer functionalWaterPoints) {
        if (totalWaterPoints <= 0) {
            return 0;
        }
        return Float.valueOf(functionalWaterPoints) / Float.valueOf(totalWaterPoints) * 100;
    }
}

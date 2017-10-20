package id.web.gama.waterpoints.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Created by gama on 10/21/17.
 */
@Data
@AllArgsConstructor
public class VillageStatistic {
    private String villageName;
    private Integer totalWater;
    private Integer totalWaterFunctioning;
    private Integer totalWaterNonFunctioning;
    private Float functioningPercentage;
    private Float nonFunctioningPercentage;

}

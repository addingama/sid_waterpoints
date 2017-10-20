package id.web.gama.waterpoints.entity;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Created by gama on 10/20/17.
 */
@Data
@AllArgsConstructor
public class WaterPoint {
    @SerializedName("communities_villages")
    private String villageName;
    @SerializedName("water_functioning")
    private String waterFunctioning;
}
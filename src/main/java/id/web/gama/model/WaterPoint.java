package id.web.gama.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by gama on 10/19/17.
 */
public class WaterPoint {
    @SerializedName("communities_villages")
    private String communityVillages;
    @SerializedName("water_functioning")
    private String waterFunctioning;

    public WaterPoint(String communityVillages, String waterFunctioning) {
        this.communityVillages = communityVillages;
        this.waterFunctioning = waterFunctioning;
    }

    public String getCommunityVillages() {
        return communityVillages;
    }

    public void setCommunityVillages(String communityVillages) {
        this.communityVillages = communityVillages;
    }

    public String getWaterFunctioning() {
        return waterFunctioning;
    }

    public void setWaterFunctioning(String waterFunctioning) {
        this.waterFunctioning = waterFunctioning;
    }

    @Override
    public String toString() {
        return "WaterPoint{" +
                "communityVillages='" + communityVillages + '\'' +
                ", waterFunctioning='" + waterFunctioning + '\'' +
                '}';
    }
}

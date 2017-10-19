package id.web.gama.model;

/**
 * Created by gama on 10/19/17.
 */
public class WaterPoint {
    private String communityVillages;
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
}

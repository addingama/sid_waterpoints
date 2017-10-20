package id.web.gama.waterpoints.control;

import com.google.common.collect.Lists;
import id.web.gama.waterpoints.entity.SortType;
import id.web.gama.waterpoints.entity.VillageStatistic;
import id.web.gama.waterpoints.entity.WaterPoint;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.contains;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * Created by gama on 10/21/17.
 */
public class WaterPointProcessorTest {
    WaterPointProcessor cut;

    @Before
    public void setUp() throws Exception {
        List<WaterPoint> data = new ArrayList<WaterPoint>();
        data.add(new WaterPoint("sukajadi", "yes"));
        data.add(new WaterPoint("sukajadi", "no"));
        data.add(new WaterPoint("sukajadi", "yes"));
        data.add(new WaterPoint("warnasuka", "yes"));
        data.add(new WaterPoint("warnasuka", "no"));
        data.add(new WaterPoint("pasir kaliki", "no"));
        data.add(new WaterPoint("cibogo", "no"));
        data.add(new WaterPoint("kuburan", "yes"));
        data.add(new WaterPoint("kuburan", "yes"));
        data.add(new WaterPoint("kuburan", "na_dn"));

        cut = new WaterPointProcessor(data);

    }

    @Test
    public void countAllWaterPoint() {
        int actual = cut.countAllWaterPoint();
        assertThat(actual, is(10));
    }

    @Test
    public void countAllFunctioningWaterPoint() throws Exception {
        int actual = cut.countAllFunctioningWaterPoint();
        assertThat(actual, is(5));
    }

    @Test
    public void countAllNonFunctionalWaterPoint() throws Exception {
        int actual = cut.countAllNonFunctioningWaterPoint();
        assertThat(actual, is(4));
    }

    @Test
    public void countAllUnknownFunctionalWaterPoint() throws Exception {
        int actual = cut.countAllUnknowFunctioningWaterPoint();
        assertThat(actual, is(1));
    }

    @Test
    public void villageStats() throws Exception {
        List<VillageStatistic> actual = cut.stats();
        assertThat(actual.size(), is(5));
        assertThat(actual.get(0).getVillageName(), is("warnasuka"));
        assertThat(actual.get(0).getTotalWater(), is(2));
        assertThat(actual.get(0).getTotalWaterFunctioning(), is(1));
        assertThat(actual.get(0).getTotalWaterNonFunctioning(), is(1));

        assertThat(actual.get(1).getVillageName(), is("pasir kaliki"));
        assertThat(actual.get(1).getTotalWater(), is(1));
        assertThat(actual.get(1).getTotalWaterFunctioning(), is(0));
        assertThat(actual.get(1).getTotalWaterNonFunctioning(), is(1));

        assertThat(actual.get(2).getVillageName(), is("sukajadi"));
        assertThat(actual.get(2).getTotalWater(), is(3));
        assertThat(actual.get(2).getTotalWaterFunctioning(), is(2));
        assertThat(actual.get(2).getTotalWaterNonFunctioning(), is(1));

    }


    @Test
    public void sortByFuctioningAsc() throws Exception {
        List<VillageStatistic> data = Lists.newArrayList();
        VillageStatistic sukajadi = new VillageStatistic("sukajadi", 0, 0, 0, 10F, 20F);
        VillageStatistic pasirKaliki = new VillageStatistic("pasir kaliki", 0, 0, 0, 30F, 15F);
        VillageStatistic warnasuka = new VillageStatistic("warnasuka", 0, 0, 0, 24F, 50F);
        data.add(sukajadi);
        data.add(pasirKaliki);
        data.add(warnasuka);

        List<VillageStatistic> actual = cut.sortStats(data, SortType.FUNCTION_ASC);
        assertThat(actual, contains(sukajadi, warnasuka, pasirKaliki));
    }

    @Test
    public void sortByFunctioningDesc() {
        List<VillageStatistic> data = Lists.newArrayList();
        VillageStatistic sukajadi = new VillageStatistic("sukajadi", 0, 0, 0, 10F, 20F);
        VillageStatistic pasirKaliki = new VillageStatistic("pasir kaliki", 0, 0, 0, 30F, 15F);
        VillageStatistic warnasuka = new VillageStatistic("warnasuka", 0, 0, 0, 24F, 50F);
        data.add(sukajadi);
        data.add(pasirKaliki);
        data.add(warnasuka);

        List<VillageStatistic> actual = cut.sortStats(data, SortType.FUNCTION_DESC);
        assertThat(actual, contains(pasirKaliki, warnasuka, sukajadi));
    }

    @Test
    public void sortByNonFuctioningAsc() throws Exception {
        List<VillageStatistic> data = Lists.newArrayList();
        VillageStatistic sukajadi = new VillageStatistic("sukajadi", 0, 0, 0, 10F, 20F);
        VillageStatistic pasirKaliki = new VillageStatistic("pasir kaliki", 0, 0, 0, 30F, 15F);
        VillageStatistic warnasuka = new VillageStatistic("warnasuka", 0, 0, 0, 24F, 50F);
        data.add(sukajadi);
        data.add(pasirKaliki);
        data.add(warnasuka);

        List<VillageStatistic> actual = cut.sortStats(data, SortType.NON_FUNCTION_ASC);
        assertThat(actual, contains(pasirKaliki, sukajadi, warnasuka));
    }

    @Test
    public void sortByNonFuctioningDesc() throws Exception {
        List<VillageStatistic> data = Lists.newArrayList();
        VillageStatistic sukajadi = new VillageStatistic("sukajadi", 0, 0, 0, 10F, 20F);
        VillageStatistic pasirKaliki = new VillageStatistic("pasir kaliki", 0, 0, 0, 30F, 15F);
        VillageStatistic warnasuka = new VillageStatistic("warnasuka", 0, 0, 0, 24F, 50F);
        data.add(sukajadi);
        data.add(pasirKaliki);
        data.add(warnasuka);

        List<VillageStatistic> actual = cut.sortStats(data, SortType.NON_FUNCTION_DESC);
        assertThat(actual, contains(warnasuka, sukajadi, pasirKaliki));
    }
}
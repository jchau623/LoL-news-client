/**
 * Created by Jason on 2015-06-10.
 */
public class Region {
    public static String acronym;
    public static String RegionName;

    public static void setAcronym(String acronym) {
        Region.acronym = acronym;
    }

    public static void setRegionName(String regionName) {
        RegionName = regionName;
    }

    public String getRegionName(){
        return RegionName;
    }
    public String getAcronym(){
        return acronym;
    }
}

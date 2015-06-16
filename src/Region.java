/**
 * Created by Jason on 2015-06-10.
 */
public class Region {
    public  String acronym;
    public  String RegionName;

    public Region(/*String acronym, String name*/) {
       /* this.RegionName = name;
        this.acronym = acronym;*/
    }

    public void setAcronym(String acronym) {
        this.acronym = acronym;
    }

    public void setRegionName(String regionName) {
        this.RegionName = regionName;
    }

    public String getRegionName(){
        return RegionName;
    }
    public String getAcronym(){
        return acronym;
    }
}

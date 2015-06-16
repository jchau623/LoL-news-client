/**
 * Created by wesley on 2015-06-12.
 */
public class Champion {

    public String name ;
    public String cost;
    public Float winRate ;
    public String resourceType;
    public String type;

    public Champion(String name ,  Float winRate ,  String type) {
        this.name = name;
      //  this.cost = cost;
        this.winRate = winRate;
    //    this.resourceType = resourceType;
        this.type = type;
    }

    public void setName(String name) {
        this.name = name;
    }

   /* public static void setCost(String cost) {
        Champion.cost = cost;
    }*/

    public void setWinRate(Float winRate) {
        this.winRate = winRate;
    }

  /*  public static void setResourceType(String resourceType) {
        Champion.resourceType = resourceType;
    }*/

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {

        return name;
    }

  /*  public static String getCost() {
        return cost;
    }*/

    public Float getWinRate() {
        return winRate;
    }

    /*public static String getResourceType() {
        return resourceType;
    }*/

    public String getType() {
        return type;
    }



}

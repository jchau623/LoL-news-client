/**
 * Created by wesley on 2015-06-12.
 */
public class Champion {

    public static String name ;
    public static String cost;
    public static Float winRate ;
    public static String resourceType;
    public static String type;

    public Champion(String name ,  Float winRate ,  String type) {
        this.name = name;
      //  this.cost = cost;
        this.winRate = winRate;
    //    this.resourceType = resourceType;
        this.type = type;
    }

    public static void setName(String name) {
        Champion.name = name;
    }

   /* public static void setCost(String cost) {
        Champion.cost = cost;
    }*/

    public static void setWinRate(Float winRate) {
        Champion.winRate = winRate;
    }

  /*  public static void setResourceType(String resourceType) {
        Champion.resourceType = resourceType;
    }*/

    public static void setType(String type) {
        Champion.type = type;
    }

    public static String getName() {

        return name;
    }

  /*  public static String getCost() {
        return cost;
    }*/

    public static Float getWinRate() {
        return winRate;
    }

    /*public static String getResourceType() {
        return resourceType;
    }*/

    public static String getType() {
        return type;
    }



}

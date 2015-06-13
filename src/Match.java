import java.util.Date;

/**
 * Created by Vivian on 2015-06-12.
 */
public class Match {
    public static int matchID;
    public static int totalKills;
    public static int numberOfBarons;
    public static int numberOfDragons;
    public static int time;
    public static int totalGold;
    public static String blueName;
    public static String redName;
    public static Date datePlayed;


    public int returnID() {
        return matchID ;
    }
    public void setID(int id) {
        this.matchID = id;
    }
    public void setTotalKills(int kills) {
        this.totalKills = kills;
    }
    public int returnTotalKills() {
        return totalKills;
    }
    public void setNumberOfBarons(int numBarons) {
        this.numberOfBarons = numBarons;
    }
    public int returnNumberOfBarons() {
        return numberOfBarons;
    }
    public void setNumberOfDragons(int numDragons) {
        this.numberOfDragons = numDragons;
    }
    public int returnNumberOfDragons() {
        return this.numberOfDragons;
    }
    public void setTime(int time) {
        this.time = time;
    }
    public int returnTime() {
        return time;
    }

    public void setTotalGold(int totalGold){
        this.totalGold = totalGold;
    }

    public int returnTotalGold() {return totalGold;}

    public void setBlueName(String blueName) {
        this.blueName = blueName;
    }
    public String returnBlueName(){
        return blueName;
    }
    public void setRedName(String redName) {this.redName = redName;}
    public String getRedName(){
        return redName;
    }
    public void setDatePlayed(Date date){
        this.datePlayed = date;
    }
    public Date getDatePlayed(){
        return datePlayed;
    }
}

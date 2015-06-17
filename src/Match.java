import java.util.Date;

/**
 * Created by Vivian on 2015-06-12.
 */
public class Match {
    public int matchID;
    public int totalKills;
    public int redNumberOfBarons;
    public int redNumberOfDragons;
    public int blueNumberOfBarons;
    public int blueNumberOfDragons;
    public String time;
    public int totalGold;
    public String blueName;
    public String redName;
    public Date datePlayed;
    public String winner;


    public int returnID() {
        return matchID;
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

    public void setRedNumberOfDragons(int numDragons) {
        this.redNumberOfDragons = numDragons;
    }

    public int returnRedNumberOfDragons() {
        return redNumberOfDragons;
    }

    public void setRedNumberOfBarons(int numBarons) {
        this.redNumberOfBarons = numBarons;
    }

    public int returnRedNumberOfBarons() {
        return redNumberOfBarons;
    }

    public void setBlueNumberOfBarons(int numBarons) {
        this.blueNumberOfBarons = numBarons;
    }

    public int returnBlueNumberOfBarons() {
        return this.blueNumberOfBarons;
    }

    public void setBlueNumberOfDragons(int numDragons) {
        this.blueNumberOfDragons = numDragons;
    }

    public int returnBlueNumberOfDragons() {
        return this.blueNumberOfDragons;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String returnTime() {
        return time;
    }

    public void setTotalGold(int totalGold) {
        this.totalGold = totalGold;
    }

    public int returnTotalGold() {
        return totalGold;
    }

    public void setBlueName(String blueName) {
        this.blueName = blueName;
    }

    public String returnBlueName() {
        return blueName;
    }

    public void setRedName(String redName) {
        this.redName = redName;
    }

    public String returnRedName() {
        return redName;
    }

    public void setDatePlayed(Date date) {
        this.datePlayed = date;
    }

    public Date returnDatePlayed() {
        return datePlayed;
    }

    public String returnWinner() {return winner; }

    public void setWinner(String winner) {this.winner = winner;}
}

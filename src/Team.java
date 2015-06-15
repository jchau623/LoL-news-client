

/**
 * Created by wesley on 2015-06-10.
 */
public class Team {

    public  String teamName ;
    public  int wins;
    public  int losses;
    public  String sponsor;
    public  String acronym;
    public  Float averageDragons;
    public  Float averageBarons;
    public  String region;

    public String returnTeamName() {
        return teamName;
    }

    public int returnWins(){
        return wins ;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public void setWins(int wins) {
        this.wins = wins;
    }

    public void setLosses(int losses) {
        this.losses = losses;
    }

    public void setSponsor(String sponsor) {
        this.sponsor = sponsor;
    }

    public void setAcronym(String acronym) {
        this.acronym = acronym;
    }

    public void setAverageDragons(Float averageDragons) {
        this.averageDragons = averageDragons;
    }

    public void setAverageBarons(Float averageBarons) {
        this.averageBarons = averageBarons;
    }

    public int returnLosses(){
        return losses ;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String returnSponsor(){
        return sponsor;
    }

    public String returnAcronym(){
        return acronym;
    }

    public  Float returnAvgD(){
        return averageDragons ;
    }

    public Float returnB(){
        return averageBarons ;
    }
    public String returnRegion() {return region;}
}


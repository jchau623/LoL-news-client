

/**
 * Created by wesley on 2015-06-10.
 */
public class Team {

    public static String teamName ;
    public static int wins;
    public static int losses;
    public static String sponsor;
    public static String acronym;
    public static Float averageDragons;
    public static Float averageBarons;
    public static String region;

    public String returnTeamName() {
        return teamName;
    }

    public int returnWins(){
        return wins ;
    }

    public static void setTeamName(String teamName) {
        Team.teamName = teamName;
    }

    public static void setWins(int wins) {
        Team.wins = wins;
    }

    public static void setLosses(int losses) {
        Team.losses = losses;
    }

    public static void setSponsor(String sponsor) {
        Team.sponsor = sponsor;
    }

    public static void setAcronym(String acronym) {
        Team.acronym = acronym;
    }

    public static void setAverageDragons(Float averageDragons) {
        Team.averageDragons = averageDragons;
    }

    public static void setAverageBarons(Float averageBarons) {
        Team.averageBarons = averageBarons;
    }

    public int returnLosses(){
        return losses ;
    }

    public static void setRegion(String region) {
        Team.region = region;
    }

    public String returnSponser(){
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


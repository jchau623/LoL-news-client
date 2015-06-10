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

    public String returnTeamName() {
        return teamName;
    }

    public int returnWins(){
        return wins ;
    }

    public int returnLosses(){
        return losses ;
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
}

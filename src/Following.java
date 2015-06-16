import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Created by Jason on 2015-06-15.
 */
public class Following {

    /*TODO in main, if you're a new user, you should create a new userID
    // TODO in searchFor -> create a button that allows you to follow the entities that you search for
    //TODO in searchFor -> make all the searchResults the same format. Probably best to use the format of searchPlayer

    */
     // how do we get uid? Should uid be an integer, or should it be the CPSC id?
    // How do we get to these methods? Are we going to have a "follows" button in search? yeah that would probably work.
    // if you search for a player/team/region, there will be a button on the results window that calls followplayer/followteam/followregion

public static void followPlayer(Connection con, String uid, String summonerID) throws SQLException {
    String addN = "INSERT INTO FollowListHasPlayer VALUES ( ?, ?)";
    PreparedStatement update = con.prepareStatement(addN);
    update.setString(1, uid);
    update.setString(2, summonerID);
    update.executeUpdate();
}

public static void followTeam(Connection con, String uid, String teamName) throws SQLException{
        String addN="INSERT INTO FollowListHasTeam VALUES ( ?, ?)";
        PreparedStatement update=con.prepareStatement(addN);
        update.setString(1,uid);
        update.setString(2,teamName);
        update.executeUpdate();
        }

public static void followRegion(Connection con, String uid, String regionName) throws SQLException {
    String addN = "INSERT INTO FollowListHasRegion VALUES ( ?, ?)";
    PreparedStatement update = con.prepareStatement(addN);
    update.setString(1, uid);
    update.setString(2, regionName);
    update.executeUpdate();
}}
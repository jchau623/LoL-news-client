import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Created by Jason on 2015-06-15.
 */
public class Following {

     // how do we get uid? Should uid be an integer, or should it be the CPSC id?
    // How do we get to these methods? Are we going to have a "follows" button in search? yeah that would probably work.
    // if you search for a player/team/region, there will be a button on the results window that calls followplayer/followteam/followregion

public static void followPlayer(Connection con, String uid, String summonerID) throws SQLException {




    String addN = "INSERT INTO FollowListHasPlayer VALUES ( ?, ?)";
    PreparedStatement update = con.prepareStatement(addN);
    update.setString(1, uid);
    update.setString(2, summonerID);
    update.executeUpdate();
   System.out.print("Finished");
    AlertBox.display("Success",  uid + " successfully followed "+ summonerID);
}

public static void followTeam(Connection con, String uid, String teamName) throws SQLException{
    String addN="INSERT INTO FollowListHasTeam VALUES ( ?, ?)";
        PreparedStatement update=con.prepareStatement(addN);
        update.setString(1,uid);
        update.setString(2,teamName);
        update.executeUpdate();
    AlertBox.display("Success",  uid + " successfully followed "+ teamName);
        }

public static void followRegion(Connection con, String uid, String regionName) throws SQLException {
    String addN = "INSERT INTO FollowListHasRegion VALUES ( ?, ?)";
    PreparedStatement update = con.prepareStatement(addN);
    update.setString(1, uid);
    update.setString(2, regionName);
    update.executeUpdate();

    AlertBox.display("Success",  uid + " successfully followed "+ regionName);
}}
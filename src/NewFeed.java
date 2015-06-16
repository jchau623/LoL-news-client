import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.lang.reflect.Array;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;


/**
 * Created by jch on 15/06/15.
 */
public class NewFeed {
    static ObservableList<String> headlines;

    public static void display(Connection con, String user) {
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("News Feed");
        headlines = FXCollections.observableArrayList();

        /*
        HERE COMES THE HARD PART!!!!!!!!
         */
        ArrayList<News> news = new ArrayList<>();
        ArrayList<News> playerNews = getPlayerNews(con, user);
        ArrayList<News> teamNews = getTeamNews(con, user);
        ArrayList<News> regionNews = getRegionNews(con, user);
        if (!(playerNews==null)) news.addAll(playerNews);
        if (!(teamNews==null)) news.addAll(teamNews);
        if (!(regionNews==null)) news.addAll(regionNews);

        for (News news1 : news) {
            headlines.add(news1.getHeadline());
        }

        /*
        And back to normal
         */
        ListView<String> listView = new ListView<>();
        listView.setItems(headlines);
        listView.setPrefHeight(600);
        listView.setPrefWidth(350);
        Scene scene = new Scene(listView);
        window.setScene(scene);
        window.showAndWait();
    }

    private static ArrayList<News> getPlayerNews(Connection con, String user) {
        try {
            Statement playerNews = con.createStatement();
            ResultSet playerNewsResultSet = playerNews.executeQuery(

                    "SELECT p.headline, p.url " +
                    "FROM PlayerNews p, FollowListHasPlayer f " +
                    "WHERE f.user_id = '" + user + "' AND f.summonerID = p.summonerID");


            ArrayList<News> newsList = new ArrayList<>();
            if (playerNewsResultSet.isBeforeFirst()) {
                while (playerNewsResultSet.next()) {
                    News news = new News();
                    news.setHeadline(playerNewsResultSet.getString("headline"));
                    news.setURL(playerNewsResultSet.getString("url"));
                    newsList.add(news);
                }
            } else {
                System.out.println("getPlayerNews is empty? Check.");
            }
            return newsList;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    private static ArrayList<News> getTeamNews(Connection con, String user) {
        try {
            Statement teamNews = con.createStatement();
            ResultSet teamNewsResultSet = teamNews.executeQuery("SELECT tn.headline, tn.url " +
                    "FROM TeamNews tn, FollowListHasTeam f " +
                    "WHERE f.user_id = '" + user + "' AND f.name = tn.name");
            ArrayList<News> newsList = new ArrayList<>();
            if (teamNewsResultSet.isBeforeFirst()) {
                while (teamNewsResultSet.next()) {
                    News news = new News();
                    news.setHeadline(teamNewsResultSet.getString("headline"));
                    news.setURL(teamNewsResultSet.getString("url"));
                    newsList.add(news);
                }
            } else {
                System.out.println("getTeamNews is empty? Check.");
            }
            return newsList;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    private static ArrayList<News> getRegionNews(Connection con, String user) {
        try {
            Statement regionNews = con.createStatement();
            ResultSet regionNewsResultSet = regionNews.executeQuery("SELECT rn.headline, rn.url " +
                    "FROM RegionNews rn, FollowListHasRegion f " +
                    "WHERE f.user_id = '" + user + "' AND f.name = rn.name");
            ArrayList<News> newsList = new ArrayList<>();
            if (regionNewsResultSet.isBeforeFirst()) {
                while (regionNewsResultSet.next()) {
                    News news = new News();
                    news.setHeadline(regionNewsResultSet.getString("headline"));
                    news.setURL(regionNewsResultSet.getString("url"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }
}

import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.cell.CheckBoxListCell;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.controlsfx.control.CheckComboBox;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;


/**
 * Created by jch on 15/06/15.
 */
public class NewsFeed {
    @FXML
    static ObservableList<String> headlines;

    @FXML
    public static void display(Connection con, String user) {
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("News Feed");

        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10));
        grid.setVgap(5);
        grid.setHgap(5);


        headlines = FXCollections.observableArrayList();

        /*
        HERE COMES THE HARD PART!!!!!!!!
         */
        ArrayList<News> news = new ArrayList<>();
        ArrayList<News> playerNews = getPlayerNews(con, user);
        ArrayList<News> teamNews = getTeamNews(con, user);
        ArrayList<News> regionNews = getRegionNews(con, user);


        // adding it to the list
        if (!(playerNews == null)) news.addAll(playerNews);
        if (!(teamNews == null)) news.addAll(teamNews);
        if (!(regionNews == null)) news.addAll(regionNews);
        Set<String> set = new TreeSet<>();
        for (News news1 : news) {
            set.add(news1.getHeadline());
        }

        for (String headline : set) {
            headlines.add(headline);
        }

        /*
        And back to normal
         */
        ListView<String> listView = new ListView<>();
        listView.setItems(headlines);
        listView.setPrefHeight(600);
        listView.setPrefWidth(350);


        // filter box code

        // just making the GUI
        final ObservableList<String> followedItemsList = FXCollections.observableArrayList();
        try {
            ArrayList<String> items = findItems(con, user );
            for (int i =0 ; i < items.size() ; i++ ) {
                System.out.print(items.get(i));
            }
            for (int i =0 ; i < items.size() ; i++ ) {
                followedItemsList.add(items.get(i));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }


        //for what happens and functionality
        final CheckComboBox<String> followList = new CheckComboBox<String>(followedItemsList) ;
        followList.getCheckModel().getCheckedItems().addListener(new ListChangeListener<String>() {
            @Override
            public void onChanged(Change<? extends String> c) {
                ObservableList<String> listOfPlayers = followList.getCheckModel().getCheckedItems() ;
                // first clear the list
                headlines.clear();
                // make all your get checked model  ;
                ArrayList<String> listOfP = new ArrayList<String>() ;
                for(int i = 0 ; i < listOfPlayers.size() ; i++){
                    listOfP.add(listOfPlayers.get(i));
                }
                try {
                    TreeSet<String> playerNewsHeadlines= displayFollowList(user, con, listOfP);
                    //for (int i = 0  ; i < playerNewsHeadlines.size() ; i++ ) {
                        headlines.addAll(playerNewsHeadlines) ;
                   // }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        });


        // ToDo : solve the problem where if I unselect everything how to make it appear back to the original List

        //try using getItemBooleanProperty and if all of them are false, then DO that ???
        if (followList.getCheckModel().getCheckedItems().isEmpty()) {
           System.out.println("I am empty");
        }

      //  if (followList.getCheckModel().ge)


        listView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            try {
                Statement getPlayerURL = con.createStatement();
                ResultSet playerRS = getPlayerURL.executeQuery("SELECT url FROM PlayerNews WHERE headline = '" + newValue + "'");
                if (playerRS.next()) {
                    Browser.display(newValue, playerRS.getString(1));
                } else {
                    Statement getTeamURL = con.createStatement();
                    ResultSet teamRS = getTeamURL.executeQuery("SELECT url FROM TeamNews WHERE headline = '" + newValue + "'");
                    if (teamRS.next()) {
                        Browser.display(newValue, teamRS.getString(1));
                    } else {
                        Statement getRegionURL = con.createStatement();
                        ResultSet regionRS = getRegionURL.executeQuery("SELECT url FROM RegionNews WHERE headline = '" + newValue + "'");
                        if (regionRS.next()) {
                            Browser.display(newValue, regionRS.getString(1));
                        }
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });




        // populated the followedItems


        Label followed = new Label("Filter Your Player View:") ;
        GridPane.setConstraints(followed,0,0);
        GridPane.setConstraints(followList,0,1);
        GridPane.setConstraints(listView,0,2);

        grid.getChildren().addAll( followed, followList, listView) ;


        Scene scene = new Scene(grid);
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
            ResultSet regionNewsResultSet = regionNews.executeQuery(
                    "SELECT rn.headline, rn.url " +
                            "FROM RegionNews rn, FollowListHasRegion f " +
                            "WHERE f.user_id = '" + user + "' AND f.name = rn.name");
            ArrayList<News> newsList = new ArrayList<>();
            if (regionNewsResultSet.isBeforeFirst()) {
                while (regionNewsResultSet.next()) {
                    News news = new News();
                    news.setHeadline(regionNewsResultSet.getString("headline"));
                    news.setURL(regionNewsResultSet.getString("url"));
                    newsList.add(news);
                }
                return newsList;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    private static ArrayList<String> findItems(Connection con, String user) throws SQLException {
        Statement playerList = con.createStatement();
        ResultSet playersfollowered = playerList.executeQuery(
                "SELECT RTRIM(p.summonerID) " +
                "FROM FollowListHasPlayer p " +
                        "WHERE p.user_id = "+ " \'" + user + " \'"
        ) ;
        ArrayList<String> allPlayersFollowed = new ArrayList<String>() ;

        if(playersfollowered.isBeforeFirst()) {}
        while (playersfollowered.next()) {
            String playerF = playersfollowered.getString(1);
            allPlayersFollowed.add(playerF);
        }

////////////////////////////CURRENTLY WORKING /////////////////////


        /*Statement teamList = con.createStatement();
        ResultSet teamsFollowed = teamList.executeQuery(
                "SELECT t.name " +
                        "FROM FollowListHasTeam t " +
                        "WHERE "

        ) ; */





        return  allPlayersFollowed ;
    }

    private static TreeSet<String>  displayFollowList(String user, Connection con, ArrayList<String> listOfPlayers) throws SQLException {

        //System.out.println("I am at the start of the function");


        if (listOfPlayers.isEmpty()) {


            ArrayList<News> news = new ArrayList<>();
            ArrayList<News> playerNews = getPlayerNews(con, user);
            ArrayList<News> teamNews = getTeamNews(con, user);
            ArrayList<News> regionNews = getRegionNews(con, user);


            // adding it to the list
            if (!(playerNews == null)) news.addAll(playerNews);
            if (!(teamNews == null)) news.addAll(teamNews);
            if (!(regionNews == null)) news.addAll(regionNews);
            TreeSet<String> set = new TreeSet<>();
            for (News news1 : news) {
                set.add(news1.getHeadline());
            }

            return set ;


        } else {
            // going to creathe the string to put in the SQL query
            String sqlString = new String();
            for (int i = 0; i < listOfPlayers.size(); i++) {
                if (i == 0) {
                    sqlString = sqlString.concat(" WHERE summonerID = " + " \'" + listOfPlayers.get(i).trim() + " \'");
                    //      System.out.println("I am in the middle of the function ");
                } else {
                    sqlString = sqlString.concat(" OR summonerID = " + " \'" + listOfPlayers.get(i).trim() + " \'");
                    //    System.out.println(sqlString);
                }
            }

            //    System.out.println(sqlString);

            // todo the bug is within the WHERE CLAUSE move to the for loop

            TreeSet<String> playerNewsHeadLines = new TreeSet<>();
            Statement viewBaby = con.createStatement();
            ResultSet rs = viewBaby.executeQuery("  " +
                    "SELECT headline" +
                    " FROM PlayerNews" +
                    sqlString +
                    " AND url IN " +
                    " (select p.url" +
                    " from playernews p, followlisthasplayer f" +
                    " where f.user_id = " + " \'" + user + " \'" +
                    " and f.summonerid = p.summonerid)");

            if (rs.isBeforeFirst()) {
            }
            while (rs.next()) {
                playerNewsHeadLines.add(rs.getString(1));
            }


            return playerNewsHeadLines;
        }

        //return null ;
    }


}

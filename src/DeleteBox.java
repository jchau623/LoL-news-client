import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by Jason and Vivian on 2015-06-10.
 */
public class DeleteBox {
    private static Connection con;
    public static void display(Connection con, String title, String message) {
        Stage window = new Stage();

        //Block other windows' input events until this window is closed
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle(title);
        window.setMinWidth(250);

        Label label = new Label(message);
        Button deletePlayer = new Button("Delete Player");

        deletePlayer.setOnAction(e -> {
            String deletedPlayer = DropPlayer.display(con, "Delete Player", "Enter Player's Name:");
            System.out.println(deletedPlayer);
        });

        Button deleteTeam = new Button("Delete Team");

         deleteTeam.setOnAction(e -> {
            String deletedTeam = DropTeam.display(con,"Delete Team", "Enter team name:");
            System.out.println(deletedTeam);
        });

        Button deleteRegion = new Button ("Delete Region");
        deleteRegion.setOnAction(e-> {
            DropRegion.display(con, "Delete Region", "Enter region name:");
        });

        Button deleteNewsItem = new Button("Delete News");
        deleteNewsItem.setOnAction(e-> {
            DropNews.display(con,"Delete News Item", "Enter the url of the news item you want to delete:");
        });

        Button deleteUser = new Button("Delete User");
        deleteUser.setOnAction(e-> {
            DropUser.display(con,"Delete User", "Delete user:");
        });

        Button deleteMatch = new Button("Delete Match");
        deleteMatch.setOnAction(e-> {
            DropMatch.display(con, "Delete Match", "Enter matchID:");
        });

        VBox layout = new VBox(10);
        layout.getChildren().addAll(label,deleteRegion, deleteNewsItem, deletePlayer , deleteTeam , deleteMatch, deleteUser);
        layout.setAlignment(Pos.CENTER);
        layout.setPadding(new Insets(6));

        Scene scene = new Scene(layout);
        window.setScene(scene);

        //Shows this stage and waits for it to be hidden (closed) before returning to the caller.
        window.showAndWait();
    }


}

class DropNews {

    static String url = new String();
    private static int rowsUpdated;
    private static javafx.scene.control.TextField tf;

    public static String display(Connection con, String title, String message) {
        Stage window = new Stage();


        //Block other windows' input events until this window is closed
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle(title);
        window.setMinWidth(250);

        Label label = new Label(message);
        Button button = new Button("Enter");


        button.setOnAction(e -> {
            try{
                deleteNews(con);
                if (rowsUpdated == 0) {
                    AlertBox.display("Error", "No rows were updated");
                } else {
                    AlertBox.display("Success", "News item deleted");
                }

            }
            catch (SQLException e1){
                e1.printStackTrace();
            }
            window.close();
        });
        tf = new javafx.scene.control.TextField();

        HBox layout = new HBox(10);
        VBox layout2 = new VBox(10);
        layout.getChildren().addAll(label, tf, button);
        layout.setAlignment(Pos.CENTER);

        Scene scene = new Scene(layout);
        window.setScene(scene);

        //Shows this stage and waits for it to be hidden (closed) before returning to the caller.
        window.showAndWait();
        return returnURL();
    }

    private static String returnURL() {
        url = tf.getText();
        return url;
    }



    // going to execute the SQL query to find the player
    public static void deleteNews(Connection con) throws SQLException {

// trying a different method here
            Statement stmt = con.createStatement();
            rowsUpdated = stmt.executeUpdate("DELETE FROM News WHERE url = \'" + returnURL() + "\'");
            // find player is the result
            stmt.execute("DELETE FROM News WHERE url = \'" + returnURL() + "\'");

    }
}

class DropPlayer {
    static String player = new String();
    private static int rowsUpdated;
    private static javafx.scene.control.TextField tf;

    public static String display(Connection con, String title, String message) {
        Stage window = new Stage();


        //Block other windows' input events until this window is closed
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle(title);
        window.setMinWidth(250);

        Label label = new Label(message);
        Button button = new Button("Enter");


        button.setOnAction(e -> {
            try{
                deletePlayer(con);
                if (rowsUpdated == 0) {
                    AlertBox.display("Error", "No rows were updated");
                } else {
                    AlertBox.display("Success", "Player deleted");
                }

            }
            catch (SQLException e1){
                if (e1.getErrorCode() == 2292)
                    AlertBox.display("Error", "Cannot delete player- player is being referenced in a different table");
            }
            window.close();
        });
        tf = new javafx.scene.control.TextField();

        HBox layout = new HBox(10);
        VBox layout2 = new VBox(10);
        layout.getChildren().addAll(label, tf, button);
        layout.setAlignment(Pos.CENTER);

        Scene scene = new Scene(layout);
        window.setScene(scene);

        //Shows this stage and waits for it to be hidden (closed) before returning to the caller.
        window.showAndWait();
        return returnPlayer();
    }

    private static String returnPlayer() {
        player = tf.getText();
        return player;
    }



    // going to execute the SQL query to find the player
    public static void deletePlayer(Connection con) throws SQLException {
// trying a different method here
        Statement stmt = con.createStatement() ;
        rowsUpdated = stmt.executeUpdate("DELETE  FROM Player WHERE summonerID = \'" + returnPlayer() + "\'");
        // find player is the result
        stmt.execute("DELETE  FROM Player WHERE summonerID = \'" + returnPlayer() + "\'");}}

class DropTeam {

    static String name = new String();
    private static int rowsUpdated;
    private static javafx.scene.control.TextField tf;

    public static String display(Connection con, String title, String message) {
        Stage window = new Stage();


        //Block other windows' input events until this window is closed
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle(title);
        window.setMinWidth(250);

        Label label = new Label(message);
        Button button = new Button("Enter");


        button.setOnAction(e -> {
            try{
                deleteTeam(con);
                if (rowsUpdated == 0) {
                    AlertBox.display("Error", "No rows were updated");
                } else {
                    AlertBox.display("Success", "Team deleted");
                }
            }
            catch (SQLException e1){
                if (e1.getErrorCode() == 2292)
                    AlertBox.display("Error", "Cannot delete team- team is being referenced in a different table");
            }
            window.close();
        });
        tf = new javafx.scene.control.TextField();

        HBox layout = new HBox(10);
        VBox layout2 = new VBox(10);
        layout.getChildren().addAll(label, tf, button);
        layout.setAlignment(Pos.CENTER);

        Scene scene = new Scene(layout);
        window.setScene(scene);

        //Shows this stage and waits for it to be hidden (closed) before returning to the caller.
        window.showAndWait();
        return returnName();
    }

    private static String returnName() {
        name = tf.getText();
        return name;
    }



    // going to execute the SQL query to find the player
    public static void deleteTeam(Connection con) throws SQLException {

// trying a different method here
        Statement stmt = con.createStatement() ;
        // find player is the result
        rowsUpdated = stmt.executeUpdate("DELETE  FROM TeamThatPlaysIn WHERE name = \'" + returnName() + "\'");
        stmt.execute("DELETE  FROM TeamThatPlaysIn WHERE name = \'" + returnName() + "\'");}}


class DropUser {static String uid = new String();
    private static javafx.scene.control.TextField tf;

    public static String display(Connection con, String title, String message) {
        Stage window = new Stage();


        //Block other windows' input events until this window is closed
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle(title);
        window.setMinWidth(250);

        Label label = new Label(message);
        Button button = new Button("Enter");


        button.setOnAction(e -> {
            try{
                deleteUser(con);}
            catch (SQLException e1){
                e1.printStackTrace();
            }
            window.close();
        });
        tf = new javafx.scene.control.TextField();

        HBox layout = new HBox(10);
        VBox layout2 = new VBox(10);
        layout.getChildren().addAll(label, tf, button);
        layout.setAlignment(Pos.CENTER);

        Scene scene = new Scene(layout);
        window.setScene(scene);

        //Shows this stage and waits for it to be hidden (closed) before returning to the caller.
        window.showAndWait();
        return returnUID();
    }

    private static String returnUID() {
        uid = tf.getText();
        return uid;
    }



    // going to execute the SQL query to find the player
    public static void deleteUser(Connection con) throws SQLException {



        System.out.println("testing");
// trying a different method here
        Statement stmt = con.createStatement() ;
        // find player is the result
        stmt.execute("DELETE  FROM FollowList WHERE uid = \'" + returnUID() + "\'");}}

class DropRegion {
    static String rname = new String();
    private static int rowsUpdated;
    private static javafx.scene.control.TextField tf;

    public static String display(Connection con, String title, String message) {
        Stage window = new Stage();


        //Block other windows' input events until this window is closed
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle(title);
        window.setMinWidth(250);

        Label label = new Label(message);
        Button button = new Button("Enter");


        button.setOnAction(e -> {
            try{
                deleteRegion(con);
                if (rowsUpdated == 0) {
                    AlertBox.display("Error", "No rows were updated");
                } else {
                    AlertBox.display("Success", "Region deleted");
                }
            }
            catch (SQLException e1){
                e1.printStackTrace();
            }
            window.close();
        });
        tf = new javafx.scene.control.TextField();

        HBox layout = new HBox(10);
        VBox layout2 = new VBox(10);
        layout.getChildren().addAll(label, tf, button);
        layout.setAlignment(Pos.CENTER);

        Scene scene = new Scene(layout);
        window.setScene(scene);

        //Shows this stage and waits for it to be hidden (closed) before returning to the caller.
        window.showAndWait();
        return returnRName();
    }

    private static String returnRName() {
        rname = tf.getText();
        return rname;
    }



    // going to execute the SQL query to find the player
    public static void deleteRegion(Connection con) throws SQLException {



        System.out.println("testing");
// trying a different method here
        Statement stmt = con.createStatement() ;
        rowsUpdated = stmt.executeUpdate("DELETE  FROM Region WHERE name = \'" + returnRName() + "\'");
        stmt.execute("DELETE  FROM Region WHERE name = \'" + returnRName() + "\'");}

}



class DropMatch {
    private static int matchID;
    private static int rowsUpdated;
    private static javafx.scene.control.TextField tf;

    public static int display(Connection con, String title, String message) {
        Stage window = new Stage();


        //Block other windows' input events until thi s window is closed
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle(title);
        window.setMinWidth(250);

        Label label = new Label(message);
        Button button = new Button("Enter");


        button.setOnAction(e -> {
            try {
                deleteMatch(con);
                if (rowsUpdated == 0) {
                    AlertBox.display("Error", "No rows were updated");
                } else {
                    AlertBox.display("Success!", "Match deleted!");
                }
            }
            catch(SQLException e1){
                e1.printStackTrace();
            }
            catch (NumberFormatException nfe){
                AlertBox.display("Error", "Please ensure MatchID is a number");
            }
            System.out.println(rowsUpdated);
            window.close();
        });

        tf = new javafx.scene.control.TextField();

        HBox layout = new HBox(10);
        VBox layout2 = new VBox(10);
        layout.getChildren().addAll(label, tf, button);
        layout.setAlignment(Pos.CENTER);

        Scene scene = new Scene(layout);
        window.setScene(scene);

        //Shows this stage and waits for it to be hidden (closed) before returning to the caller.
        window.showAndWait();
        return returnMatchID();
    }

    private static int returnMatchID() {
        matchID = Integer.parseInt(tf.getText());
        return matchID;
    }

    // going to execute the SQL query to find the player
    public static void deleteMatch(Connection con) throws SQLException {
        Statement stmt = con.createStatement() ;
        rowsUpdated = stmt.executeUpdate("DELETE FROM Match WHERE matchID = \'" + returnMatchID() + "\'");
        stmt.execute("DELETE FROM Match WHERE matchID = \'" + returnMatchID() + "\'");
    }
}



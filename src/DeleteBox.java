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
 * Created by Jason on 2015-06-10.
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
            String deletedPlayer = DropPlayer.display(con, "Delete Player", "Enter Info");
            System.out.println(deletedPlayer);
        });



        Button deleteTeam = new Button("Delete Team");




 deleteTeam.setOnAction(e -> {
            String deletedTeam = DropTeam.display(con,"Delete Team", "Enter team name: ");
            System.out.println(deletedTeam);
        });


        Button deleteRegion = new Button ("Delete Region");
        deleteRegion.setOnAction(e-> {
            DropRegion.display(con, "Delete Region", "Delete region");
        });

        Button deleteNewsItem = new Button("Delete News");
        deleteNewsItem.setOnAction(e-> {
            DropNews.display(con,"Delete News Item", "What News do you want to delete?");
        });
      Button deleteUser = new Button("delete User");
        deleteUser.setOnAction(e->DropUser.display(con,"Delete User", "Delete user"));



        VBox layout = new VBox(10);
        layout.getChildren().addAll(label,deleteRegion, deleteNewsItem, deletePlayer , deleteTeam , deleteUser);
        layout.setAlignment(Pos.CENTER);
        layout.setPadding(new Insets(5));

        Scene scene = new Scene(layout);
        window.setScene(scene);

        //Shows this stage and waits for it to be hidden (closed) before returning to the caller.
        window.showAndWait();
    }


}

class DropNews {

    static String url = new String();
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
                deleteNews(con);}
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
        Statement stmt = con.createStatement() ;
        // find player is the result
        stmt.execute("DELETE  FROM Player WHERE summonerID = \'" + returnURL() + "\'");}}

class DropPlayer {





    static String player = new String();
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
                deletePlayer(con);}
            catch (SQLException e1){
                //e1.printStackTrace();
                AlertBox.display("Error: Can't Delete", "Player has existing news or does not exist.");
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



        System.out.println("testing");
// trying a different method here
        Statement stmt = con.createStatement() ;
        // find player is the result
        stmt.execute("DELETE  FROM Player WHERE summonerID = \'" + returnPlayer() + "\'");}}

class DropTeam {static String name = new String();
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
                deleteTeam(con);}
            catch (SQLException e1){

                AlertBox.display("Error: Can't Delete", "Team has existing news or does not exist.");
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



        System.out.println("testing");
// trying a different method here
        Statement stmt = con.createStatement() ;
        // find player is the result
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

class DropRegion {static String rname = new String();
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
                deleteRegion(con);}
            catch (SQLException e1){
                //e1.printStackTrace();
                AlertBox.display("Error: Can't Delete", "Region has existing news or does not exist.");
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
        // find player is the result
        stmt.execute("DELETE  FROM Region WHERE name = \'" + returnRName() + "\'");}}





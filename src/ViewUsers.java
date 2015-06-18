import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * Created by Vivian on 2015-06-17.
 */
public class ViewUsers {
    public static void display(Connection con, String title, String message) {
        Stage window = new Stage();

        //Block other windows' input events until this window is closed
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle(title);
        window.setMinWidth(250);


        Label label = new Label("Some of our users are outstanding, " +
                "as they have followed every player, or every team, or every region.");
        Label label2 = new Label("Click below to see the top users in each category.");

        Label label1 = new Label(message);

        Button button = new Button("Users Who Have Followed Every Player");
        button.setOnAction(e -> {
            try {
                ArrayList<String> users = ViewUsers.seeUserByPlayer(con);
                ViewUsers.displayUser(con, users);
            } catch (SQLException e1) {
                System.out.println(e1);
            }
        });

        Button button1 = new Button("Users Who Have Followed Every Team");
        button1.setOnAction(e -> {
            try {
                ArrayList<String> users = ViewUsers.seeUserByTeam(con);
                ViewUsers.displayUser(con, users);
            } catch (SQLException e1) {
                System.out.println(e1);
            }
        });

        Button button2 = new Button("Users Who Have Followed Every Region");
        button2.setOnAction(e -> {
            try {
                ArrayList<String> users = ViewUsers.seeUserByRegion(con);
                ViewUsers.displayUser(con, users);
            } catch (SQLException e1) {
                System.out.println(e1);
            }
        });

        GridPane grid = new GridPane();
        grid.setPadding(new Insets(4));
        grid.setVgap(5);
        grid.setHgap(5);
        GridPane.setConstraints(label, 0, 0);
        GridPane.setConstraints(label2, 0, 1);
        GridPane.setConstraints(button, 0, 3);
        GridPane.setConstraints(button1, 0, 4);
        GridPane.setConstraints(button2, 0, 5);
        grid.getChildren().addAll(label, label2, button, button1, button2);

        Scene scene = new Scene(grid);
        window.setScene(scene);

        //Shows this stage and waits for it to be hidden (closed) before returning to the caller.
        window.showAndWait();
    }
//TODO Division for Marking
    public static ArrayList<String> seeUserByPlayer(Connection con) throws SQLException {
        ArrayList<String> listOfUsers = new ArrayList<>();
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery(
                        "SELECT p.user_id " +
                        "FROM FollowList p " +
                        "WHERE NOT EXISTS " +
                                "((select summonerid from player) " +
                                "MINUS " +
                                "(select f.summonerID from FollowListHasPlayer f where f.user_id = p.user_id))"
        );

        while (rs.next()) {
            String userResult;
            userResult = rs.getString(1);
            listOfUsers.add(userResult);
        }
        return listOfUsers;

    }

    public static ArrayList<String> seeUserByTeam(Connection con) throws SQLException {
        ArrayList<String> listOfUsers = new ArrayList<>();
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery(
                        "SELECT p.user_id " +
                        "FROM FollowList p " +
                        "WHERE NOT EXISTS " +
                        "((select name from TeamThatPlaysIn) " +
                        "MINUS " +
                        "(select f.name from FollowListHasTeam f where f.user_id = p.user_id))"
        );

        while (rs.next()) {
            String user = rs.getString(1);
            listOfUsers.add(user);
        }
        return listOfUsers;

    }

    public static ArrayList<String> seeUserByRegion(Connection con) throws SQLException {
        ArrayList<String> listOfUsers = new ArrayList<>();
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery(
                "SELECT p.user_id " +
                        "FROM FollowList p " +
                        "WHERE NOT EXISTS " +
                        "((select name from Region) " +
                        "MINUS " +
                        "(select f.name from FollowListHasRegion f where f.user_id = p.user_id))"
        );

        while (rs.next()) {
            String user = rs.getString(1);
            listOfUsers.add(user);
        }
        return listOfUsers;

    }

    public static void displayUser(Connection con, ArrayList<String> listOfUsers) {
        Stage window = new Stage();
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10));
        grid.setVgap(5);
        grid.setHgap(5);

        window.setResizable(false);

        for (int i = 0; i < listOfUsers.size(); i++) {
            Label userLabel = new Label(listOfUsers.get(i));
            GridPane.setConstraints(userLabel, 1, (i + 1));
            grid.getChildren().add(userLabel);

        };


        Scene scene = new Scene(grid);
        window.setScene(scene);
        window.setResizable(false);
        window.showAndWait();

    }

}

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * Created by Justin on 6/17/2015.
 */
public class MinMax {
    public static void display(Connection con, String user) throws SQLException {
        Stage window = new Stage();
        window.setResizable(false);
        window.setTitle("Guessing Game");
        Text title = new Text("Which team had the Min or Max average of Barons per game?");
        ChoiceBox guess = new ChoiceBox();
        VBox top = new VBox(10);
        top.getChildren().addAll(title, guess);
        top.setAlignment(Pos.CENTER);
        BorderPane bp = new BorderPane();

        Statement getTeams = con.createStatement();
        ResultSet teams = getTeams.executeQuery("SELECT name FROM TeamThatPlaysIn");
        teams.next();
        guess.getItems().add(teams.getString("name"));
        guess.setValue(teams.getString("name"));
        while (teams.next()) {
            guess.getItems().add(teams.getString("name"));
        }


        Button minButton = new Button("Min");
        Button maxButton = new Button("Max");
        maxButton.setOnAction(e-> {
            try {
                getMaxAvgBaron(con,guess.getValue(), user);
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        });

        minButton.setOnAction(e -> {
            try {
                getMinAvgBaron(con, guess.getValue(), user);
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        });

        HBox buttons = new HBox(20);
        buttons.setAlignment(Pos.CENTER);
        buttons.setPadding(new Insets(5));
        buttons.getChildren().addAll(minButton, maxButton);

        bp.setTop(top);
        bp.setCenter(buttons);
        bp.setPadding(new Insets(10));
        Scene scene = new Scene(bp);
        window.setScene(scene);
        window.showAndWait();
    }

    private static void getMaxAvgBaron(Connection con, Object value, String user) throws SQLException {
        try {
            System.out.println("getMaxAvg");
            Statement createView = con.createStatement();
            System.out.println("createView");
            try {
                createView.executeUpdate("DROP VIEW barons");
                con.commit();
                createView.executeUpdate("DROP VIEW maxBarons");
                con.commit();
                try {
                    createView.executeUpdate("CREATE VIEW barons(team, numberOfBarons) AS " +
                            "SELECT t.name AS team, m.redNumOfBarons AS numberOfBarons " +
                            "FROM TeamThatPlaysIn t, Match m " +
                            "WHERE t.name = m.redName " +
                            "UNION ALL " +
                            "SELECT t.name, m.blueNumOfBarons " +
                            "FROM TeamThatPlaysIn t, Match m " +
                            "WHERE t.name = m.blueName");
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                con.commit();
                System.out.println("Finished create view");
                Statement calculateMaxAverageBaron = con.createStatement();
                calculateMaxAverageBaron.executeUpdate("CREATE VIEW maxBarons(baron) AS " +
                        "SELECT MAX(AVG(b.numberOfBarons)) as baron " +
                        "FROM barons b " +
                        "GROUP BY team");
                con.commit();
                System.out.println("Finished maxaverbaron");
                Statement getMaxTeam = con.createStatement();
                ResultSet maxRS = getMaxTeam.executeQuery("SELECT team " +
                        "from barons b, maxBarons m " +
                        "group by b.team " +
                        "having avg(b.numberOfBarons) = (select * from maxBarons)");
                System.out.println("Finished maxRS");
                ArrayList<String> teams = new ArrayList<>();
                System.out.println("Finish getMaxAvg");
                while (maxRS.next()) {
                    System.out.println("while loop");
                    teams.add(maxRS.getString("team"));
                }
                if (teams.contains(value)) {
                    System.out.println("win");
                    GuessBox.win("Congratulations", "You guessed correctly!", value, con, user);
                } else {
                    System.out.println("lose");
                    GuessBox.lose("Fail", "You guessed incorrectly.");
                }

            } catch (SQLException p){
                p.printStackTrace();
                try {
                    createView.executeUpdate("CREATE VIEW barons(team, numberOfBarons) AS " +
                            "SELECT t.name AS team, m.redNumOfBarons AS numberOfBarons " +
                            "FROM TeamThatPlaysIn t, Match m " +
                            "WHERE t.name = m.redName " +
                            "UNION ALL " +
                            "SELECT t.name, m.blueNumOfBarons " +
                            "FROM TeamThatPlaysIn t, Match m " +
                            "WHERE t.name = m.blueName");
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                con.commit();
                System.out.println("Finished create view");
                Statement calculateMaxAverageBaron = con.createStatement();
                calculateMaxAverageBaron.executeUpdate("CREATE VIEW maxBarons(baron) AS " +
                        "SELECT MAX(AVG(b.numberOfBarons)) as baron " +
                        "FROM barons b " +
                        "GROUP BY team");
                con.commit();
                System.out.println("Finished maxaverbaron");
                Statement getMaxTeam = con.createStatement();
                ResultSet maxRS = getMaxTeam.executeQuery("SELECT team" +
                        "from barons b, maxBarons m " +
                        "group by b.team" +
                        "having avg(b.numberOfBarons) = (select * from maxBarons)");
                System.out.println("Finished minRS");
                ArrayList<String> teams = new ArrayList<>();
                System.out.println("Finish getMinAvg");
                while (maxRS.next()) {
                    System.out.println("while loop");
                    teams.add(maxRS.getString("team"));
                }
                if (teams.contains(value)) {
                    System.out.println("win");
                    GuessBox.win("Congratulations", "You guessed correctly!", value, con, user);
                } else {
                    System.out.println("lose");
                    GuessBox.lose("Fail", "You guessed incorrectly.");
                }

            }
        } catch (SQLException e) {
            Statement getMaxTeam = con.createStatement();
            ResultSet maxRS = getMaxTeam.executeQuery("SELECT team " +
                    "from barons b, maxBarons m " +
                    "group by b.team " +
                    "having avg(b.numberOfBarons) = (select * from maxBarons)");
            System.out.println("Finished minRS");
            ArrayList<String> teams = new ArrayList<>();
            System.out.println("Finish getMinAvg");
            while(maxRS.next()) {
                System.out.println("while loop");
                teams.add(maxRS.getString("team"));
            }
            if (teams.contains(value)) {
                System.out.println("win");
                GuessBox.win("Congratulations", "You guessed correctly!",value, con, user);
            } else {
                System.out.println("lose");
                GuessBox.lose("Fail", "You guessed incorrectly.");
            }
        }

    }

    private static void getMinAvgBaron(Connection con, Object value, String user) throws SQLException {
        try {
            System.out.println("getMinAvg");
            Statement createView = con.createStatement();
            System.out.println("createView");
            try {
                createView.executeUpdate("DROP VIEW barons");
                con.commit();
                createView.executeUpdate("DROP VIEW minBarons");
                con.commit();
                try {
                    createView.executeUpdate("CREATE VIEW barons(team, numberOfBarons) AS " +
                            "SELECT t.name AS team, m.redNumOfBarons AS numberOfBarons " +
                            "FROM TeamThatPlaysIn t, Match m " +
                            "WHERE t.name = m.redName " +
                            "UNION ALL " +
                            "SELECT t.name, m.blueNumOfBarons " +
                            "FROM TeamThatPlaysIn t, Match m " +
                            "WHERE t.name = m.blueName");
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                con.commit();
                System.out.println("Finished create view");
                Statement calculateMinAverageBaron = con.createStatement();
                calculateMinAverageBaron.executeUpdate("CREATE VIEW minBarons(baron) AS " +
                        "SELECT MIN(AVG(b.numberOfBarons)) as baron " +
                        "FROM barons b " +
                        "GROUP BY team");
                con.commit();
                System.out.println("Finished minaverbaron");
                Statement getMinTeam = con.createStatement();
                ResultSet minRS = getMinTeam.executeQuery("SELECT team " +
                        "from barons b, minBarons m " +
                        "group by b.team " +
                        "having avg(b.numberOfBarons) = (select * from minBarons)");
                System.out.println("Finished minRS");
                ArrayList<String> teams = new ArrayList<>();
                System.out.println("Finish getMinAvg");
                while (minRS.next()) {
                    System.out.println("while loop");
                    teams.add(minRS.getString("team"));
                }
                if (teams.contains(value)) {
                    System.out.println("win");
                    GuessBox.win("Congratulations", "You guessed correctly!", value, con, user);
                } else {
                    System.out.println("lose");
                    GuessBox.lose("Fail", "You guessed incorrectly.");
                }
//TODO: nested aggregation for marking
            } catch (SQLException p){
                p.printStackTrace();
                try {
                    createView.executeUpdate("CREATE VIEW barons(team, numberOfBarons) AS " +
                            "SELECT t.name AS team, m.redNumOfBarons AS numberOfBarons " +
                            "FROM TeamThatPlaysIn t, Match m " +
                            "WHERE t.name = m.redName " +
                            "UNION ALL " +
                            "SELECT t.name, m.blueNumOfBarons " +
                            "FROM TeamThatPlaysIn t, Match m " +
                            "WHERE t.name = m.blueName");
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                con.commit();
                System.out.println("Finished create view");
                Statement calculateMinAverageBaron = con.createStatement();
                calculateMinAverageBaron.executeUpdate("CREATE VIEW minBarons(baron) AS " +
                        "SELECT MIN(AVG(b.numberOfBarons)) as baron " +
                        "FROM barons b " +
                        "GROUP BY team");
                con.commit();
                System.out.println("Finished minaverbaron");
                Statement getMinTeam = con.createStatement();
                ResultSet minRS = getMinTeam.executeQuery("SELECT team " +
                        "from barons b, minBarons m " +
                        "group by b.team " +
                        "having avg(b.numberOfBarons) = (select * from minBarons)");
                System.out.println("Finished minRS");
                ArrayList<String> teams = new ArrayList<>();
                System.out.println("Finish getMinAvg");
                while (minRS.next()) {
                    System.out.println("while loop");
                    teams.add(minRS.getString("team"));
                }
                if (teams.contains(value)) {
                    System.out.println("win");
                    GuessBox.win("Congratulations", "You guessed correctly!", value, con, user);
                } else {
                    System.out.println("lose");
                    GuessBox.lose("Fail", "You guessed incorrectly.");
                }

            }
        } catch (SQLException e) {
            Statement getMinTeam = con.createStatement();
            ResultSet minRS = getMinTeam.executeQuery("SELECT team " +
                    "from barons b, minBarons m " +
                    "group by b.team " +
                    "having avg(b.numberOfBarons) = (select * from minBarons)");
            System.out.println("Finished minRS");
            ArrayList<String> teams = new ArrayList<>();
            System.out.println("Finish getMinAvg");
            while(minRS.next()) {
                System.out.println("while loop");
                teams.add(minRS.getString("team"));
            }
            if (teams.contains(value)) {
                System.out.println("win");
                GuessBox.win("Congratulations", "You guessed correctly!",value, con, user);
            } else {
                System.out.println("lose");
                GuessBox.lose("Fail", "You guessed incorrectly.");
            }
        }

    }

    private static double getTeamAvgBaron(Connection con, Object value) throws SQLException {
        Statement sumRedBaron = con.createStatement();
        ResultSet sumRBRS = sumRedBaron.executeQuery("SELECT SUM(redNumOfBarons) AS redBaronSum FROM Match m " +
                "WHERE m.redName = '" + value + "'");
        sumRBRS.next();
        float redBaronSum = sumRBRS.getInt(1);
        Statement sumBlueBaron = con.createStatement();
        ResultSet sumBBRS = sumBlueBaron.executeQuery("SELECT SUM(blueNumOfBarons) AS blueBaronSum FROM Match m " +
                "WHERE m.blueName = '" + value + "'");
        sumBBRS.next();
        float blueBaronSum = sumBBRS.getInt(1);
        Statement matchCount = con.createStatement();
        ResultSet tm = matchCount.executeQuery("SELECT COUNT(*) FROM Match m WHERE m.redname = '" + value + "' OR " +
                "m.bluename = '" + value +"'");
        tm.next();
        float totalMatches = tm.getInt(1);
        float averageBaron = (redBaronSum+blueBaronSum)/totalMatches;
        return averageBaron;
    }
}

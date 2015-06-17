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
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * Created by jch on 17/06/15.
 */
public class GuessBox {
    public static void win(String congratulations, String s, Object value, Connection con, String user) {
        Stage window = new Stage();

        //Block other windows' input events until this window is closed
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle(congratulations);
        window.setMinWidth(250);

        Label label = new Label(s);
        Button button = new Button("Close");
        button.setOnAction(e -> window.close());
        Button button2 = new Button("Show team details");
        button2.setOnAction(e->{
            try {
                Statement stmt = con.createStatement();
                ResultSet rs = stmt.executeQuery("SELECT * FROM TeamThatPlaysIn WHERE name = '" + value + "'");
                ArrayList<Team> searchedTeams = new ArrayList<Team>();

                rs.next();
                Team team = new Team();
                team.setTeamName(rs.getString(1));
                team.setWins(rs.getInt(2));
                team.setLosses(rs.getInt(3));
                team.setSponsor(rs.getString(4));
                team.setAcronym(rs.getString(5));

                Statement sumRedBaron = con.createStatement();
                ResultSet sumRBRS = sumRedBaron.executeQuery("SELECT SUM(redNumOfBarons) AS redBaronSum FROM Match m " +
                        "WHERE m.redName = '" + team.returnTeamName() + "'");
                sumRBRS.next();
                float redBaronSum = sumRBRS.getFloat(1);
                Statement sumBlueBaron = con.createStatement();
                ResultSet sumBBRS = sumBlueBaron.executeQuery("SELECT SUM(blueNumOfBarons) AS blueBaronSum FROM Match m " +
                        "WHERE m.blueName = '" + team.returnTeamName() + "'");
                sumBBRS.next();
                float blueBaronSum = sumBBRS.getFloat(1);
                Statement matchCount = con.createStatement();
                ResultSet tm = matchCount.executeQuery("SELECT COUNT(*) FROM Match m WHERE m.redname = '" + team.returnTeamName() + "' OR " +
                        "m.bluename = '" + team.returnTeamName() +"'");
                tm.next();
                float totalMatches = tm.getFloat(1);
                float averageBaron = (redBaronSum+blueBaronSum)/totalMatches;
                team.setAverageBarons(averageBaron);
                Statement sumRedDragon = con.createStatement();
                ResultSet sumRDRS = sumRedDragon.executeQuery("SELECT SUM(redNumOfDragons) AS redDragonSum FROM Match m " +
                        "WHERE m.redName = '" + team.returnTeamName() + "'");
                sumRDRS.next();
                float redDragonSum = sumRDRS.getFloat(1);
                Statement sumBlueDragon = con.createStatement();
                ResultSet sumBDRS = sumBlueDragon.executeQuery("SELECT SUM(blueNumOfDragons) AS blueDragonSum FROM Match m " +
                        "WHERE m.blueName = '" + team.returnTeamName() + "'");
                sumBDRS.next();
                float blueDragonSum = sumBDRS.getFloat(1);
                float averageDragon = (redDragonSum+blueDragonSum)/totalMatches;
                team.setAverageDragons(averageDragon);

                team.setRegion(rs.getString(6));

                ArrayList<Player> searchedPlayers =  SearchFor.findPlayerFromTeam(con, team.returnTeamName()) ;

                System.out.println(team.returnTeamName()) ;
                // System.out.print(searchedPlayers.get(0));

                // if (searchedPlayers != null){
                TeamResult.display(user, con, team, searchedPlayers);
                // }
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        });

        HBox buttons = new HBox(15);
        buttons.getChildren().addAll(button, button2);
        buttons.setAlignment(Pos.CENTER);

        VBox layout = new VBox(10);
        layout.getChildren().addAll(label,buttons);
        layout.setAlignment(Pos.CENTER);
        layout.setPadding(new Insets(5));

        Scene scene = new Scene(layout);
        window.setScene(scene);

        //Shows this stage and waits for it to be hidden (closed) before returning to the caller.
        window.showAndWait();
    }

    public static void lose(String fail, String s) {
        Stage window = new Stage();

        //Block other windows' input events until this window is closed
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle(fail);
        window.setMinWidth(250);

        Label label = new Label(s);
        Button button = new Button("Close");
        button.setOnAction(e -> window.close());

        VBox layout = new VBox(10);
        layout.getChildren().addAll(label,button);
        layout.setAlignment(Pos.CENTER);
        layout.setPadding(new Insets(5));

        Scene scene = new Scene(layout);
        window.setScene(scene);

        //Shows this stage and waits for it to be hidden (closed) before returning to the caller.
        window.showAndWait();

    }
}

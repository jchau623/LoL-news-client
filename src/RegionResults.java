import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * Created by wesley on 2015-06-14.
 */
public class RegionResults {

    public static void display(String user, String regionName ,Connection con) throws SQLException {

        Statement stmt = con.createStatement();
        ArrayList<Team> listOfTeams = new ArrayList<Team>() ;
        ResultSet rs = stmt.executeQuery
                ("SELECT * " +
                        "FROM TeamThatPlaysIn " +
                        "WHERE rname = \'" + regionName + "\'");

        if(!rs.isBeforeFirst()){AlertBox.display("Error", "No teams in selected region");} ;
        while (rs.next()) {
            Team team = new Team();

            team.setTeamName(rs.getString(1));
            team.setWins(rs.getInt(2));
            team.setLosses(rs.getInt(3));
            team.setSponsor(rs.getString(4));
            team.setAcronym(rs.getString(5));
            team.setAverageDragons(rs.getFloat(6));
            team.setAverageBarons(rs.getFloat(7));
            team.setRegion(rs.getString(8));

            listOfTeams.add(team);
        }
        createWindow (user, con , regionName, listOfTeams) ;

    }

    private static void createWindow(String user, Connection con , String rName , ArrayList<Team> teamlist) {
        Stage window = new Stage();
        window.setTitle(rName);
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10));
        grid.setVgap(5);
        grid.setHgap(5);

        Label regionLabel = new Label("Region Selected: " + rName) ;
        GridPane.setConstraints(regionLabel, 0 , 0);

        //list the team names as buttons
        for (int i =0; i < teamlist.size() ; i++) {
            Team t = teamlist.get(i) ;
            Button teamButton = new Button(t.returnTeamName()) ;
            GridPane.setConstraints(teamButton, 0 , (i +1));
            grid.getChildren().add(teamButton);

            teamButton.setOnAction(event -> {
                        try {
                            ArrayList<Player> searchedPlayers =  SearchFor.findPlayerFromTeam(con, t.returnTeamName()) ;
                            TeamResult.display(user, con, t, searchedPlayers);
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                    }
            );
        }

        Button followButton = new Button("(+) Follow") ;
        followButton.setOnAction(e -> {
            try {
                Following.followRegion(con, user, rName);
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        });

        GridPane.setConstraints(followButton, 1, 0);
        grid.getChildren().add(followButton);


        window.setResizable(false);
        Scene scene = new Scene(grid) ;
        window.setScene(scene);
        window.showAndWait();
    }
}

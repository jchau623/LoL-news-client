import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by wesley on 2015-06-14.
 */
public class TeamResult {

    // will be passed player objects that this class will display


    public static void display(String user, Connection con, Team team, ArrayList<Player> listOfPlayers) {
        Stage window = new Stage() ;
        window.setTitle("Team Results: " + team.returnTeamName());
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10));
        grid.setVgap(5);
        grid.setHgap(5);

        Label teamAcrLabel = new Label ("Team Acronym:  " + team.returnAcronym()) ;
        Label teamRegionLabel = new Label ("Located In: " + team.returnRegion()) ;
        Label teamSponsorLabel = new Label("Sponsored By: " + team.returnSponsor()) ;

        Label teamAverageDragonsLabel = new Label("Average Dragons: " + Float.toString(team.returnAvgD())) ;
        Label teamAverageBaronsLabel = new Label ("Average Barons: " + Float.toString(team.returnB()) );

        Label teamWinsLabel = new Label("Wins: " + Integer.toString(team.returnWins()) ) ;
        Label teamLossesLabel = new Label("Losses: " + Integer.toString(team.returnLosses())) ;
        Label pLabel = new Label("Players on the Roster") ;

        Button followButton = new Button("(+) Follow") ;

        followButton.setOnAction(e -> {
            try {
                Following.followTeam(con, user, team.returnTeamName());
            } catch (SQLException e1) {
                if(e1.getErrorCode() == 1)
                    AlertBox.display("Error", "You are already following this team");
                System.out.println(e1);
            }
        });

        GridPane.setConstraints(teamAcrLabel, 0, 0);
        GridPane.setConstraints(teamRegionLabel, 0, 1);
        GridPane.setConstraints(teamSponsorLabel, 0 , 2);
        GridPane.setConstraints(teamAverageBaronsLabel, 0 ,3);
        GridPane.setConstraints(teamAverageDragonsLabel, 0,4);
        GridPane.setConstraints(teamWinsLabel,0,5);
        GridPane.setConstraints(teamLossesLabel, 0,6);
        GridPane.setConstraints(followButton, 0 , 7);

        // intentionally left out a row to give space
        GridPane.setConstraints(pLabel, 1 , 0);


        grid.getChildren().addAll(teamAcrLabel, teamAverageBaronsLabel, teamAverageDragonsLabel,
                teamLossesLabel, teamRegionLabel, teamSponsorLabel, teamWinsLabel, followButton);


        for (int i = 0 ;  i < listOfPlayers.size() ; i++ ) {
            Player p = listOfPlayers.get(i) ;
            Button teamPlayerButton = new Button(p.returnID()) ;
            GridPane.setConstraints(teamPlayerButton, 1 , (i+1));
           teamPlayerButton.setOnAction(
                    e->PlayerResult.display(user, con, p)
            );
            grid.getChildren().add(teamPlayerButton);

        };

        // action for follow Button
       // followButton.setOnAction( e->);




        Scene scene = new Scene(grid) ;
        window.setScene(scene);
        window.setResizable(false);
        window.showAndWait();
    }


}

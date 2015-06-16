import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by wesley on 2015-06-15.
 */
public class TeamListView {

    public  static void display(Connection con , ArrayList<Team> teams) {
        Stage window = new Stage() ;
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10));
        grid.setVgap(5);
        grid.setHgap(5);

        for (int i =0 ; i < teams.size() ; i++ ) {
            Team t = teams.get(i) ;
            Button teambutton = new Button(t.returnTeamName()) ;

            GridPane.setConstraints(teambutton, 0, i);

            teambutton.setOnAction(
                    e-> {
                        try {
                          ArrayList<Player> searchedPlayers =  SearchFor.findPlayerFromTeam(con, t.returnTeamName()) ;

                           System.out.println(t.returnTeamName()) ;
                           // System.out.print(searchedPlayers.get(0));

                           // if (searchedPlayers != null){
                                TeamResult.display(t, searchedPlayers);
                           // }
                        } catch (SQLException e1) {
                            e1.printStackTrace();
                        }
                    }
            );
            grid.getChildren().add(teambutton);
        }

        Scene scene = new Scene(grid) ;
        window.setScene(scene);
        window.showAndWait();



    }
}

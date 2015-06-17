import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * Created by Vivian on 2015-06-16.
 */
public class ViewMatches {
    public static void display(Connection con, String title, String message) {
        Stage window = new Stage();

        //Block other windows' input events until this window is closed
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle(title);
        window.setMinWidth(250);


        Label teamLabel = new Label("See all matches played by this team:");
        TextField teamName = new TextField();

        Label label = new Label(message);


        Button button = new Button("Enter");
        button.setOnAction(e -> {
            try {
                ArrayList<Match> searchedMatches =  ViewMatches.seeMatches(con, teamName.getText()) ;
                MatchResults.display(con, searchedMatches);
            } catch (SQLException e1) {
                System.out.println(e1);
            }
            window.close();
        });

        GridPane grid = new GridPane();
        grid.setPadding(new Insets(3));
        grid.setVgap(5);
        grid.setHgap(5);
        GridPane.setConstraints(teamLabel, 0, 0);
        GridPane.setConstraints(teamName, 1, 0);
        GridPane.setConstraints(button, 1, 8);
        GridPane.setHalignment(button, HPos.RIGHT);
        grid.getChildren().addAll(teamLabel, teamName, button);

        Scene scene = new Scene(grid);
        window.setScene(scene);

        //Shows this stage and waits for it to be hidden (closed) before returning to the caller.
        window.showAndWait();
    }

    public static ArrayList<Match> seeMatches(Connection con, String teamName) throws SQLException {
        ArrayList<Match> listOfMatches = new ArrayList<>();
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery(
                "SELECT DISTINCT m.matchID, m.totalKills, m.redNumOfBarons, m.redNumOfDragons, m.blueNumOfDragons, m.blueNumOFBarons, m.time," +
                        "m.totalGold, m.redName, m.blueName, m.datePlayed, m.winner " +
                        "FROM TeamThatPlaysIn t , Match m " +
                        "WHERE m.redName = " + "\'" + teamName + "\'" +
                        " OR m.blueName = " + "\'" + teamName + "\'" +
                        " ORDER BY m.datePlayed DESC"
        );

        while (rs.next()) {
            Match m = new Match();
            m.setID(rs.getInt(1));
            m.setTotalKills(rs.getInt(2));
            m.setRedNumberOfBarons(rs.getInt(3));
            m.setRedNumberOfDragons(rs.getInt(4));
            m.setBlueNumberOfDragons(rs.getInt(5));
            m.setBlueNumberOfBarons(rs.getInt(6));
            m.setTime(rs.getString(7));
            m.setTotalGold(rs.getInt(8));
            m.setRedName(rs.getString(9));
            m.setBlueName(rs.getString(10));
            m.setDatePlayed(rs.getDate(11));
            m.setWinner(rs.getString(12));

            listOfMatches.add(m);
        }
        return listOfMatches;

    }
}


import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by Justin on 6/17/2015.
 */
public class MinMax {
    public static void display(Connection con) throws SQLException {
        Stage window = new Stage();
        window.setResizable(false);

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

        minButton.setOnAction(e -> {
            try {
                double team = getTeamAvgBaron(con, guess.getValue());
                double min = getMinAvgBaron(con);
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

    private static double getMinAvgBaron(Connection con) {

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

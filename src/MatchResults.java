import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.sql.Connection;
import java.util.ArrayList;

/**
 * Created by vivian on 2015-06-16.
 */
public class MatchResults {
    public static void display(Connection con, ArrayList<Match> listOfMatches) {
        Stage window = new Stage();
        window.setTitle("Match Results");
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10));
        grid.setVgap(5);
        grid.setHgap(5);


        for (int i = 0; i < listOfMatches.size(); i++) {
            Match m = listOfMatches.get(i);
            Button matchButton = new Button(m.returnDatePlayed().toString());
            GridPane.setConstraints(matchButton, 1, (i + 1));
            matchButton.setOnAction(
                    e -> MatchResults.displayEachMatch(con, m)
            );
            grid.getChildren().add(matchButton);

        };


        Scene scene = new Scene(grid);
        window.setScene(scene);
        window.setResizable(false);
        window.showAndWait();
    }

    public static void displayEachMatch(Connection con, Match m) {
        Stage window = new Stage();
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10));
        grid.setVgap(5);
        grid.setHgap(5);

        window.setResizable(false);

        Label mIDLabel = new Label("MatchID: " + m.returnID());
        Label totalKillsLabel = new Label("Total Kills: " + m.returnTotalKills());
        Label redNumDragonsLabel = new Label("Red Number of Dragons: " + m.returnRedNumberOfDragons());
        Label redNumBaronsLabel = new Label("Red Number of Barons: " + m.returnRedNumberOfBarons());
        Label blueNumBaronsLabel = new Label("Blue Number of Barons: " + m.returnBlueNumberOfBarons());
        Label blueNumDragonsLabel = new Label("Blue Number of Dragons: " + m.returnBlueNumberOfDragons());
        Label timeLabel = new Label("Time: " + m.returnTime());
        Label totalGoldLabel = new Label("Total Gold: " + m.returnTotalGold());
        Label redNameLabel = new Label("Red Team Name: " + m.returnRedName());
        Label blueNameLabel = new Label("Blue Team Name: " + m.returnBlueName());
        Label dateLabel = new Label("Date: " + m.returnDatePlayed());
        Label winnerLabel = new Label("Winner: " + m.returnWinner());

        GridPane.setConstraints(mIDLabel, 0, 0);
        GridPane.setConstraints(totalKillsLabel, 0, 1);
        GridPane.setConstraints(redNumDragonsLabel, 0, 2);
        GridPane.setConstraints(redNumBaronsLabel, 0, 3);
        GridPane.setConstraints(blueNumBaronsLabel, 0, 4);
        GridPane.setConstraints(blueNumDragonsLabel, 0, 5);
        GridPane.setConstraints(timeLabel, 0, 6);
        GridPane.setConstraints(totalGoldLabel, 0, 7);
        GridPane.setConstraints(redNameLabel, 0, 8);
        GridPane.setConstraints(blueNameLabel, 0, 9);
        GridPane.setConstraints(dateLabel, 0, 10);
        GridPane.setConstraints(winnerLabel, 0, 11);


        grid.getChildren().addAll(mIDLabel, totalKillsLabel, redNumDragonsLabel, redNumBaronsLabel, blueNumBaronsLabel, blueNumDragonsLabel, timeLabel, totalGoldLabel, redNameLabel, blueNameLabel, dateLabel, winnerLabel);
        Scene scene = new Scene(grid);
        window.setScene(scene);
        window.showAndWait();

    }
}


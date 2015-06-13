import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.sql.Connection;

/**
 * Created by Justin on 6/10/2015.
 */
public class AddMatch {
    public static void display(Connection con , String title) {
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle(title);
        window.setMinWidth(100);
        Button button = new Button("Enter");
        button.setOnAction(e -> {
            window.close();
        });

        Label totalKillsLabel = new Label("Total Kills:");
        Label numberofBaronsLabel = new Label("# of Barons:");
        Label timeLabel = new Label("Duration (MM:SS):");
        Label matchIDLabel = new Label("Match ID:");
        Label totalGoldLabel = new Label("Total Gold:");
        Label redNameLabel = new Label(" Team:"); Text red = new Text("Red"); red.setFill(Color.RED);
        Label blueNameLabel = new Label(" Team:"); Text blue = new Text("Blue"); blue.setFill(Color.BLUE);
        Label datePlayedLabel = new Label("Date:"); //format to be determined

        TextField totalKills = new TextField();
        TextField numberofBarons = new TextField();
        TextField time = new TextField();
        TextField matchID = new TextField();
        TextField totalGold = new TextField();
        TextField redName = new TextField();
        TextField blueName = new TextField();
        TextField datePlayed = new TextField();

        HBox redHBox = new HBox(0);
        HBox blueHBox = new HBox(0);
        redHBox.getChildren().addAll(red,redNameLabel);
        blueHBox.getChildren().addAll(blue, blueNameLabel);

        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10));
        grid.setVgap(5);
        grid.setHgap(5);
        GridPane.setConstraints(totalKillsLabel, 0, 0);
        GridPane.setConstraints(totalKills, 1, 0);
        GridPane.setConstraints(numberofBaronsLabel, 0, 1);
        GridPane.setConstraints(numberofBarons, 1, 1);
        GridPane.setConstraints(timeLabel, 0, 2);
        GridPane.setConstraints(time, 1, 2);
        GridPane.setConstraints(matchIDLabel, 0, 3);
        GridPane.setConstraints(matchID, 1, 3);
        GridPane.setConstraints(totalGoldLabel, 0, 4);
        GridPane.setConstraints(totalGold, 1, 4);
        GridPane.setConstraints(redHBox, 0, 5);
        GridPane.setConstraints(redName, 1, 5);
        GridPane.setConstraints(blueHBox, 0, 6);
        GridPane.setConstraints(blueName, 1, 6);
        GridPane.setConstraints(datePlayedLabel, 0, 7);
        GridPane.setConstraints(datePlayed, 1, 7);
        GridPane.setConstraints(button, 1, 8);
        GridPane.setHalignment(button, HPos.RIGHT);
        grid.getChildren().addAll(totalKillsLabel, totalKills, numberofBaronsLabel, numberofBarons, timeLabel, time, matchIDLabel, matchID, totalGoldLabel, totalGold, redHBox, redName, blueHBox, blueName, datePlayedLabel, datePlayed, button);

        Scene scene = new Scene(grid);
        window.setScene(scene);
        window.showAndWait();

    }
}

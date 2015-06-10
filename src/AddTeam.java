import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by Vivian on 2015-06-09.
 */
public class AddTeam {
    static String team = new String();
    private static javafx.scene.control.TextField name;
    private static javafx.scene.control.TextField averageBarons;
    private static javafx.scene.control.TextField averageDragons;
    private static javafx.scene.control.TextField acronym;
    private static javafx.scene.control.TextField win;
    private static javafx.scene.control.TextField loss;
    private static javafx.scene.control.TextField sponsor;

    public static void display(String title) {
        Stage window = new Stage();


        //Block other windows' input events until this window is closed
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle(title);
        window.setMinWidth(100);

        Button button = new Button("Enter");
        button.setOnAction(e -> {
            //returnTeam();
            window.close();
        });

        Label nameLabel = new Label("Name:");
        name = new javafx.scene.control.TextField();
        Label acronymLabel = new Label("Acronym:");
        acronym = new javafx.scene.control.TextField();
        Label averageBaronsLabel = new Label("Average Barons:");
        averageBarons = new javafx.scene.control.TextField();
        Label averageDragonsLabel = new Label ("Average Dragons:");
        averageDragons = new javafx.scene.control.TextField();
        Label winLabel = new Label ("Wins:");
        win = new javafx.scene.control.TextField();
        Label lossLabel = new Label ("Losses:");
        loss = new javafx.scene.control.TextField();
        Label sponsorLabel = new Label ("Sponsor:");
        sponsor = new javafx.scene.control.TextField();

        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10));
        grid.setVgap(5);
        grid.setHgap(5);
        GridPane.setConstraints(nameLabel, 0, 0);
        GridPane.setConstraints(name, 1, 0);
        GridPane.setConstraints(acronymLabel, 0, 1);
        GridPane.setConstraints(acronym, 1, 1);
        GridPane.setConstraints(averageBaronsLabel, 0, 2);
        GridPane.setConstraints(averageBarons, 1, 2);
        GridPane.setConstraints(averageDragonsLabel, 0, 3);
        GridPane.setConstraints(averageDragons, 1, 3);
        GridPane.setConstraints(winLabel, 0, 4);
        GridPane.setConstraints(win, 1, 4);
        GridPane.setConstraints(lossLabel, 0, 5);
        GridPane.setConstraints(loss, 1, 5);
        GridPane.setConstraints(sponsorLabel, 0, 6);
        GridPane.setConstraints(sponsor, 1, 6);
        GridPane.setConstraints(button, 1, 7);
        GridPane.setHalignment(button, HPos.RIGHT);
        grid.getChildren().addAll(nameLabel,name,acronymLabel,acronym,averageBaronsLabel,averageBarons,averageDragonsLabel,averageDragons,winLabel,win,lossLabel,loss,sponsorLabel,sponsor,button);

        Scene scene = new Scene(grid);
        window.setScene(scene);

        //Shows this stage and waits for it to be hidden (closed) before returning to the caller.
        window.showAndWait();
        //return returnTeam();
    }

    public void insertPlayer(Connection con) throws SQLException{
        Statement stmt = con.createStatement();
        stmt.executeUpdate("INSERT INTO player VALUES (" + name.getText() + "," + acronym.getText() + ", " +
               averageBarons.getText() + ", " + averageDragons.getText() + ", " + win.getText() + ", " + loss.getText()
               + ", " + sponsor.getText());
    }

       /* private static String returnTeam() {
            //team = tf.getText();
            return team;
        }*/

}


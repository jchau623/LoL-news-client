import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

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

    public static void display(String title, String message) {
        Stage window = new Stage();


        //Block other windows' input events until this window is closed
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle(title);
        window.setMinWidth(250);

        Label label = new Label(message);
        Button button = new Button("Enter");
        button.setOnAction(e -> {
            //returnTeam();
            window.close();
        });

        Label nameLabel = new Label("Name:");
        name = new javafx.scene.control.TextField();
        HBox hName = new HBox(10);

        Label acronymLabel = new Label("Acronym:");
        acronym = new javafx.scene.control.TextField();
        HBox hAcronym = new HBox(10);
        
        Label averageBaronsLabel = new Label("Average Barons:");
        averageBarons = new javafx.scene.control.TextField();
        HBox hAverageBarons = new HBox(10);

        Label averageDragonsLabel = new Label ("Average Dragons:");
        averageDragons = new javafx.scene.control.TextField();
        HBox hAverageDragons = new HBox(10);

        Label winLabel = new Label ("Wins:");
        win = new javafx.scene.control.TextField();
        HBox hWin = new HBox(10);

        Label lossLabel = new Label ("Losses:");
        loss = new javafx.scene.control.TextField();
        HBox hLoss = new HBox(10);

        Label sponsorLabel = new Label ("Sponsor:");
        sponsor = new javafx.scene.control.TextField();
        HBox hSponsor = new HBox(10);

        VBox layout = new VBox(10);


        hName.getChildren().addAll(nameLabel, name);
        hAcronym.getChildren().addAll(acronymLabel, acronym);
        hAverageBarons.getChildren().addAll(averageBaronsLabel, averageBarons);
        hAverageDragons.getChildren().addAll(averageDragonsLabel, averageDragons);
        hWin.getChildren().addAll(winLabel, win);
        hLoss.getChildren().addAll(lossLabel, loss);
        hSponsor.getChildren().addAll(sponsorLabel, sponsor);


        layout.getChildren().addAll(label, hName, hAcronym, hAverageBarons, hAverageDragons, hWin, hLoss, hSponsor, button);
        layout.setAlignment(Pos.CENTER);

        Scene scene = new Scene(layout);
        window.setScene(scene);

        //Shows this stage and waits for it to be hidden (closed) before returning to the caller.
        window.showAndWait();
        //return returnTeam();
    }

   /* private static String returnTeam() {
        //team = tf.getText();
        return team;
    }*/

}

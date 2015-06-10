import javafx.geometry.*;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.awt.*;

/**
 * Created by Justin on 5/31/2015.
 */
public class AddPlayer {
    static String player = new String();
    private static javafx.scene.control.TextField summonerID;
    public static String display(String title, String message) {
        Stage window = new Stage();


        //Block other windows' input events until this window is closed
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle(title);
        window.setMinWidth(100);
        window.setResizable(false);

        Label label = new Label(message);
        Button button = new Button("Enter");
        button.setOnAction(e -> {
            returnPlayer();
            window.close();
        });

        //Attribute text fields
        Label summonerLabel = new Label("summonerID:");
        summonerID = new javafx.scene.control.TextField();
        Label ageLabel = new Label("Age:");
        TextField age = new TextField();
        Label nameLabel = new Label("Name:");
        TextField name = new TextField();
        Label natLabel = new Label("Nationality:");
        TextField nationality = new TextField(); //TODO: Change to a dropdown box with all countries possible
        Label csPerGameLabel = new Label("csPerGame:");
        TextField csPerGame = new TextField();
        Label goldPerMinLabel = new Label("goldPerMin:");
        TextField goldPerMin = new TextField();
        Label KADLabel = new Label("KA/D Ratio:");
        TextField KAD = new TextField();

        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10, 0, 0, 10));
        grid.setVgap(5);
        grid.setHgap(5);
        GridPane.setConstraints(summonerLabel, 0, 0);
        GridPane.setConstraints(summonerID, 1, 0);
        GridPane.setConstraints(ageLabel, 0, 1);
        GridPane.setConstraints(age, 1, 1);
        GridPane.setConstraints(nameLabel, 0, 2);
        GridPane.setConstraints(name, 1, 2);
        GridPane.setConstraints(natLabel, 0, 3);
        GridPane.setConstraints(nationality, 1, 3);
        GridPane.setConstraints(csPerGameLabel, 0 ,4);
        GridPane.setConstraints(csPerGame, 1, 4);
        GridPane.setConstraints(goldPerMinLabel, 0, 5);
        GridPane.setConstraints(goldPerMin, 1, 5);
        GridPane.setConstraints(KADLabel, 0, 6);
        GridPane.setConstraints(KAD, 1, 6);
        GridPane.setConstraints(button, 1, 7);
        GridPane.setHalignment(button, HPos.RIGHT);
        grid.getChildren().addAll(summonerLabel,summonerID,ageLabel,age,nameLabel,name,natLabel,nationality,csPerGameLabel,csPerGame,goldPerMinLabel,goldPerMin,KADLabel,KAD,button);

        Scene scene = new Scene(grid);
        window.setScene(scene);

        //Shows this stage and waits for it to be hidden (closed) before returning to the caller.
        window.showAndWait();
        return returnPlayer();
    }

    private static String returnPlayer() {
        player = summonerID.getText();
        return player;
    }

}

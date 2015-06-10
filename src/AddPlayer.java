import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

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
        window.setMinWidth(350);
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
        HBox summonerHBox = new HBox(5);
        summonerHBox.getChildren().addAll(summonerLabel, summonerID);

        Label ageLabel = new Label("Age:");
        TextField age = new TextField();
        HBox ageHBox = new HBox(5);
        ageHBox.getChildren().addAll(ageLabel,age);

        Label nameLabel = new Label("Name:");
        TextField name = new TextField();
        HBox nameHBox = new HBox(5);
        nameHBox.getChildren().addAll(nameLabel,name);

        Label natLabel = new Label("Nationality:");
        TextField nationality = new TextField(); //TODO: Change to a dropdown box with all countries possible
        HBox nationalityHBox = new HBox(5);
        nationalityHBox.getChildren().addAll(natLabel,nationality);

        Label csPerGameLabel = new Label("csPerGame:");
        TextField csPerGame = new TextField();
        HBox csHBox = new HBox(5);
        csHBox.getChildren().addAll(csPerGameLabel, csPerGame);

        Label goldPerMinLabel = new Label("goldPerMin:");
        TextField goldPerMin = new TextField();
        HBox gpmHBox = new HBox(5);
        gpmHBox.getChildren().addAll(goldPerMinLabel,goldPerMin);

        Label KADLabel = new Label("KA/D Ratio:");
        TextField KAD = new TextField();
        HBox KADHBox = new HBox(5);
        KADHBox.getChildren().addAll(KADLabel, KAD);


        VBox layout = new VBox(10);
        layout.getChildren().addAll(label, summonerHBox, ageHBox, nameHBox, nationalityHBox, csHBox, gpmHBox, KADHBox, button);
        layout.setAlignment(Pos.CENTER);

        Scene scene = new Scene(layout);
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

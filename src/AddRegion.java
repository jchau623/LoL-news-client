


import javafx.geometry.Insets;
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
 * Created by Jason on 5/31/2015.
 */
public class AddRegion {
    static String region ;
    private static javafx.scene.control.TextField acronym;
    public static String display(String title, String message) {
        Stage window = new Stage();


        //Block other windows' input events until this window is closed
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle(title);
        window.setMinWidth(350);

        Label label = new Label(message);
        Button button = new Button("Enter");
        button.setOnAction(e -> {
            returnRegion();
            window.close();
        });

        //Attribute text fields
        Label acronymLabel = new Label("acronym:");
        acronym = new javafx.scene.control.TextField();
        HBox acronymHBox = new HBox(5);
        acronymHBox.getChildren().addAll(acronymLabel, acronym);



        Label KADLabel = new Label("Name:");
        TextField KAD = new TextField();
        HBox KADHBox = new HBox(5);
        KADHBox.getChildren().addAll(KADLabel, KAD);


        VBox layout = new VBox(10);
        layout.getChildren().addAll(label, acronymHBox,  KADHBox, button);
        layout.setAlignment(Pos.CENTER);
        layout.setPadding(new Insets(5));

        Scene scene = new Scene(layout);
        window.setScene(scene);

        //Shows this stage and waits for it to be hidden (closed) before returning to the caller.
        window.showAndWait();
        return returnRegion();
    }

    private static String returnRegion() {
        region = acronym.getText();
        return region;
    }

}


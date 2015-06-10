

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
public class AddNewsItem {
    static String newsItem = new String();
    private static javafx.scene.control.TextField url;
    public static String display(String title, String message) {
        Stage window = new Stage();


        //Block other windows' input events until this window is closed
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle(title);
        window.setMinWidth(350);

        Label label = new Label(message);
        Button button = new Button("Enter");
        button.setOnAction(e -> {
            returnNewsItem();
            window.close();
        });

        //Attribute text fields
        Label summonerLabel = new Label("url:");
        url = new javafx.scene.control.TextField();
        HBox summonerHBox = new HBox(5);
        summonerHBox.getChildren().addAll(summonerLabel, url);

        Label ageLabel = new Label("Date:");
        TextField date = new TextField();
        HBox ageHBox = new HBox(5);
        ageHBox.getChildren().addAll(ageLabel,date);

        Label nameLabel = new Label("Headline");
        TextField name = new TextField();
        HBox nameHBox = new HBox(5);
        nameHBox.getChildren().addAll(nameLabel,name);




        VBox layout = new VBox(10);
        layout.getChildren().addAll(label, summonerHBox, ageHBox, nameHBox,  button);
        layout.setAlignment(Pos.CENTER);
        layout.setPadding(new Insets(5));

        Scene scene = new Scene(layout);
        window.setScene(scene);

        //Shows this stage and waits for it to be hidden (closed) before returning to the caller.
        window.showAndWait();
        return returnNewsItem();
    }

    private static String returnNewsItem() {
        newsItem = url.getText();
        return newsItem;
    }

}


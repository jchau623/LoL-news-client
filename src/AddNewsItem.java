

import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
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
    public static String display(String title) {
        Stage window = new Stage();


        //Block other windows' input events until this window is closed
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle(title);
        window.setMinWidth(100);

        Button button = new Button("Enter");
        button.setOnAction(e -> {
            returnNewsItem();
            window.close();
        });

        //Attribute text fields
        Label urlLabel = new Label("URL:");
        url = new javafx.scene.control.TextField();

        Label dateLabel = new Label("Date:");
        TextField date = new TextField();

        Label headlineLabel = new Label("Headline:");
        TextField headline = new TextField();

        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10, 10, 10, 10));
        grid.setVgap(5);
        grid.setHgap(5);
        GridPane.setConstraints(urlLabel, 0, 0);
        GridPane.setConstraints(url, 1, 0);
        GridPane.setConstraints(headlineLabel, 0, 1);
        GridPane.setConstraints(headline, 1, 1);
        GridPane.setConstraints(button, 1, 2);
        GridPane.setHalignment(button, HPos.RIGHT);
        grid.getChildren().addAll(urlLabel,url,headlineLabel,headline,button);

        Scene scene = new Scene(grid);
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


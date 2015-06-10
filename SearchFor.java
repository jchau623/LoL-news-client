import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * Created by Justin on 6/1/2015.
 */
public class SearchFor {

    public static void display() {
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Search");
        window.setMinWidth(300);

        TextField tf = new TextField();
        tf.setPromptText("Enter name");
        tf.setMinWidth(234);

        Button searchButton = new Button("Search");

        CheckBox player = new CheckBox("Player");
        CheckBox team = new CheckBox("Team");
        CheckBox region = new CheckBox("Region");

        HBox searchBar = new HBox(5);
        searchBar.getChildren().addAll(tf, searchButton);
        searchBar.setAlignment(Pos.CENTER);

        GridPane layout = new GridPane();
        Label selectCategories = new Label("Select Categories:");
        GridPane.setConstraints(selectCategories, 0, 0);
        GridPane.setConstraints(player, 0, 1);
        GridPane.setConstraints(team, 0, 2);
        GridPane.setConstraints(region, 1, 1);
        layout.getChildren().addAll(selectCategories, player, team, region);
        layout.setAlignment(Pos.TOP_CENTER);

        BorderPane borderPane = new BorderPane(layout);
        borderPane.setTop(searchBar);

        Scene scene = new Scene(borderPane, 300, 100);
        window.setScene(scene);
        window.show();

    }
}

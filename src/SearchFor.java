import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
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
        //window.setMinWidth(300);

        TextField tf = new TextField();
        tf.setPromptText("Enter name");
        tf.setMinWidth(234);

        Button searchButton = new Button("Search");

        ToggleGroup choices = new ToggleGroup();
        RadioButton player = new RadioButton("Player");
        RadioButton team = new RadioButton("Team");
        RadioButton region = new RadioButton("Region");
        player.setToggleGroup(choices);
        team.setToggleGroup(choices);
        region.setToggleGroup(choices);
        player.setSelected(true);

        HBox searchBar = new HBox(5);
        searchBar.getChildren().addAll(tf, searchButton);
        searchBar.setAlignment(Pos.CENTER);

        GridPane layout = new GridPane();
        layout.setVgap(5);
        Label selectCategories = new Label("Select Categories:");
        GridPane.setConstraints(selectCategories, 0, 0);
        GridPane.setConstraints(player, 0, 1);
        GridPane.setConstraints(team, 0, 2);
        GridPane.setConstraints(region, 1, 1);
        layout.getChildren().addAll(selectCategories, player, team, region);
        layout.setAlignment(Pos.TOP_CENTER);

        Label askReturn = new Label("Return data sorted by:");
        ChoiceBox<String> attributes = new ChoiceBox<>();
        attributes.getItems().addAll("summonerID", "Age", "Name", "KA/D Ratio", "csPerGame", "goldPerMin", "Nationality");
        player.setOnAction(e -> {
            attributes.getItems().clear();
            attributes.getItems().addAll("summonerID", "Age", "Name", "KA/D Ratio", "csPerGame", "goldPerMin", "Nationality");})
            ;
        team.setOnAction(e-> {
            attributes.getItems().clear();
            attributes.getItems().addAll("Name", "Acronym", "Average Barons", "Average Dragons", "Wins", "Losses", "Sponsor");
        });
        region.setOnAction(e->{
            attributes.getItems().clear();
            attributes.getItems().addAll("Acronym", "Name");
        });

        GridPane.setConstraints(askReturn, 0, 3);
        GridPane.setConstraints(attributes, 0, 4);
        Text in = new Text("in");
        GridPane.setConstraints(in, 0, 5);
        ChoiceBox<String> order = new ChoiceBox<>();
        order.getItems().addAll("", "Ascending order", "Descending order");
        GridPane.setConstraints(order, 0, 6);

        layout.getChildren().addAll(askReturn, attributes, in, order);

            BorderPane borderPane = new BorderPane(layout);
            borderPane.setTop(searchBar);
            borderPane.setPadding(new Insets(10));
            Scene scene = new Scene(borderPane);
        window.setScene(scene);
        window.show();

    }
}

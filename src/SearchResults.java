import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.ComboBoxListCell;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.util.ArrayList;

/**
 * Created by wesley on 2015-06-13.
 */


public class SearchResults {

    public static final ObservableList playerStats = FXCollections.observableArrayList();
    public static final ObservableList playerName = FXCollections.observableArrayList();

    public static void display ( ArrayList<Player> players) {

        Stage window = new Stage();
        window.setTitle("Following Results");
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10));
        grid.setVgap(5);
        grid.setHgap(5);

        for (int i=0 ; i < players.size() ; i++ ) {
            Player p = players.get(i);
            Button playerButton = new Button(p.returnID());
            GridPane.setConstraints(playerButton,0,i);
            playerButton.setOnAction(e -> {
                        // open up a new player page
                        PlayerResult.display(p);
                    }
            );

            grid.getChildren().add(playerButton);
        }


        Scene scene = new Scene(grid) ;
        window.setScene(scene);
        window.showAndWait();

    }





}

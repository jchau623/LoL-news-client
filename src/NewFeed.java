import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.stage.Modality;
import javafx.stage.Stage;


/**
 * Created by jch on 15/06/15.
 */
public class NewFeed {
    static ObservableList<String> headlines;

    public static void display() {
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("News Feed");
        window.setResizable(false);
        headlines = FXCollections.observableArrayList();



        ListView<String> listView = new ListView<>();
        listView.setItems(headlines);
        listView.setPrefHeight(500);
        listView.setPrefWidth(150);
        Scene scene = new Scene(listView);
        window.setScene(scene);
        window.showAndWait();
    }
}

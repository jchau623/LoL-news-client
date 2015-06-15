import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

/**
 * Created by wesley on 2015-06-14.
 */
public class PlayerResult {

    public static void display(Player player) {
        Stage window = new Stage();

        ListView<String> list = new ListView<String>() ;

        ObservableList<String> items = FXCollections.observableArrayList() ;

        items.addAll(player.returnID(), player.returnName(), Float.toString(player.returnCsPerGame()), Integer.toString(player.returnAge()),
                Float.toString(player.returnGPM()), Float.toString(player.returnKDA()), player.returnNationality()) ;

        list.setItems(items);
        list.setPrefWidth(100);
        list.setPrefHeight(90);


    }




}

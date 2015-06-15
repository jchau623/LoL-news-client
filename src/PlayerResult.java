import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
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

        items.addAll(
                "SummonerID: " + player.returnID(),
                "Name: " + player.returnName(),
                "CsPerGame: " + Float.toString(player.returnCsPerGame()),
                "Age: " + Integer.toString(player.returnAge()),
                "GoldPerMin: " + Float.toString(player.returnGPM()),
                "KDA: "+ Float.toString(player.returnKDA()),
                "Nationality: " + player.returnNationality()) ;

        list.setItems(items);
        list.setPrefWidth(200);
        list.setPrefHeight(150);

        Scene scene = new Scene(list) ;
        window.setScene(scene);
        window.showAndWait();


    }




}

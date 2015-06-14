import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.ComboBoxListCell;
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
        final ListView listView = new ListView(playerName) ;
        listView.setPrefSize(250,250);  //check dimensions
        listView.setEditable(true);

       // playerName.addAll("gameplayer020") ;
/*
        try {
            while (resultSet.next()) {
                playerStats.add(resultSet.getString(1)) ;
                playerName.add(resultSet.getString("summonerID"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }*/

        for(Player p : players) {
            playerName.add(p.returnID());
            playerStats.addAll("Name:" + p.returnName() , "Age: " + p.returnAge(), "KDA: " + p.returnKDA(),"Nationality: " +  p.returnNationality(), "CsPerGame: "+ p.returnCsPerGame());
        }
        //added


        listView.setItems(playerName);
        listView.setCellFactory(ComboBoxListCell.forListView(playerStats));
        StackPane root = new StackPane() ;
        root.getChildren().addAll(listView) ;
        window.setScene(new Scene (root, 200 , 250)) ;
        window.show();

        //System.out.println(resultSet);
       // System.out.println(resultSet.getString(1));
       System.out.print(players);
        System.out.println(playerStats);

        //Block other windows' input events until this window is closed
        //window.initModality(Modality.APPLICATION_MODAL);


    }





}

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.ComboBoxListCell;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

/**
 * Created by wesley on 2015-06-13.
 */


public class SearchResults {

    public static final ObservableList names = FXCollections.observableArrayList();
    public static final ObservableList data = FXCollections.observableArrayList();

    public static void display (ResultSet resultSet) throws SQLException {

        Stage window = new Stage();
        window.setTitle("Following Results");
        final ListView listView = new ListView(data) ;
        listView.setPrefSize(200,250);  //check dimensions
        listView.setEditable(true);


        try {
            while (resultSet.next()) {
                names.add(resultSet.getString(1)) ;
                data.add(resultSet.getString(8));
            }

            names.addAll("Hello") ;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        listView.setItems(data);
        listView.setCellFactory(ComboBoxListCell.forListView(names));
        StackPane root = new StackPane() ;
        root.getChildren().addAll(listView) ;
        window.setScene(new Scene (root, 200 , 250)) ;
        window.show();

        System.out.println(resultSet);
       // System.out.println(resultSet.getString(1));
        System.out.println(names);

        //Block other windows' input events until this window is closed
        //window.initModality(Modality.APPLICATION_MODAL);


    }





}

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by jch on 15/06/15.
 */
public class ChampPool {
    @FXML
    static ObservableList<String> champs;
    @FXML
    public static void display(Connection con, String summonerID) {
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Champion Pool");
        champs = FXCollections.observableArrayList();

        ListView<String> champList = null;
        try {
            Statement getPool = con.createStatement();
            ResultSet gprs = getPool.executeQuery("SELECT cname FROM HasPlayed WHERE summonerID = '" + summonerID + "'");
            if (!gprs.isBeforeFirst()) {
                AlertBox.display("Error", "This player hasn't played a single champion.");
            } else {
                while (gprs.next()) {
                    champs.add(gprs.getString("cname"));
                    champList = new ListView<>();
                    champList.setItems(champs);
                    champList.setPrefHeight(400);
                    champList.setPrefWidth(150);
                }
                champList.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
                    DisplayChamp.display(con, newValue);
                });
                Scene scene = new Scene(champList);
                window.setScene(scene);
                window.showAndWait();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }




    }


}

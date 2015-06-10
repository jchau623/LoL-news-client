import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

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

    // player attributes :
    public static String summonerID ;
    public static int age ;
    public static String name ;
    public static String nationality ;
    public static float csPerGame ;
    public static float goldPerMin ;
    public static float kDA;


    private static TextField tf = new TextField();

    public static void display() {
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Search");
        window.setMinWidth(300);

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

        // i guess i need the text field so i moved it to a class field

    }

    // going to execute the SQL query to find the player
    public static void findPlayer(Connection con) throws SQLException {



        Statement stmt = con.createStatement() ;
        // find player is the result

        // needs the box with the text field
        ResultSet rs = stmt.executeQuery("SELECT * FROM Player" + "WHERE summonerID =" + tf.getText() ) ;

        while (rs.next())  {
            summonerID = rs.getString("summonerID") ;
            age = rs.getInt(2);
            name = rs.getString(3) ;
            nationality = rs.getString(4);
            csPerGame = rs.getFloat(5) ;
            goldPerMin = rs.getFloat(6) ;
            kDA = rs.getFloat(7) ;
        }
    }
}

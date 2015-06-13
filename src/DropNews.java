import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by Jason on 2015-06-11.
 */
public class DropNews {

    static String url = new String();
    private static javafx.scene.control.TextField tf;

    public static String display(Connection con, String title, String message) {
        Stage window = new Stage();


        //Block other windows' input events until this window is closed
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle(title);
        window.setMinWidth(250);

        Label label = new Label(message);
        Button button = new Button("Enter");


        button.setOnAction(e -> {
            try{
                deleteNews(con);}
            catch (SQLException e1){
                e1.printStackTrace();
            }
            window.close();
        });
        tf = new javafx.scene.control.TextField();

        HBox layout = new HBox(10);
        VBox layout2 = new VBox(10);
        layout.getChildren().addAll(label, tf, button);
        layout.setAlignment(Pos.CENTER);

        Scene scene = new Scene(layout);
        window.setScene(scene);

        //Shows this stage and waits for it to be hidden (closed) before returning to the caller.
        window.showAndWait();
        return returnURL();
    }

    private static String returnURL() {
        url = tf.getText();
        return url;
    }



    // going to execute the SQL query to find the player
    public static void deleteNews(Connection con) throws SQLException {




// trying a different method here
        Statement stmt = con.createStatement() ;
        // find player is the result
        stmt.execute("DELETE  FROM Player WHERE summonerID = \'" + returnURL() + "\'");}}


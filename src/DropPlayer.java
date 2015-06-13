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

public class DropPlayer {
    public static String summonerID ;
    public static int age ;
    public static String name ;
    public static String nationality ;
    public static float csPerGame ;
    public static float goldPerMin ;
    public static float kDA;




    static String player = new String();
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
             deletePlayer(con);}
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
        return returnPlayer();
    }

    private static String returnPlayer() {
        player = tf.getText();
        return player;
    }



    // going to execute the SQL query to find the player
    public static void deletePlayer(Connection con) throws SQLException {



         /*   PreparedStatement preparedStatement = null;

            String deleteSQL = "DELETE Player WHERE summonerID = ?";

            try {

                preparedStatement = con.prepareStatement(deleteSQL);
                preparedStatement.setString(1,returnPlayer());

                // execute delete SQL statement
                preparedStatement.executeUpdate();

                System.out.println("Record is deleted!");
                System.out.println(returnPlayer());

            } catch (SQLException e) {

                System.out.println(e.getMessage());

            }
            }

        }
*/

        System.out.println("testing");
// trying a different method here
        Statement stmt = con.createStatement() ;
        // find player is the result
        stmt.execute("DELETE  FROM Player WHERE summonerID = \'" + returnPlayer() + "\'");}}


       // System.out.println(rs);

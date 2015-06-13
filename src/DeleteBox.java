import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.sql.Connection;

/**
 * Created by Jason on 2015-06-10.
 */
public class DeleteBox {
    private static Connection con;
    public static void display(Connection con, String title, String message) {
        Stage window = new Stage();

        //Block other windows' input events until this window is closed
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle(title);
        window.setMinWidth(250);

        Label label = new Label(message);
        Button deletePlayer = new Button("delete Player");

        deletePlayer.setOnAction(e -> {
            String deletedPlayer = DropPlayer.display(con, "delete Player", "Enter Info");
            System.out.println(deletedPlayer);
        });



        Button deleteTeam = new Button("delete Team");




 deleteTeam.setOnAction(e -> {
            String deletedTeam = DropTeam.display(con,"delete Team", "Enter team name: ");
            System.out.println(deletedTeam);
        });

/*
        Button deleteRegion = new Button ("delete Region");
        deleteRegion.setOnAction(e-> {
            DeleteRegion.display(con);
        });
*/
        Button deleteNewsItem = new Button("delete News");
        deleteNewsItem.setOnAction(e-> {
            DropNews.display(con,"delete News Item", "What News do you want to delete?");
        });
    /*   Button deleteUser = new Button("delete User");
        deleteUser.setOnAction(e->DeleteUser.display("delete User", "delete user"));
*/


        VBox layout = new VBox(10);
        layout.getChildren().addAll(label,/*deleteRegion, */deleteNewsItem, deletePlayer , deleteTeam /*, deleteUser*/);
        layout.setAlignment(Pos.CENTER);
        layout.setPadding(new Insets(5));

        Scene scene = new Scene(layout);
        window.setScene(scene);

        //Shows this stage and waits for it to be hidden (closed) before returning to the caller.
        window.showAndWait();
    }


}

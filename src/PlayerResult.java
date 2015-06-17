import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by wesley on 2015-06-14.
 */
public class PlayerResult {

    public static void display(String user, Connection con, Player player) {
        Stage window = new Stage();
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10));
        grid.setVgap(5);
        grid.setHgap(5);

        window.setResizable(false);

        Label options = new Label("Options");
        Button champPoolButton = new Button("Champion Pool");
        champPoolButton.setOnAction(e->ChampPool.display(con,player.returnID()));
        Button followButton = new Button("(+) Follow");
        followButton.setOnAction(e -> {
            try {
                System.out.println(user);
                Following.followPlayer(con, user, player.returnID());
            } catch (SQLException e1) {
                if(e1.getErrorCode() == 1)
                    AlertBox.display("Error", "You are already following this player");
                System.out.println(e1);
            }
        });



        Label pIDLabel = new Label("SummonerID: " + player.returnID()) ;
        Label pNameLabel = new Label( "Name: " + player.returnName());
        Label pCSLabel = new Label("CsPerGame: " + Double.toString(player.returnCsPerGame()));
        Label pAgeLabel = new Label( "Age: " + Integer.toString(player.returnAge())) ;
        Label pGoldLabel = new Label( "GoldPerMin: " + Double.toString(player.returnGPM())) ;
        Label pKDALabel  = new Label( "KDA: "+ Double.toString(player.returnKDA())) ;
        Label pNationLabel = new Label("Nationality: " + player.returnNationality()) ;
        Label pRoleLabel = new Label("Role: " + player.getRole());

        GridPane.setConstraints(pIDLabel, 0,0);
        GridPane.setConstraints(pNameLabel, 0 ,1);
        GridPane.setConstraints(pAgeLabel, 0 , 2);
        GridPane.setConstraints(pRoleLabel,0,3);
        GridPane.setConstraints(pGoldLabel,0,4);
        GridPane.setConstraints(pNationLabel,0,5);
        GridPane.setConstraints(pKDALabel, 0 ,6);
        GridPane.setConstraints(pCSLabel,0,7);

        GridPane.setConstraints(options,1,0);
        GridPane.setConstraints(followButton,1,1);
        GridPane.setConstraints(champPoolButton,1,2);

        grid.getChildren().addAll(followButton, pIDLabel, pNameLabel, pCSLabel, pAgeLabel, pGoldLabel, pKDALabel,pNationLabel,pRoleLabel);
        grid.getChildren().addAll(champPoolButton,options);
        Scene scene = new Scene(grid) ;
        window.setScene(scene);
        window.showAndWait();


    }




}

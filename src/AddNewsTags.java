import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Created by Jason on 2015-06-14.
 */
public class AddNewsTags {


    public static void  display(Connection con , String headline, String URL) throws
    SQLException{
        Stage window = new Stage();


        //Block other windows' input events until this window is closed
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Tags");
        window.setMinWidth(100);
        window.setResizable(false);






        //Attribute text fields
        ToggleGroup choices = new ToggleGroup();
        RadioButton player = new RadioButton("Player");
        RadioButton team = new RadioButton("Team");
        RadioButton region = new RadioButton("Region");
        player.setToggleGroup(choices);
        team.setToggleGroup(choices);
        region.setToggleGroup(choices);
        player.setSelected(true);
        Label textFieldLabel = new Label("Add Tag:");
        TextField tf = new TextField();

        Button button = new Button("Enter");
        button.setOnAction(e -> {
            if (choices.getSelectedToggle()==player) {
                try{
                    addPlayerNews(con, player.getText(), headline, URL);
                    //AlertBox.display("Success", "Region is successfully added to the database.");
                    System.out.println("player news added");
                  //  window.close();
                }
                catch (SQLException e1){
                    if (e1.getErrorCode()==1) {
                        AlertBox.display("Error", "Player Doesn't Exist");
                    }
                    e1.printStackTrace();
                }
            }
            else if (choices.getSelectedToggle()==team){
                try{
                    addTeamNews(con, team.getText(), headline, URL);
                    //AlertBox.display("Success", "Region is successfully added to the database.");
                    System.out.println("team news added");
                    //  window.close();
                }
                catch (SQLException e1){
                    if (e1.getErrorCode()==1) {
                        AlertBox.display("Error", "Team doesn't exist");
                    }
                    e1.printStackTrace();

            }}
            else if (choices.getSelectedToggle() == region){
            try{
                addRegionNews(con, region.getText(), headline, URL);
                //AlertBox.display("Success", "Region is successfully added to the database.");
                System.out.println("player news added");
                //  window.close();
            }
            catch (SQLException e1){
                if (e1.getErrorCode()==1) {
                    AlertBox.display("Error", "Region doesn't exist.");
                }
                e1.printStackTrace(); //TODO: remove at code completion
            }
        }


        });

        //layout
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10));
        grid.setVgap(5);
        grid.setHgap(5);
        GridPane.setConstraints(player, 0, 1);
        GridPane.setConstraints(team, 0, 2);
        GridPane.setConstraints(region, 1, 1);
        GridPane.setConstraints(tf, 1, 2);
        GridPane.setConstraints(button, 2, 3);
        GridPane.setHalignment(button, HPos.RIGHT);
        grid.getChildren().addAll( tf, textFieldLabel, player ,team,  region, button);

        Scene scene = new Scene(grid);
        window.setScene(scene);

        //Shows this stage and waits for it to be hidden (closed) before returning to the caller.
        window.showAndWait();}

    private static void addPlayerNews(Connection con, String text, String headline, String url) throws SQLException {
        String addN = "INSERT INTO PlayerNews VALUES ( ?, ?, ?)";
        PreparedStatement update = con.prepareStatement(addN);
        update.setString(1, text);
        update.setString(2, headline);
        update.setString(3, url);
        update.executeUpdate();
    }

    private static void addTeamNews(Connection con, String text, String headline, String url) throws SQLException {
        String addN = "INSERT INTO TeamNews VALUES ( ?, ?, ?)";
        PreparedStatement update = con.prepareStatement(addN);
        update.setString(1, text);
        update.setString(2, headline);
        update.setString(3, url);
        update.executeUpdate();
    }

    private static void addRegionNews(Connection con, String text, String headline, String url) throws SQLException {
        String addN = "INSERT INTO RegionNews VALUES ( ?, ?, ?)";
        PreparedStatement update = con.prepareStatement(addN);
        update.setString(1, text);
        update.setString(2, headline);
        update.setString(3, url);
        update.executeUpdate();}






}

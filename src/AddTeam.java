import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.sql.*;
import java.util.ArrayList;

/**
 * Created by Vivian on 2015-06-09.
 */
public class AddTeam {



    public static void display(Connection con , String title)  {
        Stage window = new Stage();


        //Block other windows' input events until this window is closed
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle(title);
        window.setMinWidth(100);





        Label nameLabel = new Label("Name:");
       TextField name = new TextField();
        Label acronymLabel = new Label("Acronym:");
        TextField acronym = new TextField();
        Label averageBaronsLabel = new Label("Average Barons:");
       TextField averageBarons = new TextField();
        Label averageDragonsLabel = new Label ("Average Dragons:");
     TextField   averageDragons = new TextField();
        Label winLabel = new Label ("Wins:");
       TextField win = new TextField();
        Label lossLabel = new Label ("Losses:");
      TextField  loss = new TextField();
        Label sponsorLabel = new Label ("Sponsor:");
     TextField   sponsor = new TextField();

        ArrayList<String> teamList = null;
        try {
            teamList = getAllTeams(con);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        Label regionLabel = new Label("Region:");
        ChoiceBox<String> region= new ChoiceBox<>();
        region.getItems().addAll(teamList);
        region.getItems().add("Other");


        Button button = new Button("Enter");
        button.setOnAction(e -> {
            try {
                addTeam(con, name.getText(), acronym.getText(), Float.parseFloat(averageBarons.getText()), Float.parseFloat(averageDragons.getText()), Integer.parseInt(win.getText()),Integer.parseInt(loss.getText()),sponsor.getText(), region.getValue());
                AlertBox.display("Success", "Team is successfully added to the database.");

            }
            catch (SQLException e1){
                e1.printStackTrace();
            }
            window.close();
        });

        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10));
        grid.setVgap(5);
        grid.setHgap(5);
        GridPane.setConstraints(nameLabel, 0, 0);
        GridPane.setConstraints(name, 1, 0);
        GridPane.setConstraints(acronymLabel, 0, 1);
        GridPane.setConstraints(acronym, 1, 1);
        GridPane.setConstraints(averageBaronsLabel, 0, 2);
        GridPane.setConstraints(averageBarons, 1, 2);
        GridPane.setConstraints(averageDragonsLabel, 0, 3);
        GridPane.setConstraints(averageDragons, 1, 3);
        GridPane.setConstraints(winLabel, 0, 4);
        GridPane.setConstraints(win, 1, 4);
        GridPane.setConstraints(lossLabel, 0, 5);
        GridPane.setConstraints(loss, 1, 5);
        GridPane.setConstraints(sponsorLabel, 0, 6);
        GridPane.setConstraints(sponsor, 1, 6);
        GridPane.setConstraints(button, 1, 8);
        GridPane.setConstraints(regionLabel, 0, 7);
        GridPane.setConstraints(region, 1, 7);
        GridPane.setHalignment(button, HPos.RIGHT);
        grid.getChildren().addAll(nameLabel,name,acronymLabel,acronym,averageBaronsLabel,averageBarons,averageDragonsLabel,averageDragons,winLabel,win,lossLabel,loss,sponsorLabel,sponsor,region, regionLabel,button);

        Scene scene = new Scene(grid);
        window.setScene(scene);

        //Shows this stage and waits for it to be hidden (closed) before returning to the caller.
        window.showAndWait();
        //return returnTeam();
    }

    public static void addTeam(Connection con, String name, String acronym, Float averageB, Float averageD, Integer wins, Integer losses, String sponsor, String region) throws SQLException{
        String addR = "INSERT INTO TeamThatPlaysIn VALUES (?,?,?,?,?,?,?,?)";
        PreparedStatement update = con.prepareStatement(addR);
        update.setString(1, name);
        update.setString(5, acronym);
        update.setFloat(7, averageB);
        update.setFloat(6,averageD);
        update.setInt(2,wins);
        update.setInt(3,losses);
        update.setString(4,sponsor);
        update.setString(8, region);
        update.executeUpdate();
    }

       /* private static String returnTeam() {
            //team = tf.getText();
            return team;
        }*/

    public static ArrayList<String> getAllTeams(Connection con) throws SQLException {



        ArrayList<String> temp = new ArrayList<String>();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT name FROM Region");

            while (rs.next()) {

                String arr ;
                String n = rs.getString("name");
                arr = n.replace("\n", ",");
System.out.println("added to teamlist");
                    System.out.println(arr);
                    temp.add(arr);

            }
        return temp;
        }
}


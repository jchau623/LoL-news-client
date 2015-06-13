import javafx.geometry.*;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.*;

/**
 * Created by Justin on 5/31/2015.
 */
public class AddPlayer {
    static String player = new String();
    private static javafx.scene.control.TextField summonerID;
    public static String display(Connection con, String title, String message) {
        Stage window = new Stage();


        //Attribute text fields
        Label summonerLabel = new Label("summonerID:");
        summonerID = new javafx.scene.control.TextField();

        Label ageLabel = new Label("Age:");
        TextField age = new TextField();

        Label nameLabel = new Label("Name:");
        TextField name = new TextField();

        Label natLabel = new Label("Nationality:");
        ChoiceBox<String> nationality = new ChoiceBox<>();

        //Block other windows' input events until this window is closed
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle(title);
        window.setMinWidth(100);
        window.setResizable(false);

        Label label = new Label(message);
        Button button = new Button("Enter");




        //get all countries
        List<String> countryList = new ArrayList<>();
        Locale[] locales = Locale.getAvailableLocales();
        for (Locale locale : locales){
            String country = locale.getDisplayCountry();
            if (!country.equals("")) {
                countryList.add(country);
            }
        }
        Collections.sort(countryList);
        Set<String> countryList2 = new TreeSet<>(); //move everything over to treeset to remove duplicates, Collections.sort wont work on sets
        countryList2.addAll(countryList); // there's probably a better way of doing this...
        nationality.getItems().addAll(countryList2);
        nationality.getItems().add("Other");

        Label csPerGameLabel = new Label("csPerGame:");
        TextField csPerGame = new TextField();
        Label goldPerMinLabel = new Label("goldPerMin:");
        TextField goldPerMin = new TextField();
        Label KADLabel = new Label("KA/D Ratio:");
        TextField KAD = new TextField();

        Label rollForGameLabel = new Label("Role");
        ChoiceBox<String> role = new ChoiceBox<>();
        role.getItems().addAll("Top", "Mid" , "Jungle", "Support" , "Marksman") ;

        button.setOnAction(e -> {
            returnPlayer();
            window.close();
            try {
                addPlayer( con, summonerID.getText(), age.getText(), name.getText(),KAD.getText(), csPerGame.getText(), goldPerMin.getText() ,nationality.getValue(), role.getValue()  );

                AlertBox.display("Success", "Player is successfully added to the database.");

            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        });

        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10));
        grid.setVgap(5);
        grid.setHgap(5);
        GridPane.setConstraints(summonerLabel, 0, 0);
        GridPane.setConstraints(summonerID, 1, 0);
        GridPane.setConstraints(ageLabel, 0, 1);
        GridPane.setConstraints(age, 1, 1);
        GridPane.setConstraints(nameLabel, 0, 2);
        GridPane.setConstraints(name, 1, 2);
        GridPane.setConstraints(natLabel, 0, 6);
        GridPane.setConstraints(nationality, 1, 6);
        GridPane.setConstraints(csPerGameLabel, 0 ,4);
        GridPane.setConstraints(csPerGame, 1, 4);
        GridPane.setConstraints(goldPerMinLabel, 0, 5);
        GridPane.setConstraints(goldPerMin, 1, 5);
        GridPane.setConstraints(KADLabel, 0, 3);
        GridPane.setConstraints(KAD, 1, 3);
        GridPane.setConstraints(rollForGameLabel, 0,7);
        GridPane.setConstraints(role, 1,7);
        GridPane.setConstraints(button, 1, 8);
        GridPane.setHalignment(button, HPos.RIGHT);

        grid.getChildren().addAll(summonerLabel,summonerID,ageLabel,age,nameLabel,name,natLabel,nationality,csPerGameLabel,csPerGame,goldPerMinLabel,goldPerMin,KADLabel,KAD,button , role , rollForGameLabel);

        Scene scene = new Scene(grid);
        window.setScene(scene);

        //Shows this stage and waits for it to be hidden (closed) before returning to the caller.
        window.showAndWait();
        return returnPlayer();
    }

    private static String returnPlayer() {
        player = summonerID.getText();
        return player;
    }

    private static void addPlayer(Connection con, String summonerID , String age, String name, String kda, String cs, String gold, String nationality , String role) throws SQLException {

        String result = "INSERT INTO Player VALUES (?, ?, ?, ?, ?,?,? , ?) " ;
        PreparedStatement update = con.prepareStatement(result) ;
        update.setString(1,summonerID );
        update.setString(2, age );
        update.setString(3, name);
        update.setString(4, nationality);
        update.setString(5,cs);
        update.setString(6, gold);
        update.setString(7, kda);
        update.setString(8, role);

        update.executeUpdate() ;
        System.out.println("testing");

    }

}

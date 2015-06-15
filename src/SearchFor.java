import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Justin on 6/1/2015.
 */
public class SearchFor {
    private static TextField tf = new TextField();


    public static void display(Connection con) {
        Connection connection = con;
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Search");
        //window.setMinWidth(300);

        tf.setPromptText("Enter name");
        tf.setMinWidth(234);

        Button searchButton = new Button("Search");

        ToggleGroup choices = new ToggleGroup();
        RadioButton player = new RadioButton("Player");
        RadioButton team = new RadioButton("Team");
        RadioButton region = new RadioButton("Region");
        player.setToggleGroup(choices);
        team.setToggleGroup(choices);
        region.setToggleGroup(choices);
        player.setSelected(true);

        HBox searchBar = new HBox(5);
        searchBar.getChildren().addAll(tf, searchButton);
        searchBar.setAlignment(Pos.CENTER);

        GridPane layout = new GridPane();
        layout.setVgap(5);
        Label selectCategories = new Label("Select Categories:");
        GridPane.setConstraints(selectCategories, 0, 0);
        GridPane.setConstraints(player, 0, 1);
        GridPane.setConstraints(team, 0, 2);
        GridPane.setConstraints(region, 1, 1);
        layout.getChildren().addAll(selectCategories, player, team, region);
        layout.setAlignment(Pos.TOP_CENTER);

        Label askReturn = new Label("Return playerName sorted by:");
        ChoiceBox<String> attributes = new ChoiceBox<>();
        attributes.getItems().addAll("summonerID", "Age", "Name", "KA/D Ratio", "csPerMin", "goldPerMin", "Nationality");
        player.setOnAction(e -> {
            attributes.getItems().clear();
            attributes.getItems().addAll("summonerID", "Age", "Name", "KA/D Ratio", "csPerMin", "goldPerMin", "Nationality");


        });
        team.setOnAction(e-> {
            attributes.getItems().clear();
            attributes.getItems().addAll("Name", "Acronym", "Average Barons", "Average Dragons", "Wins", "Losses", "Sponsor", "Region");
        });
        region.setOnAction(e->{
            attributes.getItems().clear();
            attributes.getItems().addAll("Acronym", "Name");
        });
        GridPane.setConstraints(askReturn, 0, 3);
        GridPane.setConstraints(attributes, 0, 4);
        Text in = new Text("in");
        GridPane.setConstraints(in, 0, 5);
        ChoiceBox<String> order = new ChoiceBox<>();
        order.getItems().addAll("Default", "Ascending order", "Descending order");
        GridPane.setConstraints(order, 0, 6);

        layout.getChildren().addAll(askReturn, attributes, in, order);

        BorderPane borderPane = new BorderPane(layout);
        borderPane.setTop(searchBar);
        borderPane.setPadding(new Insets(10));
        Scene scene = new Scene(borderPane);

        searchButton.setOnAction(e->{
            try {
                if (choices.getSelectedToggle() == player) {
                    findPlayer("summonerID " ,connection, attributes.getValue(), order.getValue()) ;
                    System.out.println(findPlayer("summonerID " ,connection, attributes.getValue(), order.getValue()));
                    SearchResults.display(findPlayer("summonerID " ,connection, attributes.getValue(), order.getValue()));

                } else if (choices.getSelectedToggle() == team) {
                    findTeam(connection, attributes.getValue(), order.getValue());
                } else if (choices.getSelectedToggle() == region) {
                    findRegion(connection, attributes.getValue(), order.getValue());
                }
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            window.close();
        });

        window.setScene(scene);
        window.show();


    }
    // going to execute the SQL query to find the player
    public static ArrayList<Player> findPlayer(String filter , Connection con, String attribute, String value) throws SQLException {

        String realAttributeName = (attribute=="Age") ? "age" : (attribute=="Name") ? "name" :
                (attribute=="KA/D Ratio") ? "KDA" : (attribute=="Nationality") ? "nationality" : attribute;
        String order = (value=="Ascending order")?"ASC":"DESC";


        Statement stmt = con.createStatement() ;

        ResultSet rs =
                stmt.executeQuery(
                                "SELECT * " +
                                "FROM Player WHERE " +

                                        filter +   //"summonerID " +

                                        "= \'" + tf.getText() + "\'" +
                                " ORDER BY " + realAttributeName + " " + order);

        ArrayList<Player> p = new ArrayList<Player>() ;
        if (!rs.next()) {
            AlertBox.display("Error", "No results found");
        }

// dont mess this up
        else {
            Player player1 = new Player();
            player1.setID(rs.getString(1));
            player1.setAge(rs.getInt(2));
            player1.setName(rs.getString(3));
            player1.setNationality(rs.getString(4));
            player1.setCsPerMin(rs.getFloat(5));
            player1.setGPM(rs.getFloat(6));
            player1.setKDA(rs.getFloat(7));

            //    listOfPlayers.add(player1);
            p.add(player1);

            //  System.out.println(player.returnGPM());
            // System.out.println(player.returnAge());
            // System.out.println(player.returnName());

            while (rs.next()) {
                Player player = new Player();
                player.setID(rs.getString(1));
                player.setAge(rs.getInt(2));
                player.setName(rs.getString(3));
                player.setNationality(rs.getString(4));
                player.setCsPerMin(rs.getFloat(5));
                player.setGPM(rs.getFloat(6));
                player.setKDA(rs.getFloat(7));

                //   listOfPlayers.add(player);
                p.add(player);

                //  System.out.println(player.returnGPM());
                //  System.out.println(player.returnAge());
                //  System.out.println(player.returnName());

            }
        }
        return  p;

    }






    public static void findTeam(Connection con, String attribute, String value) throws SQLException {
        List<Team> listOfTeams = new ArrayList<>();
        String realAttributeName = (attribute=="Name")?"name":(attribute=="Acronym")?"acronym":
                (attribute=="Average Barons")?"averageBarons":(attribute=="Average Dragons")?
                        "averageDragons":(attribute=="Wins")?"wins":(attribute=="Losses")?
                        "losses":(attribute=="Sponsor")?"sponsor":(attribute=="Region")?"region":attribute;
        String order = (value=="Ascending order")?"ASC":"DESC";
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM TeamThatPlaysIn WHERE name = \'" + tf.getText() + "\' ORDER BY " + realAttributeName + " " + order);
        while(rs.next()) {

            Team team = new Team();
            team.setTeamName(rs.getString(1));
            team.setWins(rs.getInt(2));
            team.setLosses(rs.getInt(3));
            team.setSponsor(rs.getString(4));
            team.setAcronym(rs.getString(5));
            team.setAverageDragons(rs.getFloat(6));
            team.setAverageBarons(rs.getFloat(7));
            team.setRegion(rs.getString(8));
            listOfTeams.add(team);

        }
    }

    public static void findRegion(Connection con, String attribute, String value) throws SQLException {
        List<Region> listofRegions = new ArrayList<>();
        String realAttributeName = (attribute=="Acronym")?"acronym":(attribute=="Name")?"name":attribute;
        String order = (value=="Ascending order")?"ASC":"DESC";
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM Region WHERE name = \'" + tf.getText() + "\' ORDER BY " + realAttributeName + " " + order);
        while(rs.next()) {
            Region region = new Region();
            region.setRegionName(rs.getString(1));
            region.setAcronym(rs.getString(2));
            System.out.println(region.getAcronym());
            System.out.println(region.getRegionName());
        }
    }





}
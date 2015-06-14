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
    private static Label askReturn;
    private static TextField tf = new TextField();
//TODO: We should also be able to search for newsitems, matches, and champions.

    // currently displaying

    public static void display(Connection con) {
        Connection connection = con;
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Search");
        //window.setMinWidth(300);

        tf.setPromptText("Enter name");
        tf.setMinWidth(234);

        Button searchButton = new Button("Search");
        CheckBox advanced = new CheckBox("Advanced Search");
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
        GridPane.setConstraints(advanced, 1, 2);
        layout.getChildren().addAll(selectCategories, player, team, region, advanced);
        layout.setAlignment(Pos.TOP_CENTER);

       Label askReturn = new Label("Return Player sorted by:");

        ChoiceBox<String> attributes = new ChoiceBox<>();
        attributes.getItems().addAll("summonerID", "Age", "Name", "KA/D Ratio", "csPerMin", "goldPerMin", "Nationality", "Role");
        player.setOnAction(e -> {
            askReturn.setText("Return Player sorted by:");
            attributes.getItems().clear();
            attributes.getItems().addAll("summonerID", "Age", "Name", "KA/D Ratio", "csPerMin", "goldPerMin", "Nationality", "Role");


        });
        team.setOnAction(e-> {
            attributes.getItems().clear();
            attributes.getItems().addAll("Name", "Acronym", "Average Barons", "Average Dragons", "Wins", "Losses", "Sponsor", "Region");
            askReturn.setText("Return Team sorted by:");
        });
        region.setOnAction(e->{
            attributes.getItems().clear();
            attributes.getItems().addAll("Acronym", "Name");
            askReturn.setText("Return Region sorted by:");
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
// TODO expand the search box to include the fields for the advanced search, decompress the box to get rid of advanced fields\
//
        advanced.setOnAction(e -> {
            Label selectLabel = new Label("select:");
            ChoiceBox<String> select = new ChoiceBox<>();
            select.getItems().addAll("summonerID", "Age", "Name", "KA/D Ratio", "csPerMin", "goldPerMin", "Nationality");
            Label whereLabel = new Label("Where");
            ChoiceBox<String> whereAttributes = new ChoiceBox<>();
            whereAttributes.getItems().addAll("summonerID", "Age", "Name", "KA/D Ratio", "csPerMin", "goldPerMin", "Nationality");
            ChoiceBox<String> condition = new ChoiceBox<>();
            condition.getItems().addAll("=", "<", ">", "<>");
            TextField condition2 = new TextField();
            condition2.setMinWidth(100);
            GridPane.setConstraints(selectLabel, 2, 7);
            GridPane.setConstraints(whereLabel ,1,8 );
            GridPane.setConstraints(whereAttributes, 2, 8);
            GridPane.setConstraints(condition, 3, 8);
            GridPane.setConstraints(condition2, 4, 8);
            if (advanced.isSelected() && choices.getSelectedToggle() == player) {
                GridPane layout3 = new GridPane();
                layout3.setVgap(5);
                layout3.setPadding(new Insets(10));
                layout3.getChildren().addAll(selectCategories, player, team, region, advanced, askReturn, attributes, in, order, selectLabel, whereLabel, whereAttributes, condition, condition2);
                layout.setAlignment(Pos.TOP_CENTER);
                BorderPane borderPane2 = new BorderPane(layout3);
                borderPane2.setTop(searchBar);
                borderPane2.setPadding(new Insets(10));
                Scene scene2 = new Scene(borderPane2);
                window.setScene(scene2);
            }
            if (!advanced.isSelected()) {
                GridPane layout2 = new GridPane();
                layout2.setVgap(5);
                layout2.setPadding(new Insets(10));
                layout2.getChildren().addAll(selectCategories, player, team, region, advanced, askReturn, attributes, in, order);
                layout2.setAlignment(Pos.TOP_CENTER);
                BorderPane borderPane3 = new BorderPane(layout2);
                borderPane3.setTop(searchBar);
                borderPane3.setPadding(new Insets(10));
                Scene scene3 = new Scene(borderPane3);
                window.setScene(scene3);
            }
        });
// TODO: add in a toggle button "Advanced Search". If this is checked off, we will do AdvancedFindPlayer, or AdvancedFindTeam, or AdvancedFindRegion
        searchButton.setOnAction(e->{
            try {
               /* if (advanced.isSelected()){
                    if (choices.getSelectedToggle() == player) {
                        advancedFindPlayer(connection, attributes.getValue(), order.getValue()) ;
                        System.out.println(findPlayer(connection, attributes.getValue(), order.getValue()));
                        SearchResults.display(findPlayer(connection, attributes.getValue(), order.getValue()));

                    } else if (choices.getSelectedToggle() == team) {
                        advancedFindTeam(connection, attributes.getValue(), order.getValue());
                    } else if (choices.getSelectedToggle() == region) {
                        advancedFindRegion(connection, attributes.getValue(), order.getValue());
                    }
                }
                else */
                if (choices.getSelectedToggle() == player) {
                    findPlayer(connection, attributes.getValue(), order.getValue()) ;
                    System.out.println(findPlayer(connection, attributes.getValue(), order.getValue()));
                    SearchResults.display(findPlayer(connection, attributes.getValue(), order.getValue()));

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
    public static ArrayList<Player> findPlayer(Connection con, String attribute, String value) throws SQLException {
        String realAttributeName = (attribute=="Age") ? "age" : (attribute=="Name") ? "name" :
                (attribute=="KA/D Ratio") ? "KDA" : (attribute=="Nationality") ? "nationality" : (attribute=="Role") ? "role" : attribute;
        String order = (value=="Ascending order")?"ASC":"DESC";
    //KA/D Ratio is either KAD or KDA
        List<Player> listOfPlayers = new ArrayList<>();
        Statement stmt = con.createStatement() ;
        //ResultSet rs = stmt.executeQuery("SELECT * FROM Player WHERE " + realAttributeName + " = \'" + tf.getText() + "\'");
        ResultSet rs = stmt.executeQuery("SELECT * FROM Player WHERE summonerID = \'" + tf.getText() + "\' ORDER BY " + realAttributeName + " " + order);
        //results = rs;
        if (!rs.next()) {
            AlertBox.display("Error", "No results found");
        }

        ArrayList<Player> p = new ArrayList<Player>() ;

        // Todo : this currently works but if I do not comment out the while portion, the result set becomes blank. What is wrong?
       // while (rs.next())  {

            Player player = new Player();
            player.setID(rs.getString(1));
            player.setAge(rs.getInt(2));
            player.setName(rs.getString(3));
            player.setNationality(rs.getString(4));
            player.setCsPerMin(rs.getFloat(5));
            player.setGPM(rs.getFloat(6));
            player.setKDA(rs.getFloat(7));
            player.setRole(rs.getString(8));

            listOfPlayers.add(player);
            p.add(player);

            System.out.println(player.returnGPM());
            System.out.println(player.returnAge());
            System.out.println(player.returnName());

   //     }
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

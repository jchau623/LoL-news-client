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
import java.util.Arrays;
import java.util.List;

/**
 * Created by Justin on 6/1/2015.
 */
public class SearchFor {
    private static TextField tf = new TextField();
    private static Label askReturn;

    static Label whereLabel = new Label("Where");
    static ChoiceBox<String> select = new ChoiceBox<>();
    static ChoiceBox<String> select2 = new ChoiceBox<>();
    static ChoiceBox<String> select3 = new ChoiceBox<>();
    static ChoiceBox<String> whereAttributes = new ChoiceBox<String>();
    static ChoiceBox<String> condition = new ChoiceBox<>();
    static Label selectLabel = new Label("select:");
    static TextField condition2 = new TextField();
    static Label whereLabel2 = new Label("Where");
    static ChoiceBox<String> whereAttributes2 = new ChoiceBox<>();
    static ChoiceBox<String> condition3 = new ChoiceBox<>();
    static TextField condition4 = new TextField();

    //TODO FIX ADVANCED SEARCH SPACING, PLS JUSTIN SENPAI, I DON'T KNOW HOW TO DO IT QQ
    public static void display(Connection con, String user) {
        System.out.println(user);
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

        Label askReturn = new Label("Return playerName sorted by:");
        ChoiceBox<String> attributes = new ChoiceBox<String>();
        attributes.getItems().addAll("summonerID", "Age", "Name", "KDA Ratio", "csPerMin", "goldPerMin", "Nationality");
        player.setOnAction(e -> {

            askReturn.setText("Return Player sorted by:");
            attributes.getItems().clear();
            attributes.getItems().addAll("summonerID", "Age", "Name", "KDA Ratio", "csPerMin", "goldPerMin", "Nationality", "Role");
            select.getItems().clear();
            select2.getItems().clear();
            select3.getItems().clear();
            whereAttributes.getItems().clear();
            whereAttributes2.getItems().clear();
            condition.getItems().clear();
            condition3.getItems().clear();
            select.getItems().addAll("summonerID", "Age", "Name", "KDA Ratio", "csPerMin", "goldPerMin", "Nationality");
            select2.getItems().addAll("summonerID", "Age", "Name", "KDA Ratio", "csPerMin", "goldPerMin", "Nationality");
            select3.getItems().addAll("summonerID", "Age", "Name", "KDA Ratio", "csPerMin", "goldPerMin", "Nationality");
            whereAttributes.getItems().addAll("summonerID", "Age", "Name", "KDA Ratio", "csPerMin", "goldPerMin", "Nationality");
            condition.getItems().addAll("=", "<", ">", "<>");
            whereAttributes2.getItems().addAll("summonerID", "Age", "Name", "KDA Ratio", "csPerMin", "goldPerMin", "Nationality");
            condition3.getItems().addAll("=", "<", ">", "<>");
            condition4.setMinWidth(100);

        });


        team.setOnAction(e -> {
            askReturn.setText("Return Team sorted by:");
            attributes.getItems().clear();
            attributes.getItems().addAll("Name", "Acronym", "Wins", "Losses", "Sponsor", "rname");
            select.getItems().clear();
            select2.getItems().clear();
            select3.getItems().clear();
            whereAttributes.getItems().clear();
            whereAttributes2.getItems().clear();
            condition.getItems().clear();
            condition3.getItems().clear();
            select.getItems().addAll("Name", "Acronym", "Wins", "Losses", "Sponsor", "rname");
            select2.getItems().addAll("Name", "Acronym",  "Wins", "Losses", "Sponsor", "rname");
            select3.getItems().addAll("Name", "Acronym",  "Wins", "Losses", "Sponsor", "rname");
            whereAttributes.getItems().addAll("Name", "Acronym", "Wins", "Losses", "Sponsor", "rname");
            condition.getItems().addAll("=", "<", ">", "<>");

            whereAttributes2.getItems().addAll("Name", "Acronym", "Wins", "Losses", "Sponsor", "rname");
            condition3.getItems().addAll("=", "<", ">", "<>");
            condition4.setMinWidth(100);

        });
        region.setOnAction(e -> {
            askReturn.setText("Return Region sorted by:");
            attributes.getItems().clear();
            attributes.getItems().addAll("Acronym", "Name");
            select.getItems().clear();
            select2.getItems().clear();
            select3.getItems().clear();
            whereAttributes.getItems().clear();
            whereAttributes2.getItems().clear();
            condition.getItems().clear();
            condition3.getItems().clear();
            select.getItems().addAll("Acronym", "Name");
            select2.getItems().addAll("Acronym", "Name");
            select3.getItems().addAll("Acronym", "Name");
            whereAttributes.getItems().addAll("Acronym", "Name");
            condition.getItems().addAll("=", "<", ">", "<>");


            whereAttributes2.getItems().addAll("Acronym", "Name");
            condition3.getItems().addAll("=", "<", ">", "<>");
            condition4.setMinWidth(100);

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

        advanced.setOnAction(e -> {
            if (choices.getSelectedToggle() == player) {
                select.getItems().clear();
                select2.getItems().clear();
                select3.getItems().clear();
                whereAttributes.getItems().clear();
                whereAttributes2.getItems().clear();
                condition.getItems().clear();
                condition3.getItems().clear();
                select.getItems().addAll("summonerID", "Age", "Name", "KDA Ratio", "csPerMin", "goldPerMin", "Nationality");
                select2.getItems().addAll("summonerID", "Age", "Name", "KDA Ratio", "csPerMin", "goldPerMin", "Nationality");
                select3.getItems().addAll("summonerID", "Age", "Name", "KDA Ratio", "csPerMin", "goldPerMin", "Nationality");
                whereAttributes.getItems().addAll("summonerID", "Age", "Name", "KDA Ratio", "csPerMin", "goldPerMin", "Nationality");
                condition.getItems().addAll("=", "<", ">", "<>");
                whereAttributes2.getItems().addAll("summonerID", "Age", "Name", "KDA Ratio", "csPerMin", "goldPerMin", "Nationality");
                condition3.getItems().addAll("=", "<", ">", "<>");
                condition4.setMinWidth(100);

                GridPane.setConstraints(selectLabel, 1, 7);
                GridPane.setConstraints(select, 2, 7);
                GridPane.setConstraints(select2, 3, 7);
                GridPane.setConstraints(select3, 4, 7);
                GridPane.setConstraints(whereLabel, 1, 8);
                GridPane.setConstraints(whereAttributes, 2, 8);
                GridPane.setConstraints(condition, 3, 8);
                GridPane.setConstraints(condition2, 4, 8);

                GridPane.setConstraints(whereLabel2, 1, 9);
                GridPane.setConstraints(whereAttributes2, 2, 9);
                GridPane.setConstraints(condition3, 3, 9);
                GridPane.setConstraints(condition4, 4, 9);
                if (advanced.isSelected() && choices.getSelectedToggle() == player) {
                    GridPane layout3 = new GridPane();
                    layout3.setVgap(5);
                    layout3.setPadding(new Insets(10));
                    layout3.getChildren().addAll(select, select2, select3, whereLabel2, whereAttributes2, condition3, condition4, selectCategories, player, team, region, advanced, askReturn, attributes, in, order, selectLabel, whereLabel, whereAttributes, condition, condition2);
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
            }
            // "Name", "Acronym", "Average Barons", "Average Dragons", "Wins", "Losses", "Sponsor", "Region"

            else if (choices.getSelectedToggle() == team) {

                GridPane.setConstraints(selectLabel, 2, 7);
                GridPane.setConstraints(select, 3, 7);
                GridPane.setConstraints(select2, 4, 7);
                GridPane.setConstraints(select3, 5, 7);
                GridPane.setConstraints(whereLabel, 1, 8);
                GridPane.setConstraints(whereAttributes, 2, 8);
                GridPane.setConstraints(condition, 3, 8);
                GridPane.setConstraints(condition2, 4, 8);

                GridPane.setConstraints(whereLabel2, 1, 9);
                GridPane.setConstraints(whereAttributes2, 2, 9);
                GridPane.setConstraints(condition3, 3, 9);
                GridPane.setConstraints(condition4, 4, 9);
                if (advanced.isSelected() && choices.getSelectedToggle() == team) {
                    GridPane layout3 = new GridPane();
                    layout3.setVgap(5);
                    layout3.setPadding(new Insets(10));
                    layout3.getChildren().addAll(select, select2, select3, whereLabel2, whereAttributes2, condition3, condition4, selectCategories, player, team, region, advanced, askReturn, attributes, in, order, selectLabel, whereLabel, whereAttributes, condition, condition2);
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
            }//"Acronym", "Name"
            else if (choices.getSelectedToggle() == region) {

                GridPane.setConstraints(selectLabel, 2, 7);
                GridPane.setConstraints(select, 3, 7);
                GridPane.setConstraints(select2, 4, 7);
                GridPane.setConstraints(select3, 5, 7);
                GridPane.setConstraints(whereLabel, 1, 8);
                GridPane.setConstraints(whereAttributes, 2, 8);
                GridPane.setConstraints(condition, 3, 8);
                GridPane.setConstraints(condition2, 4, 8);

                GridPane.setConstraints(whereLabel2, 1, 9);
                GridPane.setConstraints(whereAttributes2, 2, 9);
                GridPane.setConstraints(condition3, 3, 9);
                GridPane.setConstraints(condition4, 4, 9);
                if (advanced.isSelected() && choices.getSelectedToggle() == region) {
                    GridPane layout3 = new GridPane();
                    layout3.setVgap(5);
                    layout3.setPadding(new Insets(10));
                    layout3.getChildren().addAll(select, select2, select3, whereLabel2, whereAttributes2, condition3, condition4, selectCategories, player, team, region, advanced, askReturn, attributes, in, order, selectLabel, whereLabel, whereAttributes, condition, condition2);
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
            }


        });
        //Todo must finish linking results and search  !!!
        searchButton.setOnAction(e -> {
            try {
                if (advanced.isSelected()) {
                    Toggle choice = choices.getSelectedToggle();
                    String searchFor = null;
                    if (choice == player) {
                        searchFor = "Player";

                    }
                    if (choice == team) searchFor = "TeamThatPlaysIn";
                    if (choice == region) searchFor = "Region";

                    if (select.getValue() != null && select2.getValue() == null && select3.getValue() == null && whereAttributes.getValue() != null && condition.getValue() != null && condition2.getText() != null && whereAttributes2.getValue() == null && condition3.getValue() == null && condition4.getText().equals("")) {

                        String a = null;
                        try {
                            a = advancedFindPlayer(connection, attributes.getValue(), order.getValue(), 1, searchFor);
                        } catch (SQLException e1) {
                            e1.printStackTrace();
                        }
                        AlertBox.display("Results:", a);


                    } else if (select.getValue() != null && select2.getValue() != null && select3.getValue() == null && whereAttributes.getValue() != null && condition.getValue() != null && condition2.getText() != null && whereAttributes2.getValue() == null && condition3.getValue() == null && condition4.getText().equals("")) {

                        String a = null;
                        try {
                            a = advancedFindPlayer(connection, attributes.getValue(), order.getValue(), 2, searchFor);
                        } catch (SQLException e1) {
                            e1.printStackTrace();
                        }
                        AlertBox.display("Players:", a);

                    } else if (select.getValue() != null && select2.getValue() != null && select3.getValue() != null && whereAttributes.getValue() != null && condition.getValue() != null && condition2.getText() != null && whereAttributes2.getValue() == null && condition3.getValue() == null && condition4.getText().equals("")) {

                        String a = null;
                        try {
                            a = advancedFindPlayer(connection, attributes.getValue(), order.getValue(), 3, searchFor);
                        } catch (SQLException e1) {
                            e1.printStackTrace();
                        }
                        AlertBox.display("Players:", a);

                    } else if (select.getValue() != null && select2.getValue() == null && select3.getValue() == null && whereAttributes.getValue() != null && condition.getValue() != null && condition2.getText() != null && whereAttributes2.getValue() != null && condition3.getValue() != null && !condition4.getText().equals("")) {

                        String a = null;
                        try {
                            a = advancedFindPlayer(connection, attributes.getValue(), order.getValue(), 4, searchFor);
                        } catch (SQLException e1) {
                            e1.printStackTrace();
                        }
                        AlertBox.display("Players:", a);

                    } else if (select.getValue() != null && select2.getValue() != null && select3.getValue() == null && whereAttributes.getValue() != null && condition.getValue() != null && condition2.getText() != null && whereAttributes2.getValue() != null && condition3.getValue() != null && !condition4.getText().equals("")) {

                        String a = null;
                        try {
                            a = advancedFindPlayer(connection, attributes.getValue(), order.getValue(), 5, searchFor);
                        } catch (SQLException e1) {
                            e1.printStackTrace();
                        }
                        AlertBox.display("Players:", a);

                    } else if (select.getValue() != null && select2.getValue() != null && select3.getValue() != null && whereAttributes.getValue() != null && condition.getValue() != null && condition2.getText() != null && whereAttributes2.getValue() != null && condition3.getValue() != null && !condition4.getText().equals("")) {

                        String a = null;
                        try {
                            a = advancedFindPlayer(connection, attributes.getValue(), order.getValue(), 6, searchFor);
                        } catch (SQLException e1) {
                            e1.printStackTrace();
                        }
                        AlertBox.display("Players:", a);


                    }

                } else if (choices.getSelectedToggle() == player) {
                    SearchResults.display(user, con, findPlayer("summonerID ", connection, attributes.getValue(), order.getValue()));

                } else if (choices.getSelectedToggle() == team) {
                    ArrayList<Team> seachedteam = findTeam(connection, attributes.getValue(), order.getValue());
                    // TeamListView.display(seachedteam);
                    ArrayList<Player> searchedPlayers = null;

                    if (seachedteam != null) {
                        //      searchedPlayers = findPlayerFromTeam(connection, seachedteam.returnTeamName());
                        TeamListView.display(user, connection, seachedteam);

                        //for (int i = 0; i < searchedPlayers.size(); i++) {
                        //   System.out.println(searchedPlayers.get(i).returnID());
                        //  }

                        // TeamResult.display(seachedteam, searchedPlayers);
                    }
                    //check -- this works too, i used CounterLogicGaming , returned DoubleLift

                } else if (choices.getSelectedToggle() == region) {
                    ArrayList<Region> searchedRegion = findRegion(connection, attributes.getValue(), order.getValue());
                    //System.out.println(searchedRegion.get(0).getRegionName());

                    RegionListResult.display(user, connection, searchedRegion);



                }
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        });

        window.setScene(scene);
        window.show();


    }

// TODO if we have time we should also add in another choice box, choosing between AND and OR. 
    private static String advancedFindPlayer(Connection con, String attribute, String value, int i, String searchFor) throws SQLException {
        String realAttributeName = (attribute == "Age") ? "age" : (attribute == "Name") ? "name" :
                (attribute == "KDA Ratio") ? "KDA" : (attribute == "Nationality") ? "nationality" : attribute;
        String order = (value == "Ascending order") ? "ASC" : "DESC";


        Statement s = con.createStatement();
        List<String> list = Arrays.asList("Nationality", "Name", "rname", "summonerID", "Acronym", "Sponsor");

        String cond2 = condition2.getText();
        if (list.contains(whereAttributes.getValue())) {System.out.println("got here");
        cond2 ="'"+ condition2.getText() + "'";}


        String cond4 = condition4.getText();
        if (list.contains(whereAttributes2.getValue())) {System.out.println("got here");
            cond4 ="'"+ condition4.getText() + "'";}
        if (i == 1) {
            String query = "SELECT " + select.getValue() + " FROM " + searchFor + " WHERE " + whereAttributes.getValue() + " " + condition.getValue() + " " + cond2;
            System.out.println(query);
            try {

                ResultSet rs;
                rs = s.executeQuery(query);
                return runQuery(rs, query, 1);

            } catch (SQLException e1) {
                System.out.println(e1);
            }

        } else if (i == 2) {

            String query = "SELECT " + select.getValue() + ", " + select2.getValue() + " FROM " + searchFor + " WHERE " + whereAttributes.getValue() + " " + condition.getValue() + " " + cond2;
            System.out.println(query);
            try {
                ResultSet rs;
                rs = s.executeQuery(query);
                return runQuery(rs, query, 2);
            } catch (SQLException e1) {
                System.out.println(e1);
            }

        } else if (i == 3) {
            String query = "SELECT " + select.getValue() + ", " + select2.getValue() + ", " + select3.getValue() + " FROM " + searchFor + " WHERE " + whereAttributes.getValue() + " " + condition.getValue() + " " + cond2;
            System.out.println(query);
            try {
                ResultSet rs;
                rs = s.executeQuery(query);
                return runQuery(rs, query, 3);
            } catch (SQLException e1) {
                System.out.println(e1);
            }

        } else if (i == 4) {
            String query = "SELECT " + select.getValue() + " FROM " + searchFor + " WHERE " + whereAttributes.getValue() + " " + condition.getValue() + " " + cond2 + "AND " + whereAttributes2.getValue() + " " + condition3.getValue() + " " + cond4;
            System.out.println(query);
            try {   ResultSet rs;
                rs = s.executeQuery(query);
                return runQuery(rs, query, 1);
            } catch (SQLException e1) {
                System.out.println(e1);
            }
        } else if (i == 5) {
            String query = "SELECT " + select.getValue() + ", " + select2.getValue() + " FROM " + searchFor + " WHERE " + whereAttributes.getValue() + " " + condition.getValue() + " " + cond2 + "AND " + whereAttributes2.getValue() + " " + condition3.getValue() + " " + cond4;
            System.out.println(query);
            try {
                ResultSet rs;
                rs = s.executeQuery(query);
                return runQuery(rs, query, 2);
            } catch (SQLException e1) {
                System.out.println(e1);
            }

        } else {
            String query = "SELECT " + select.getValue() + ", " + select2.getValue() + ", " + select3.getValue() + " FROM " + searchFor + " WHERE " + whereAttributes.getValue() + " " + condition.getValue() + " " + cond2 + "AND " + whereAttributes2.getValue() + " " + condition3.getValue() + " " + cond4;
            System.out.println(query);
            try {
                ResultSet rs;
                rs = s.executeQuery(query);
                return runQuery(rs, query, 3);
            } catch (SQLException e1) {
                System.out.println(e1);
            }
        }
        return null;
    }


    private static String runQuery(ResultSet rs, String query, int i) throws SQLException {
 System.out.println("got to runQuery");
        String p = "Results:" +
                "";
        if (!rs.isBeforeFirst()) {
            AlertBox.display("Error", "No results found");
        }
        if (i==1){
            while (rs.next()) {
                p = p +
                        select.getValue() +": " + rs.getString(1);
            }
        }
        else if (i==2){
            while (rs.next()) {
                p = p + " " +
                      select.getValue() +  ": " + rs.getString(1) +" "+ select2.getValue() +": " + rs.getString(2);
            }
        }
        if (i == 3) {
            while (rs.next()) {
                p = p + " " +select.getValue() +  ": "+ rs.getString(1) + select2.getValue() +": " + rs.getString(2) + " "+ select3.getValue() +": "  + rs.getString(3);
            }
        }
        return p;
    }

    // going to execute the SQL query to find the player
    public static ArrayList<Player> findPlayer(String filter, Connection con, String attribute, String value) throws SQLException {

        String realAttributeName = (attribute == "Age") ? "age" : (attribute == "Name") ? "name" :
                (attribute == "KDA Ratio") ? "KDA" : (attribute == "Nationality") ? "nationality" : attribute;
        String order = (value == "Ascending order") ? "ASC" : "DESC";


        Statement stmt = con.createStatement();

        ResultSet rs =
                stmt.executeQuery(
                        "SELECT * " +
                                "FROM Player WHERE " +

                                filter +   //"summonerID " +

                                "LIKE \'%" + tf.getText() + "%\'" +
                                " ORDER BY " + realAttributeName + " " + order);

        ArrayList<Player> p = new ArrayList<Player>();
        if (!rs.isBeforeFirst()) {
            AlertBox.display("Error", "No results found");
        } else {
            while (rs.next()) {
                Player player = new Player();
                player.setID(rs.getString(1));
                player.setAge(rs.getInt(2));
                player.setName(rs.getString(3));
                player.setNationality(rs.getString(4));
                player.setCsPerMin(rs.getDouble(5));
                player.setGPM(rs.getDouble(6));
                player.setKDA(rs.getDouble(7));
                player.setRole(rs.getString(8));
                System.out.println(player.returnID());
                p.add(player);
            }
        }
        return p;
    }

    public static ArrayList<Team> findTeam(Connection con, String attribute, String value) throws SQLException {
        List<Team> listOfTeams = new ArrayList<>();
        String realAttributeName = (attribute == "Name") ? "name" : (attribute == "Acronym") ? "acronym" :
               (attribute == "Wins") ? "wins" : (attribute == "Losses") ?
                        "losses" : (attribute == "Sponsor") ? "sponsor" : (attribute == "rname") ? "rname" : attribute;
        String order = (value == "Ascending order") ? "ASC" : "DESC";
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM TeamThatPlaysIn WHERE name LIKE \'%" + tf.getText() + "%\' ORDER BY " + realAttributeName + " " + order);
        ArrayList<Team> searchedTeams = new ArrayList<Team>();

        if (!rs.isBeforeFirst()) {
            //System.out.println("TEST");
            AlertBox.display("Error", "No results found");
        }
        while (rs.next()) {
            System.out.println("got here");
            Team team = new Team();
            team.setTeamName(rs.getString(1));
            team.setWins(rs.getInt(2));
            team.setLosses(rs.getInt(3));
            team.setSponsor(rs.getString(4));
            team.setAcronym(rs.getString(5));

            Statement sumRedBaron = con.createStatement();
            ResultSet sumRBRS = sumRedBaron.executeQuery("SELECT SUM(redNumOfBarons) AS redBaronSum FROM Match m " +
                    "WHERE m.redName = '" + team.returnTeamName() + "'");
            sumRBRS.next();
            float redBaronSum = sumRBRS.getFloat(1);
            Statement sumBlueBaron = con.createStatement();
            ResultSet sumBBRS = sumBlueBaron.executeQuery("SELECT SUM(blueNumOfBarons) AS blueBaronSum FROM Match m " +
                    "WHERE m.blueName = '" + team.returnTeamName() + "'");
            sumBBRS.next();
            float blueBaronSum = sumBBRS.getFloat(1);
            Statement matchCount = con.createStatement();
            ResultSet tm = matchCount.executeQuery("SELECT COUNT(*) FROM Match m WHERE m.redname = '" + team.returnTeamName() + "' OR " +
                    "m.bluename = '" + team.returnTeamName() +"'");
            tm.next();
            float totalMatches = tm.getFloat(1);
            float averageBaron = (redBaronSum+blueBaronSum)/totalMatches;
            team.setAverageBarons(averageBaron);
            Statement sumRedDragon = con.createStatement();
            ResultSet sumRDRS = sumRedDragon.executeQuery("SELECT SUM(redNumOfDragons) AS redDragonSum FROM Match m " +
                    "WHERE m.redName = '" + team.returnTeamName() + "'");
            sumRDRS.next();
            float redDragonSum = sumRDRS.getFloat(1);
            Statement sumBlueDragon = con.createStatement();
            ResultSet sumBDRS = sumBlueDragon.executeQuery("SELECT SUM(blueNumOfDragons) AS blueDragonSum FROM Match m " +
                    "WHERE m.blueName = '" + team.returnTeamName() + "'");
            sumBDRS.next();
            float blueDragonSum = sumBDRS.getFloat(1);
            float averageDragon = (redDragonSum+blueDragonSum)/totalMatches;
            team.setAverageDragons(averageDragon);

                    team.setRegion(rs.getString(6));
            searchedTeams.add(team);
        }
        return searchedTeams;

    }

    public static ArrayList<Region> findRegion(Connection con, String attribute, String value) throws SQLException {
        ArrayList<Region> listofRegions = new ArrayList<>();
        String realAttributeName = (attribute == "Acronym") ? "acronym" : (attribute == "Name") ? "name" : attribute;
        String order = (value == "Ascending order") ? "ASC" : "DESC";
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery
                ("SELECT * " +
                        "FROM Region" +
                        " WHERE name LIKE \'%" + tf.getText() + "%\' " +
                        "ORDER BY " + realAttributeName + " " + order);


        if (!rs.isBeforeFirst()) {
            AlertBox.display("Error", "No results found");
        }
        ;
        while (rs.next()) {
            Region region = new Region();
            region.setRegionName(rs.getString(1));
            region.setAcronym(rs.getString(2));

            listofRegions.add(region);
            System.out.println(region.getAcronym());
            System.out.println(region.getRegionName());
        }
        return listofRegions;
    }

    public static ArrayList<Player> findPlayerFromTeam(Connection con, String teamName) throws SQLException {

        ArrayList<Player> listOfPlayers = new ArrayList<Player>();
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery(
                "SELECT p.summonerID, p.age, p.name, p.nationality , p.csPerMin, p.goldPerMin , p.KDA , p.role " +
                        "FROM BelongsTo b , Player p " +
                        "WHERE b.summonerID = p.summonerID " +
                        "AND b.name = " + "\'" + teamName + "\'"
        );
        while (rs.next()) {
            Player p = new Player();
            p.setID(rs.getString(1));
            p.setAge(rs.getInt(2));
            p.setName(rs.getString(3));
            p.setNationality(rs.getString(4));
            p.setCsPerMin(rs.getFloat(5));
            p.setGPM(rs.getFloat(6));
            p.setKDA(rs.getFloat(7));
            p.setRole(rs.getString(8));
            listOfPlayers.add(p);
        }
        return listOfPlayers;
    }



}
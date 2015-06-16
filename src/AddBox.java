

import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.sql.*;
import java.sql.Date;
import java.util.*;

/**
 * Created by Justin on 5/31/2015.
 */
public class AddBox {

    public static void display(Connection con, String title, String message) {
        Stage window = new Stage();

        //Block other windows' input events until this window is closed
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle(title);
        window.setMinWidth(250);

        Label label = new Label(message);
        Button addPlayer = new Button("Add Player");

        addPlayer.setOnAction(e -> {
            AddPlayer.display(con, "Add Player", "Enter Info");
        });

        Button addTeam = new Button("Add Team");
        addTeam.setOnAction(e -> {
            AddTeam.display(con, "Add Team");
        });

       /* addTeam.setOnAction(e -> {
            String addedTeam = AddBox.AddTeam.display("Add Team", "Enter team name: ");
            System.out.println(addedTeam);
        });*/

        //test
        Button addRegion = new Button("Add Region");
        addRegion.setOnAction(e -> {
            AddRegion.display(con, "Add Region");
        });

        Button deleteRegion = new Button("delete Region");
        deleteRegion.setOnAction(e -> {
            DropRegion.display(con, "Delete Region", "delete region");
        });

        Button addChampion = new Button("Add Champion");
        addChampion.setOnAction(e -> {
            AddChampion.display(con, "Add Champion");
        });

        Button addNewsItem = new Button("Add News");
        addNewsItem.setOnAction(e -> {
            AddNewsItem.display(con, "Add News Item");
        });
        Button addMatch = new Button("Add Match");
        addMatch.setOnAction(e -> AddMatch.display(con, "Add Match"));

        VBox layout = new VBox(10);
        layout.getChildren().addAll(label, addRegion, addChampion, addMatch, addNewsItem, addPlayer, addTeam);
        layout.setAlignment(Pos.CENTER);
        layout.setPadding(new Insets(5));

        Scene scene = new Scene(layout);
        window.setScene(scene);

        //Shows this stage and waits for it to be hidden (closed) before returning to the caller.
        window.showAndWait();
    }

    /**
     * Created by Vivian on 2015-06-09.
     */
    public static class AddTeam {

        public static void display(Connection con, String title) {
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
            Label averageDragonsLabel = new Label("Average Dragons:");
            TextField averageDragons = new TextField();
            Label winLabel = new Label("Wins:");
            TextField win = new TextField();
            Label lossLabel = new Label("Losses:");
            TextField loss = new TextField();
            Label sponsorLabel = new Label("Sponsor:");
            TextField sponsor = new TextField();

            ArrayList<String> teamList = null;
            try {
                teamList = getAllRegions(con);
            } catch (SQLException e) {
                e.printStackTrace();
            }

            Label regionLabel = new Label("Region:");
            ChoiceBox<String> region = new ChoiceBox<>();
            region.getItems().addAll(teamList);
            region.getItems().add("Other");

            List<TextField> textFields = new ArrayList<>();
            textFields.add(name);
            textFields.add(acronym);
            textFields.add(averageBarons);
            textFields.add(averageDragons);
            textFields.add(win);
            textFields.add(loss);
            textFields.add(sponsor);
            List<ChoiceBox> choiceBoxes = new ArrayList<>();
            choiceBoxes.add(region);

            Button button = new Button("Enter");
            button.setOnAction(e -> {
                if (choiceBoxChecker(choiceBoxes, textFields) && fieldChecker(textFields, choiceBoxes)) {
                    try {
                        addTeam(con, name.getText(), acronym.getText(), Float.parseFloat(averageBarons.getText()), Float.parseFloat(averageDragons.getText()), Integer.parseInt(win.getText()), Integer.parseInt(loss.getText()), sponsor.getText(), region.getValue());
                        AlertBox.display("Success", "Team is successfully added to the database.");
                        window.close();
                    } catch (SQLException e1) {
                        if (e1.getErrorCode() == 1)
                            AlertBox.display("Error", "A team with the same name or acronym already exists");
                    } catch (NumberFormatException nfe) {
                        AlertBox.display("Error", "Please ensure Average Barons, Average Dragons, Wins, and Losses are numbers");
                    }
                }
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
            grid.getChildren().addAll(nameLabel, name, acronymLabel, acronym, averageBaronsLabel, averageBarons, averageDragonsLabel, averageDragons, winLabel, win, lossLabel, loss, sponsorLabel, sponsor, region, regionLabel, button);

            Scene scene = new Scene(grid);
            window.setScene(scene);

            //Shows this stage and waits for it to be hidden (closed) before returning to the caller.
            window.showAndWait();
            //return returnTeam();
        }

        public static void addTeam(Connection con, String name, String acronym, Float averageB, Float averageD, Integer wins, Integer losses, String sponsor, String region) throws SQLException {
            String addR = "INSERT INTO TeamThatPlaysIn VALUES (?,?,?,?,?,?,?,?)";
            PreparedStatement update = con.prepareStatement(addR);
            update.setString(1, name);
            update.setString(5, acronym);
            update.setFloat(7, averageB);
            update.setFloat(6, averageD);
            update.setInt(2, wins);
            update.setInt(3, losses);
            update.setString(4, sponsor);
            update.setString(8, region);
            update.executeUpdate();
        }

           /* private static String returnTeam() {
                //team = tf.getText();
                return team;
            }*/

        public static ArrayList<String> getAllRegions(Connection con) throws SQLException {


            ArrayList<String> temp = new ArrayList<String>();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT name FROM Region");

            while (rs.next()) {

                String arr;
                String n = rs.getString("name");
                arr = n.replace("\n", ",");
                temp.add(arr);

            }
            return temp;
        }

        private static boolean fieldChecker(List<TextField> textFields, List<ChoiceBox> choiceBoxes) {
            boolean empty = true;
            for (TextField textfield : textFields) {
                if (textfield.getText().isEmpty()) {
                    empty = false;
                    textfield.setStyle("-fx-background-color: #ff9ca0");
                } else {
                    textfield.setStyle("-fx-background-color: white");
                }
            }
            for (ChoiceBox choiceBox : choiceBoxes) {
                if (choiceBox.getValue() == null) {
                    choiceBox.setStyle("-fx-background-color: #ff9ca0");
                } else {
                    choiceBox.setStyle("-fx-background-color: white");
                }
            }
            return empty;
        }

        private static boolean choiceBoxChecker(List<ChoiceBox> choiceBoxes, List<TextField> textFields) {
            boolean empty = true;
            for (ChoiceBox choiceBox : choiceBoxes) {
                if (choiceBox.getValue() == null) {
                    empty = false;
                    choiceBox.setStyle("-fx-background-color: #ff9ca0");
                } else {
                    choiceBox.setStyle("-fx-background-color: white");
                }
            }
            for (TextField textfield : textFields) {
                if (textfield.getText().isEmpty()) {
                    textfield.setStyle("-fx-background-color: #ff9ca0");
                } else {
                    textfield.setStyle("-fx-background-color: white");
                }
            }
            return empty;
        }
    }

    /**
     * Created by Jason on 5/31/2015.
     */
    public static class AddRegion {
        static String region;

        public static void display(Connection con, String title) {
            Stage window = new Stage();


            //Block other windows' input events until this window is closed
            window.initModality(Modality.APPLICATION_MODAL);
            window.setTitle(title);
            window.setMinWidth(100);
            window.setResizable(false);


            //Attribute text fields
            Label acronymLabel = new Label("Acronym:");
            TextField acronym = new TextField();
            Label nameLabel = new Label("Name:");
            TextField name = new TextField();

            Button button = new Button("Enter");
            button.setOnAction(e -> {
                if (!acronym.getText().isEmpty() && !name.getText().isEmpty()) {
                    try {
                        addRegion(con, name.getText(), acronym.getText());
                        AlertBox.display("Success", "Region is successfully added to the database.");
                        window.close();
                    } catch (SQLException e1) {
                        if (e1.getErrorCode() == 1) {
                            AlertBox.display("Error", "A region with that name or acronym already exists");
                        }
                        e1.printStackTrace(); //TODO: remove at code completion
                    }
                }
                if (acronym.getText().isEmpty()) {
                    acronym.setStyle("-fx-background-color: #ff9ca0");
                    if (!name.getText().isEmpty()) {
                        name.setStyle("-fx-background-color: white");
                    }
                }
                if (name.getText().isEmpty()) {
                    name.setStyle("-fx-background-color: #ff9ca0");
                    if (!acronym.getText().isEmpty()) {
                        acronym.setStyle("-fx-background-color: white");
                    }
                }
            });

            //layout
            GridPane grid = new GridPane();
            grid.setPadding(new Insets(10));
            grid.setVgap(5);
            grid.setHgap(5);
            GridPane.setConstraints(acronymLabel, 0, 0);
            GridPane.setConstraints(acronym, 1, 0);
            GridPane.setConstraints(nameLabel, 0, 1);
            GridPane.setConstraints(name, 1, 1);
            GridPane.setConstraints(button, 1, 2);
            GridPane.setHalignment(button, HPos.RIGHT);
            grid.getChildren().addAll(acronymLabel, acronym, nameLabel, name, button);

            Scene scene = new Scene(grid);
            window.setScene(scene);

            //Shows this stage and waits for it to be hidden (closed) before returning to the caller.
            window.showAndWait();
        }


        public static void addRegion(Connection con, String name, String acronym) throws SQLException {
            String addR = "INSERT INTO Region VALUES (?,?)";
            PreparedStatement update = con.prepareStatement(addR);
            update.setString(1, name);
            update.setString(2, acronym);
            update.executeUpdate();
        }


    }

    /**
     * Created by Justin on 5/31/2015.
     */
    public static class AddPlayer {
        static String player = new String();
        private static TextField summonerID;

        public static void display(Connection con, String title, String message) {
            Stage window = new Stage();


            //Attribute text fields
            Label summonerLabel = new Label("summonerID:");
            summonerID = new TextField();

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
            for (Locale locale : locales) {
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
            role.getItems().addAll("Top", "Mid", "Jungle", "Support", "Marksman");

            List<TextField> textFields = new ArrayList<>();
            textFields.add(summonerID);
            textFields.add(age);
            textFields.add(name);
            textFields.add(KAD);
            textFields.add(goldPerMin);
            textFields.add(csPerGame);
            List<ChoiceBox> choiceBoxes = new ArrayList<>();
            choiceBoxes.add(nationality);
            choiceBoxes.add(role);

            button.setOnAction(e -> {
                if (fieldChecker(textFields, choiceBoxes) && choiceBoxChecker(choiceBoxes, textFields))
                    try {
                        addPlayer(con, summonerID.getText(), Integer.parseInt(age.getText()), name.getText(), Float.parseFloat(KAD.getText()), Float.parseFloat(csPerGame.getText()), Float.parseFloat(goldPerMin.getText()), nationality.getValue(), role.getValue());
                        AlertBox.display("Success", "Player is successfully added to the database.");
                        window.close();

                    } catch (SQLException e1) {
                        if (e1.getErrorCode() == 1) {
                            AlertBox.display("Error", "This summonerID already exists in the database");
                            e1.printStackTrace();
                        }
                    } catch (NumberFormatException nfe) {
                        AlertBox.display("Error", "Please ensure Age, KA/D Ratio, csPerGame, and goldPerMin are numbers");
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
            GridPane.setConstraints(csPerGameLabel, 0, 4);
            GridPane.setConstraints(csPerGame, 1, 4);
            GridPane.setConstraints(goldPerMinLabel, 0, 5);
            GridPane.setConstraints(goldPerMin, 1, 5);
            GridPane.setConstraints(KADLabel, 0, 3);
            GridPane.setConstraints(KAD, 1, 3);
            GridPane.setConstraints(rollForGameLabel, 0, 7);
            GridPane.setConstraints(role, 1, 7);
            GridPane.setConstraints(button, 1, 8);
            GridPane.setHalignment(button, HPos.RIGHT);

            grid.getChildren().addAll(summonerLabel, summonerID, ageLabel, age, nameLabel, name, natLabel, nationality, csPerGameLabel, csPerGame, goldPerMinLabel, goldPerMin, KADLabel, KAD, button, role, rollForGameLabel);

            Scene scene = new Scene(grid);
            window.setScene(scene);

            //Shows this stage and waits for it to be hidden (closed) before returning to the caller.
            window.showAndWait();
        }

        private static void addPlayer(Connection con, String summonerID, Integer age, String name, Float kda, Float cs, Float gold, String nationality, String role) throws SQLException {

            String result = "INSERT INTO Player VALUES (?, ?, ?, ?, ?,?,? , ?) ";
            PreparedStatement update = con.prepareStatement(result);
            update.setString(1, summonerID);
            update.setInt(2, age);
            update.setString(3, name);
            update.setString(4, nationality);
            update.setFloat(5, cs);
            update.setFloat(6, gold);
            update.setFloat(7, kda);
            update.setString(8, role);

            update.executeUpdate();
            System.out.println("testing");

        }

        private static boolean fieldChecker(List<TextField> textFields, List<ChoiceBox> choiceBoxes) {
            boolean empty = true;
            for (TextField textfield : textFields) {
                if (textfield.getText().isEmpty()) {
                    empty = false;
                    textfield.setStyle("-fx-background-color: #ff9ca0");
                } else {
                    textfield.setStyle("-fx-background-color: white");
                }
            }
            for (ChoiceBox choiceBox : choiceBoxes) {
                if (choiceBox.getValue() == null) {
                    choiceBox.setStyle("-fx-background-color: #ff9ca0");
                } else {
                    choiceBox.setStyle("-fx-background-color: white");
                }
            }
            return empty;
        }

        private static boolean choiceBoxChecker(List<ChoiceBox> choiceBoxes, List<TextField> textFields) {
            boolean empty = true;
            for (ChoiceBox choiceBox : choiceBoxes) {
                if (choiceBox.getValue() == null) {
                    empty = false;
                    choiceBox.setStyle("-fx-background-color: #ff9ca0");
                } else {
                    choiceBox.setStyle("-fx-background-color: white");
                }
            }
            for (TextField textfield : textFields) {
                if (textfield.getText().isEmpty()) {
                    textfield.setStyle("-fx-background-color: #ff9ca0");
                } else {
                    textfield.setStyle("-fx-background-color: white");
                }
            }
            return empty;
        }

    }

    /**
     * Created by Jason on 5/31/2015.
     */
    public static class AddNewsItem {
        static String newsItem = new String();
        private static TextField url;
        private static TextField headline;

        public static String display(Connection con, String title) {
            Stage window = new Stage();


            //Block other windows' input events until this window is closed
            window.initModality(Modality.APPLICATION_MODAL);
            window.setTitle(title);
            window.setMinWidth(100);

            java.sql.Date sqlDate = new java.sql.Date(new java.util.Date().getTime());

            Button button = new Button("Enter");
            button.setOnAction(e -> {
                if (!url.getText().isEmpty() && !headline.getText().isEmpty()) {
                    try {

                        addNews(con, url.getText(), sqlDate, headline.getText());


                        window.close();
                    } catch (SQLException e1) {
                        if (e1.getErrorCode() == 1) {
                            AlertBox.display("Error", "URL already exists in database");
                        }
                    }
                }
                if (url.getText().isEmpty()) {
                    url.setStyle("-fx-background-color: #ff9ca0");
                    if (!headline.getText().isEmpty()) {
                        headline.setStyle("-fx-background-color: white");
                    }
                }
                if (headline.getText().isEmpty()) {
                    headline.setStyle("-fx-background-color: #ff9ca0");
                    if (!url.getText().isEmpty()) {
                        url.setStyle("-fx-background-color: white");
                    }
                }
            });

            //Attribute text fields
            Label urlLabel = new Label("URL:");
            url = new TextField();

            Label dateLabel = new Label("Date:");
            TextField date = new TextField();

            Label headlineLabel = new Label("Headline:");
            headline = new TextField();

            GridPane grid = new GridPane();
            grid.setPadding(new Insets(10, 10, 10, 10));
            grid.setVgap(5);
            grid.setHgap(5);
            GridPane.setConstraints(urlLabel, 0, 0);
            GridPane.setConstraints(url, 1, 0);
            GridPane.setConstraints(headlineLabel, 0, 1);
            GridPane.setConstraints(headline, 1, 1);
            GridPane.setConstraints(button, 1, 2);
            GridPane.setHalignment(button, HPos.RIGHT);
            grid.getChildren().addAll(urlLabel, url, headlineLabel, headline, button);

            Scene scene = new Scene(grid);
            window.setScene(scene);

            //Shows this stage and waits for it to be hidden (closed) before returning to the caller.
            window.showAndWait();
            return returnNewsItem();
        }

        private static String returnNewsItem() {
            newsItem = url.getText();
            return newsItem;
        }

        public static void addNews(Connection con, String URL, Date date, String headline) throws SQLException {

            System.out.println("testing");
            String addN = "INSERT INTO News VALUES (?, ?, ?)";
            PreparedStatement update = con.prepareStatement(addN);
            update.setString(2, URL);
            update.setString(1, headline);
            update.setDate(3, date);


            update.executeUpdate();

            AddNewsTags.display(con, headline, URL);


        }
    }

    /**
     * Created by Justin on 6/10/2015.
     */
    public static class AddMatch {
        public static void display(Connection con, String title) {
            Stage window = new Stage();
            window.initModality(Modality.APPLICATION_MODAL);
            window.setTitle(title);
            window.setMinWidth(100);
            Button button = new Button("Enter");
            button.setOnAction(e -> {
                window.close();
            });

            Label totalKillsLabel = new Label("Total Kills:");
            Label redNumberofBaronsLabel = new Label("Red Team # of Barons:");
            Label redNumberofDragonsLabel = new Label("Red Team # of Dragons:");
            Label blueNumberofBaronsLabel = new Label("Red Team # of Barons:");
            Label blueNumberofDragonsLabel = new Label("Red Team # of Dragons:");
            Label timeLabel = new Label("Duration (MM:SS):");
            Label matchIDLabel = new Label("Match ID:");
            Label totalGoldLabel = new Label("Total Gold:");
            Label redNameLabel = new Label(" Team:");
            Text red = new Text("Red");
            red.setFill(Color.RED);
            Label blueNameLabel = new Label(" Team:");
            Text blue = new Text("Blue");
            blue.setFill(Color.BLUE);
            Label datePlayedLabel = new Label("Date: (YYYYMMDD"); //format to be determined
            Label winnerLabel = new Label("Winner:");

            TextField totalKills = new TextField();
            TextField redNumberofBarons = new TextField();
            TextField blueNumberofBarons = new TextField();
            TextField blueNumberofDragons = new TextField();
            TextField redNumberofDragons = new TextField();
            TextField time = new TextField();
            TextField matchID = new TextField();
            TextField totalGold = new TextField();
            TextField redName = new TextField();
            TextField blueName = new TextField();
            TextField datePlayed = new TextField();
            TextField winner = new TextField();

            HBox redHBox = new HBox(0);
            HBox blueHBox = new HBox(0);
            redHBox.getChildren().addAll(red, redNameLabel);
            blueHBox.getChildren().addAll(blue, blueNameLabel);

            GridPane grid = new GridPane();
            grid.setPadding(new Insets(10));
            grid.setVgap(5);
            grid.setHgap(5);
            GridPane.setConstraints(totalKillsLabel, 0, 0);
            GridPane.setConstraints(totalKills, 1, 0);
            GridPane.setConstraints(redNumberofBaronsLabel, 0, 1);
            GridPane.setConstraints(redNumberofBarons, 1, 1);
            GridPane.setConstraints(redNumberofDragonsLabel, 0, 2);
            GridPane.setConstraints(redNumberofDragons, 1, 2);
            GridPane.setConstraints(blueNumberofBaronsLabel, 0, 3);
            GridPane.setConstraints(blueNumberofBarons, 1, 3);
            GridPane.setConstraints(blueNumberofDragonsLabel, 0, 4);
            GridPane.setConstraints(blueNumberofDragons, 1, 4);
            GridPane.setConstraints(winner, 1, 5);
            GridPane.setConstraints(winnerLabel, 0, 5);
            GridPane.setConstraints(timeLabel, 0, 6);
            GridPane.setConstraints(time, 1, 6);
            GridPane.setConstraints(matchIDLabel, 0, 7);
            GridPane.setConstraints(matchID, 1, 7);
            GridPane.setConstraints(totalGoldLabel, 0, 8);
            GridPane.setConstraints(totalGold, 1, 8);
            GridPane.setConstraints(redHBox, 0, 9);
            GridPane.setConstraints(redName, 1, 9);
            GridPane.setConstraints(blueHBox, 0, 10);
            GridPane.setConstraints(blueName, 1, 10);
            GridPane.setConstraints(datePlayedLabel, 0, 11);
            GridPane.setConstraints(datePlayed, 1, 11);
            GridPane.setConstraints(button, 1, 12);
            GridPane.setHalignment(button, HPos.RIGHT);
            grid.getChildren().addAll(totalKillsLabel, totalKills, redNumberofBarons, redNumberofBaronsLabel, redNumberofDragons, redNumberofDragonsLabel, timeLabel, time, matchIDLabel, matchID, totalGoldLabel, totalGold, redHBox, redName, blueHBox, blueName, datePlayedLabel, datePlayed, button);

            Scene scene = new Scene(grid);
            window.setScene(scene);
            window.showAndWait();

        }
    }

    /**
     * Created by jch on 09/06/15.
     */
    public static class AddChampion {

        public static void display(Connection con, String title) {
            Stage window = new Stage();
            window.initModality(Modality.APPLICATION_MODAL);
            window.setTitle(title);
            window.setMinHeight(100);
            window.setResizable(false);


            //attributes
            Label nameLabel = new Label("Name:");
            TextField name = new TextField();

            Label winRateLabel = new Label("Win rate:");
            TextField winRate = new TextField();

            Label typeLabel = new Label("Type:");
            ChoiceBox<String> type = new ChoiceBox<>();
            type.getItems().addAll("Pick one:", "Tank", "Mage", "Fighter", "Support", "Hybrid", "Marksman");
            type.setValue("Pick one:");


            Button button = new Button("Enter");


            window.close();
            button.setOnAction(e -> {
                if (!name.getText().isEmpty() && !winRate.getText().isEmpty() && !(type.getValue() == "Pick one:")) {
                    try {
                        addChamp(con, name.getText(), Float.parseFloat(winRate.getText()), type.getValue());
                        AlertBox.display("Success", "Champion is successfully added to the database.");
                        window.close();
                    } catch (SQLException e1) {
                        if (e1.getErrorCode() == 1)
                            AlertBox.display("Error", "A champion with that name already exists");
                    } catch (NumberFormatException nfe) {
                        AlertBox.display("Error", "Win rate should be a number (e.g. 40.20)");
                    }
                }

                if (name.getText().isEmpty()) {
                    name.setStyle("-fx-background-color: #ff9ca0");
                    if (type.getValue() != "Pick one:") {
                        type.setStyle("-fx-background-color: white");
                    }
                    if (!winRate.getText().isEmpty()) {
                        winRate.setStyle("-fx-background-color: white");
                    }
                }
                if (winRate.getText().isEmpty()) {
                    winRate.setStyle("-fx-background-color: #ff9ca0");
                    if (type.getValue() != "Pick one:") {
                        type.setStyle("-fx-background-color: white");
                    }
                    if (!name.getText().isEmpty()) {
                        name.setStyle("-fx-background-color: white");
                    }
                }
                if (type.getValue() == "Pick one:") {
                    type.setStyle("-fx-background-color: #ff9ca0");
                    if (!name.getText().isEmpty()) {
                        name.setStyle("-fx-background-color: white");
                    }
                    if (!winRate.getText().isEmpty()) {
                        winRate.setStyle("-fx-background-color: white");
                    }
                }
            });

            //layout
            GridPane grid = new GridPane();
            grid.setPadding(new Insets(10));
            grid.setVgap(5);
            grid.setHgap(5);
            GridPane.setConstraints(nameLabel, 0, 0);
            GridPane.setConstraints(name, 1, 0);

            GridPane.setConstraints(winRateLabel, 0, 2);
            GridPane.setConstraints(winRate, 1, 2);

            GridPane.setConstraints(typeLabel, 0, 4);
            GridPane.setConstraints(type, 1, 4);
            GridPane.setConstraints(button, 1, 5);
            GridPane.setHalignment(button, HPos.RIGHT);
            grid.getChildren().addAll(nameLabel, name, winRateLabel, winRate, typeLabel, type, button);


            Scene scene = new Scene(grid);
            window.setScene(scene);

            //Shows this stage and waits for it to be hidden (closed) before returning to the caller.
            window.showAndWait();
        }

        public static void addChamp(Connection con, String name, Float winRate, String type) throws SQLException {
            Champion Champ = new Champion(name, winRate, type);
            String addC = "INSERT INTO Champion VALUES ( ?, ?, ?)";
            PreparedStatement update = con.prepareStatement(addC);
            update.setString(1, Champ.getName());
            update.setFloat(2, Champ.getWinRate());
            update.setString(3, Champ.getType());
            update.executeUpdate();


        }
    }
}


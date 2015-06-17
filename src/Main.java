import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.sql.*;
import java.util.ArrayList;

public class Main extends Application {
    String user;
    Stage window, login;
    Scene scene1;
    Connection con;

    public static void main(String[] args) {
        launch(args);
    }


    @Override
    public void start(Stage primaryStage) throws Exception {
        login = primaryStage;
        //START: login screen
        login.setTitle("Log In");
        login.setMaxHeight(132);
        login.setResizable(false);
        TextField username = new TextField();

        username.setPromptText("CS ID (e.g. a2x9)");
        PasswordField password = new PasswordField();
        password.setPromptText("UBC ID");

        //Admin or User?
        ChoiceBox<String> adminUser = new ChoiceBox<>();
        adminUser.getItems().addAll("Log in as: (Select one)", "Admin", "User");
        adminUser.setMinWidth(150);
        adminUser.setValue("Log in as: (Select one)");
        username.setOnKeyPressed(event -> {
                    if (event.getCode().equals(KeyCode.ENTER)) {
                        if (!username.getText().isEmpty() && !password.getText().isEmpty() && adminUser.getValue() != "Log in as: (Select one)") {
                            loginPress(username.getText(), password.getText(), adminUser.getValue());
                        }
                        if (username.getText().isEmpty()) {
                            username.setStyle("-fx-background-color: #ff9ca0");
                            if (!password.getText().isEmpty()) {
                                password.setStyle("-fx-background-color: white");
                            }
                            if (adminUser.getValue() != "Log in as: (Select one)") {
                                adminUser.setStyle("-fx-background-color: white");
                            }
                        }
                        if (password.getText().isEmpty()) {
                            password.setStyle("-fx-background-color: #ff9ca0");
                            if (!username.getText().isEmpty()) {
                                username.setStyle("-fx-background-color: white");
                            }
                            if (adminUser.getValue() != "Log in as: (Select one)") {
                                adminUser.setStyle("-fx-background-color: white");
                            }
                        }
                        if (adminUser.getValue() == "Log in as: (Select one)") {
                            adminUser.setStyle("-fx-background-color: #ff9ca0");
                            if (!password.getText().isEmpty()) {
                                password.setStyle("-fx-background-color: white");
                            }
                            if (!username.getText().isEmpty()) {
                                username.setStyle("-fx-background-color: white");
                            }
                        }
                    }
                }
        );
        password.setOnKeyPressed(event -> {
                    if (event.getCode().equals(KeyCode.ENTER)) {
                        if (!username.getText().isEmpty() && !password.getText().isEmpty() && adminUser.getValue() != "Log in as: (Select one)") {
                            loginPress(username.getText(), password.getText(), adminUser.getValue());
                        }
                        if (username.getText().isEmpty()) {
                            username.setStyle("-fx-background-color: #ff9ca0");
                            if (!password.getText().isEmpty()) {
                                password.setStyle("-fx-background-color: white");
                            }
                            if (adminUser.getValue() != "Log in as: (Select one)") {
                                adminUser.setStyle("-fx-background-color: white");
                            }
                        }
                        if (password.getText().isEmpty()) {
                            password.setStyle("-fx-background-color: #ff9ca0");
                            if (!username.getText().isEmpty()) {
                                username.setStyle("-fx-background-color: white");
                            }
                            if (adminUser.getValue() != "Log in as: (Select one)") {
                                adminUser.setStyle("-fx-background-color: white");
                            }
                        }
                        if (adminUser.getValue() == "Log in as: (Select one)") {
                            adminUser.setStyle("-fx-background-color: #ff9ca0");
                            if (!password.getText().isEmpty()) {
                                password.setStyle("-fx-background-color: white");
                            }
                            if (!username.getText().isEmpty()) {
                                username.setStyle("-fx-background-color: white");
                            }
                        }
                    }
                }
        );
        //Label logIn = new Label("Log in:");
        Button logInButton = new Button("Login");
        logInButton.setMinHeight(90);

        //Login screen layout
        HBox loginHBox = new HBox(5);
        loginHBox.setPadding(new Insets(5));
        VBox fields = new VBox(5);
        fields.setPadding(new Insets(5));
        fields.getChildren().addAll(username, password, adminUser);
        loginHBox.getChildren().addAll(fields, logInButton);
        logInButton.setOnAction(event -> {
                    if (!username.getText().isEmpty() && !password.getText().isEmpty() && adminUser.getValue() != "Log in as: (Select one)") {
                        loginPress(username.getText(), password.getText(), adminUser.getValue());
                    }
                    if (username.getText().isEmpty()) {
                        username.setStyle("-fx-background-color: #ff9ca0");
                        if (!password.getText().isEmpty()) {
                            password.setStyle("-fx-background-color: white");
                        }
                        if (adminUser.getValue() != "Log in as: (Select one)") {
                            adminUser.setStyle("-fx-background-color: white");
                        }
                    }
                    if (password.getText().isEmpty()) {
                        password.setStyle("-fx-background-color: #ff9ca0");
                        if (!username.getText().isEmpty()) {
                            username.setStyle("-fx-background-color: white");
                        }
                        if (adminUser.getValue() != "Log in as: (Select one)") {
                            adminUser.setStyle("-fx-background-color: white");
                        }
                    }
                    if (adminUser.getValue() == "Log in as: (Select one)") {
                        adminUser.setStyle("-fx-background-color: #ff9ca0");
                        if (!password.getText().isEmpty()) {
                            password.setStyle("-fx-background-color: white");
                        }
                        if (!username.getText().isEmpty()) {
                            username.setStyle("-fx-background-color: white");
                        }
                    }
                }
        );
        Scene loginScene = new Scene(loginHBox);

        //Start window
        login.setOnCloseRequest(e -> {
            e.consume(); //consumed event, it won't close the program automatically
            try {
                closeProgram(login);
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        });
        login.setScene(loginScene);
        login.show();
    }

    private void adminMenu(String userID, String userState) throws SQLException {
        window = new Stage();
        window.setOnCloseRequest(e -> {
            e.consume(); //consumed event, it won't close the program automatically
            try {
                closeProgram(window);
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        });

        Stage s = new Stage();

        //Post-login: Main menu
        Text message = new Text("Logged in as:");
        Text loggedInAs = new Text(userID);
        user = userID;

        System.out.println(user);

        loggedInAs.setFont(Font.font(40));

        Button button0 = new Button("Log out");
        button0.setOnAction(e -> {
            try {
                start(login);
                try {
                    con.close();
                    window.close();
                } catch (NullPointerException c) {
                    window.close();
                }
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        });

        HBox userAndButton = new HBox(15);
        userAndButton.setAlignment(Pos.CENTER);
        userAndButton.getChildren().addAll(loggedInAs, button0);

        Button button1 = new Button("Check your feed");
        button1.setOnAction(e -> NewsFeed.display(con, userID) ) ;


        Button button2 = new Button("Add Something");
        button2.setOnAction(e -> AddBox.display(con, "Add an Entity", "What do you want to add?"));
        Button button3 = new Button("Return to desktop");
        button3.setOnAction(e -> {
            try {
                closeProgram(window);
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        });

        Button button4 = new Button("Search");
        button4.setOnAction(e -> SearchFor.display(con, user));

        Button button5 = new Button("Delete Something");
        button5.setOnAction(e -> DeleteBox.display(con, "Delete Something", "Choose Something To Delete"));


        Button button6 = new Button("Update Something");
        button6.setOnAction(e -> Update.display(con, "Update Something", "Choose Something To Update"));

        VBox layout = new VBox(20);
        layout.getChildren().addAll(message, userAndButton, button1, button2, button4, button5, button6, button3);
        layout.setAlignment(Pos.CENTER);
        scene1 = new Scene(layout, 300, 500);

        window.setTitle("LOLNews (" + userState + ")");
        window.setScene(scene1);
        window.show();

        if(!getAllUsers(con).contains(userID)){
            String addR = "INSERT INTO FollowList VALUES (?)";
            PreparedStatement update = con.prepareStatement(addR);
            update.setString(1, userID);
            update.executeUpdate();
        }

    }

    private void closeProgram(Stage stage) throws SQLException {
        Boolean answer = ConfirmBox.display("Closing", "Are you sure you wish to exit?");
        if (answer) {
            try {
                con.close();
                stage.close();
            } catch (NullPointerException e) {
                stage.close();
            }
        }
    }

    private void loginPress(String user, String password, String state) {
        LoginConnection loginConnection = new LoginConnection();
        try {
            con = loginConnection.logIn(user, password);
            login.close();
            if (state == "Admin") adminMenu(user, state);
            else if (state == "User") userMenu(con, user, state);
        } catch (SQLException e) {
            if (e.getErrorCode() == 17002) {
                AlertBox.display("Error", "Could not establish connection to the database");
            } else if (e.getErrorCode() == 1017) {
                AlertBox.display("Error", "Wrong user/password!");
            }
        }
    }

    private void userMenu(Connection con, String userID, String userState) throws SQLException {

        window = new Stage();
        window.setOnCloseRequest(e -> {
            e.consume(); //consumed event, it won't close the program automatically
            try {
                closeProgram(window);
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        });
        //Post-login: Main menu
        Text message = new Text("Logged in as:");
        Text loggedInAs = new Text(userID);
        loggedInAs.setFont(Font.font(40));

        Button button0 = new Button("Log out");
        button0.setOnAction(e -> {
            try {
                start(login);
                try {
                    con.close();
                    window.close();
                } catch (NullPointerException c) {
                    window.close();
                }
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        });

        HBox userAndButton = new HBox(15);
        userAndButton.setAlignment(Pos.CENTER);
        userAndButton.getChildren().addAll(loggedInAs, button0);
        Button button1 = new Button("Check your feed");
        button1.setOnAction(e -> NewsFeed.display(con, userID));
        Button button3 = new Button("Return to desktop");
        button3.setOnAction(e -> {
            try {
                closeProgram(window);
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        });
        Button button4 = new Button("Search");
        button4.setOnAction(e -> SearchFor.display(con, userID));

        Button button5 = new Button("View Matches");
        button5.setOnAction(e -> ViewMatches.display(con, "View Matches", "View Matches By Team"));

        Button button6 = new Button("Guessing Game");
        button6.setOnAction(e -> {
            try {
                MinMax.display(con, userID);
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        });


        Button button7 = new Button("View Our Top Users!");
        button7.setOnAction(e ->ViewUsers.display(con, "View Our Top Users", "View Our Top Users"));

        VBox layout = new VBox(20);
        layout.getChildren().addAll(message, userAndButton, button1, button4, button5, button6, button7, button3);
        layout.setAlignment(Pos.CENTER);
        scene1 = new Scene(layout, 300, 500);

        window.setTitle("LOLNews (" + userState + ")");
        window.setScene(scene1);
        window.show();

        if(!getAllUsers(con).contains(userID)){
            String addR = "INSERT INTO FollowList VALUES (?)";
            PreparedStatement update = con.prepareStatement(addR);
            update.setString(1, userID);
            update.executeUpdate();
        }
    }

    public static ArrayList<String> getAllUsers(Connection con) throws SQLException {
        ArrayList<String> temp = new ArrayList<String>();
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery("SELECT user_ID FROM FollowList");

        while (rs.next()) {

            String arr ;
            String n = rs.getString("user_ID");
            arr = n.replace("\n", ",");
            temp.add(arr);

        }
        return temp;
    }
}
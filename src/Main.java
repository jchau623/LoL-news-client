import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;

import javafx.scene.input.KeyCode;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.SQLException;

public class Main extends Application{

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
                        if (!username.getText().isEmpty() && !password.getText().isEmpty() && adminUser.getValue()!= "Log in as: (Select one)") {
                            loginPress(username.getText(), password.getText());
                        }
                        if (username.getText().isEmpty()) {
                            username.setStyle("-fx-background-color: #ff9ca0");
                            if (!password.getText().isEmpty()) {
                                password.setStyle("-fx-background-color: white");
                            }
                            if (adminUser.getValue()!="Log in as: (Select one)") {
                                adminUser.setStyle("-fx-background-color: white");
                            }
                        }
                        if (password.getText().isEmpty()) {
                            password.setStyle("-fx-background-color: #ff9ca0");
                            if (!username.getText().isEmpty()) {
                                username.setStyle("-fx-background-color: white");
                            }
                            if (adminUser.getValue()!="Log in as: (Select one)") {
                                adminUser.setStyle("-fx-background-color: white");
                            }
                        }
                        if (adminUser.getValue()=="Log in as: (Select one)") {
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
                        if (!username.getText().isEmpty() && !password.getText().isEmpty() && adminUser.getValue()!= "Log in as: (Select one)") {
                            loginPress(username.getText(), password.getText());
                        }
                        if (username.getText().isEmpty()) {
                            username.setStyle("-fx-background-color: #ff9ca0");
                            if (!password.getText().isEmpty()) {
                                password.setStyle("-fx-background-color: white");
                            }
                            if (adminUser.getValue()!="Log in as: (Select one)") {
                                adminUser.setStyle("-fx-background-color: white");
                            }
                        }
                        if (password.getText().isEmpty()) {
                            password.setStyle("-fx-background-color: #ff9ca0");
                            if (!username.getText().isEmpty()) {
                                username.setStyle("-fx-background-color: white");
                            }
                            if (adminUser.getValue()!="Log in as: (Select one)") {
                                adminUser.setStyle("-fx-background-color: white");
                            }
                        }
                        if (adminUser.getValue()=="Log in as: (Select one)") {
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
        VBox fields = new VBox (5);
        fields.setPadding(new Insets(5));
        fields.getChildren().addAll(username, password, adminUser);
        loginHBox.getChildren().addAll(fields, logInButton);
        logInButton.setOnAction(event -> {
                    if (!username.getText().isEmpty() && !password.getText().isEmpty() && adminUser.getValue()!= "Log in as: (Select one)") {
                        loginPress(username.getText(), password.getText());
                    }
                    if (username.getText().isEmpty()) {
                        username.setStyle("-fx-background-color: #ff9ca0");
                        if (!password.getText().isEmpty()) {
                            password.setStyle("-fx-background-color: white");
                        }
                        if (adminUser.getValue()!="Log in as: (Select one)") {
                            adminUser.setStyle("-fx-background-color: white");
                        }
                    }
                    if (password.getText().isEmpty()) {
                        password.setStyle("-fx-background-color: #ff9ca0");
                        if (!username.getText().isEmpty()) {
                            username.setStyle("-fx-background-color: white");
                        }
                        if (adminUser.getValue()!="Log in as: (Select one)") {
                            adminUser.setStyle("-fx-background-color: white");
                        }
                    }
                    if (adminUser.getValue()=="Log in as: (Select one)") {
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

    private void mainMenu() {
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
        Button button1 = new Button("Check your feed");
        button1.setOnAction(e -> AlertBox.display("News feed", "Click button to close"));
        Button button2 = new Button("Add Something");
        button2.setOnAction(e -> AddBox.display("Add an Entity", "What do you want to add?"));
        Button button3 = new Button("Return to desktop");
        button3.setOnAction(e -> {
            try {
                closeProgram(window);
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        });
      Button button4 = new Button("Search");
        button4.setOnAction(e -> SearchFor.display(con));
      Button button5 = new Button("Delete Something");
        button5.setOnAction(e-> DeleteBox.display(con,"Delete Something", "Choose Something To Delete"));

        VBox layout = new VBox(20);
        layout.getChildren().addAll(button1, button2, button3, button4 ,button5);
        layout.setAlignment(Pos.CENTER);
        scene1 = new Scene(layout, 300, 500);

        window.setTitle("LOLNews");
        window.setScene(scene1);
        window.show();
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

    private void loginPress (String user, String password) {
        LoginConnection loginConnection = new LoginConnection();
        try {
            con = loginConnection.logIn(user,password);
            login.close();
            mainMenu();
        } catch (SQLException e) {
            AlertBox.display("Error", "Wrong user/password!");
            e.printStackTrace();
        }
    }
}
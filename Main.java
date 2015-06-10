import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.sql.SQLException;

public class Main extends Application{

    Stage window, login;
    Scene scene1;

    public static void main(String[] args) {
        launch(args);
    }


    @Override
    public void start(Stage primaryStage) throws Exception {
        login = primaryStage;
        //START: login screen
        login.setTitle("Log In");
        login.setMaxHeight(100);
        login.setResizable(false);
        TextField username = new TextField();
        username.setPromptText("CS ID (e.g. a2x9)");
        PasswordField password = new PasswordField();
        password.setPromptText("UBC ID");
        username.setOnKeyPressed(event -> {
                    if (event.getCode().equals(KeyCode.ENTER)) {
                        if (!username.getText().isEmpty() && !password.getText().isEmpty()) {
                            loginPress(username.getText(), password.getText());
                        }
                        if (username.getText().isEmpty()) {
                            username.setStyle("-fx-background-color: #ff9ca0");
                        }
                        if (password.getText().isEmpty()) {
                            password.setStyle("-fx-background-color: #ff9ca0");
                        }
                    }
                }
        );
        password.setOnKeyPressed(event -> {
                    if (event.getCode().equals(KeyCode.ENTER)) {
                        if (!username.getText().isEmpty() && !password.getText().isEmpty()) {
                            loginPress(username.getText(), password.getText());
                        }
                        if (username.getText().isEmpty()) {
                            username.setStyle("-fx-background-color: #ff9ca0");
                        }
                        if (password.getText().isEmpty()) {
                            password.setStyle("-fx-background-color: #ff9ca0");
                        }
                    }
                }
        );
        //Label logIn = new Label("Log in:");
        Button logInButton = new Button("Login");
        logInButton.setMinHeight(60);

        //Login screen layout
        HBox loginHBox = new HBox(5);
        loginHBox.setPadding(new Insets(5));
        VBox fields = new VBox (5);
        fields.setPadding(new Insets(5));
        fields.getChildren().addAll(username, password);
        loginHBox.getChildren().addAll(fields, logInButton);
        logInButton.setOnAction(event -> {
                    if (!username.getText().isEmpty() && !password.getText().isEmpty()) {
                        loginPress(username.getText(), password.getText());
                    }
                    if (username.getText().isEmpty()) {
                        username.setStyle("-fx-background-color: #ff9ca0");
                        if (!password.getText().isEmpty()) {
                            password.setStyle("-fx-background-color: white");
                        }
                    }
                    if (password.getText().isEmpty()) {
                        password.setStyle("-fx-background-color: #ff9ca0");
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
            closeProgram(login);
        });
        login.setScene(loginScene);
        login.show();
    }

    private void mainMenu() {
        window = new Stage();
        window.setOnCloseRequest(e -> {
            e.consume(); //consumed event, it won't close the program automatically
            closeProgram(window);
        });
        //Post-login: Main menu
        Button button1 = new Button("Check your feed");
        button1.setOnAction(e -> AlertBox.display("News feed", "Click button to close"));
        Button button2 = new Button("Add player");
        button2.setOnAction(e -> {
            String addedPlayer = AddPlayer.display("Add Player", "Enter player name: ");
            System.out.println(addedPlayer);
        });
        Button button3 = new Button("Return to desktop");
        button3.setOnAction(e -> closeProgram(window));
        Button button4 = new Button("Search");
        button4.setOnAction(e -> SearchFor.display());

        VBox layout = new VBox(20);
        layout.getChildren().addAll(button1, button2, button4, button3);
        layout.setAlignment(Pos.CENTER);
        scene1 = new Scene(layout, 200, 200);

        window.setTitle("LOLNews");
        window.setScene(scene1);
        window.show();
    }

    private void closeProgram(Stage stage) {
        Boolean answer = ConfirmBox.display("Closing", "Are you sure you wish to exit?");
        if (answer) stage.close();
    }

    private void loginPress (String user, String password) {
        LoginConnection loginConnection = new LoginConnection();
        try {
            loginConnection.logIn(user,password);
            login.close();
            mainMenu();
        } catch (SQLException e) {
            AlertBox.display("Error", "Wrong user/password!");
        }
    }
}
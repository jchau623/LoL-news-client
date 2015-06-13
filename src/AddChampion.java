import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by jch on 09/06/15.
 */
public class AddChampion {

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
                    if (e1.getErrorCode() == 1) AlertBox.display("Error", "A champion with that name already exists");
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
                if (!type.getValue().isEmpty()) {
                    type.setStyle("-fx-background-color: white");
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
        grid.getChildren().addAll(nameLabel,name,winRateLabel,winRate, typeLabel, type, button);


        Scene scene = new Scene(grid);
        window.setScene(scene);

        //Shows this stage and waits for it to be hidden (closed) before returning to the caller.
        window.showAndWait();
    }

    public static void addChamp (Connection con , String name ,   Float winRate, String type ) throws SQLException {
       Champion Champ = new Champion(name,winRate, type);
        String addC = "INSERT INTO Champion VALUES ( ?, ?, ?)";
        PreparedStatement update = con.prepareStatement(addC);
        update.setString(1, Champ.getName());
        update.setFloat(2, Champ.getWinRate());
        update.setString(3, Champ.getType());
        update.executeUpdate();




    }


}

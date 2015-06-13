import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by jch on 09/06/15.
 */
public class AddChampion {


    public static void display(String title, Connection con) {
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle(title);
        window.setMinHeight(100);
        window.setResizable(false);


        //attributes
        Label nameLabel = new Label("Name:");
        TextField name = new TextField();
        Label costLabel = new Label("Cost:");
        TextField cost = new TextField();
        Label winRateLabel = new Label("Win rate:");
        TextField winRate = new TextField();
        Label resourceTypeLabel = new Label("Resource type:");
        TextField resourceType = new TextField();
        Label typeLabel = new Label("Type:");
        TextField type = new TextField();


        Button button = new Button("Enter");
        button.setOnAction(e -> {
           // returnPlayer();
            try {
                addChamp(con, name.getText(), cost.getText(), winRate.getText(), resourceType.getText(), type.getText());
                window.close();
            }
            catch (SQLException f ) {
                f.printStackTrace();

            }
        });


        //layout
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10, 5, 0, 10));
        grid.setVgap(5);
        grid.setHgap(5);
        GridPane.setConstraints(nameLabel, 0, 0);
        GridPane.setConstraints(name, 1, 0);
        GridPane.setConstraints(costLabel, 0, 1);
        GridPane.setConstraints(cost, 1, 1);
        GridPane.setConstraints(winRateLabel, 0, 2);
        GridPane.setConstraints(winRate, 1, 2);
        GridPane.setConstraints(resourceTypeLabel, 0, 3);
        GridPane.setConstraints(resourceType, 1, 3);
        GridPane.setConstraints(typeLabel, 0, 4);
        GridPane.setConstraints(type, 1, 4);
        GridPane.setConstraints(button, 1, 5);
        GridPane.setHalignment(button, HPos.RIGHT);
        grid.getChildren().addAll(nameLabel,name,costLabel,cost,winRateLabel,winRate,resourceTypeLabel, resourceType, typeLabel, type, button);


        Scene scene = new Scene(grid);
        window.setScene(scene);

        //Shows this stage and waits for it to be hidden (closed) before returning to the caller.
        window.showAndWait();
    }

    public static void addChamp (Connection con , String name , String cost , String winRate, String resourceType , String type ) throws SQLException {
        new Champion(name, cost, winRate, resourceType, type);
        Statement stmt = con.createStatement() ;
        int rowCount = stmt.executeUpdate
                ("INSERT INTO Champion " +
                "VALUES ("  + name.toLowerCase().trim() +","
                + cost.toLowerCase().trim() + ","
                        + winRate.toLowerCase().trim() +
                        "," + resourceType.toLowerCase().trim()
                        + "," + type.toLowerCase().trim() +   ")") ;
        System.out.println("Added");
    }


}

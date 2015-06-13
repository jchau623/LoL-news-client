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
        TextField type = new TextField();


        Button button = new Button("Enter");


        window.close();
        button.setOnAction(e -> {
            try{
            addChamp(con, name.getText(), Float.parseFloat(winRate.getText()) , type.getText());}
            catch (SQLException e1){
                e1.printStackTrace();
            }
            window.close();
        });

        //layout
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10, 5, 0, 10));
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
System.out.println("testing");
        String addC = "INSERT INTO Champion VALUES ( ?, ?, ?)";
        PreparedStatement update = con.prepareStatement(addC);
        update.setString(1, Champ.getName());
        update.setFloat(2, Champ.getWinRate());
        update.setString(3, Champ.getType());
        update.executeUpdate();




    }


}

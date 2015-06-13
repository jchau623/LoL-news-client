


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

/**
 * Created by Jason on 5/31/2015.
 */
public class AddRegion {
    static String region ;

    public static void  display(Connection con , String title) {
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
            try{
                addRegion(con, name.getText(), acronym.getText());}
            catch (SQLException e1){
                e1.printStackTrace();
            }
            window.close();
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
        grid.getChildren().addAll(acronymLabel,acronym,nameLabel,name,button);

        Scene scene = new Scene(grid);
        window.setScene(scene);

        //Shows this stage and waits for it to be hidden (closed) before returning to the caller.
        window.showAndWait();}





     public static void addRegion (Connection con, String name, String acronym) throws SQLException {
System.out.println("testin");
        String addR = "INSERT INTO Region VALUES (?,?)";
        PreparedStatement update = con.prepareStatement(addR);
        update.setString(1, name);
        update.setString(2, acronym);
         update.executeUpdate();
    }



}


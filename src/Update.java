import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by Vivian on 2015-06-15.
 */
public class Update {
    private static int rowsUpdated;
    public static void display(Connection con, String title, String message) {
        Stage window = new Stage();

        //Block other windows' input events until this window is closed
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle(title);
        window.setMinWidth(250);


        Label tableLabel = new Label("Update table:");
        TextField tableName = new TextField();
        Label setLabel = new Label("Change:");
        TextField set = new TextField();
        Label toLabel = new Label("To:");
        TextField to = new TextField();
        Label whereLabel = new Label("Where:");
        TextField where = new TextField();

        Label label = new Label(message);


        Button button = new Button("Enter");
        button.setOnAction(e -> {
            try {
                updateSomething(con, tableName.getText(), set.getText(), to.getText(), where.getText());
                if (rowsUpdated == 0) {
                    AlertBox.display("Error", "No rows were updated");
                    window.close();
                }
                else {
                    AlertBox.display("Success", "Row was successfully updated");
                    window.close();
                }
            }catch (SQLException e1) {
                if (e1.getErrorCode() == 920 || e1.getErrorCode() == 900) {
                    AlertBox.display("Error", "Invalid Query");
                }
                if (e1.getErrorCode() == 901){
                    AlertBox.display("Error", "Ensure that you have inputted valid table and attribute names");
                }
                if (e1.getErrorCode() == 902){
                    AlertBox.display("Error", "Table name does not exist");
                }
                if (e1.getErrorCode() == 2290){
                    AlertBox.display("Error", "Check constraint violated");
                }
                //System.out.println(e1);
            }
            window.close();
        });

        GridPane grid = new GridPane();
        grid.setPadding(new Insets(9));
        grid.setVgap(5);
        grid.setHgap(5);
        GridPane.setConstraints(tableLabel, 0, 0);
        GridPane.setConstraints(tableName, 1, 0);
        GridPane.setConstraints(setLabel, 0, 1);
        GridPane.setConstraints(set, 1, 1);
        GridPane.setConstraints(toLabel, 0, 2);
        GridPane.setConstraints(to, 1, 2);
        GridPane.setConstraints(whereLabel, 0, 3);
        GridPane.setConstraints(where, 1, 3);
        GridPane.setConstraints(button, 1, 8);
        GridPane.setHalignment(button, HPos.RIGHT);
        grid.getChildren().addAll(tableLabel,tableName, setLabel, set, toLabel, to, whereLabel, where, button);

        Scene scene = new Scene(grid);
        window.setScene(scene);

        //Shows this stage and waits for it to be hidden (closed) before returning to the caller.
        window.showAndWait();
    }

    public static void updateSomething(Connection con, String name, String set, String to, String where) throws SQLException {
        Statement stmt = con.createStatement();
        rowsUpdated = stmt.executeUpdate("UPDATE " + name + " SET  " + set + " = " + to + " WHERE " + where);
        stmt.execute("UPDATE " + name + " SET  " + set + " = " + to + " WHERE " + where);
    }
}

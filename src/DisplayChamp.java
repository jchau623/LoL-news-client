import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DisplayChamp {
    public static void display(Connection con, String champ) {
        Stage window = new Stage();
        window.setTitle(champ);
        window.setResizable(false);
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10));
        grid.setVgap(5);
        grid.setHgap(5);
        String winRateS = null;
        String typeS = null;

        window.setResizable(false);
        try {
            Statement query = con.createStatement();
            ResultSet rs = query.executeQuery("SELECT winRate, type FROM Champion WHERE name = '" + champ + "'");
            if (rs.next()) {
                winRateS = rs.getString("winRate");
                typeS = rs.getString("type");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }


        Label name = new Label("Name: " + champ);
        Label winRate = new Label("Win rate (%): " + winRateS);
        Label type = new Label("Type: " + typeS);

        GridPane.setConstraints(name, 0, 0);
        GridPane.setConstraints(winRate, 0, 1);
        GridPane.setConstraints(type, 0, 2);
        grid.getChildren().addAll(name,winRate,type);
        Scene scene = new Scene(grid);
        window.setScene(scene);
        window.showAndWait();

    }
}

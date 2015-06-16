import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by wesley on 2015-06-15.
 */
public class RegionListResult {

    public static void display (String user, Connection con, ArrayList<Region> listOfRegions) {

        Stage window = new Stage();
        window.setTitle("Region Search Results");
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10));
        grid.setVgap(5);
        grid.setHgap(5);


        for (int i =0 ; i < listOfRegions.size() ; i++) {
            Region r = listOfRegions.get(i);
            Button regionButton = new Button(r.getRegionName());
            GridPane.setConstraints(regionButton, 0 , i);
            grid.getChildren().add(regionButton);

            regionButton.setOnAction(e->{
                try {
                    RegionResults.display(user, r.getRegionName(),con);
                } catch (SQLException e1) {
                    System.out.println(e1);
                }


            });

        }

        window.setResizable(false);
        Scene scene = new Scene(grid) ;
        window.setScene(scene);
        window.showAndWait();


    }

}




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

/**
 * Created by Jason on 5/31/2015.
 */
public class AddRegion {
    static String region ;
    private static javafx.scene.control.TextField acronym;
    public static String display(String title) {
        Stage window = new Stage();


        //Block other windows' input events until this window is closed
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle(title);
        window.setMinWidth(100);
        window.setResizable(false);

        Button button = new Button("Enter");
        button.setOnAction(e -> {
            returnRegion();
            window.close();
        });

        //Attribute text fields
        Label acronymLabel = new Label("Acronym:");
        acronym = new javafx.scene.control.TextField();
        Label nameLabel = new Label("Name:");
        TextField name = new TextField();

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
        window.showAndWait();
        return returnRegion();
    }

    private static String returnRegion() {
        region = acronym.getText();
        return region;
    }

}


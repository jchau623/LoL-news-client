import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * Created by jch on 09/06/15.
 */
public class AddChampion {

    public static void display(String title, String message) {
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle(title);
        window.setMinHeight(350);
        window.setResizable(false);

        Label label = new Label(message);
        Button button = new Button("Enter");
        button.setOnAction(e -> {
           // returnPlayer();
            window.close();
        });

        Label nameLabel = new Label("Name:");
        TextField name = new TextField();
        HBox nameHBox = new HBox(5);
        nameHBox.getChildren().addAll(nameLabel,name);

        Label costLabel = new Label("Cost:");
        TextField cost = new TextField();
        HBox costHBox = new HBox(5);
        costHBox.getChildren().addAll(costLabel, cost);

        Label winRateLabel = new Label("Win rate:");
        TextField winRate = new TextField();
        HBox winRateHBox = new HBox(5);
        winRateHBox.getChildren().addAll(winRateLabel, winRate);

        Label resourceTypeLabel = new Label("Resource type:");
        TextField resourceType = new TextField();
        HBox resourceTypeHBox = new HBox(5);
        resourceTypeHBox.getChildren().addAll(resourceTypeLabel, resourceType);

        Label typeLabel = new Label("Type:");
        TextField type = new TextField();
        HBox typeHBox = new HBox(5);
        typeHBox.getChildren().addAll(typeLabel, type);

        VBox layout = new VBox(10);
        layout.getChildren().addAll(label, nameHBox, costHBox, winRateHBox, resourceTypeHBox, typeHBox,button);
        layout.setAlignment(Pos.CENTER);
        layout.setPadding(new Insets(5));

        Scene scene = new Scene(layout);
        window.setScene(scene);

        //Shows this stage and waits for it to be hidden (closed) before returning to the caller.
        window.showAndWait();
    }

}

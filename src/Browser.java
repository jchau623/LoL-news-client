import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Worker;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

/**
 * Created by jch on 15/06/15.
 */
public class Browser {
    public static void display(String headline, String URL) {
        Stage window = new Stage();
        window.setTitle(headline);

        WebView browser = new WebView();
        WebEngine webEngine = browser.getEngine();

        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setContent(browser);

        webEngine.load(URL);
        Scene scene = new Scene(scrollPane);
        window.setScene(scene);
        window.show();
    }
}

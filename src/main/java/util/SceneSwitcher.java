package util;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class SceneSwitcher {
    public static void switchScene(Stage stage, String fxmlPath) throws IOException {
        Parent load = FXMLLoader.load(SceneSwitcher.class.getResource(fxmlPath));
        Scene scene = new Scene(load);
        stage.setScene(scene);
        stage.show();
    }
}

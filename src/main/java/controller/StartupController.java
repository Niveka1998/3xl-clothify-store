package controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public class StartupController {

    @FXML
    private JFXButton btnAdminInterface;

    @FXML
    private JFXButton btnEmployeeInterface;

    @FXML
    void btnOpenAdminInterfaceOnClick(ActionEvent event) {
        openWindow("view/admin-interface.fxml", "Admin Interface");
    }

    @FXML
    void btnOpenEmployeeInterfaceOnClick(ActionEvent event) {
        openWindow("view/employee-interface.fxml", "Employee Interface");
    }

    private void openWindow(String fxmlPath, String title) {
        try {
            // Get the FXML file as a resource
            URL resource = getClass().getClassLoader().getResource(fxmlPath);
            if (resource == null) {
                System.err.println("❌ FXML file not found at: " + fxmlPath);
                return;
            }

            // Load the scene
            FXMLLoader fxmlLoader = new FXMLLoader(resource);
            Parent root = fxmlLoader.load();

            // Show new window
            Stage stage = new Stage();
            stage.setTitle(title);
            stage.setScene(new Scene(root));
            stage.show();

            // Close current window
            Stage currentStage = (Stage) btnAdminInterface.getScene().getWindow();
            currentStage.close();

        } catch (IOException e) {
            System.err.println("❌ Failed to load FXML: " + fxmlPath);
            e.printStackTrace();
        }
    }
}

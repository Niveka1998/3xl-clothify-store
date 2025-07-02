package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import dto.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Hyperlink;
import javafx.stage.Stage;
import service.custom.UserService;
import service.custom.impl.UserServiceImpl;
import util.SceneSwitcher;

import java.io.IOException;
import java.sql.SQLException;

public class LoginController {

    @FXML
    private JFXButton btnAdminLogin;

    @FXML
    private JFXButton btnCreateAccount;

    @FXML
    private Hyperlink linkForgotPwd;

    @FXML
    private JFXTextField txtAdminEmail;

    @FXML
    private JFXPasswordField txtAdminPassword;

    @FXML
    private JFXButton btnCreateEmployeeAccount;

    @FXML
    private JFXButton btnEmployeeLogin;

    @FXML
    private Hyperlink linkForgotPwdEmpl;

    @FXML
    private JFXTextField txtEmployeeEmail;

    @FXML
    private JFXPasswordField txtEmployeePassword;

    private final UserService userService = new UserServiceImpl();
    @FXML
    void btnAdminCreateAccountOnClick(ActionEvent event) {

    }

    @FXML
    void btnAdminLoginOnClick(ActionEvent event) {
        String email = txtAdminEmail.getText();
        String password = txtAdminPassword.getText();

        if (email.isEmpty() || password.isEmpty()) {
            new Alert(Alert.AlertType.ERROR, "Please fill all admin fields").show();
            return;
        }

        try {
            User user = userService.login(email, password);
            if (user != null && "ADMIN".equalsIgnoreCase(user.getRole())) {
                // Redirect to Admin Dashboard
                new Alert(Alert.AlertType.INFORMATION, "Welcome Admin! Redirecting...").show();
                System.out.println("Redirecting to Admin Dashboard...");
                Stage stage = (Stage) btnAdminLogin.getScene().getWindow();
                SceneSwitcher.switchScene(stage,"/view/admin-interface.fxml");
            } else {
                new Alert(Alert.AlertType.ERROR, "Invalid admin credentials.").show();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Error during login: " + e.getMessage()).show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void linkForgotPwdOnAction(ActionEvent event) {

    }


    @FXML
    void btnEmployeeCreateAccountOnClick(ActionEvent event) {

    }

    @FXML
    void btnEmployeeLoginOnClick(ActionEvent event) {
        String email = txtEmployeeEmail.getText();
        String password = txtEmployeePassword.getText();

        if (email.isEmpty() || password.isEmpty()) {
            new Alert(Alert.AlertType.ERROR, "Please fill all employee fields").show();
            return;
        }

        try {
            User user = userService.login(email, password);
            if (user != null && "EMPLOYEE".equalsIgnoreCase(user.getRole())) {
                // Redirect to Employee Dashboard
                new Alert(Alert.AlertType.INFORMATION, "Welcome Employee! Redirecting...").show();
                System.out.println("Redirecting to Employee Dashboard...");
                Stage stage = (Stage) btnEmployeeLogin.getScene().getWindow();
                SceneSwitcher.switchScene(stage, "/view/employee-interface.fxml");
            } else {
                new Alert(Alert.AlertType.ERROR, "Invalid employee credentials.").show();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Error during login: " + e.getMessage()).show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void linkForgotPwdOnActionEmpl(ActionEvent event) {

    }

}

package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import service.custom.UserService;
import service.custom.impl.UserServiceImpl;

import java.sql.SQLException;

public class UpdatePasswordController {
    @FXML
    private TextField txtEmail;

    @FXML
    private PasswordField txtNewPassword;

    private final UserService userService = new UserServiceImpl();

    @FXML
    public void onUpdatePassword(ActionEvent actionEvent) {
        String email = txtEmail.getText();
        String newPassword = txtNewPassword.getText();

        try {
            boolean success = userService.updatePassword(email,newPassword);
            if(success){
                new Alert(Alert.AlertType.INFORMATION,"Password updated!").show();
                Stage stage = (Stage) txtEmail.getScene().getWindow();
                stage.close();
            }else{
//                new Alert(Alert.AlertType.ERROR,"Password update failed!").show();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Something went wrong!").show();
        }
    }
}

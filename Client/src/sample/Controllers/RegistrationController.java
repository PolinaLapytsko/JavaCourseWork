package sample.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class RegistrationController {
    @FXML
    private javafx.scene.control.TextField registrationName;
    @FXML
    private javafx.scene.control.TextField registrationSurname;
    @FXML
    private javafx.scene.control.TextField registrationPosition;
    @FXML
    private javafx.scene.control.TextField registrationLogin;
    @FXML
    private javafx.scene.control.TextField registrationPassword;

    public void getRegistrationData(ActionEvent event) {
        String regName = registrationName.getText();
        String regSurname = registrationSurname.getText();
        String regPosition = registrationPosition.getText();
        String regLogin = registrationLogin.getText();
        String regPassword = registrationPassword.getText();
        System.out.println(regName);
        System.out.println(regSurname);
        System.out.println(regPosition);
        System.out.println(regLogin);
        System.out.println(regPassword);
    }
}
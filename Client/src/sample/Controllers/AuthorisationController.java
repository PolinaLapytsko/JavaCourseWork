package sample.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class AuthorisationController {
    @FXML
    private javafx.scene.control.TextField authorisationLogin;
    @FXML
    private javafx.scene.control.TextField authorisationPassword;

    public void getAuthorisationData(ActionEvent event) {
        String login = authorisationLogin.getText();
        String password = authorisationPassword.getText();
        System.out.println(login + ' ' + password);
    }
}
package sample.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import sample.clientConnection;

import java.io.IOException;

public class Authorisation {
    @FXML
    private javafx.scene.control.TextField authorisationLogin;
    @FXML
    private javafx.scene.control.TextField authorisationPassword;

    public void getAuthorisationData(ActionEvent event) throws IOException {
        clientConnection client = new clientConnection();

        String login = authorisationLogin.getText();
        String password = authorisationPassword.getText();

        client.sendAuthorisationData(login, password, event);
    }
}

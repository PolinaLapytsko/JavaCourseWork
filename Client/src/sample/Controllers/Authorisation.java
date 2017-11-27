package sample.Controllers;

import javafx.fxml.FXML;
import sample.clientConnection;

import java.io.IOException;

public class Authorisation {
    @FXML
    private javafx.scene.control.TextField AuthorisationLogin;
    @FXML
    private javafx.scene.control.TextField AuthorisationPassword;

    public void getAuthorisationData() throws IOException {
        clientConnection client = new clientConnection();

        String login = AuthorisationLogin.getText();
        String password = AuthorisationPassword.getText();

        client.sendAuthorisationData(login, password);
    }
}

package sample.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import sample.clientConnection;

import java.io.IOException;

public class Registration {
    public void ToAdminSalaryOrRegistration(ActionEvent actionEvent) {
        Parent root;
        try {
            root= FXMLLoader.load(getClass().getResource("../Frames/RegistrationOrSalary.fxml"));
            Stage stage = new Stage();
            stage.setTitle("Admin");
            stage.setScene(new Scene(root));
            stage.show();
            // Hide this current window (if this is what you want)
            ((Node)(actionEvent.getSource())).getScene().getWindow().hide();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

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

    public void getRegistrationData(ActionEvent actionEvent) throws IOException {
        String name = registrationName.getText();
        String surname = registrationSurname.getText();
        String position = registrationPosition.getText();
        String login = registrationLogin.getText();
        String password = registrationPassword.getText();

        clientConnection regData = new clientConnection();
        regData.sendRegData(name, surname, position, login, password, actionEvent);

        registrationName.clear();
        registrationSurname.clear();
        registrationPosition.clear();
        registrationLogin.clear();
        registrationPassword.clear();

    }
}

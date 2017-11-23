package sample.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HomepageController {
    @FXML
    private javafx.scene.control.Button enter;

    public void EnterAction(ActionEvent event) {
        try {
            Stage parentFrame = (Stage)enter.getScene().getWindow();
            parentFrame.close();
            Stage stage = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("../Frames/Authorisation.fxml"));
            stage.setTitle("Authorisation");
            stage.setScene(new Scene(root));
            stage.initOwner(((Node) event.getSource()).getScene().getWindow());
            stage.show();
        } catch (IOException o) {
            o.printStackTrace();
        }
    }

    public void RegistrationAction(ActionEvent event) {
        try {
            Stage parentFrame = (Stage)enter.getScene().getWindow();
            parentFrame.close();
            Stage stage = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("../Frames/registration.fxml"));
            stage.setTitle("Registration");
            stage.setScene(new Scene(root));
            stage.initOwner(((Node) event.getSource()).getScene().getWindow());
            stage.show();
        } catch (IOException o) {
            o.printStackTrace();
        }

    }
}
package sample.Controllers;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

import javafx.event.ActionEvent;
import java.io.IOException;

public class ConfirmSalaryCount {
    public void ConfirmSalaryMsg(ActionEvent event){
        Stage stage = new Stage();
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("../Frames/ConfirmSalaryCount.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        stage.setTitle("Confirmation");
            stage.setScene(new Scene(root));
            stage.initModality(Modality.WINDOW_MODAL);
            stage.initOwner(((Node)(event.getSource())).getScene().getWindow());
            stage.show();
    }
}

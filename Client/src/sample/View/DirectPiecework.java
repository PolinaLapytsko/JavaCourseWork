package sample.View;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class DirectPiecework {
    public void getView(ActionEvent actionEvent){
        Parent root;
        try {
            root= FXMLLoader.load(getClass().getResource("../Frames/DirectPiecework.fxml"));
            Stage stage = new Stage();
            stage.setTitle("Прямая сдельная");
            stage.setScene(new Scene(root));
            stage.show();
            // Hide this current window (if this is what you want)
            //((Node)(actionEvent.getSource())).getScene().getWindow().hide();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}

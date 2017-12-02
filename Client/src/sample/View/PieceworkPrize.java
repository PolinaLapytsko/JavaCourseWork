package sample.View;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import javafx.event.ActionEvent;
import java.io.IOException;


public class PieceworkPrize {
    public  void getView(ActionEvent event){
        Parent root;
        try {
            root= FXMLLoader.load(getClass().getResource("../Frames/PieceworkPrize.fxml"));
            Stage stage = new Stage();
            stage.setTitle("Сдельно-премиальная");
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

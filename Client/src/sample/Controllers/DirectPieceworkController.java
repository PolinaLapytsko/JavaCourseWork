package sample.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.stage.Stage;
import sample.clientConnection;

import java.io.IOException;

public class DirectPieceworkController {
    @FXML
    private javafx.scene.control.TextField Product;
    @FXML
    private javafx.scene.control.TextField PieceRate;
    @FXML
    private javafx.scene.control.Button ConfirmSimpleTime;
    public void getData(ActionEvent event) throws IOException {
        clientConnection client = new clientConnection();

        String product = Product.getText();
        String pieceRate = PieceRate.getText();

        if(client.sendDirectPieceworkSalaryData(product, pieceRate, event)){
            Stage stagep = (Stage) ConfirmSimpleTime.getScene().getWindow();
            stagep.close();
            ConfirmSalaryCount csc = new ConfirmSalaryCount();
            csc.ConfirmSalaryMsg(event);
        }
    }
}

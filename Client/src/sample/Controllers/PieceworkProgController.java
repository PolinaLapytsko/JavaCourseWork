package sample.Controllers;

import javafx.fxml.FXML;
import javafx.stage.Stage;
import sample.clientConnection;

import javafx.event.ActionEvent;

import java.io.IOException;

public class PieceworkProgController {
    @FXML
    private javafx.scene.control.TextField ProductFact;
    @FXML
    private javafx.scene.control.TextField ProductPlan;
    @FXML
    private javafx.scene.control.TextField PieceRant;
    @FXML
    private javafx.scene.control.TextField Coef;
    @FXML
    private javafx.scene.control.Button ConfirmProgPrize;

    public void getData(ActionEvent event) throws IOException {
        clientConnection client = new clientConnection();

        String productFact = ProductFact.getText();
        String productPlan = ProductPlan.getText();
        String pieceRate = PieceRant.getText();
        String coef = Coef.getText();

        if(client.sendPieceworkProgSalaryData(productFact, productPlan,pieceRate, coef, event)){
            Stage stagep = (Stage) ConfirmProgPrize.getScene().getWindow();
            stagep.close();
            ConfirmSalaryCount csc = new ConfirmSalaryCount();
            csc.ConfirmSalaryMsg(event);
        }
    }
}

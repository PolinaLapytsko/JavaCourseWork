package sample.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.stage.Stage;
import sample.clientConnection;

import java.io.IOException;

public class PieceworkPrizeController {
    @FXML
    private javafx.scene.control.TextField Product;
    @FXML
    private javafx.scene.control.TextField PieceRate;
    @FXML
    private javafx.scene.control.TextField PlanPrize;
    @FXML
    private javafx.scene.control.TextField Prize;
    @FXML
    private javafx.scene.control.TextField Plan;
    @FXML
    private javafx.scene.control.Button ConfirmPieceworkPrize;

    public void getData(ActionEvent event) throws IOException {
        clientConnection client = new clientConnection();

        String product = Product.getText();
        String pieceRate = PieceRate.getText();
        String planPrize = PlanPrize.getText();
        String prize = Prize.getText();
        String plan = Plan.getText();

        if(client.sendPieceworkPrizeSalaryData(product, pieceRate, planPrize, prize, plan, event)){
            Stage stagep = (Stage) ConfirmPieceworkPrize.getScene().getWindow();
            stagep.close();
            ConfirmSalaryCount csc = new ConfirmSalaryCount();
            csc.ConfirmSalaryMsg(event);
        }
    }
}

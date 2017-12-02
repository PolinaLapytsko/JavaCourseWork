package sample.Controllers;

import javafx.fxml.FXML;
import javafx.stage.Stage;
import sample.clientConnection;

import javafx.event.ActionEvent;
import java.io.IOException;


public class SimpleTimePrizeSalaryController {
    @FXML
    private javafx.scene.control.TextField WorkTime;
    @FXML
    private javafx.scene.control.TextField Rank;
    @FXML
    private javafx.scene.control.TextField Prize;
    @FXML
    private javafx.scene.control.Button ConfirmTimePrize;

    public void getData(ActionEvent event) throws IOException {
        clientConnection client = new clientConnection();

        String workTime = WorkTime.getText();
        String rank = Rank.getText();
        String prize = Prize.getText();
        if(client.sendSimpleTimePrizeSalaryData(workTime, rank, prize, event)){
            Stage stagep = (Stage) ConfirmTimePrize.getScene().getWindow();
            stagep.close();
            ConfirmSalaryCount csc = new ConfirmSalaryCount();
            csc.ConfirmSalaryMsg(event);
        }
       }

}

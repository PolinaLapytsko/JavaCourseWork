package sample.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.stage.Stage;
import sample.clientConnection;

import java.io.IOException;

public class SalaryController {
    @FXML
    private javafx.scene.control.TextField Position;
    @FXML
    private javafx.scene.control.Button ConfirmSalary;

    public void getData(ActionEvent event) throws IOException {
        clientConnection client = new clientConnection();

        String workTime = Position.getText();

        if(client.sendSalaryData(workTime, event)){
            Stage stagep = (Stage) ConfirmSalary.getScene().getWindow();
            stagep.close();
            ConfirmSalaryCount csc = new ConfirmSalaryCount();
            csc.ConfirmSalaryMsg(event);
        }
    }
}

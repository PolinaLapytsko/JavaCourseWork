package sample.Controllers;

        import javafx.fxml.FXML;
        import javafx.stage.Stage;
        import sample.clientConnection;

        import javafx.event.ActionEvent;
        import java.io.IOException;

public class SimpleTimeController {
    @FXML
    private javafx.scene.control.TextField WorkTime;
    @FXML
    private javafx.scene.control.TextField Rank;
    @FXML
    private javafx.scene.control.Button ConfirmSimpleTime;

    public void getData(ActionEvent event) throws IOException {
        clientConnection client = new clientConnection();

        String workTime = WorkTime.getText();
        String rank = Rank.getText();

        if(client.sendSimpleTimeSalaryData(workTime, rank)){
            Stage stagep = (Stage) ConfirmSimpleTime.getScene().getWindow();
            stagep.close();
            ConfirmSalaryCount csc = new ConfirmSalaryCount();
            csc.ConfirmSalaryMsg(event);
        }
    }
}

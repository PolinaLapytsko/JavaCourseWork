package sample.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.net.*;
import java.io.*;;

public class RegistrationController {
    @FXML
    private javafx.scene.control.TextField registrationName;
    @FXML
    private javafx.scene.control.TextField registrationSurname;
    @FXML
    private javafx.scene.control.TextField registrationPosition;
    @FXML
    private javafx.scene.control.TextField registrationLogin;
    @FXML
    private javafx.scene.control.TextField registrationPassword;

    public void getRegistrationData(ActionEvent event) {
        int serverPort = 6666; // здесь обязательно нужно указать порт к которому привязывается сервер.
        String address = "127.0.0.1"; // это IP-адрес компьютера, где исполняется наша серверная программа.
        // Здесь указан адрес того самого компьютера где будет исполняться и клиент.
        try {
            InetAddress ipAddress = InetAddress.getByName(address); // создаем объект который отображает вышеописанный IP-адрес.
            Socket socket = new Socket(ipAddress, serverPort); // создаем сокет используя IP-адрес и порт сервера.
            InputStream sin = socket.getInputStream();
            OutputStream sout = socket.getOutputStream();

            DataInputStream in = new DataInputStream(sin);
            DataOutputStream out = new DataOutputStream(sout);

            String name = registrationName.getText();
            String surname = registrationSurname.getText();
            String position = registrationPosition.getText();
            String login = registrationLogin.getText();
            String password = registrationPassword.getText();

            out.writeUTF("2");
            out.writeUTF(name);
            out.writeUTF(surname);
            out.writeUTF(position);
            out.writeUTF(login);
            out.writeUTF(password);

            out.flush(); // заставляем поток закончить передачу данных.

            String submit = in.readUTF(); // ожидаем пока клиент пришлет строку текста.

            if (submit.equals("OK"))
            {
                ToHomepage(event);
            }
        } catch (Exception x) {
            x.printStackTrace();
        }
    }

    @FXML
    private javafx.scene.control.Button backToHomepage;

    public void ToHomepage(ActionEvent event) throws IOException {
        try {
            Stage parentFrame = (Stage)backToHomepage.getScene().getWindow();
            parentFrame.close();
            Stage stage = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("../Frames/sample.fxml"));
            stage.setTitle("Homepage");
            stage.setScene(new Scene(root));
            stage.initOwner(((Node) event.getSource()).getScene().getWindow());
            stage.show();
        } catch (IOException o) {
            o.printStackTrace();
        }
    }
}
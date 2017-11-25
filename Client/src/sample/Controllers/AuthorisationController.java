package sample.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.net.*;
import java.io.*;

public class AuthorisationController {
    @FXML
    private javafx.scene.control.TextField authorisationLogin;
    @FXML
    private javafx.scene.control.TextField authorisationPassword;

    public void getAuthorisationData(ActionEvent event) {
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

            String login = authorisationLogin.getText();
            String password = authorisationPassword.getText();

            out.writeUTF("1");
            out.writeUTF(login); // отсылаем введенную строку текста серверу.
            out.writeUTF(password);
            out.flush(); // заставляем поток закончить передачу данных.
            out.flush();
            String line = in.readUTF(); // ждем пока сервер отошлет строку текста.
            String line2 = in.readUTF();
            System.out.println("login : " + line);
            System.out.println("password : " + line2);
            System.out.println("Looks like the server is pleased with us. Go ahead and enter more lines.");
            System.out.println();

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

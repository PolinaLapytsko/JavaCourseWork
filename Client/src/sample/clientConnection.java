package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class clientConnection {
    public static Socket cok = null;
    public static PrintStream ps;
    public static BufferedReader br;
    public static DataInputStream deserializer;
    public static DataOutputStream serializer;

    public static void connection() throws UnknownHostException, IOException {
        cok = new Socket(InetAddress.getLocalHost(), 8072);
        ps = new PrintStream(cok.getOutputStream());
        br = new BufferedReader(new InputStreamReader(cok.getInputStream()));
        serializer = new DataOutputStream(cok.getOutputStream());
        deserializer = new DataInputStream(cok.getInputStream());
    }
    @FXML
    private javafx.scene.control.Button SubmitAuthorisation;

    public void sendAuthorisationData(String login, String password) throws IOException {
        serializer.writeUTF("Authorisation");
        serializer.writeUTF(login);
        serializer.writeUTF(password);
        serializer.flush();

        String answer = deserializer.readUTF();
        if (answer.equals("ADMIN"))
        {
            System.out.println("sdf");
        }
    }

    public void ToRegistrationOrSalary(){

    }
}
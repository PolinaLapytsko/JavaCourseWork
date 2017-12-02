package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import sample.Controllers.UserAccount;

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

    @FXML
    private javafx.scene.control.TextArea actiontarget;

    public void sendAuthorisationData(String login, String password, ActionEvent event) throws IOException {
        serializer.writeUTF("Authorisation");
        serializer.writeUTF(login);
        serializer.writeUTF(password);
        serializer.flush();

        String answer = deserializer.readUTF();
        //System.out.println(answer);
        if (answer.equals("ADMIN"))
        {
           // System.out.println("sdf");
            Parent root;
            try {
                root = FXMLLoader.load(getClass().getResource("Frames/RegistrationOrSalary.fxml"));
                Stage stage = new Stage();
                stage.setTitle("Admin");
                stage.setScene(new Scene(root));
                stage.show();
                // Hide this current window (if this is what you want)
                ((Node)(event.getSource())).getScene().getWindow().hide();
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }
        else
            if (answer.equals("ERROR"))
            {
                System.out.println("Error");
            }
    }

    public void sendRegData(String name, String surname, String position, String login, String password, ActionEvent event) throws IOException {
       serializer.writeUTF("Registration");
       serializer.writeUTF(name);
       serializer.writeUTF(surname);
       serializer.writeUTF(position);
       serializer.writeUTF(login);
       serializer.writeUTF(password);
       serializer.flush();

       String reg = deserializer.readUTF();

      if(reg.equals("OK")){
            Stage stage = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("Frames/ConfirmRegistration.fxml"));
            stage.setTitle("Confirmation");
            stage.setScene(new Scene(root));
            stage.initModality(Modality.WINDOW_MODAL);
            stage.initOwner(((Node)(event.getSource())).getScene().getWindow());
            stage.show();

       }
    }

    public void sendUserListRequest(String msg) throws IOException {
        serializer.writeUTF(msg);
        serializer.flush();
    }

    public void sendUserSalaryRequest(String msg, int ind) throws IOException {
        serializer.writeUTF(msg);
        String index = String.valueOf(ind);
        serializer.writeUTF(index);
        serializer.flush();
    }

    final ObservableList<UserAccount> data = FXCollections.observableArrayList();

    public String message() throws IOException {
        return deserializer.readUTF();
    }

    public UserAccount getUserList() throws IOException {
        String idString = deserializer.readUTF();
        int id = Integer.parseInt(idString);
        String name = deserializer.readUTF();
        String surname = deserializer.readUTF();
        String position = deserializer.readUTF();
        //data.add(new UserAccount (id, name, surname, position));
        return new UserAccount (id, name, surname, position);
    }

    public UserAccount getUserSalaryList() throws IOException {
        String date = deserializer.readUTF();
        String salary = deserializer.readUTF();
        return new UserAccount(date,salary);
    }

    public boolean sendSimpleTimeSalaryData (String workTime, String rank) throws IOException {
      serializer.writeUTF("SimpleTimeSalary");
      serializer.writeUTF(workTime);
      serializer.writeUTF(rank);
      serializer.flush();
      String answer = deserializer.readUTF();
      if(answer.equals("ok")) return true;
      return false;
    }

    public void sendUserIndex (int index) throws IOException {
        serializer.writeUTF(String.valueOf(index));
        serializer.flush();
    }

    public void getUserMsgCount(String msg) throws IOException {
        serializer.writeUTF(msg);
        serializer.flush();
    }

    public boolean sendSimpleTimePrizeSalaryData(String workTime, String rank, String prize, ActionEvent event) throws IOException {
        serializer.writeUTF("SimpleTimePrizeSalary");
        serializer.writeUTF(workTime);
        serializer.writeUTF(rank);
        serializer.writeUTF(prize);
        serializer.flush();
        String answer = deserializer.readUTF();
        if(answer.equals("ok")) return true;
        return false;
    }

    public boolean sendSalaryData (String pos, ActionEvent event) throws IOException {
        serializer.writeUTF("Salary");
        serializer.writeUTF(pos);
        serializer.flush();
        String answer = deserializer.readUTF();
        if(answer.equals("ok")) return true;
        return false;
    }

    public boolean sendDirectPieceworkSalaryData (String product, String pieceRate, ActionEvent event) throws IOException {
        serializer.writeUTF("DirectPiecework");
        serializer.writeUTF(product);
        serializer.writeUTF(pieceRate);
        serializer.flush();
        String answer = deserializer.readUTF();
        if(answer.equals("ok")) return true;
        return false;
    }

    public boolean sendPieceworkPrizeSalaryData (String product, String pieceRate, String planPrize, String prize, String plan, ActionEvent event) throws IOException {
        serializer.writeUTF("PieceworkPrize");
        serializer.writeUTF(product);
        serializer.writeUTF(pieceRate);
        serializer.writeUTF(planPrize);
        serializer.writeUTF(prize);
        serializer.writeUTF(plan);
        serializer.flush();
        String answer = deserializer.readUTF();
        if(answer.equals("ok")) return true;
        return false;
    }

    public boolean sendPieceworkProgSalaryData (String productFact, String productPlan,String pieceRate,
                                                String  coef,ActionEvent event) throws IOException {
        serializer.writeUTF("PieceworkProg");
        serializer.writeUTF(productFact);
        serializer.writeUTF(productPlan);
        serializer.writeUTF(pieceRate);
        serializer.writeUTF(coef);
        serializer.flush();
        String answer = deserializer.readUTF();
        if(answer.equals("ok")) return true;
        return false;
    }
}
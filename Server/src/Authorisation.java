import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class Authorisation {
    private String login;
    private  String password;


    public void getDataFromClient(DataInputStream in) throws IOException {
        login = in.readUTF(); // ожидаем пока клиент пришлет строку текста.
        password = in.readUTF();
    }

    public void sendDataToClient(DataOutputStream out) throws IOException {
        System.out.println("login : " + login);
        System.out.println("password : " + password);
        System.out.println("I'm sending it back...");
        out.writeUTF(login);
        out.writeUTF(password); // отсылаем клиенту обратно ту самую строку текста.
        out.flush(); // заставляем поток закончить передачу данных.
        System.out.println("Waiting for the next line...");
        System.out.println();
    }
}

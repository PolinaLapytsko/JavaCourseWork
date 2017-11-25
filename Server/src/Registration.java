import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class Registration {
    private String name = null;
    private String surname = null;
    private String position = null;
    private String login = null;
    private String password = null;

    public void getDataFromClient(DataInputStream in) throws IOException {
        name = in.readUTF(); // ожидаем пока клиент пришлет строку текста.
        surname = in.readUTF();
        position = in.readUTF();
        login = in.readUTF();
        password = in.readUTF();
    }

    public void sendDataToClient(DataOutputStream out) throws IOException {
        System.out.println("name : " + name);
        System.out.println("surname : " + surname);
        System.out.println("position : " + position);
        System.out.println("login : " + login);
        System.out.println("password : " + password);
        System.out.println("I'm sending it back...");

        out.writeUTF(name);
        out.writeUTF(surname);
        out.writeUTF(position);
        out.writeUTF(login);
        out.writeUTF(password);// отсылаем клиенту обратно ту самую строку текста.
        out.flush(); // заставляем поток закончить передачу данных.
        System.out.println("Waiting for the next line...");
        System.out.println();
    }
}

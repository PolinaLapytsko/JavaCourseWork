import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Registration {
    public void getRegistrationData(DataInputStream in, DataOutputStream out, Connection connection) throws IOException, SQLException {

        String  name = in.readUTF();
        String  surname = in.readUTF();
        String  position = in.readUTF();
        String  login = in.readUTF();
        String  password = in.readUTF();


        Statement statement = connection.createStatement();
        statement.execute ("INSERT INTO coursework.employees (login, password, position, name, surname) " +
                "VALUES ('"+login+"','"+password+"','"+position+"','"+name+"','"+surname+"')");

        out.writeUTF("OK");
    }
}

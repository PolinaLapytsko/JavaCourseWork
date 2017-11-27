import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Authorisation {
    public void getAuthorisationData(DataInputStream in, DataOutputStream out, Connection connection) throws IOException, SQLException {
       String login = in.readUTF();
       String  password = in.readUTF();

        Statement statement = connection.createStatement();
        ResultSet result = statement.executeQuery("SELECT * FROM coursework.employees WHERE (login = '" + login + "' AND password = '" + password + "')");
        int columns = result.getMetaData().getColumnCount();
        //System.out.println(columns);
        String line = "1";
        while(result.next()){
            for (int i = 2; i < columns-2; i++)
                line += result.getString(i);
        }
        if (line.length() == 1) {
            out.writeUTF("ERROR");
        } else {
            if (line.equals("1adminadmin")) {
                out.writeUTF("ADMIN");
            }
            else {
                out.writeUTF("USER");
            }
        }
    }
}

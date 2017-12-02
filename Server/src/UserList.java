import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class UserList {
    public void getListFromDB(DataInputStream in, DataOutputStream out, Connection connection) throws SQLException, IOException {
        Statement statement = connection.createStatement();
        ResultSet result = statement.executeQuery("SELECT idEmployees, name, surname, position FROM coursework.employees");
        int columns = result.getMetaData().getColumnCount();
        while(result.next()){
            out.writeUTF("start");
            for (int i = 1; i < columns+1; i++){
                out.writeUTF(result.getString(i));
            }
            out.flush();
        }
        out.writeUTF("Stop");
    }
}

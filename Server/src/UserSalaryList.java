import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserSalaryList {
    public void getListFromDB(DataInputStream in, DataOutputStream out, Connection connection) throws IOException, SQLException {
        String indexS = in.readUTF();
        int index = Integer.parseInt(indexS);

        String selectSQL="SELECT datetime, salary FROM coursework.salary WHERE idEmployees = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(selectSQL);
        preparedStatement.setInt(1, index);
        ResultSet rs = preparedStatement.executeQuery();
        int columns = rs.getMetaData().getColumnCount();
        while(rs.next()){
            out.writeUTF("start");
            for (int i = 1; i < columns+1; i++){
                //System.out.println(rs.getString(i));
                out.writeUTF(rs.getString(i));
            }
            out.flush();
        }
        out.writeUTF("Stop");
    }
}

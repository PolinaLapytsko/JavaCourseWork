import java.io.DataInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Salary {
    public double countSalary(DataInputStream in, Connection connection) throws SQLException, IOException {
        String position = in.readUTF();

        String selectSQL="SELECT quantity FROM coursework.qualificationindex WHERE position = ?";

        PreparedStatement preparedStatement = connection.prepareStatement(selectSQL);
        preparedStatement.setString(1, position);
        ResultSet rs = preparedStatement.executeQuery();
        String koef=null;
        while(rs.next()){
            koef = rs.getString(1);
        }
        double salary = Double.parseDouble(koef);
        System.out.println(salary);
        return  salary;
    }
}

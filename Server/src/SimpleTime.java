import java.io.DataInputStream;
import java.io.IOException;
import java.sql.*;

public class SimpleTime {

    public double countSalary(DataInputStream in, Connection connection) throws IOException, SQLException {
        String workTimeString = in.readUTF();
        Double workTime = Double.valueOf(workTimeString);
        String rank = in.readUTF();

        String selectSQL="SELECT coefficient FROM coursework.tarifindex WHERE rank = ?";

          PreparedStatement preparedStatement = connection.prepareStatement(selectSQL);
          preparedStatement.setString(1, rank);
          ResultSet rs = preparedStatement.executeQuery();
            String koef=null;
          while(rs.next()){
              koef = rs.getString(1);
          }
          double d = Double.parseDouble(koef);
          Double salary = 33.0*d*workTime;
          return  salary;
            //System.out.println(salary);
    }
}
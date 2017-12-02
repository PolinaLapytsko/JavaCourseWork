import java.io.DataInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;

public class SimpleTimePrize {
    public double countSalary(DataInputStream in, Connection connection) throws IOException, SQLException {
        String workTimeString = in.readUTF();
        Double workTime = Double.valueOf(workTimeString);
        String rank = in.readUTF();
        String prizeString = in.readUTF();
        Double prize = Double.valueOf(prizeString);
        String selectSQL="SELECT coefficient FROM coursework.tarifindex WHERE rank = ?";

        PreparedStatement preparedStatement = connection.prepareStatement(selectSQL);
        preparedStatement.setString(1, rank);
        ResultSet rs = preparedStatement.executeQuery();
        String koef=null;
        while(rs.next()){
            koef = rs.getString(1);
        }
        double d = Double.parseDouble(koef);
        Double salary = 33.0*d*workTime*(1+prize/100);
        return  salary;

    }

}

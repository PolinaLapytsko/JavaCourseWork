import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Calendar;

public class DBSalarySet {
    public void setInDB(Double salary, String index, Connection connection) throws SQLException {
        Calendar cal = Calendar.getInstance();
        int m = cal.get(Calendar.MONTH) + 1;
        String month = Integer.toString(m);
        int d = cal.get(Calendar.DAY_OF_MONTH);
        String day = Integer.toString(d);
        int y = cal.get(Calendar.YEAR);
        String year = Integer.toString(y);
        String date = day+"."+month+"."+year;  //String date
        int id = Integer.parseInt(index);      //int id

        String selectSQL="INSERT INTO coursework.salary(idEmployees, salary, datetime) VALUES (?,?,?)";

        PreparedStatement preparedStatement = connection.prepareStatement(selectSQL);
        preparedStatement.setInt(1, id);
        preparedStatement.setDouble(2, salary);
        preparedStatement.setString(3, date);
        preparedStatement.executeUpdate();
        System.out.println("ok");
    }
}

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.sql.*;

public class Authorisation {
    private String login;
    private  String password;

    private static final String USERNAME = "root";
    private static final String USERPASSWORD = "1111";
    private static final String URL = "jdbc:mysql://localhost:3306/mysql?autoReconnect=true&useSSL=false&serverTimezone=UTC";

    public int getDataFromClient(DataInputStream in) throws IOException {
        login = in.readUTF(); // ожидаем пока клиент пришлет строку текста.
        password = in.readUTF();
        try {
            Driver driver = new com.mysql.jdbc.Driver();
            DriverManager.registerDriver(driver);
        } catch (SQLException ex) {
            System.out.println("Ошибка регистрации драйвера");
            return 0;
        }
        try (Connection connection = DriverManager.getConnection(URL, USERNAME, USERPASSWORD);
             Statement statement = connection.createStatement()) {

            ResultSet result = statement.executeQuery("SELECT * FROM coursework.employees WHERE (login = '" + login + "' AND password = '" + password + "')");
            int columns = result.getMetaData().getColumnCount();
            //System.out.println(columns);
            String line = "1";
            while(result.next()){
                for (int i = 1; i < columns; i++)
                    line += result.getString(i);
            }
            //System.out.println(line.length());
            if (line.length() == 1) {
                System.out.println("ERROR");
                return 1;
            } else {
                System.out.println("OK");
                return 2;
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return 0;
    }

    public void sendDataToClient(DataOutputStream out, String msg) throws IOException {

        out.writeUTF(msg);
        out.flush(); // заставляем поток закончить передачу данных.

    }
}

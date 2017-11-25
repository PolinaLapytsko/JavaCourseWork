import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.sql.*;


public class Registration {
    private String name = null;
    private String surname = null;
    private String position = null;
    private String login = null;
    private String password = null;

    private static final String USERNAME = "root";
    private static final String USERPASSWORD = "1111";
    private static final String URL = "jdbc:mysql://localhost:3306/mysql?autoReconnect=true&useSSL=false&serverTimezone=UTC";

    public void getDataFromClient(DataInputStream in) throws IOException {
        name = in.readUTF(); // ожидаем пока клиент пришлет строку текста.
        surname = in.readUTF();
        position = in.readUTF();
        login = in.readUTF();
        password = in.readUTF();
        try{
            Driver driver = new com.mysql.jdbc.Driver();
            DriverManager.registerDriver(driver);
        } catch(SQLException ex){
            System.out.println("Ошибка регистрации драйвера");
            return;
        }

        try(Connection connection = DriverManager.getConnection(URL, USERNAME, USERPASSWORD);
            Statement statement = connection.createStatement()) {

            statement.execute ("INSERT INTO coursework.employees (login, password, position, name, surname) " +
                    "VALUES ('"+login+"','"+password+"','"+position+"','"+name+"','"+surname+"')");

        } catch (SQLException ex){
            ex.printStackTrace();
        }
    }

    public void sendDataToClient(DataOutputStream out) throws IOException {
        out.writeUTF("OK");
        out.flush(); // заставляем поток закончить передачу данных.
    }
}

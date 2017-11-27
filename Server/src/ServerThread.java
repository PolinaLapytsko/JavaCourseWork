import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

class ServerThread extends Thread {
    private Socket socket;
    private PrintStream os;
    private BufferedReader is;
    private InetAddress addr;


    public ServerThread(Socket s) throws IOException {
        socket = s;
        addr = s.getInetAddress();
        os = new PrintStream(s.getOutputStream());
        is = new BufferedReader(new InputStreamReader(s.getInputStream()));
    }

    String login = null;
    String password = null;

    public void run() {

        try {
            OutputStream serializer = socket.getOutputStream();
            InputStream deserializer = socket.getInputStream();

            DataInputStream in = new DataInputStream(deserializer);
            DataOutputStream out = new DataOutputStream(serializer);

           String url = "jdbc:mysql://localhost:3306/mysql?autoReconnect=true&useSSL=false&serverTimezone=UTC";
            Properties prop = new Properties();
            prop.put("user", "root");
            prop.put("password", "1111");
            prop.put("autoReconnect", "true");
            prop.put("characterEncoding", "UTF-8");
            prop.put("useUnicode", "true");
            DriverManager.registerDriver(new com.mysql.jdbc.Driver());
            Connection conn = DriverManager.getConnection(url, prop);

            String flag = null;

            while (true)
            {
                flag = in.readUTF();
                if(flag.equals("Authorisation"))
                {
                    Authorisation object = new Authorisation();
                    object.getAuthorisationData(in, out, conn);
                    out.flush();
                }
            }
        }catch (IOException e) {
            //System.err.println(e);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            disconnect();
        }
    }
    public void disconnect() {
        try {
            if (os != null) {
                os.close();
            }
            if (is != null) {
                is.close();
            }
            System.out.println("Закончил работу: "+addr.getHostName());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            this.interrupt();
        }
    }
}
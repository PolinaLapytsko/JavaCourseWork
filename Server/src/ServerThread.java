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
                else
                    if (flag.equals("Registration")){
                       // System.out.println("Reg OK");
                        Registration object = new Registration();
                        object.getRegistrationData(in, out, conn);
                        out.flush();
                    }
                    else
                        if (flag.equals("UserList")){
                          // System.out.println("UserList");
                            UserList list = new UserList();
                            list.getListFromDB(in, out, conn);
                        }
                        else
                            if (flag.equals("UserSalaryList")){
                                UserSalaryList SalaryList = new UserSalaryList();
                                SalaryList.getListFromDB(in, out, conn);
                            }
                            else
                                if(flag.equals("Count"))
                                {
                                    String index = in.readUTF();
                                    DBSalarySet dbSalarySet = new DBSalarySet();
                                    //System.out.println(index);
                                    String kindOfSalary = in.readUTF();
                                    if (kindOfSalary.equals("SimpleTimeSalary")){
                                        SimpleTime simpleTime = new SimpleTime();
                                        dbSalarySet.setInDB(simpleTime.countSalary(in, conn), index, conn);
                                        out.writeUTF("ok");
                                    } else
                                        if (kindOfSalary.equals("SimpleTimePrizeSalary")){
                                            SimpleTimePrize simpleTimePrize = new SimpleTimePrize();
                                            dbSalarySet.setInDB(simpleTimePrize.countSalary(in, conn), index, conn);
                                            out.writeUTF("ok");
                                        } else
                                    if (kindOfSalary.equals("Salary")){
                                            Salary salary = new Salary();
                                            dbSalarySet.setInDB(salary.countSalary(in, conn), index, conn);
                                            out.writeUTF("ok");
                                    } else
                                    if (kindOfSalary.equals("DirectPiecework")){
                                        DirectPiecework directPiecework = new DirectPiecework();
                                        dbSalarySet.setInDB(directPiecework.countSalary(in), index, conn);
                                        out.writeUTF("ok");
                                    } else
                                    if (kindOfSalary.equals("PieceworkPrize")){
                                            PieceworkPrize pieceworkPrize = new PieceworkPrize();
                                            dbSalarySet.setInDB(pieceworkPrize.countSalary(in), index, conn);
                                            out.writeUTF("ok");
                                    } else
                                        if (kindOfSalary.equals("PieceworkProg")){
                                            PieceworkProg pieceworkProg = new PieceworkProg();
                                            dbSalarySet.setInDB(pieceworkProg.countSalary(in), index, conn);
                                            out.writeUTF("ok");
                                        }
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
import java.net.*;
import java.io.*;
/*changes*/
public class Server {
   public static void main(String[] ar)    {
     int port = 6666; // случайный порт (может быть любое число от 1025 до 65535)
       try {
         ServerSocket ss = new ServerSocket(port); // создаем сокет сервера и привязываем его к вышеуказанному порту
         System.out.println("Waiting for a client...");

         Socket socket = ss.accept(); // заставляем сервер ждать подключений и выводим сообщение когда кто-то связался с сервером
         System.out.println("Got a client :) ... Finally, someone saw me through all the cover!");
         System.out.println();

 // Берем входной и выходной потоки сокета, теперь можем получать и отсылать данные клиенту. 
         InputStream sin = socket.getInputStream();
         OutputStream sout = socket.getOutputStream();

 // Конвертируем потоки в другой тип, чтоб легче обрабатывать текстовые сообщения.
         DataInputStream in = new DataInputStream(sin);
         DataOutputStream out = new DataOutputStream(sout);

         String getIndex = null;
         getIndex = in.readUTF();
         int index = Integer.parseInt(getIndex);
         switch (index){
           case 1:{ //Authorisation
               Authorisation authorisationData = new Authorisation();
              // authorisationData.getDataFromClient(in);
               int flag = authorisationData.getDataFromClient(in);
               if(flag == 2)
                 authorisationData.sendDataToClient(out, "OK");
               else
                   if (flag == 1)
                        authorisationData.sendDataToClient(out, "ERROR");
             break;
           }
           case 2:{ //registration
               Registration registrationData = new Registration();
               registrationData.getDataFromClient(in);
               registrationData.sendDataToClient(out);
             break;
           }
         }


       } catch(Exception x) { x.printStackTrace(); }
   }
}
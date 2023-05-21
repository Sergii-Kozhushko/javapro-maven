/**
 * Client.java
 *
 * @author Sergii Kozhushko, sergiikozhushko@gmail.com
 * Date of creation: 19-May-2023 11:07
 */

package loshmanov.webchat;

import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Client {

   public static void main(String[] args) {
      System.out.println("Запускается клиент...");
      try {
         // Подключаемся к серверу
         Socket socket = new Socket("localhost", 8189);
         PrintWriter out = new PrintWriter(socket.getOutputStream());
         Scanner in = new Scanner(socket.getInputStream());
         Scanner scanner = new Scanner(System.in);
         while (true) {
            String s = scanner.nextLine();
            out.println(s);
            out.flush();
            if (s.equals("end")) {
               break;
            }
            String message = in.nextLine();
            System.out.println("Сообщение от сервера: " + message);
         }
         scanner.close();
         in.close();
         out.close();
         socket.close();
      } catch (Exception e) {
         e.printStackTrace();
      }
      System.out.println("Клиент завершил работу");
   }

}

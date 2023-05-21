/**
 * Server.java
 *
 * @author Sergii Kozhushko, sergiikozhushko@gmail.com
 * Date of creation: 19-May-2023 11:08
 */

package loshmanov.webchat;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
   private final int serverPort;

   public Server(int port) {
      serverPort = port;
      ServerSocket server = null;
      Socket s = null;
      try {
         server = new ServerSocket(serverPort);
         System.out.println("Сервер запущен. Ожидание клиентов...");
         while (true) {
            // Как только клиент подключился, создаем сокет (соединение)
            s = server.accept();
            System.out.println("Клиент подключился");
            // В отдельном потоке запускаем обработчик этого клиента
            new Thread(new ClientHandler(s)).start();
         }
      } catch (IOException e) {
         e.printStackTrace();
      } finally {
         try {
            server.close();
            System.out.println("Сервер закрыт");
            s.close();
         } catch (IOException e) {
            e.printStackTrace();
         }
      }
   }

}

/**
 * Server.java
 *
 * @author Sergii Kozhushko, sergiikozhushko@gmail.com
 * Date of creation: 19-May-2023 11:08
 */

package loshmanov.webchat;

import lombok.Getter;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

@Getter
public class Server {
   private final int serverPort;
   private List<ClientHandler> userList = new ArrayList<>();

   public Server(int port) {
      serverPort = port;
      ServerSocket server = null;
      Socket s = null;
      try {
         server = new ServerSocket(serverPort);
         System.out.println("Rabbit Chat Server started. Waiting for clients...");
         while (true) {
            // Как только клиент подключился, создаем сокет (соединение)
            s = server.accept();
            System.out.print("New client connected. ");
            System.out.print(" Host: " + s.getLocalAddress());
            System.out.println( " Port: " + s.getPort());
            // В отдельном потоке запускаем обработчик этого клиента
            ClientHandler clientHandler = new ClientHandler(s, this);
            userList.add(clientHandler);
            Thread thread = new Thread(clientHandler);
            thread.start();
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

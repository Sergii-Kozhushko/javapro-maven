/**
 * ClientHandler.java
 *
 * @author Sergii Kozhushko, sergiikozhushko@gmail.com
 * Date of creation: 19-May-2023 11:08
 */

package loshmanov.webchat;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class ClientHandler implements Runnable {
   private Socket s;
   private PrintWriter out;
   private Scanner in;
   private static int CLIENTS_COUNT = 0;
   private String name;

   public ClientHandler(Socket s) {
      try {
         this.s = s;
         out = new PrintWriter(s.getOutputStream());
         in = new Scanner(s.getInputStream());
         CLIENTS_COUNT++;
         name = "Клиент № " + CLIENTS_COUNT;
      } catch (IOException e) {
         e.printStackTrace();
      }
   }

   @Override
   public void run() {
      while (true) {
         if (in.hasNext()) {
            String w = in.nextLine();
            System.out.println(name + ": " + w);
            out.println("echo: " + w);
            out.flush();
            if (w.equals("end")) {
               break;
            }
         }
      }
      try {
         System.out.println("Клиент отключился");
         in.close();
         out.close();
         s.close();
      } catch (IOException e) {
         e.printStackTrace();
      }
   }
}

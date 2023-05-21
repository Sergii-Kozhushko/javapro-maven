/**
 * ServerApp.java
 *
 * @author Sergii Kozhushko, sergiikozhushko@gmail.com
 * Date of creation: 19-May-2023 11:08
 */

package loshmanov.webchat;

public class ServerApp {

   public static final int PORT = 8189;

   public static void main(String[] args) {
      new Server(PORT);
   }

}

/**
 * ClientApp.java
 *
 * @author Sergii Kozhushko, sergiikozhushko@gmail.com
 * Date of creation: 19-May-2023 11:07
 */

package loshmanov.webchat;

public class ClientApp {

   public static final String SERVER_HOST = "localhost";
   public static final int SERVER_PORT = 8189;

   public static void main(String[] args) {
      new ClientWindow(SERVER_HOST, SERVER_PORT);
   }

}

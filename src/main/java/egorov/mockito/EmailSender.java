/**
 * EmailSender.java
 *
 * @author Sergii Kozhushko, sergiikozhushko@gmail.com
 * Date of creation: 17-May-2023 22:37
 */

package egorov.mockito;

public class EmailSender {
   public void sendEmail(String recipient, String message){
      System.out.println("Sent email with text '" + message + "' to recipient: " + recipient);
   }

}

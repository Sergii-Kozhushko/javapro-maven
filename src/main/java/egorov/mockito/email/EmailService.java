/**
 * EmailService.java
 *
 * @author Sergii Kozhushko, sergiikozhushko@gmail.com
 * Date of creation: 21-May-2023 10:47
 */

package egorov.mockito.email;

public class EmailService {
   private EmailClient emailClient;

   public EmailService(EmailClient emailClient) {
      this.emailClient = emailClient;
   }

   public void sendEmail(String recipient, String message){
      emailClient.sendEmail(recipient, message);
   }
}

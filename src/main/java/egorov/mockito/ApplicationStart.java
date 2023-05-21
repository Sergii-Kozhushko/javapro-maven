/**
 * ApplicationStart.java
 *
 * @author Sergii Kozhushko, sergiikozhushko@gmail.com
 * Date of creation: 21-May-2023 10:52
 */

package egorov.mockito;

import egorov.mockito.email.EmailClient;
import egorov.mockito.email.EmailService;

public class ApplicationStart {
   public static void main(String[] args) {

      EmailService emailService = new EmailService(new EmailClient());
      emailService.sendEmail("usergmail.com", "text");
   }

}

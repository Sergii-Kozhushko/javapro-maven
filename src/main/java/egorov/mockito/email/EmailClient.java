/**
 * EmailClient.java
 *
 * @author Sergii Kozhushko, sergiikozhushko@gmail.com
 * Date of creation: 21-May-2023 09:25
 */

package egorov.mockito.email;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EmailClient {

   public void sendEmail(String recipient, String message) {
      if (recipient == null) {
         throw new IllegalArgumentException("Recipient is null");
      }
      Pattern pattern = Pattern.compile("\\w+([\\.-]?\\w+)*@\\w+([\\.-]?\\w+)*\\.\\w{2,4}");
      Matcher matcher = pattern.matcher(recipient);

      if (!matcher.matches()){
         throw new IllegalArgumentException("Email is incorrect");
      }
      System.out.println("Sent email to: " + recipient +". Message: " + message);
   }

}

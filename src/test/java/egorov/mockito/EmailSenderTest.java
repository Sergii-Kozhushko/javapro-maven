package egorov.mockito;

import egorov.mockito.EmailSender;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

/**
 * egorov.mockito.EmailSenderTest.java
 *
 * @author Sergii Kozhushko, sergiikozhushko@gmail.com
 * Date of creation: 17-May-2023 22:40
 */

/*Создайте класс EmailSender, который содержит метод sendEmail(String recipient, String message),
        отправляющий электронную почту получателю с заданным сообщением.
        Напишите тест, используя Mockito, чтобы убедиться,
        что метод sendEmail был вызван с правильными аргументами.*/

@ExtendWith(MockitoExtension.class)
public class EmailSenderTest {
   @Mock
   EmailSender emailSender;

   @Test
   public void sendTest(){
      emailSender.sendEmail("user1", "Message text");
      Mockito.verify(emailSender).sendEmail("user1", "Message text");


   }

}

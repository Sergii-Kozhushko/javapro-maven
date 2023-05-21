package egorov.mockito;

import egorov.mockito.Logger;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

/**
 * egorov.mockito.LoggerTest.java
 *
 * @author Sergii Kozhushko, sergiikozhushko@gmail.com
 * Date of creation: 17-May-2023 23:01
 */
/*
Создайте класс Logger, который содержит метод log(String message),
        записывающий сообщение в журнал.
        Напишите тест, используя Mockito, чтобы убедиться,
        что метод log был вызван хотя бы один раз.
*/

@ExtendWith(MockitoExtension.class)
public class LoggerTest {
   @Mock
   Logger logger;

   @Test
   public void testLog(){
      logger.log("Some message");
      Mockito.verify(logger, Mockito.only()).log(Mockito.any(String.class));

   }

}

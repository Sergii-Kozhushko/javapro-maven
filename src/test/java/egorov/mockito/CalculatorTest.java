package egorov.mockito;

import egorov.mockito.Calculator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

/**
 * egorov.mockito.CalculatorTest.java
 *
 * @author Sergii Kozhushko, sergiikozhushko@gmail.com
 * Date of creation: 17-May-2023 22:25
 */

/*Создайте класс Calculator, который содержит метод add(int a, int b)
        для сложения двух чисел. Напишите тест, используя Mockito, чтобы
        убедиться, что метод add был вызван правильно.*/

@ExtendWith(MockitoExtension.class)
public class CalculatorTest {
   @Mock
   Calculator calculator;

   @Test
   public void testAdd(){
      int res = calculator.add(3, 2);
      Mockito.verify(calculator).add(3, 2);
   }

}

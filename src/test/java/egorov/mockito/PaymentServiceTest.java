package egorov.mockito;

import egorov.mockito.order.PaymentGateway;
import egorov.mockito.order.PaymentService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

/**
 * egorov.mockito.PaymentServiceTest.java
 *
 * @author Sergii Kozhushko, sergiikozhushko@gmail.com
 * Date of creation: 17-May-2023 23:15
 */
/*Создайте класс PaymentService, который зависит от класса PaymentGateway.
        PaymentGateway содержит метод processPayment(double amount),
        который возвращает true, если платеж успешно обработан,
        и false в противном случае. Напишите тест, используя Mockito,
        чтобы проверить, что метод processPayment был вызван и вернул ожидаемый результат.*/

@ExtendWith(MockitoExtension.class)
public class PaymentServiceTest {

   @Mock
   PaymentGateway paymentGateway;

   @Test
   public void doTestPayment(){
      PaymentService paymentService = new PaymentService(paymentGateway);
      paymentService.makePayment(101);
      Mockito.verify(paymentGateway).processPayment(101);
      Assertions.assertFalse(paymentService.makePayment(0));
      Assertions.assertFalse(paymentService.makePayment(-100));
   }

}

package egorov.mockito;

import egorov.mockito.order.InventoryManager;
import egorov.mockito.order.OrderService;
import egorov.mockito.order.PaymentFailedException;
import egorov.mockito.order.PaymentGateway;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

/**
 * egorov.mockito.OrderServiceTest.java
 *
 * @author Sergii Kozhushko, sergiikozhushko@gmail.com
 * Date of creation: 18-May-2023 20:52
 */
@ExtendWith(MockitoExtension.class)
public class OrderServiceTest {
   @Mock
   PaymentGateway paymentGateway;
   @Mock
   InventoryManager inventoryManager;
   OrderService orderService;
   @BeforeEach
   public void init(){
      orderService = new OrderService(paymentGateway, inventoryManager);
   }


   @Test
   public void makeOrder(){
      Mockito.doReturn(true).when(inventoryManager).checkStock(Mockito.anyString(), Mockito.anyInt());
      orderService.makeOrder("Book", 10, 150.20);

      Mockito.verify(paymentGateway).processPayment(150.20);
      Mockito.verify(inventoryManager, Mockito.times(1))
              .checkStock("Book", 10);
   }

//   Создайте класс OrderService, который зависит от класса PaymentGateway.
//   PaymentGateway содержит метод processPayment(double amount),
//   который может выбросить исключение egorov.mockito.order.
//        PaymentFailedException, если платеж не удался. Напишите тест,
//   используя Mockito, чтобы проверить, что при выбрасывании исключения
//   PaymentFailedException в нужных случаях, метод processPayment был обработан правильно.
   @Test
   public void makeOrderEx(){
      //  мокируем только paymentGateway, тк нужно проверить, что он выкидывает нужный эксепшн
      OrderService orderService1 = new OrderService(paymentGateway, new InventoryManager());
      // задаем поведение - имитация выбрасывания исключения
      Mockito.when(paymentGateway.processPayment(Mockito.anyDouble())).thenThrow(PaymentFailedException.class);

      Assertions.assertThrows(PaymentFailedException.class, () -> orderService1.makeOrder("Product", 10, 10));




   }


}

/**
 * PaymentGateway.java
 *
 * @author Sergii Kozhushko, sergiikozhushko@gmail.com
 * Date of creation: 17-May-2023 23:09
 */

package egorov.mockito.order;

public class PaymentGateway {
   public boolean processPayment(double amount){
      boolean isDBAvailable = true;//флаг имитирует работу с БД
      if (!isDBAvailable){
          throw new PaymentFailedException("DB is not available");
      }
      if (amount > 100){
         return true;
      }
      else {
         return false;
      }
   }

}

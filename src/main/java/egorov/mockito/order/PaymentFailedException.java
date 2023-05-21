/**
 * PaymentFailedException.java
 *
 * @author Sergii Kozhushko, sergiikozhushko@gmail.com
 * Date of creation: 21-May-2023 13:43
 */

package egorov.mockito.order;

public class PaymentFailedException extends RuntimeException{
   public PaymentFailedException(String message) {
      super(message);
   }
}

/**
 * PaymentService.java
 *
 * @author Sergii Kozhushko, sergiikozhushko@gmail.com
 * Date of creation: 17-May-2023 23:09
 */

package egorov.mockito.order;

public class PaymentService {

   private PaymentGateway paymentGateway;

   public PaymentService(PaymentGateway paymentGateway) {
      this.paymentGateway = paymentGateway;
   }

   public boolean makePayment(double amount){
      return paymentGateway.processPayment(amount);
   }

   public PaymentGateway getPaymentGateway() {
      return paymentGateway;
   }
}

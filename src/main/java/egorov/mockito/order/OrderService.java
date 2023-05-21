/**
 * OrderService.java
 *
 * @author Sergii Kozhushko, sergiikozhushko@gmail.com
 * Date of creation: 18-May-2023 16:05
 */

package egorov.mockito.order;

public class OrderService {
   PaymentGateway paymentGateway;
   InventoryManager inventoryManager;

   public OrderService(PaymentGateway paymentGateway, InventoryManager inventoryManager) {
      this.paymentGateway = paymentGateway;
      this.inventoryManager = inventoryManager;
   }

   public void makeOrder(String item, int quantity, double sum){
      if (inventoryManager.checkStock(item, quantity)){
         paymentGateway.processPayment(sum);
         System.out.println("Successfully created order: " + item +
                 " x " + quantity + ". $" + sum);
      }
   }


}

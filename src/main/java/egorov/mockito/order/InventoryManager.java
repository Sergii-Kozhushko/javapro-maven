/**
 * InventoryManager.java
 *
 * @author Sergii Kozhushko, sergiikozhushko@gmail.com
 * Date of creation: 18-May-2023 15:50
 */

package egorov.mockito.order;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public class InventoryManager {
   ProductRepository productRepository;

   public boolean checkStock(String item, int quantity){
      return true;
//      if (item.equals("Book") && quantity == 10){
//         return true;
//      }
//      return false;
   }

   public void updateStock(String productId, int quantity){
      ProductEntity product = productRepository.getProductById(productId);
      if (product == null){
         return;
      }
      product.setQuantity(quantity);
      productRepository.saveToDB(product);

      System.out.println("Here we updated product in InventoryManager '" + product.getName() + "' to quantity: " + quantity);
   }

}

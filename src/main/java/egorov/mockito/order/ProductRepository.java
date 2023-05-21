/**
 * ProductRepository.java
 *
 * @author Sergii Kozhushko, sergiikozhushko@gmail.com
 * Date of creation: 21-May-2023 12:04
 */

package egorov.mockito.order;

public class ProductRepository {
   public ProductEntity getProductById(String productId){
      return new ProductEntity("Some product", 10, 1);
   }
   public void saveToDB(ProductEntity productEntity){
      System.out.println("Save product to DB");
   }

}

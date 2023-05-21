/**
 * ProductService.java
 *
 * @author Sergii Kozhushko, sergiikozhushko@gmail.com
 * Date of creation: 21-May-2023 12:03
 */

package egorov.mockito.order;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ProductService {
    private ProductRepository productRepository;
    private InventoryManager inventoryManager;


    public void updateProduct(String productId, int quantity){
        if (productRepository.getProductById(productId) == null){
            return;
        }
        inventoryManager.updateStock(productId, quantity);
    }


}

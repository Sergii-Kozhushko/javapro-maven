package egorov.mockito.order;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

/*
Создайте класс ProductService, который зависит от классов ProductRepository и InventoryManager.
        ProductRepository содержит метод getProductById(String productId),
        возвращающий объект Product по идентификатору продукта.
        InventoryManager содержит метод updateStock(String productId, int quantity),
        обновляющий количество товара на складе.
        Напишите тест, используя Mockito, чтобы убедиться, что методы getProductById и
        updateStock были вызваны с правильными аргументами
*/
@ExtendWith(MockitoExtension.class)
class ProductServiceTest {
    @Mock
    private ProductRepository productRepository;
    @Test
    public void updateStock() {
        InventoryManager inventoryManager = Mockito.mock(InventoryManager.class);
        ProductService productService = new ProductService(productRepository, inventoryManager);
        Mockito.doReturn(new ProductEntity("Product name", 10, 10))
                .when(productRepository).getProductById("67");
        productService.updateProduct("67", 20);
        Mockito.verify(productRepository).getProductById("67");
        Mockito.verify(inventoryManager).updateStock("67", 20);
    }
}
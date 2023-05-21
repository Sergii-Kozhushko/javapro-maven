/**
 * ProductEnity.java
 *
 * @author Sergii Kozhushko, sergiikozhushko@gmail.com
 * Date of creation: 21-May-2023 12:04
 */

package egorov.mockito.order;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ProductEntity {
    private String name;
    private int price;
    private int quantity;

}

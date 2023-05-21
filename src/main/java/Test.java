/**
 * Test.java
 *
 * @author Sergii Kozhushko, sergiikozhushko@gmail.com
 * Date of creation: 21-May-2023 20:12
 */

public class Test {

    public static void main(String[] args) {
        String fileName = "icon.png";
        System.out.println(fileName);
        System.out.println(new Test().getClass().getResource(fileName));
        System.out.println(new Test().getClass().getClassLoader().getResource(fileName));
    }


}

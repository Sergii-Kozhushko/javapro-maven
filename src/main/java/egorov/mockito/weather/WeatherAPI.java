/**
 * WeatherAPI.java
 *
 * @author Sergii Kozhushko, sergiikozhushko@gmail.com
 * Date of creation: 18-May-2023 15:29
 */

package egorov.mockito.weather;

public class WeatherAPI {
   public int getTemperature(String city){
      System.out.println("Provide temperature for city " + city);
      return 10;
   }

}

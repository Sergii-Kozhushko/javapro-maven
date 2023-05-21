/**
 * WeatherService.java
 *
 * @author Sergii Kozhushko, sergiikozhushko@gmail.com
 * Date of creation: 18-May-2023 15:42
 */

package egorov.mockito.weather;

public class WeatherService {
   WeatherAPI weatherAPI;

   public WeatherService(WeatherAPI weatherAPI) {
      this.weatherAPI = weatherAPI;
   }

   public int getTemperatureCity(String city){
      return weatherAPI.getTemperature(city);
   }

}

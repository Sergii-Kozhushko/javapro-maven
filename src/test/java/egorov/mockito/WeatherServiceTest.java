package egorov.mockito;

import egorov.mockito.weather.WeatherAPI;
import egorov.mockito.weather.WeatherService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

/**
 * egorov.mockito.WeatherServiceTest.java
 *
 * @author Sergii Kozhushko, sergiikozhushko@gmail.com
 * Date of creation: 18-May-2023 15:44
 */

@ExtendWith(MockitoExtension.class)
public class WeatherServiceTest {
   @Mock
   WeatherAPI weatherAPI;

   @Test
   public void serviceTest(){
      WeatherService weatherService = new WeatherService(weatherAPI);
      String city = "Kyiv";
      weatherService.getTemperatureCity(city);
      Mockito.verify(weatherAPI).getTemperature(city);
   }

}

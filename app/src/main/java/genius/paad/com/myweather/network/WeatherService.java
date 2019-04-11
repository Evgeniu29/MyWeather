package genius.paad.com.myweather.network;

import genius.paad.com.myweather.currentModel.Weather;
import genius.paad.com.myweather.forecastModel.WeatherForecast;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;


public interface WeatherService {

    @GET("weather?")
    Call<Weather> getCurrentWeather(
            @Query("q") String townName,

            @Query("units") String metric,

            @Query("appid") String key);



    @GET("forecast/daily?")
    Call<WeatherForecast> getForecast(
            @Query("q") String townName,
            @Query("units") String metric,
            @Query("appid") String key,
            @Query("cnt") String number);



}

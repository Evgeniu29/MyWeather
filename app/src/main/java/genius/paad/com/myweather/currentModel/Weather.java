package genius.paad.com.myweather.currentModel;


import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by son on 31.05.2016.
 *
 *
 */
public class Weather {



    @SerializedName("weather")
    public ArrayList<DescriptionWeather> descriptionWeather = new ArrayList<DescriptionWeather>();

    @SerializedName("main")
    public MainWeather main;

    @SerializedName("wind")
    public WindWeather wind;


    @SerializedName("sys")
    public SysWeather sys;

    @SerializedName("name")
    private String cityName;





    public ArrayList<DescriptionWeather> getDescriptionWeather() {
        return descriptionWeather;
    }


    public MainWeather getMain() {
        return main;
    }

    public WindWeather getWind() {
        return wind;
    }

    public SysWeather getSys() {
        return sys;
    }

    public String getCityName() {
        return cityName;
    }
}


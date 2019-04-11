package genius.paad.com.myweather.forecastModel;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

import genius.paad.com.myweather.forecastModel.descriptionDay;
import genius.paad.com.myweather.forecastModel.tempDay;

public class Day {


    @SerializedName("weather")
    public ArrayList<genius.paad.com.myweather.forecastModel.descriptionDay> descriptionDay = new ArrayList<descriptionDay>();

    @SerializedName("temp")
    public genius.paad.com.myweather.forecastModel.tempDay tempDay;


    @SerializedName("pressure")


    public float pressure;

    @SerializedName("humidity")


    public float humidity;

    @SerializedName("speed")


    public float speed;



    @SerializedName("dt")
    public long dt;



    public ArrayList<descriptionDay> getDescriptionWeather() {
        return descriptionDay;
    }


    public tempDay getTemp() {
        return tempDay;
    }

    public float getPressure() {
        return pressure;
    }

    public float getHumidity() {
        return humidity;
    }

    public float getSpeed() {
        return speed;
    }

    public long getDt() {
        return dt;
    }

}

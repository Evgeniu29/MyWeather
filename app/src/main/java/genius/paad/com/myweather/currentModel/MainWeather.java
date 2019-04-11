package genius.paad.com.myweather.currentModel;

import com.google.gson.annotations.SerializedName;

public class MainWeather {

    @SerializedName("temp")

    public float temp;


    @SerializedName("pressure")


    public float pressure;

    @SerializedName("humidity")


    public float humidity;

    @SerializedName("temp_min")
    public float minTemp;

    @SerializedName("temp_max")
    public  float maxTemp;

    public float getTemp() {
        return temp;
    }

    public float getPressure() {
        return pressure;
    }

    public float getHumidity() {
        return humidity;
    }

    public float getMinTemp() {
        return minTemp;
    }

    public float getMaxTemp() {
        return maxTemp;
    }

}

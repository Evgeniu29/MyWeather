package genius.paad.com.myweather.currentModel;

import com.google.gson.annotations.SerializedName;

public class SysWeather {

    @SerializedName("sunset")

    public long sunset;


    @SerializedName("sunrise")


    public long sunrise;



    public long getSunset() {
        return sunset;
    }

    public long getSunrise() {
        return sunrise;
    }


}

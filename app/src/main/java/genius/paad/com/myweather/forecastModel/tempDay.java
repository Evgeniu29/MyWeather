package genius.paad.com.myweather.forecastModel;

import com.google.gson.annotations.SerializedName;

public class tempDay {

    @SerializedName("day")

    public float day;


    @SerializedName("min")
    public float min;

    @SerializedName("max")
    public  float max;

    public float getDay() {
        return day;
    }



    public float getMin() {
        return min;
    }

    public float getMax() {
        return max;
    }

}



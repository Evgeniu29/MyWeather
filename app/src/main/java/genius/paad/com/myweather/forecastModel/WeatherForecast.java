package genius.paad.com.myweather.forecastModel;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class WeatherForecast {
        @SerializedName("list")
        public ArrayList<Day> days = new ArrayList<>();



        public ArrayList<Day> getDays() {
            return days;
        }
    }


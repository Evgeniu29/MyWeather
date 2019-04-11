package genius.paad.com.myweather;

import android.content.Context;

public class WeatherInfo {

    private Context context;

    public WeatherInfo(Context current){
        this.context = current;
    }


    public String getWeatherInfo(String description) {

        if (description.contains("sky is clear") || description.contains("clear sky")) {

            description = context.getResources().getString(R.string.clear_sky);

        }


        if (description.contains("few clouds")) {

            description = context.getResources().getString(R.string.few_clouds);

        }

        if (description.contains("scattered clouds")) {

            description = context.getResources().getString(R.string.scattered_clouds);

        }

        if (description.contains("broken clouds") || description.contains("overcast clouds")){

            description = context.getResources().getString(R.string.broken_clouds);

        }

        if (description.contains("drizzle") || description.contains("light intensity drizzle")|| description.contains("heavy intensity drizzle")|| description.contains("light intensity drizzle rain")|| description.contains("drizzle rain")|| description.contains("heavy intensity drizzle rain")|| description.contains("shower rain and drizzle")|| description.contains("heavy shower rain and drizzle") || description.contains("shower drizzle")) {

            description = context.getResources().getString(R.string.drizzle);

        }


        if (description.contains("shower rain")) {

            description = context.getResources().getString(R.string.shower_rain);

        }

        if (description.contains("rain") || description.contains("light rain")|| description.contains("moderate rain")|| description.contains("heavy intensity rain")|| description.contains("very heavy rain")|| description.contains("extreme rain")|| description.contains("freezing rain")|| description.contains("light intensity shower rain") || description.contains("heavy intensity shower rain") || description.contains("ragged shower rain")) {

            description = context.getResources().getString(R.string.rain);

        }

        if (description.contains("thunderstorm") || description.contains("thunderstorm with light rain")|| description.contains("thunderstorm with rain")|| description.contains("thunderstorm with heavy rain")|| description.contains("light thunderstorm")|| description.contains("heavy thunderstorm")|| description.contains("ragged thunderstorm")|| description.contains("thunderstorm with light drizzle") || description.contains("thunderstorm with drizzle") || description.contains("thunderstorm with heavy drizzle")) {

            description = context.getResources().getString(R.string.thunderstorm);

        }

        if (description.contains("snow")  || description.contains("light snow")|| description.contains("heavy snow")|| description.contains("sleet")|| description.contains("shower sleet")|| description.contains("light rain and snow")|| description.contains("rain and snow")|| description.contains("light shower snow") || description.contains("shower snow") || description.contains("heavy shower snow")) {

            description = context.getResources().getString(R.string.snow);

        }


        if (description.contains("mist") || description.contains("smoke")|| description.contains("haze")|| description.contains("dust")|| description.contains("fog")|| description.contains("sand, dust whirls")|| description.contains("sand")|| description.contains("volcanic ash") || description.contains("squalls") || description.contains("tornado")) {

            description = context.getResources().getString(R.string.mist);

        }

        return description;


    }


}

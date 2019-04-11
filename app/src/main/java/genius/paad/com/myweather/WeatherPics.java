package genius.paad.com.myweather;

import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.Drawable;

import genius.paad.com.myweather.currentModel.Weather;

public class WeatherPics {

    MainActivity mainActivity;

    Weather weather;


    WeatherPics(MainActivity mainActivity, Weather weather) {

        this.mainActivity = mainActivity;

        this.weather = weather;
    }

    void getWeatherPics(Weather weather) {

        Long sunriseTime = weather.sys.getSunrise();

        Long sunsetTime = weather.sys.getSunset();

        long javasunsetTime = sunsetTime * 1000L;

        long javasunriseTime = sunriseTime * 1000L;

        long now = System.currentTimeMillis();

        if ((Long) javasunsetTime == null && (Long) javasunriseTime == null) {
            return;
        }

        if (now > javasunriseTime && now < javasunsetTime) {
            //set your background here
            analizeWeatherDay(weather.descriptionWeather.get(0).getDescription());
        } else {
            //set your background here
            analizeWeatherNight(weather.descriptionWeather.get(0).getDescription());
        }
    }

    void analizeWeatherDay(String weatherDescription) {

        setDayColor();

        Drawable drawable = null;

        Resources res = mainActivity.getResources();

        if (weatherDescription.contains("clear sky") || weatherDescription.contains("sky is clear") ) {

            drawable = res.getDrawable(R.drawable.sun);


        }
        if (weatherDescription.contains("drizzle") || weatherDescription.contains("light intensity drizzle") || weatherDescription.contains("heavy intensity drizzle") || weatherDescription.contains("light intensity drizzle rain") || weatherDescription.contains("drizzle rain") || weatherDescription.contains("heavy intensity drizzle rain") || weatherDescription.contains("shower rain and drizzle") || weatherDescription.contains("heavy shower rain and drizzle") || weatherDescription.contains("shower drizzle")) {


            drawable = res.getDrawable(R.drawable.drizzle);


        }

        if (weatherDescription.contains("rain") || weatherDescription.contains("snower rain") || weatherDescription.contains("light rain") || weatherDescription.contains("moderate rain") || weatherDescription.contains("heavy intensity rain") || weatherDescription.contains("very heavy rain") || weatherDescription.contains("extreme rain") || weatherDescription.contains("freezing rain") || weatherDescription.contains("light intensity shower rain") || weatherDescription.contains("heavy intensity shower rain") || weatherDescription.contains("ragged shower rain")) {

            drawable = res.getDrawable(R.drawable.rain);

        }

        if (weatherDescription.contains("few clouds") || weatherDescription.contains("scattered clouds") || weatherDescription.contains("broken clouds") || weatherDescription.contains("overcast clouds")) {

            drawable = res.getDrawable(R.drawable.cloud);

        }

        if (weatherDescription.contains("snow") || weatherDescription.contains("light snow") || weatherDescription.contains("heavy snow") || weatherDescription.contains("sleet") || weatherDescription.contains("shower sleet") || weatherDescription.contains("light rain and snow") || weatherDescription.contains("rain and snow") || weatherDescription.contains("light shower snow") || weatherDescription.contains("shower snow") || weatherDescription.contains("heavy shower snow")) {

            drawable = res.getDrawable(R.drawable.snow);

        }

        if (weatherDescription.contains("thunderstorm") || weatherDescription.contains("thunderstorm with light rain") || weatherDescription.contains("thunderstorm with rain") || weatherDescription.contains("thunderstorm with heavy rain") || weatherDescription.contains("light thunderstorm") || weatherDescription.contains("heavy thunderstorm") || weatherDescription.contains("ragged thunderstorm") || weatherDescription.contains("thunderstorm with light drizzle") || weatherDescription.contains("thunderstorm with drizzle") || weatherDescription.contains("thunderstorm with heavy drizzle")) {


            drawable = res.getDrawable(R.drawable.thunderstorm);

        }

        if (weatherDescription.contains("mist") || weatherDescription.contains("smoke") || weatherDescription.contains("haze") || weatherDescription.contains("dust") || weatherDescription.contains("fog") || weatherDescription.contains("sand, dust whirls") || weatherDescription.contains("sand") || weatherDescription.contains("volcanic ash") || weatherDescription.contains("squalls") || weatherDescription.contains("tornado")) {
            drawable = res.getDrawable(R.drawable.mist);

        }

        this.mainActivity.fone.setBackground(drawable);

    }


    void analizeWeatherNight(String weatherDescription) {

        setNightColor();

        Drawable drawable = null;


        Resources res = this.mainActivity.getResources();

        if (weatherDescription.contains("clear sky") || weatherDescription.contains("sky is clear")) {

            drawable = res.getDrawable(R.drawable.night_clear);


        }
        if (weatherDescription.contains("drizzle") || weatherDescription.contains("light intensity drizzle") || weatherDescription.contains("heavy intensity drizzle") || weatherDescription.contains("light intensity drizzle rain") || weatherDescription.contains("drizzle rain") || weatherDescription.contains("heavy intensity drizzle rain") || weatherDescription.contains("shower rain and drizzle") || weatherDescription.contains("heavy shower rain and drizzle") || weatherDescription.contains("shower drizzle")) {


            drawable = res.getDrawable(R.drawable.night_drizzle);

        }

        if (weatherDescription.contains("rain") || weatherDescription.contains("snower rain") || weatherDescription.contains("light rain") || weatherDescription.contains("moderate rain") || weatherDescription.contains("heavy intensity rain") || weatherDescription.contains("very heavy rain") || weatherDescription.contains("extreme rain") || weatherDescription.contains("freezing rain") || weatherDescription.contains("light intensity shower rain") || weatherDescription.contains("heavy intensity shower rain") || weatherDescription.contains("ragged shower rain")) {

            drawable = res.getDrawable(R.drawable.night_rain);

        }

        if (weatherDescription.contains("few clouds") || weatherDescription.contains("scattered clouds") || weatherDescription.contains("broken clouds") || weatherDescription.contains("overcast clouds")) {

           drawable = res.getDrawable(R.drawable.night_cloud);

        }

        if (weatherDescription.contains("snow") || weatherDescription.contains("light snow") || weatherDescription.contains("heavy snow") || weatherDescription.contains("sleet") || weatherDescription.contains("shower sleet") || weatherDescription.contains("light rain and snow") || weatherDescription.contains("rain and snow") || weatherDescription.contains("light shower snow") || weatherDescription.contains("shower snow") || weatherDescription.contains("heavy shower snow")) {

            drawable = res.getDrawable(R.drawable.night_snow);

        }

        if (weatherDescription.contains("thunderstorm") || weatherDescription.contains("thunderstorm with light rain") || weatherDescription.contains("thunderstorm with rain") || weatherDescription.contains("thunderstorm with heavy rain") || weatherDescription.contains("light thunderstorm") || weatherDescription.contains("heavy thunderstorm") || weatherDescription.contains("ragged thunderstorm") || weatherDescription.contains("thunderstorm with light drizzle") || weatherDescription.contains("thunderstorm with drizzle") || weatherDescription.contains("thunderstorm with heavy drizzle")) {

            drawable = res.getDrawable(R.drawable.night_thunderstorm);

        }


        if (weatherDescription.contains("mist") || weatherDescription.contains("smoke") || weatherDescription.contains("haze") || weatherDescription.contains("dust") || weatherDescription.contains("fog") || weatherDescription.contains("sand, dust whirls") || weatherDescription.contains("sand") || weatherDescription.contains("volcanic ash") || weatherDescription.contains("squalls") || weatherDescription.contains("tornado")) {
            drawable = res.getDrawable(R.drawable.mist);

        }

        this.mainActivity.fone.setBackground(drawable);



    }


    private void setDayColor() {
        this.mainActivity.cityText.setTextColor(Color.parseColor("#000000"));

        this.mainActivity.description.setTextColor(Color.parseColor("#000000"));


        this.mainActivity.average.setTextColor(Color.parseColor("#000000"));


        this.mainActivity.max.setTextColor(Color.parseColor("#000000"));


        this.mainActivity.min.setTextColor(Color.parseColor("#000000"));


        this.mainActivity.wind.setTextColor(Color.parseColor("#000000"));

        this.mainActivity.pressure.setTextColor(Color.parseColor("#000000"));

        this.mainActivity.humidity.setTextColor(Color.parseColor("#000000"));
    }

    private void setNightColor() {
        this.mainActivity.cityText.setTextColor(Color.parseColor("#FFFFFF"));

        this.mainActivity.description.setTextColor(Color.parseColor("#FFFFFF"));


        this.mainActivity.average.setTextColor(Color.parseColor("#FFFFFF"));


        this.mainActivity.max.setTextColor(Color.parseColor("#FFFFFF"));


        this.mainActivity.min.setTextColor(Color.parseColor("#FFFFFF"));


        this.mainActivity.wind.setTextColor(Color.parseColor("#FFFFFF"));

        this.mainActivity.pressure.setTextColor(Color.parseColor("#FFFFFF"));

        this.mainActivity.humidity.setTextColor(Color.parseColor("#FFFFFF"));

    }


}

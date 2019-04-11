package genius.paad.com.myweather;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import genius.paad.com.myweather.forecastModel.Day;

import static genius.paad.com.myweather.R.*;

public class WeatherAdapter extends RecyclerView.Adapter<WeatherAdapter.ViewHolder> {



    private ArrayList<Day> days;

    String imageUrl = "http://openweathermap.org/img/w/";

    Context context;





    public WeatherAdapter(Context context, ArrayList<Day> days) {
        this.context = context;
        this.days = days;

    }


    @Override public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(layout.item, parent, false);

        return new ViewHolder(v);

    }


    @Override public void onBindViewHolder(ViewHolder holder, int position) {

        String grad = "ºC";

        Day day = days.get(position);


        Picasso.with(holder.image.getContext()).load(imageUrl + day.descriptionDay.get(0).getIcon() + ".png")
                .into(holder.image);

        holder.description.setText(new WeatherInfo(context).getWeatherInfo(day.descriptionDay.get(0).getDescription()));


        holder.average.setText(day.tempDay.getDay() + " " + context.getResources().getString(string.grad) );


        holder.max.setText(context.getResources().getString(string.max)+ " " +  Math.round(day.tempDay.getMax()) + " " + context.getResources().getString(string.grad));


        holder.min.setText(context.getResources().getString(string.min)+ " " + Math.round(day.tempDay.getMin()) + " " + context.getResources().getString(string.grad));


        holder.wind.setText(context.getResources().getString(string.wind) + " "  + day.getSpeed()+ " " + context.getResources().getString(string.ms));

        holder.pressure.setText(context.getResources().getString(string.pressure) + " " + day.getPressure()+ " " + context.getResources().getString(string.mmHg));

        holder.humidity.setText(context.getResources().getString(string.humidity) + " " +  day.getHumidity() + " " +  context.getResources().getString(string.percent));

        long unixSeconds  = Long.valueOf(day.getDt());

        Date transformedDate = new Date(unixSeconds * 1000);

        SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd");

        SimpleDateFormat time = new SimpleDateFormat("HH:mm:ss");

        String formattedDate =     date.format(transformedDate).toString();


        int hours = transformedDate.getHours();


        String formattedTime =time.format(transformedDate).toString();




                    if (hours>=8 && hours<20 ) {

                        Drawable drawable = analizeWeatherDay(day.descriptionDay.get(0).getDescription());

                        holder.itemView.setBackground(drawable );





                        setDayColor(holder);



                    }

                    else {
                        Drawable drawable = analizeWeatherNight(day.descriptionDay.get(0).getDescription());

                        holder.itemView.setBackground(drawable);

                        setNightColor(holder);



                    }



        holder.date.setText(formattedDate);

        holder.time.setText(formattedTime);

        holder.itemView.setTag(day);


    }



    @Override public int getItemCount() {

        return days.size();

    }



    public static class ViewHolder extends RecyclerView.ViewHolder {

        public ImageView image, fone;

        TextView description, average, max, min, wind, pressure, humidity, date, time;


        public ViewHolder(View itemView) {

            super(itemView);

            image = (ImageView) itemView.findViewById(id.image);

            fone = (ImageView) itemView.findViewById(id.fone);

            average = (TextView) itemView.findViewById(id.average);

            description = (TextView) itemView.findViewById(id.description);

            min = (TextView) itemView.findViewById(id.min);

            max = (TextView) itemView.findViewById(id.max);

            wind = (TextView) itemView.findViewById(id.wind);
            pressure = (TextView) itemView.findViewById(id.pressure);
            humidity = (TextView) itemView.findViewById(id.humidity);

            date = (TextView) itemView.findViewById(id.date);

            time = (TextView) itemView.findViewById(id.time);

        }

    }

    Drawable analizeWeatherDay(String weatherDescription) {

        Resources res = context.getResources();

        Drawable drawable = null;

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

        return  drawable;




    }


    Drawable  analizeWeatherNight(String weatherDescription) {

        Drawable drawable = null;


        Resources res = context.getResources();

        if (weatherDescription.contains("clear sky") || weatherDescription.contains("sky is clear") ) {

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
            drawable = res.getDrawable(R.drawable.mist_night);

        }

        return  drawable;


    }

    private void setDayColor(ViewHolder viewHolder) {

        viewHolder.description.setTextColor(Color.parseColor("#000000"));


        viewHolder.average.setTextColor(Color.parseColor("#000000"));


        viewHolder.max.setTextColor(Color.parseColor("#000000"));


        viewHolder.min.setTextColor(Color.parseColor("#000000"));


        viewHolder.wind.setTextColor(Color.parseColor("#000000"));

        viewHolder.pressure.setTextColor(Color.parseColor("#000000"));

        viewHolder.humidity.setTextColor(Color.parseColor("#000000"));

        viewHolder.time.setTextColor(Color.parseColor("#000000"));

        viewHolder.date.setTextColor(Color.parseColor("#000000"));
    }


    private void setNightColor(ViewHolder viewHolder) {

        viewHolder.description.setTextColor(Color.parseColor("#ffffff"));


        viewHolder.average.setTextColor(Color.parseColor("#ffffff"));


        viewHolder.max.setTextColor(Color.parseColor("#ffffff"));


        viewHolder.min.setTextColor(Color.parseColor("#ffffff"));


        viewHolder.wind.setTextColor(Color.parseColor("#ffffff"));

        viewHolder.pressure.setTextColor(Color.parseColor("#ffffff"));

        viewHolder.humidity.setTextColor(Color.parseColor("#ffffff"));

        viewHolder.time.setTextColor(Color.parseColor("#ffffff"));

        viewHolder.date.setTextColor(Color.parseColor("#ffffff"));
    }


}
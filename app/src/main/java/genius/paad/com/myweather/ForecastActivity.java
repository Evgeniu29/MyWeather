package genius.paad.com.myweather;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import java.util.ArrayList;

import genius.paad.com.myweather.forecastModel.Day;
import genius.paad.com.myweather.forecastModel.WeatherForecast;
import genius.paad.com.myweather.fragments.MyOfflineFragment;
import genius.paad.com.myweather.fragments.MyWarningFragment;
import genius.paad.com.myweather.network.WeatherService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ForecastActivity extends AppCompatActivity {


    RecyclerView recyclerView;
    WeatherAdapter adapter;
    String reformattedStr;
    String template = "http://api.openweathermap.org/data/2.5/";
    String metric = "metric";
    String key = "fea8931edad2b0eb4688fdf3ac9d7008";

    public android.support.v4.app.FragmentManager fragmentManager;




    String townName;

    String number;


    WeatherForecast weatherForecast;



    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.forecastactivity);

        recyclerView = (RecyclerView) findViewById(R.id.list);


        townName = getIntent().getExtras().get("one").toString();

        number = getIntent().getExtras().get("two").toString();



        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(llm);



        RecyclerViewMargin decoration = new RecyclerViewMargin(10, 7);
        recyclerView.addItemDecoration(decoration);

        connect(townName, number);

    }

    public void connect(String townName, String number) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(template)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        WeatherService service = retrofit.create(WeatherService.class);
        Call<WeatherForecast> call = service.getForecast(townName, metric, key, number);
        call.enqueue(new Callback<WeatherForecast>() {
            @Override
            public void onResponse(@NonNull Call<WeatherForecast> call, @NonNull Response<WeatherForecast> response) {
                //Log.d(TAG,response.toString());

                if (response.code() == 200) {
                    weatherForecast = response.body();

                    ArrayList<Day> days = new ArrayList<>();

                    days = weatherForecast.getDays();

                    adapter = new WeatherAdapter(getApplicationContext(), days);
                    recyclerView.setAdapter(adapter);


                }

                else {
                    fragmentManager = getSupportFragmentManager();
                    MyWarningFragment newFragment = new MyWarningFragment();

                    newFragment.show(fragmentManager, "fragment");

                }

            }

            @Override
            public void onFailure(Call<WeatherForecast> call, Throwable t) {

                fragmentManager = getSupportFragmentManager();
                MyOfflineFragment newFragment = new MyOfflineFragment();

                newFragment.show(fragmentManager, "fragment");
            }
        });

    }


}





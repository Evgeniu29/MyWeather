package genius.paad.com.myweather;


import android.content.Context;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.net.wifi.WifiManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.getbase.floatingactionbutton.FloatingActionButton;
import com.getbase.floatingactionbutton.FloatingActionsMenu;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.places.AutocompleteFilter;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlaceAutocompleteFragment;
import com.google.android.gms.location.places.ui.PlaceSelectionListener;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.tasks.Task;
import com.google.firebase.appindexing.FirebaseAppIndex;
import com.google.firebase.appindexing.FirebaseUserActions;
import com.google.firebase.appindexing.builders.Actions;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Timer;

import genius.paad.com.myweather.currentModel.Weather;
import genius.paad.com.myweather.fragments.MyOfflineFragment;
import genius.paad.com.myweather.fragments.MyWarningFragment;
import genius.paad.com.myweather.network.WeatherService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;



public class MainActivity extends AppCompatActivity {
    private Handler handler = new Handler();


    private static final int GOOGLE_API_CLIENT_ID = 0;
    private AutoCompleteTextView mAutocompleteTextView;
    private TextView mNameView;
    String text;
    public FragmentManager fragmentManager;


    ImageView image;
    String weatherUrl;
    String template = "http://api.openweathermap.org/data/2.5/";
    String key = "fea8931edad2b0eb4688fdf3ac9d7008";
    String imageUrl = "http://openweathermap.org/img/w/";
    ArrayList<Weather> weatherList = new ArrayList<Weather>();
    public String townName;
    static Timer timer;
    String metric = "metric";
    String code;
    Integer count = 1;
    String grad;

    ArrayList<Weather> weathers = new ArrayList<Weather>();


    private static final String TAG = "myLogs";
    private int progressStatus = 0;
    String sunrise;
    String sunset;


    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    PlaceAutocompleteFragment places;
    ProgressBar simpleProgressBar;
    private Spinner spinner;
    Button delete;

    public TextView cityText, description, average, max, min, wind, pressure, humidity;
    public String cityLocale;
    WifiManager wm;
    ImageView fone;

    TextView tvTemp;
    ImageView tvImage;


    Boolean isFABOpen = true;

    String number;

    FloatingActionButton actionA, actionB, actionC;
    FloatingActionsMenu menuMultipleActions;
    Weather weather = new Weather();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        wm = (WifiManager) getApplicationContext().getSystemService(WIFI_SERVICE);


        menuMultipleActions = (FloatingActionsMenu) findViewById(R.id.multiple_actions);


        actionA = (FloatingActionButton) menuMultipleActions.findViewById(R.id.action_a);
        actionA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this, ForecastActivity.class);

                if (townName != null) {

                    number = "3";


                    intent.putExtra("one", townName);

                    intent.putExtra("two", number);

                    startActivity(intent);
                } else {

                }

            }
        });


        actionB = (FloatingActionButton) menuMultipleActions.findViewById(R.id.action_b);
        actionB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this, ForecastActivity.class);

                if (townName != null) {

                    number = "5";


                    intent.putExtra("one", townName);

                    intent.putExtra("two", number);

                    startActivity(intent);
                } else {



                }

            }
        });


        actionC = (FloatingActionButton) menuMultipleActions.findViewById(R.id.action_c);
        actionC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this, ForecastActivity.class);

                if (townName != null) {

                    number = "7";


                    intent.putExtra("one", townName);

                    intent.putExtra("two", number);

                    startActivity(intent);
                } else {

                }

            }
        });

        cityText = (TextView) findViewById(R.id.cityText);

        average = (TextView) findViewById(R.id.average);

        image = (ImageView) findViewById(R.id.image);

        fone = (ImageView) findViewById(R.id.fone);

        description = (TextView) findViewById(R.id.description);

        min = (TextView) findViewById(R.id.min);

        max = (TextView) findViewById(R.id.max);

        wind = (TextView) findViewById(R.id.wind);
        pressure = (TextView) findViewById(R.id.pressure);
        humidity = (TextView) findViewById(R.id.humidity);

        simpleProgressBar = (ProgressBar) findViewById(R.id.progress);


        PlaceAutocompleteFragment places = (PlaceAutocompleteFragment)
                getFragmentManager().findFragmentById(R.id.place_autocomplete_fragment);


        AutocompleteFilter typeFilter = new AutocompleteFilter.Builder()
                .setTypeFilter(AutocompleteFilter.TYPE_FILTER_CITIES)
                .build();

        places.setFilter(typeFilter);
        places.setOnPlaceSelectedListener(new

                                                  PlaceSelectionListener() {
                                                      @Override
                                                      public void onPlaceSelected(Place place) {
                                                          LatLng coordinates = place.getLatLng();
                                                          Geocoder geocoder = new Geocoder(getApplicationContext(), Locale.getDefault());

                                                          List<Address> addresses; // Only retrieve 1 address
                                                          addresses = null;
                                                          try {
                                                              addresses = geocoder.getFromLocation(
                                                                      coordinates.latitude,
                                                                      coordinates.longitude, 1);
                                                          } catch (IOException e) {
                                                              e.printStackTrace();
                                                          }

                                                          try {
                                                              Address address = addresses.get(0);
                                                              code = address.getCountryCode();
                                                              townName = address.getLocality();
                                                              connect();

                                                          } catch (NullPointerException e) {
                                                              return;
                                                          } catch (Exception e) {
                                                              return;
                                                          }

                                                      }


                                                      @Override
                                                      public void onError(Status status) {

                                                          Toast.makeText(getApplicationContext(), status.toString(), Toast.LENGTH_SHORT).show();

                                                      }
                                                  });

    }


        class MyTask extends AsyncTask<Integer, Integer, String> {
            @Override
            protected String doInBackground(Integer... params) {
                for (; count <= params[0]; count++) {
                    try {
                        Thread.sleep(10);
                        publishProgress(count + 10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                return "Progress update";
            }


            @Override
            protected void onPostExecute(String result) {
                simpleProgressBar.setVisibility(View.GONE);

            }


            @Override
            protected void onPreExecute() {


            }

            @Override
            protected void onProgressUpdate(Integer... values) {

                simpleProgressBar.setProgress(values[0]);
            }
        }


        public void connect () {
            if (wm.isWifiEnabled()) {

                if (townName != null) {

                    Retrofit retrofit = new Retrofit.Builder()
                            .baseUrl(template)
                            .addConverterFactory(GsonConverterFactory.create())
                            .build();
                    WeatherService service = retrofit.create(WeatherService.class);
                    Call<Weather> call = service.getCurrentWeather(townName, metric, key);
                    call.enqueue(new Callback<Weather>() {
                        @Override
                        public void onResponse(@NonNull Call<Weather> call, @NonNull Response<Weather> response) {
                            Log.e(TAG, "onResponse");

                            if (response.code() == 200) {
                                weather = response.body();

                                instantiateWeather(weather, getApplicationContext());


                            }

                            else {
                                addWarningFragment();

                            }


                        }

                        @Override
                        public void onFailure(Call<Weather> call, Throwable t) {

                            Log.e(TAG, "onFailure");
                            Log.e(TAG, t.toString());
                        }
                    });

                }


            } else {

                addOfflineFragment();



            }


        }

        public void instantiateWeather (Weather weather, Context context){

            cityText.setText(townName);

            description.setText(new WeatherInfo(this).getWeatherInfo(weather.getDescriptionWeather().get(0).getDescription()));


            average.setText(Math.round(weather.main.getTemp()) + " " + getResources().getString(R.string.grad));


            max.setText(getResources().getString(R.string.max) + " " + Math.round(weather.main.getMaxTemp()) + " " + getResources().getString(R.string.grad));


            min.setText(getResources().getString(R.string.min) + " " + Math.round(weather.main.getMinTemp()) + " " + getResources().getString(R.string.grad));


            wind.setText(getResources().getString(R.string.wind) + " " + weather.wind.getSpeed() + " " + getResources().getString(R.string.ms));

            pressure.setText(getResources().getString(R.string.pressure) + " " + weather.main.getPressure() + " " + getString(R.string.mmHg));

            humidity.setText(getResources().getString(R.string.humidity) + " " + weather.main.getHumidity() + " " + getString(R.string.percent));


            Picasso.with(getApplicationContext())
                    .load(imageUrl + weather.descriptionWeather.get(0).getIcon() + ".png")
                    .into(this.image);

            WeatherPics weatherPics = new WeatherPics(this, weather);

            weatherPics.getWeatherPics(weather);

            count = 0;
            simpleProgressBar.setVisibility(View.VISIBLE);
            simpleProgressBar.setProgress(0);

            new MyTask().execute(100);

        }


      public   void addOfflineFragment(){
            fragmentManager = getSupportFragmentManager();
            MyOfflineFragment newFragment = new MyOfflineFragment();

            newFragment.show(fragmentManager, "fragment");


            }

    void addWarningFragment(){
        fragmentManager = getSupportFragmentManager();
        MyWarningFragment newFragment = new MyWarningFragment();

        newFragment.show(fragmentManager, "fragment");
    }

    }










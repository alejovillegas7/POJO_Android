package co.com.compumovil.examplepojo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import co.com.compumovil.examplepojo.POJO.Examplepojo;
import co.com.compumovil.examplepojo.POJO.RestInterface;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    TextView city, status, humidity, pressure;
    //String url = "http://api.openweathermap.org/data/2.5";
    public static String BaseUrl = "http://api.openweathermap.org/";
    public static String AppId = "9fee4e93d94baeaf902f8dcb75d5f613";
    private TextView weatherData;


        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);

            city = (TextView) findViewById(R.id.txt_city);
            status = (TextView) findViewById(R.id.txt_status);
            humidity = (TextView) findViewById(R.id.txt_humidity);
            pressure = (TextView) findViewById(R.id.txt_press);

            weatherData = findViewById(R.id.textView);

            findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    getCurrentData();
                }
            });
        }

        void getCurrentData() {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(BaseUrl)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            RestInterface service = retrofit.create(RestInterface.class);
            Call<Examplepojo> call = service.getCurrentWeatherData("medellín", AppId);
            call.enqueue(new Callback<Examplepojo>() {
                @Override
                public void onResponse(@NonNull Call<Examplepojo> call, @NonNull Response<Examplepojo> response) {
                    if (response.code() == 200) {
                        Examplepojo weatherResponse = response.body();
                        assert weatherResponse != null;

                        city.setText("city :"+weatherResponse.getName());
                        status.setText("Status :"+weatherResponse.getWeather().get(0).getDescription());
                        humidity.setText("humidity :"+weatherResponse.getMain().getHumidity().toString());
                        pressure.setText("pressure :"+weatherResponse.getMain().getPressure().toString());


                    }
                }

                @Override
                public void onFailure(@NonNull Call<Examplepojo> call, @NonNull Throwable t) {
                    weatherData.setText(t.getMessage());
                }
            });
        }
    }


package com.example.weatherapi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.service.controls.templates.TemperatureControlTemplate;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.math.RoundingMode;
import java.text.DecimalFormat;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    EditText editText;
    Button searchbutton;
    TextView temp, longitude, latitude, humidity, sunrise, sunset, pressure, wind, max_temp, min_temp, feels;
     ApiInterface apiInterface;
    //SharedPreferences sharedPref ;
    Cursor res;
   // SharedPreferences.Editor editor ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = findViewById(R.id.edittextSearch);
        searchbutton = findViewById(R.id.searchbutton);
        temp = findViewById(R.id.temperature);

        longitude = findViewById(R.id.longitude);
        latitude = findViewById(R.id.latitude);
        humidity = findViewById(R.id.humidity);
        sunrise = findViewById(R.id.sunrise);
        sunset = findViewById(R.id.sunset);
        pressure = findViewById(R.id.pressure);
        wind = findViewById(R.id.wind);
        max_temp = findViewById(R.id.temp_max);
        min_temp = findViewById(R.id.min_temp);
        feels = findViewById(R.id.feels);

        final DatabaseHelper databaseHelper = new DatabaseHelper(this);

        //sharedPref = MainActivity.this.getPreferences(Context.MODE_PRIVATE);
        
        apiInterface = RetrofitInstance.getRetrofit().create(ApiInterface.class);
        final DecimalFormat df = new DecimalFormat("0.00");
        df.setRoundingMode(RoundingMode.DOWN);
        
        searchbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                
                String place = editText.getText().toString();

                 res = databaseHelper.getData(place);
                StringBuffer stringplace = new StringBuffer();
                StringBuffer stringtemp = new StringBuffer();
                StringBuffer stringmaxtemp = new StringBuffer();
                StringBuffer stringmintemp = new StringBuffer();
                StringBuffer stringfeels = new StringBuffer();
                StringBuffer stringlat = new StringBuffer();
                StringBuffer stringlon = new StringBuffer();
                StringBuffer stringhumidity = new StringBuffer();
                StringBuffer stringsunrise = new StringBuffer();
                StringBuffer stringsunset = new StringBuffer();
                StringBuffer stringpressure = new StringBuffer();
                StringBuffer stringwindspeed = new StringBuffer();
                if(res!=null && res.getCount()>0){
                    while (res.moveToNext()){
                        stringplace.append(res.getString(1));
                        stringtemp.append(res.getString(2));
                        stringmaxtemp.append(res.getString(3));
                        stringmintemp.append(res.getString(4));
                        stringfeels.append(res.getString(5));
                        stringlat.append(res.getString(6));
                        stringlon.append(res.getString(7));
                        stringhumidity.append(res.getString(8));
                        stringsunrise.append(res.getString(9));
                        stringsunset.append(res.getString(10));
                        stringpressure.append(res.getString(11));
                        stringwindspeed.append(res.getString(12));
                    }
                }

                if(stringplace.toString().contains(place)){

                    Toast.makeText(MainActivity.this, "Data Fetched from database..", Toast.LENGTH_SHORT).show();

                   // String savedata = sharedPref.getString(place,null);
//                    Gson gson = new Gson();
//                    model data1 = gson.fromJson(dataa, model.class);

                    temp.setText("Temperature\n"+ stringtemp +" °C");
                    min_temp.setText("Min Temp\n"+stringmintemp+" °C");
                    max_temp.setText("Max Temp\n"+stringmaxtemp+" °C");
                    feels.setText(": "+stringfeels+" °C");
                    latitude.setText(": "+stringlat+"°  N");
                    longitude.setText(": "+stringlon+"°  E");
                    humidity.setText(": "+stringhumidity+" %");
                    sunrise.setText(": "+stringsunrise);
                    sunset.setText(": "+stringsunset);
                    pressure.setText(": "+stringpressure+" hPa");
                    wind.setText(": "+stringwindspeed+"  km/h");

//                    double tempp = data1.getMain().getTemp() - 273.15;
//                    temp.setText("Temperature\n"+ df.format(tempp) +" °C");
//                    double mintempp = data1.getMain().getTemp() - 273.15;
//                    min_temp.setText("Min Temp\n"+df.format(mintempp)+" °C");
//                    double maxtempp = data1.getMain().getTemp() - 273.15;
//                    max_temp.setText("Max Temp\n"+df.format(maxtempp)+" °C");
//                    double feelslikee = data1.getMain().getFeels_like() - 273.15;
//                    feels.setText(": "+df.format(feelslikee)+" °C");
//                    latitude.setText(": "+data1.getCoord().getLat()+"°  N");
//                    longitude.setText(": "+data1.getCoord().getLon()+"°  E");
//                    humidity.setText(": "+data1.getMain().getHumidity()+" %");
//                    sunrise.setText(": "+data1.getSys().getSunrise());
//                    sunset.setText(": "+data1.getSys().getSunset());
//                    pressure.setText(": "+data1.getMain().getPressure()+" hPa");
//                    wind.setText(": "+data1.getWind().getSpeed()+"  km/h");



                }else {
                    apiInterface.get_weather(place, "0573dc8b7cb73f2272e26656e38c6339").enqueue(new Callback<model>() {
                        @Override
                        public void onResponse(Call<model> call, Response<model> response) {

                            if(response.isSuccessful()){
                                Toast.makeText(MainActivity.this, "Data fetched using Api..", Toast.LENGTH_SHORT).show();

                                model data = response.body();

                                double tempp = data.getMain().getTemp() - 273.15;
                                temp.setText("Temperature\n"+ df.format(tempp) +" °C");
                                double mintempp = data.getMain().getTemp() - 273.15;
                                min_temp.setText("Min Temp\n"+df.format(mintempp)+" °C");
                                double maxtempp = data.getMain().getTemp() - 273.15;
                                max_temp.setText("Max Temp\n"+df.format(maxtempp)+" °C");
                                double feelslikee = data.getMain().getFeels_like() - 273.15;
                                feels.setText(": "+df.format(feelslikee)+" °C");
                                latitude.setText(": "+data.getCoord().getLat()+"°  N");
                                longitude.setText(": "+data.getCoord().getLon()+"°  E");
                                humidity.setText(": "+data.getMain().getHumidity()+" %");
                                sunrise.setText(": "+data.getSys().getSunrise());
                                sunset.setText(": "+data.getSys().getSunset());
                                pressure.setText(": "+data.getMain().getPressure()+" hPa");
                                wind.setText(": "+data.getWind().getSpeed()+"  km/h");

//                                model modeljson =new model(data.getWeather(), data.getCoord(), data.getMain(), data.getWind(), data.getSys());
//
//                                Gson gson = new Gson();
//                                String json = gson.toJson(modeljson);

                                databaseHelper.insert(place, tempp, mintempp, maxtempp, feelslikee, data.getCoord().getLat(),
                                         data.getCoord().getLon(), data.getMain().getHumidity(), data.getSys().getSunrise(),
                                         data.getSys().getSunset(), data.getMain().getPressure(), data.getWind().getSpeed());


//                                editor = sharedPref.edit();
//                                editor.putString(place,json);
//                                editor.apply();

                            }
                        }

                        @Override
                        public void onFailure(Call<model> call, Throwable t) {
                            Toast.makeText(MainActivity.this, ""+t.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });

                }
                
               }
        });
    }
}
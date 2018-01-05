package com.example.marin.ms_pb_17;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.content.Intent;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class MapsActivity extends FragmentActivity implements OnMapReadyCallback, Callback<CurrencyExchange>,SensorEventListener {

    private GoogleMap mMap;
    private SensorManager sensorManager;
    private Sensor sensor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        //declaring Sensor Manager and sensor type
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        sensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

    }

    /**
     *
     * Sensor
     *
     */

    @Override
    public void onAccuracyChanged(Sensor arg0, int arg1) {
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        float x = event.values[0];
        float y = event.values[1];
        if (Math.abs(x) > Math.abs(y)) {
            if (x > 0) { //left
                Intent intent = new Intent(MapsActivity.this, IntroApp.class);
                startActivity(intent);
            }
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        sensorManager.registerListener(this, sensor, SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    protected void onPause() {
        super.onPause();
        //unregister Sensor listener
        sensorManager.unregisterListener(this);
    }


    /**
     *
     * Data loading - API Fixer  + Maps Markers
     *
     */

    @Override
    protected void onStart() {
        super.onStart();
        loadCurrencyExchangeData();
    }

    private void loadCurrencyExchangeData() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://api.fixer.io/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        CurrencyExchangeService service = retrofit.create(CurrencyExchangeService.class);
        Call<CurrencyExchange> call = service.loadCurrencyExchange();
        call.enqueue(this);

    }

    @Override
    public void onResponse(Call<CurrencyExchange> call, Response<CurrencyExchange> response) {
        CurrencyExchange currencyExchange = response.body();

        //Poland
        double pln = currencyExchange.getRates().getPLN();
        LatLng poland = new LatLng(52.2, 21.0);
        mMap.addMarker(new MarkerOptions().position(poland).title("PLN : " + pln));

        //USA
        double usd = currencyExchange.getRates().getUSD();
        LatLng america = new LatLng(38.9, -77.0);
        mMap.addMarker(new MarkerOptions().position(america).title("USD : " + usd));
       // mMap.moveCamera(CameraUpdateFactory.newLatLng(usd));

        //South Africa
        double zar = currencyExchange.getRates().getZAR();
        LatLng southAfrica  = new LatLng(-33.9, 18.4);
        mMap.addMarker(new MarkerOptions().position(southAfrica).title("ZAR : " + zar));

        //UK
        double gbp = currencyExchange.getRates().getGBP();
        LatLng uk  = new LatLng(51.0, -0.1);
        mMap.addMarker(new MarkerOptions().position(uk).title("GBP : " + gbp));

        //Turkey
        double tryt = currencyExchange.getRates().getTRY();
        LatLng turkey  = new LatLng(39.9, 32.9);
        mMap.addMarker(new MarkerOptions().position(turkey).title("TRY : " + tryt));

        //Thailand
        double thb = currencyExchange.getRates().getTHB();
        LatLng thailand  = new LatLng(13.7, 100.5);
        mMap.addMarker(new MarkerOptions().position(thailand).title("THB : " + thb));

        //Bulgaria
        double bgn = currencyExchange.getRates().getBGN();
        LatLng bulgaria  = new LatLng(42.7, 23.3);
        mMap.addMarker(new MarkerOptions().position(bulgaria).title("BGN : " + bgn));

        //Brasil
        double brl = currencyExchange.getRates().getBRL();
        LatLng brasil  = new LatLng(-15.8, -47.9);
        mMap.addMarker(new MarkerOptions().position(brasil).title("brl : " + brl));

        //Canada
        double cad = currencyExchange.getRates().getCAD();
        currencyExchange.getBase().toString();
        LatLng canada  = new LatLng(45.4, -75.7);
        mMap.addMarker(new MarkerOptions().position(canada).title("CAD : " + cad));

        //Switzerland
        double chf = currencyExchange.getRates().getCHF();
        LatLng switzerland  = new LatLng(47.4, 8.5);
        mMap.addMarker(new MarkerOptions().position(switzerland).title("CHF : " + chf));
    }

    @Override
    public void onFailure(Call<CurrencyExchange> call, Throwable t) {
        Toast.makeText(this, t.getMessage(), Toast.LENGTH_LONG).show();
    }

    //Map

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */

    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

    }
}

package com.gbc.flightbooker;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.gbc.flightbooker.db.AppDatabase;

public class FlightSearchActivity extends Activity {

    AppDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flight_search);

        db = AppDatabase.getDatabase(getApplicationContext());
    }
}

package com.gbc.flightbooker;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.gbc.flightbooker.db.AppDatabase;

public class FlightSelectionActivity extends Activity {

    AppDatabase db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flight_selection);

        db = AppDatabase.getDatabase(getApplicationContext());
    }
}
